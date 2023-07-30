package com.github.trade.goods.service;

import com.github.trade.goods.db.model.Goods;

public interface GoodsService {

    boolean insertGoods(Goods goods);

    /**
     * search goods
     * @param id
     * @return
     */
    Goods queryGoodsById(long id);
}
