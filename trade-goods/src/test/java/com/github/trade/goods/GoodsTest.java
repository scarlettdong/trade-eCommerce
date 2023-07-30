package com.github.trade.goods;


import com.alibaba.fastjson.JSON;
import com.github.trade.goods.db.dao.GoodsDao;
import com.github.trade.goods.db.model.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsTest {
    @Autowired
    private GoodsDao goodsDao;

    @Test
    public void unitTest(){
        System.out.println("Hello unitTest");
    }

    @Test
    public void goodsInsertTest(){
        Goods goods = new Goods();
        goods.setTitle("iphone 14 pro max test3");
        goods.setBrand("苹果 Apple");
        goods.setCategory("手机");
        goods.setNumber("NO123456");
        goods.setImage("test");
        goods.setDescription("iphone 14 pro max is very good");
        goods.setKeywords("苹果 手机 apple");
        goods.setSaleNum(0);
        goods.setAvailableStock(100);
        goods.setLockStock(10000);
        goods.setPrice(999999);
        goods.setStatus(1);
        goods.setCreateTime(new Date());
        boolean insertResult = goodsDao.insertGoods(goods);
        System.out.println(insertResult);
    }

    @Test
    public void deleteGoodsTest(){
        boolean deleteResult = goodsDao.deleteGoods(1);
        System.out.println(deleteResult);
    }

    @Test
    public void queryGoodsByIdTest(){
        Goods goods = goodsDao.queryGoodsById(2);
        System.out.println(JSON.toJSONString(goods));
    }

    @Test
    public void updateGoodsTest(){
        Goods goods = goodsDao.queryGoodsById(2);
        goods.setTitle(goods.getTitle() + "update");
        boolean updateResult = goodsDao.updateGoods(goods);
        System.out.println(updateResult);
    }


}
