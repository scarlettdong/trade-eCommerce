package com.github.trade.goods.db.dao;


import com.github.trade.goods.db.mappers.GoodsMapper;
import com.github.trade.goods.db.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class GoodsDaoImpl implements GoodsDao{

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public boolean insertGoods(Goods goods) {
        return goodsMapper.insert(goods) > 0;
    }

    @Override
    public boolean deleteGoods(long id) {
        return goodsMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public Goods queryGoodsById(long id) {
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        return goods;
    }

    @Override
    public boolean updateGoods(Goods goods) {
        return goodsMapper.updateByPrimaryKeySelective(goods) > 0;
    }
}
