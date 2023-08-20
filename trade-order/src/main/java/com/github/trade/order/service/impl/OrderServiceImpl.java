package com.github.trade.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.trade.goods.db.dao.GoodsDao;
import com.github.trade.goods.db.model.Goods;
import com.github.trade.order.db.dao.OrderDao;
import com.github.trade.order.db.model.Order;
import com.github.trade.order.exceptions.BizException;
import com.github.trade.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private GoodsDao goodsDao;

    @Override
    public Order createOrder(long userId, long goodsId) {
        Order order = new Order();
        //普通商品购买默认无活动
        order.setActivityId(0L);
        order.setActivityType(0);
        order.setGoodsId(goodsId);
        order.setUserId(userId);
        /**
         * 1.查询goodsId对应的商品，判断是否存在
         * 2.如果不存在抛出异常
         * 3. 存在，获取商品价格
         */
        Goods goods = goodsDao.queryGoodsById(goodsId);
        if (goods == null) {
            log.error("goods is null goodsId={},userId={}", goodsId, userId);
            throw new BizException("goods is not exist");
        }

        order.setPayPrice(goods.getPrice());

        boolean res = orderDao.insertOrder(order);
        if(!res){
            //order didn't insert success
            log.error("order insert error order={}", JSON.toJSONString(order));
            throw new BizException("order insert error");
        }

        return order;
    }
}
