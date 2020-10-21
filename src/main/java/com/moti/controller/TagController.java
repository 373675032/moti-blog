package com.moti.controller;

import com.alibaba.fastjson.JSONObject;
import com.moti.entity.Article;
import com.moti.entity.Tag;
import com.moti.redis.ArticleKey;
import com.moti.redis.KindKey;
import com.moti.redis.TagKey;
import com.moti.utils.CacheUtils;
import com.moti.utils.ImageUtils;
import com.moti.utils.LogUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: TagController
 * @Description: 标签控制器
 * @author: 莫提
 * @date 2020/9/7 21:54
 * @Version: 1.0
 **/
@Controller
public class TagController extends BaseController {

    private Logger logger = LogUtils.getInstance(TagController.class);

    /**
     * 添加标签
     */
    @PostMapping("/addTag")
    @ResponseBody
    public String addTag(Tag tag){
        // 检查是否已经创建过这个标签
        List<Tag> tags = tagService.queryAll(new Tag(tag.getName()));
        if (tags.size() != 0){
            result.setCode(501);
            logger.warn("【失败】添加标签，该名称已被占用");
            return JSONObject.toJSONString(result);
        }
        // 为标签设置随机封面
        tag.setImg(ImageUtils.getRandomFace());
        if (tagService.insert(tag)) {
            result.setCode(200);
            CacheUtils.cleanTagCache(redisService,null);
            logger.warn("【成功】添加标签");
        }else {
            result.setCode(500);
            logger.warn("【失败】添加标签");
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 更新标签
     */
    @PostMapping("/updateTag")
    @ResponseBody
    public String updateTag(Tag tag){
        // 检查用户是不是没有更改任何的信息
        Tag old = tagService.queryById(tag.getId());
        if (tag.getName().equals(old.getName()) && tag.getIntroduce().equals(old.getIntroduce())){
            result.setCode(501);
            logger.warn("【失败】更新标签，没有修改任何信息");
            return JSONObject.toJSONString(result);
        }
        // 如果更新之后的标签名称已被占用
        if ( !tag.getName().equals(old.getName()) && tagService.queryAll(new Tag(tag.getName())).size()  != 0){
            result.setCode(502);
            logger.warn("【失败】更新标签，该名称已被占用");
            return JSONObject.toJSONString(result);
        }
        if (tagService.update(tag)) {
            result.setCode(200);
            CacheUtils.cleanArticleCacheByTagId(articleService,redisService,tag.getId());
            CacheUtils.cleanTagCache(redisService,tag.getId());
            logger.warn("【成功】更新标签");
        }else {
            result.setCode(500);
            logger.warn("【失败】更新标签");
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 删除标签
     */
    @GetMapping("/deleteTag")
    public String deleteTag(Integer id){
        CacheUtils.cleanArticleCacheByTagId(articleService,redisService,id);
        if (id != null){
            if (tagService.deleteById(id)) {
                CacheUtils.cleanTagCache(redisService,id);
                logger.warn("【成功】删除标签");
            }else {
                logger.warn("【失败】删除标签");
            }
        }
        return "redirect:/tags";
    }

}
