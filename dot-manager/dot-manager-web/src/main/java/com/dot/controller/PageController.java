package com.dot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 页面跳转
 */
@Controller
public class PageController {

    //首页
    @RequestMapping("/")
    public  String showIndex(){
        return "redirect:/pages/login.html";
    }

    //展示其他页面
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
    	System.out.println(page);
        return page;
    }
    
    //展示其他页面
    @RequestMapping("/staticPage/{page}")
    public String showStaticPage(@PathVariable String page){
        return "redirect:/pages"+page;
    }    
}
