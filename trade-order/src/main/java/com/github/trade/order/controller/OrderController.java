package com.github.trade.order.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class OrderController {

    @RequestMapping("/goods/hello")
    @ResponseBody
    public String sayHello(){
        log.info("call sayHello()");
        return "hello order";
    }
}
