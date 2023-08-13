package com.github.trade.web.portal.controller;

import com.alibaba.fastjson.JSON;
import com.github.trade.goods.db.model.Goods;
import com.github.trade.goods.service.GoodsService;
import com.github.trade.goods.service.SearchService;
import com.github.trade.web.portal.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class PortalController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private SearchService searchService;

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

    /**
     * 跳转搜索页面
     * @return
     */
    @RequestMapping("/search")
    public String searchPage(){
        return "search";
    }

    /**
     * 搜索页面
     * @param searchWords
     * @param resultMap
     * @return
     */

    @RequestMapping("/searchAction")
    public String search(@RequestParam("searchWords") String searchWords, Map<String, Object> resultMap){
        log.info("search searchWords : {}", searchWords);
        List<Goods> goodsList = searchService.searchGoodsList(searchWords, 0, 10);
        resultMap.put("goodsList", goodsList);
        return "search";
    }
}
