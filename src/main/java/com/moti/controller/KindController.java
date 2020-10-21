package com.moti.controller;

import com.alibaba.fastjson.JSONObject;
import com.moti.entity.Article;
import com.moti.entity.Kind;
import com.moti.redis.ArticleKey;
import com.moti.redis.KindKey;
import com.moti.redis.LinkKey;
import com.moti.utils.CacheUtils;
import com.moti.utils.ImageUtils;
import com.moti.utils.LogUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: KindController
 * @Description: 文集控制器
 * @author: 莫提
 * @date 2020/9/7 21:54
 * @Version: 1.0
 **/
@Controller
public class KindController extends BaseController {

    private Logger logger = LogUtils.getInstance(KindController.class);

    /**
     * 添加文集
     */
    @PostMapping("/addKind")
    @ResponseBody
    public String addKind(Kind kind){
        // 检查是否已经创建过这个文集
        List<Kind> kinds = kindService.queryAll(new Kind(kind.getName()));
        if (kinds.size() != 0){
            result.setCode(501);
            logger.warn("【失败】添加文集，该名称已被占用");
            return JSONObject.toJSONString(result);
        }
        // 为文集设置随机封面
        kind.setImg(ImageUtils.getRandomFace());
        if (kindService.insert(kind)) {
            result.setCode(200);
            CacheUtils.cleanKindCache(redisService,kind.getId());
            logger.warn("【成功】添加文集");
        }else {
            result.setCode(500);
            logger.warn("【失败】添加文集");
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 更新文集
     */
    @PostMapping("/updateKind")
    @ResponseBody
    public String updateKind(Kind kind){
        // 检查用户是不是没有更改任何的信息
        Kind old = kindService.queryById(kind.getId());
        if (kind.getName().equals(old.getName()) && kind.getIntroduce().equals(old.getIntroduce())){
            result.setCode(501);
            logger.warn("【失败】更新文集，没有修改任何信息");
            return JSONObject.toJSONString(result);
        }
        // 如果更新之后的文集名称已被占用
        if ( !kind.getName().equals(old.getName()) && kindService.queryAll(new Kind(kind.getName())).size()  != 0){
            result.setCode(502);
            logger.warn("【失败】更新文集，该名称已被占用");
            return JSONObject.toJSONString(result);
        }
        if (kindService.update(kind)) {
            result.setCode(200);
            cleanCacheByKindId(kind.getId());
            CacheUtils.cleanKindCache(redisService,kind.getId());
            logger.warn("【成功】更新文集");
        }else {
            result.setCode(500);
            logger.warn("【失败】更新文集");
        }
        return JSONObject.toJSONString(result);
    }
    
    /**
     * 删除文集
     */
    @GetMapping("/deleteKind")
    public String deleteKind(Integer id){
        if (id != null){
            if (kindService.deleteById(id)) {
                cleanCacheByKindId(id);
                CacheUtils.cleanKindCache(redisService,id);
                logger.warn("【成功】删除文集");
            }else {
                logger.warn("【失败】删除文集");
            }
        }
        return "redirect:/kinds";
    }

    /**
     * 清理标签ID清理文章缓存
     */
    private void cleanCacheByKindId(Integer kId){
        List<Article> articles = articleService.findByKindId(kId);
        articles.forEach(article -> {
            redisService.del(ArticleKey.getById,String.valueOf(article.getId()));
        });
        logger.warn("清理文集涉及的文章缓存，KindId："+kId);
    }

}
