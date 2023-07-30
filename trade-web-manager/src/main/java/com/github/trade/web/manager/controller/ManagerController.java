package com.github.trade.web.manager.controller;

import com.github.trade.goods.db.model.Goods;
import com.github.trade.goods.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;

@Slf4j
@Controller
public class ManagerController {

    @Autowired
    private GoodsService goodsService;
    /**
     * 跳转到主页面
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * 跳转商品添加页面
     * @return
     */
    @RequestMapping("/add_goods")
    public String addGoods(){
        return "add_goods";
    }

    @RequestMapping("addGoodsAction")
    public String addGoodsAction(@RequestParam("title") String title,
                                 @RequestParam("number") String number,
                                 @RequestParam("brand") String brand,
                                 @RequestParam("image") String image,
                                 @RequestParam("description") String description,
                                 @RequestParam("price") int price,
                                 @RequestParam("keywords") String keywords,
                                 @RequestParam("category") String category,
                                 @RequestParam("stock") int stock, Map<String, Object> resultMap){
        Goods goods = new Goods();
        goods.setTitle(title);
        goods.setNumber(number);
        goods.setBrand(brand);
        goods.setImage(image);
        goods.setDescription(description);
        goods.setPrice(price);
        goods.setKeywords(keywords);
        goods.setCategory(category);
        goods.setAvailableStock(stock);
        goods.setLockStock(0);
        //初始为上架状态
        goods.setStatus(1);
        //初始的销售数量为0
        goods.setSaleNum(0);
        goods.setCreateTime(new Date());
        boolean result = goodsService.insertGoods(goods);
        log.info("add goods result={}", result);
        resultMap.put("goodsInfo", goods);
        return "add_goods";
    }
}
