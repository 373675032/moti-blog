package com.moti.controller;

import com.alibaba.fastjson.JSONObject;
import com.moti.entity.Front;
import com.moti.entity.Link;
import com.moti.entity.Menu;
import com.moti.redis.*;
import com.moti.utils.CacheUtils;
import com.moti.utils.LogUtils;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: FrontController
 * @Description: 前台控制器，主要控制展示的信息【头图、公告等】
 * @author: 莫提
 * @date 2020/9/21 10:42
 * @Version: 1.0
 **/
@RestController
public class FrontController extends BaseController {

    private Logger logger = LogUtils.getInstance(FrontController.class);

    /**
     * 更新公告
     */
    @PostMapping("/updateFront")
    public String updateFront(Front temp){
        temp.setId(1);
        if (frontService.update(temp)) {
            result.setCode(200);
            CacheUtils.cleanAdminCache(redisService);
            logger.warn("【成功】更新公告");
        }else {
            result.setCode(500);
            logger.warn("【失败】更新公告");
        }
        redisService.del(AdminKey.getByFront,String.valueOf(temp.getId()));
        return JSONObject.toJSONString(result);
    }

    /**
     * 添加菜单
     */
    @PostMapping("/addMenu")
    public String addMenu(Menu menu){
        if (menuService.insert(menu)) {
            result.setCode(200);
            CacheUtils.cleanAdminCache(redisService);
            logger.warn("【成功】添加菜单");
        }else {
            result.setCode(500);
            logger.warn("【失败】添加菜单");
        }
        redisService.del(MenuKey.getLeftMenu,"");
        redisService.del(MenuKey.getRightMenu,"");
        return JSONObject.toJSONString(result);
    }
    
    /**
     * 添加友情链接
     */
    @PostMapping("/addLink")
    public String addLink(Link link){
        if (linkService.insert(link)) {
            result.setCode(200);
            CacheUtils.cleanAdminCache(redisService);
            logger.warn("【成功】添加友情链接");
        }else {
            result.setCode(500);
            logger.warn("【失败】添加友情链接");
        }
        redisService.del(LinkKey.getIndex,"");
        return JSONObject.toJSONString(result);
    }

    /**
     * 删除菜单
     */
    @GetMapping("/deleteMenu")
    public String deleteMenu(@RequestParam("id")Integer id){
        if (menuService.deleteById(id)) {
            result.setCode(200);
            CacheUtils.cleanAdminCache(redisService);
            logger.warn("【成功】删除菜单，ID："+id);
        }else {
            result.setCode(500);
            logger.warn("【失败】删除菜单，ID："+id);
        }
        redisService.del(MenuKey.getLeftMenu,"");
        redisService.del(MenuKey.getRightMenu,"");
        return JSONObject.toJSONString(result);
    }

    /**
     * 删除友情链接
     */
    @GetMapping("/deleteLink")
    public String deleteLink(@RequestParam("id")Integer id){
        if (linkService.deleteById(id)) {
            result.setCode(200);
            CacheUtils.cleanAdminCache(redisService);
            logger.warn("【成功】删除友情链接，ID："+id);
        }else {
            result.setCode(500);
            logger.warn("【失败】删除友情链接，ID："+id);
        }
        redisService.del(LinkKey.getIndex,"");
        return JSONObject.toJSONString(result);
    }
}
