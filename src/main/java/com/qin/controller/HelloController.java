package com.qin.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class HelloController {
    @RequestMapping({"/", "/index"})
    public String index() {
        return "/index";
    }

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, Map<String, Object> map) {
        System.out.println("home controller login");
        String exception = (String) request.getAttribute("shiroLoginFailure");
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("unknown account");
                msg = "账号不存在";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("密码不正确");
                msg = "密码不正确";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("验证码错误");
                msg = "验证码错误";
            } else {
                System.out.println("else " + exception);
                msg = "else " + exception;
            }
        }
        map.put("msg", msg);
        return "/login";
    }

    @RequestMapping("/403")
    public String authorizedRole() {
        System.out.println("权限不够");
        return "403";
    }
}
