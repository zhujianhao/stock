package com.zjh.stock.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjh.stock.entity.StockDailyData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  maapper
 *
 * @author zhujianhao
 *
 * @date 2021-02-03 15:20:49
 */
@Mapper
public interface StockDailyDataDao extends BaseMapper<StockDailyData> {


    List<StockDailyData> getKline(@Param("code") String stockCode, @Param("limit") Integer limit);

}
