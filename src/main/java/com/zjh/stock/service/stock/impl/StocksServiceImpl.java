package com.zjh.stock.service.stock.impl;

import com.zjh.stock.dao.StocksDao;
import com.zjh.stock.entity.Stocks;
import com.zjh.stock.service.stock.StocksService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;



@Service("stocksService")
public class StocksServiceImpl extends ServiceImpl<StocksDao, Stocks> implements StocksService {



}
