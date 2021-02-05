package com.zjh.stock.service.stock.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjh.stock.dao.StockDailyDataDao;
import com.zjh.stock.entity.StockDailyData;
import com.zjh.stock.service.stock.StockDailyDataService;
import com.zjh.stock.target.boll.BollTarget;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("stockDailyDataService")
public class StockDailyDataServiceImpl extends ServiceImpl<StockDailyDataDao, StockDailyData> implements StockDailyDataService {



    @Override
    public void judgeBoll(String stockCode, Integer day) {
        List<StockDailyData> kline =  getBaseMapper().getKline(stockCode,day);
        Collections.reverse(kline);
        BollTarget bollTarget = new BollTarget(kline);

        System.out.println("hhhh");
    }
}
