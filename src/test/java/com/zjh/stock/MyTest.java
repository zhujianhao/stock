package com.zjh.stock;

import com.zjh.stock.entity.StockDailyData;
import com.zjh.stock.service.stock.StockDailyDataService;
import com.zjh.stock.service.tpStock.TpStockService;
import com.zjh.stock.util.HttpClientUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sun.applet.resources.MsgAppletViewer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhujianhao
 * @date 2021/2/3 11:08
 * @modify
 */
public class MyTest extends StockPredictApplicationTests {

    @Autowired
    private TpStockService stockService;
    @Autowired
    private StockDailyDataService stockDailyDataService;

    @Test
    public void testHttp() throws InterruptedException {
        stockService.initStockListData();
    }
    @Test
    public void testStockYearData() throws InterruptedException {
        stockService.getYesterdayData();
    }

    @Test
    public void testBoll() {
        stockDailyDataService.judgeBoll("600000",30);
    }
}
