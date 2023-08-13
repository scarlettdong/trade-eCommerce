package com.github.trade.goods.service.impl;

import com.github.trade.goods.db.dao.GoodsDao;
import com.github.trade.goods.db.model.Goods;
import com.github.trade.goods.service.GoodsService;
import com.github.trade.goods.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private SearchService searchService;

    @Override
    public boolean insertGoods(Goods goods) {
        boolean result = goodsDao.insertGoods(goods);
        searchService.addGoodsToES(goods);
        return result;
    }

    @Override
    public Goods queryGoodsById(long id) {

        return goodsDao.queryGoodsById(id);
    }
}
