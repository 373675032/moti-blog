package com.moti.controller;

import com.alibaba.fastjson.JSONObject;
import com.moti.dto.ResponseResult;
import com.moti.entity.Article;
import com.moti.entity.Kind;
import com.moti.entity.Tag;
import com.moti.utils.CacheUtils;
import com.moti.utils.ImageUtils;
import com.moti.utils.LogUtils;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName: UploadController
 * @Description: 图片控制器
 * @author: 莫提
 * @date 2020/9/1 14:28
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/image")
public class ImageController extends BaseController{

    private Logger logger = LogUtils.getInstance(ImageController.class);

    /**
     * 图片上传
     */
    @PostMapping("/upload")
    public String upload(@RequestParam("image") MultipartFile file,@RequestParam("flag")Integer flag,Integer id) throws IOException {
        if (!ImageUtils.checkFileSize(file.getSize(),1,"M")) {
            result.setCode(502);
            return JSONObject.toJSONString(result);
        }
        String imgPath = ImageUtils.upload(file,"o");
        if (flag != null){
            if (flag <= 3){
                updateAdmin(flag,imgPath);
            }else if (flag > 3 && flag < 7){
                updateImg(flag,imgPath,id);
            }else{
                updateFront(flag,imgPath);
            }
        }
        // 返回结果
        result.setCode(200);
        result.setData(imgPath);
        return JSONObject.toJSONString(result);
    }

    /**
     * 更新管理员的图片信息
     */
    public void updateAdmin(Integer flag,String imgPath){
        // 记录原来图片的位置
        String img = "";
        // 更新管理员信息
        if (flag == 1){
            img = loginAdmin.getImg();
            loginAdmin.setImg(imgPath);
            logger.warn("更新管理员头像，flag:"+flag+"，imgPath:"+imgPath);
        }else if (flag == 2){
            img = loginAdmin.getWechat();
            loginAdmin.setWechat(imgPath);
            logger.warn("更新管理员微信图片，flag:"+flag+"，imgPath:"+imgPath);
        }else if (flag == 3){
            img = loginAdmin.getPublicWechat();
            loginAdmin.setPublicWechat(imgPath);
            logger.warn("更新管理员公众号图片，flag:"+flag+"，imgPath:"+imgPath);
        }
        if (adminService.update(loginAdmin)) {
            CacheUtils.cleanAdminCache(redisService);
            // 更新session
            session.setAttribute("admin",loginAdmin);
            logger.warn("【成功】更新管理员图片信息");
        }else {
            logger.warn("【失败】更新管理员图片信息");
        }

    }

    /**
     * 更新图片信息（文集、标签、文章）
     */
    public void updateImg(Integer flag,String imgPath,Integer id){
        // 记录原来图片的位置
        String img = "";

        if (flag == 4){         // 更新文集封面
            Kind kind = kindService.queryById(id);
            img = kind.getImg();
            kind.setImg(imgPath);
            if (kindService.update(kind)) {
                logger.warn("【成功】更新文集封面，flag:"+flag+"，imgPath:"+imgPath+"，id:"+id);
            }else {
                logger.warn("【失败】更新文集封面，flag:"+flag+"，imgPath:"+imgPath+"，id:"+id);
            }
            CacheUtils.cleanKindCache(redisService,id);
        }else if (flag == 5){   // 更新标签封面
            Tag tag = tagService.queryById(id);
            img = tag.getImg();
            tag.setImg(imgPath);
            if (tagService.update(tag)) {
                logger.warn("【成功】更新标签封面，flag:"+flag+"，imgPath:"+imgPath+"，id:"+id);
            }else {
                logger.warn("【失败】更新标签封面，flag:"+flag+"，imgPath:"+imgPath+"，id:"+id);
            }
            CacheUtils.cleanTagCache(redisService,id);
        }else if (flag == 6){   // 更新文章封面
            Article article = articleService.queryById(id);
            article.setImg(imgPath);
            if (articleService.update(article)) {
                logger.warn("【成功】更新文章封面，flag:"+flag+"，imgPath:"+imgPath+"，id:"+id);
            }else {
                logger.warn("【失败】更新文章封面，flag:"+flag+"，imgPath:"+imgPath+"，id:"+id);
            }
            CacheUtils.cleanArticleCache(redisService,id);
        }
    }

    /**
     * 更新前台主页的图片信息
     */
    public void updateFront(Integer flag,String imgPath){
        // 获取前台主页对象
        if (flag == 7){
            front.setBackground(imgPath);
            logger.warn("更新前台主页的背景图片");
        }else if (flag == 8){
            front.setNoticeImg(imgPath);
            logger.warn("更新公告的背景图片");
        }
        if (frontService.update(front)) {
            CacheUtils.cleanAdminCache(redisService);
            logger.warn("【成功】更新前台主页的图片信息");
        }else {
            logger.warn("【失败】更新前台主页的图片信息");
        }
    }

    /**
     * 文章上传图片到Gitee图床
     */
    @PostMapping("/article")
    public ResponseResult testUploadMarkDown(@RequestParam("editormd-image-file")MultipartFile file) throws IOException {
        String url = ImageUtils.upload(file,"a");
        if (url != null){
            result.setUrl(url);
            result.setSuccess(1);
            logger.warn("【成功】文章上传图片到Gitee图床，URL："+url);
        }else {
            logger.warn("【失败】文章上传图片到Gitee图床");
        }
        return result;
    }
}
