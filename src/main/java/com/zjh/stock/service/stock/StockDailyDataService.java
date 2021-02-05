package com.zjh.stock.service.stock;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjh.stock.entity.StockDailyData;

import java.util.Map;

/**
 *
 *
 * @author zhujianhao
 * @email 1205628174@qq.com
 * @date 2021-02-03 15:20:49
 */
public interface StockDailyDataService extends IService<StockDailyData> {

    void judgeBoll(String stockCode,Integer day);

}

