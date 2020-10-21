package com.moti.controller;

import com.alibaba.fastjson.JSONObject;
import com.moti.entity.Admin;
import com.moti.entity.Article;
import com.moti.redis.AdminKey;
import com.moti.redis.ArticleKey;
import com.moti.utils.CacheUtils;
import com.moti.utils.LogUtils;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @ClassName: AdminController
 * @Description: 管理员控制器
 * @author: 莫提
 * @date 2020/9/1 10:50
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {

    private Logger logger = LogUtils.getInstance(AdminController.class);

    /**
     * 修改管理员信息
     */
    @PostMapping("/update")
    public String updateAdmin(Admin update,String birthdayStr,String flag){
        // 为修改的对象赋值ID
        update.setId(loginAdmin.getId());
        // 修改基本信息
        if ("basic".equals(flag)){
            logger.warn("修改管理员基本信息");
            boolean nameEquals = loginAdmin.getName().equals(update.getName());
            boolean addressEquals = loginAdmin.getAddress().equals(update.getAddress());
            boolean sexEquals = loginAdmin.getSex().equals(update.getSex());
            boolean careerEquals = loginAdmin.getCareer().equals(update.getCareer());
            boolean birEquals = new SimpleDateFormat("yyyy年MM月dd日").format(loginAdmin.getBirthday()).equals(birthdayStr);
            // 只更新修改过的属性
            update.setName(nameEquals?null:update.getName());
            update.setAddress(addressEquals?null:update.getAddress());
            update.setSex(sexEquals?null:update.getSex());
            update.setCareer(careerEquals?null:update.getCareer());

            try {
                update.setBirthday(birEquals?null:new SimpleDateFormat("yyyy年MM月dd日").parse(birthdayStr));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (nameEquals && addressEquals && sexEquals && careerEquals && birEquals){
                result.setCode(501);
                logger.warn("【失败】没有修改任何信息");
                return JSONObject.toJSONString(result);
            }
        }
        // 修改联系方式
        if ("contact".equals(flag)){
            logger.warn("修改管理员联系方式");
            boolean emailEquals = loginAdmin.getEmail().equals(update.getEmail());
            boolean phoneEquals = loginAdmin.getPhone().equals(update.getPhone());
            boolean qqEquals = loginAdmin.getQq().equals(update.getQq());
            update.setEmail(emailEquals?null:update.getEmail());
            update.setPhone(phoneEquals?null:update.getPhone());
            update.setQq(qqEquals?null:update.getQq());
            if (emailEquals && phoneEquals && qqEquals){
                result.setCode(501);
                logger.warn("【失败】没有修改任何信息");
                return JSONObject.toJSONString(result);
            }
        }
        // 修改其他信息
        if ("other".equals(flag)){
            logger.warn("修改管理员其他信息");
            if (loginAdmin.getInfo().equals(update.getInfo())){
                result.setCode(501);
                logger.warn("【失败】没有修改任何信息");
                return JSONObject.toJSONString(result);
            }
        }
        // 修改资料
        if (adminService.update(update)) {
            result.setCode(200);
            logger.warn("【成功】修改管理员资料成功");
            CacheUtils.cleanAdminCache(redisService);
            // 更新session
            session.setAttribute("admin",adminService.queryById(1));
            logger.warn("删除Redis中管理员的信息");
        }else {
            result.setCode(500);
            logger.warn("【失败】服务器异常");
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 清理并重建ElasticSearch缓存
     */
    @GetMapping("/resetEs")
    public String resetEs(){
        logger.warn("【ElasticSearch】开始保存文章数据到ES");
        long startTime = System.currentTimeMillis();
        // 先清空再添加
        articleRespository.deleteAll();
        List<Article> articles = articleService.queryByStatus(1);
        for (Article article : articles) {
            article.setStatus(null);
            article.setKinds(null);
            article.setTags(null);
            article.setImg(null);
            article.setRecentEdit(null);
            articleRespository.save(article);
        }
        long endTime = System.currentTimeMillis();
        logger.warn("【ElasticSearch】结束保存文章数据到ES，"+"程序运行时间：" + (endTime - startTime) + "ms");
        result.setCode(200);
        result.setData(endTime - startTime);
        redisService.set(ArticleKey.getEsCount,"",articles.size(),60*60*24);
        return JSONObject.toJSONString(result);
    }

    /**
     * 清空Redis数据库
     */
    @GetMapping("/cleanRedis")
    public String cleanRedis(){
        logger.warn("【Redis】开始清空Redis");
        long startTime = System.currentTimeMillis();
        CacheUtils.flush(redisService);
        long endTime = System.currentTimeMillis();
        logger.warn("【Redis】清空完成，"+"程序运行时间：" + (endTime - startTime) + "ms");
        result.setCode(200);
        result.setData(endTime - startTime);
        return JSONObject.toJSONString(result);
    }

}
