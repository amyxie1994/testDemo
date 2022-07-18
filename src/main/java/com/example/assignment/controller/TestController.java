package com.example.assignment.controller;

import com.example.assignment.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/testCrabController")
public class TestController {

    @Resource
    private TestService testService;

    @ResponseBody
    @RequestMapping("/queryOverseaStockistList")
    public  String queryOverseaStockistList() {

        testService.testService();

        return " I am just testing ";

    }

}
