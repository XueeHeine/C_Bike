package com.qdu.controller;

import com.qdu.utils.ResultMsg;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


/**
 * Created by Alpha-LGC on 2019/1/28.
 */
@Controller
public class LoginController {


    @RequestMapping(value = "/login/login" )
    @ResponseBody
    public ResultMsg login(String req){

System.out.println("123");
                return new ResultMsg(200, "success");

    }


    @RequestMapping("/test")
    public String test() {
        System.out.println("------test-------");
        return "test";
    }

    @RequestMapping("/login/logout")
    public String logOut(HttpSession session) {
        /*Subject subject = SecurityUtils.getSubject();
        subject.logout();*/
        session.invalidate();
        return "redirect:/";
    }
    @RequestMapping("/login/unlog")
    public String unlog(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        //session.invalidate();
        return "redirect:/pages/403.jsp";
    }

    @RequestMapping("/403")
    public String au403() {

        return "/pages/403.jsp";
    }
    @RequestMapping("/4033")
    public String au4033() {

        return "redirect:/pages/403.html";
    }
   /* @RequestMapping("/haveSession")
    @ResponseBody
    public ResultMsg hasSession() {
        Session ss = SecurityUtils.getSubject().getSession();

        return ss == null ? (new ResultMsg(200,"isnull")) :(new ResultMsg(200,"notnull"));
    }*/
   @RequestMapping("/haveSession")
   @ResponseBody
   public String hasSession() {
       Session ss = SecurityUtils.getSubject().getSession();

       return ss == null ? "isnull" :"notnull";
   }
}
