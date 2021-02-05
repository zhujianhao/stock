package com.zjh.stock.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjh.stock.entity.Stocks;
import org.apache.ibatis.annotations.Mapper;

/**
 *  maapper
 *
 * @author zhujianhao
 *
 * @date 2021-02-03 15:20:49
 */
@Mapper
public interface StocksDao extends BaseMapper<Stocks> {

}
