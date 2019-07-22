package com.qdu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Alpha-LGC on 2019/1/23.
 */

@Controller
public class IndexController {
    @RequestMapping(value = {"/","#"} ,method = RequestMethod.GET )
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/emp/empAddAndUpdate",method = RequestMethod.GET)
    public String empAddAndUpdate(){
        return "emp/addAndUpdate";
    }
    @RequestMapping(value = "/login/index")
    public String index(){
        return "index";
    }



}
