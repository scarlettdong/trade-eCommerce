package com.github.trade.web.portal.controller;

import com.alibaba.fastjson.JSON;
import com.github.trade.goods.db.model.Goods;
import com.github.trade.goods.service.GoodsService;
import com.github.trade.web.portal.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class PortalController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/goods_detail")
    public String index(){
        return "goods_detail";
    }

    @RequestMapping("/goods/{goodsId}")
    public ModelAndView itemPage(@PathVariable long goodsId){
        Goods goods = goodsService.queryGoodsById(goodsId);
        log.info("goodsId = {}, goods = {}", goodsId, JSON.toJSON(goods));
        String showPrice = CommonUtils.changeCentToCNY(goods.getPrice());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("goods", goods);
        modelAndView.addObject("showPrice", showPrice);
        modelAndView.setViewName("goods_detail");
        return modelAndView;
    }

    /**
     * buy goods request
     * @param userId
     * @param goodsId
     * @return
     */
    @RequestMapping("/buy/{userId}/{goodsId}")
    public ModelAndView buy(@PathVariable long userId, @PathVariable long goodsId){
        log.info("buy userId = {}, goodsId = {}", userId, goodsId);
        return null;
    }
}
