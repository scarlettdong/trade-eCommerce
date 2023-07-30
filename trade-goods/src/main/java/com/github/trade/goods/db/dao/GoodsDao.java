package com.github.trade.goods.db.dao;

import com.github.trade.goods.db.model.Goods;

public interface GoodsDao {

    /**
     * crud - create
     * @param goods
     * @return
     */
    boolean insertGoods(Goods goods);

    /**
     * crud - delete
     * @param id
     * @return
     */
    boolean deleteGoods(long id);

    /**
     * crud - read
     * @param id
     * @return
     */
    Goods queryGoodsById(long id);

    /**
     * crud - update
     * @param goods
     * @return
     */
    boolean updateGoods(Goods goods);

}
