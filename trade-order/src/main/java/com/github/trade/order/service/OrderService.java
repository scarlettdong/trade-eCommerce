package com.github.trade.order.service;

import com.github.trade.order.db.model.Order;

public interface OrderService {
    /**
     * create order
     * @param userId 用户id
     * @param goodsId 商品id
     * @return
     */
    Order createOrder(long userId, long goodsId);
}
