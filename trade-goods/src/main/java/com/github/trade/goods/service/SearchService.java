package com.github.trade.goods.service;

import com.github.trade.goods.db.model.Goods;

import java.util.List;

public interface SearchService {

    void addGoodsToES(Goods goods);

    List<Goods> searchGoodsList(String keyword, int from, int size);
}
