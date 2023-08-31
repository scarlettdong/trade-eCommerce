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

    /**
     * 订单查询
     * @param orderId
     * @return
     */
    Order queryOrder(long orderId);

    /**
     * 支付订单
     * @param orderId
     */
    void payOrder(long orderId);
}
