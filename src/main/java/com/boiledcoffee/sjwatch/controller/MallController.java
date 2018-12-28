package com.boiledcoffee.sjwatch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ${juha} on 2018/12/4.
 */
@Controller
public class MallController {

    @RequestMapping(value = "/mall/index")
    public String index(Model model){
        model.addAttribute("name","json");
        return "/home/index";
    }
}
