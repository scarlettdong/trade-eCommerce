package com.github.trade.goods.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class GoodsController {

    @RequestMapping("/goods/test")
    @ResponseBody
    public String test() {
        log.info("call sayHello");
        return "hello word java!";
    }

}
