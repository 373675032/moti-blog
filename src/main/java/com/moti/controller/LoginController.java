package com.moti.controller;

import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSONObject;
import com.moti.entity.Admin;
import com.moti.utils.LogUtils;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;

/**
 * @ClassName: LoginController
 * @Description: 登录控制器
 * @author: 莫提
 * @date 2020/8/30 21:46
 * @Version: 1.0
 **/
@Controller
public class LoginController extends BaseController {

    private Logger logger = LogUtils.getInstance(LoginController.class);

    /**
     * 免密登录，用于开发测试【上线请删除此方法】
     */
    @GetMapping("/admin")
    public String admin(){
        // 获取管理员对象
        Admin admin = adminService.queryById(1);
        admin.setRecentLogin(new Date());
        adminService.update(admin);
        session.setAttribute("admin",admin);
        logger.warn("【成功】免密登录");
        return "redirect:/dashboard";
    }

    /**
     * 管理员登录
     */
    @PostMapping("/login")
    @ResponseBody
    public String login(String name,String password){
        // 获取管理员对象
        Admin admin = adminService.queryById(1);
        // 获取Md5加密对象
        MD5 md5 = new MD5(admin.getSalt().getBytes());
        // 进行判断
        if (admin.getName().equals(name) && admin.getPassword().equals(md5.digestHex16(password))){
            result.setCode(200);
            logger.warn("【成功】密码登录");
            admin.setRecentLogin(new Date());
            adminService.update(admin);
            session.setAttribute("admin",admin);
        }else {
            result.setCode(100);
            logger.warn("【错误】密码登录，用户名或密码错误");
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public String logout(){
        session.invalidate();
        logger.warn("【成功】退出登录");
        return "redirect:/admin-login";
    }

    /**
     * 请求QQ登录
     */
    @RequestMapping("/loginByQQ")
    public void loginByQQ() {
        response.setContentType("text/html;charset=utf-8");
        try {
            response.sendRedirect(new Oauth().getAuthorizeURL(request));
            logger.warn("【QQ登录】请求QQ登录,开始跳转...");
        } catch (QQConnectException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * QQ登录回调地址
     *
     * @return
     */
    @RequestMapping("/connection")
    public String connection() {
        try {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
            String accessToken = null, openID = null;
            long tokenExpireIn = 0L;

            if ("".equals(accessTokenObj.getAccessToken())) {
                logger.error("【QQ登录】登录失败:没有获取到响应参数");
                return "accessTokenObj=>" + accessTokenObj + "; accessToken" + accessTokenObj.getAccessToken();
            } else {
                accessToken = accessTokenObj.getAccessToken();
                tokenExpireIn = accessTokenObj.getExpireIn();
                request.getSession().setAttribute("demo_access_token", accessToken);
                request.getSession().setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));

                OpenID openIDObj = new OpenID(accessToken);
                openID = openIDObj.getUserOpenID();

                UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
                UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
                if (userInfoBean.getRet() == 0) {
                    //设置用户信息
                    Admin admin = adminService.queryById(1);
                    if (admin.getOpenId() != null){
                        // 未绑定QQ用户
                        admin.setOpenId(openID);
                        admin.setName(removeNonBmpUnicode(userInfoBean.getNickname()));
                        admin.setImg(userInfoBean.getAvatar().getAvatarURL100());
                        adminService.update(admin);
                    }else {
                        return "error/401";
                    }
                    return "redirect:/dashboard";
                } else {
                    logger.error("【QQ登录】很抱歉，我们没能正确获取到您的信息，原因是： " + userInfoBean.getMsg());
                }
            }
        } catch (QQConnectException e) {
        } finally {
            logger.warn("【QQ登录】登录成功!");
        }
        return "登录失败!请查看日志信息...";
    }

    /**
     * 处理掉QQ网名中的特殊表情
     *
     * @param str
     * @return
     */
    public String removeNonBmpUnicode(String str) {
        if (str == null) {
            return null;
        }
        str = str.replaceAll("[^\\u0000-\\uFFFF]", "");
        if ("".equals(str)) {
            str = "($ _ $)";
        }
        return str;
    }

}
