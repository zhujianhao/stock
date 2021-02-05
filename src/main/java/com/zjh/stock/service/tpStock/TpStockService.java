package com.zjh.stock.service.tpStock;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mysql.cj.xdevapi.JsonArray;
import com.zjh.stock.entity.StockDailyData;
import com.zjh.stock.entity.Stocks;
import com.zjh.stock.service.stock.StockDailyDataService;
import com.zjh.stock.service.stock.StocksService;
import com.zjh.stock.util.HttpClientUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhujianhao
 * @date 2021/2/3 15:11
 * @modify
 */
@Service
public class TpStockService {

    @Autowired
    private StocksService stocksService;
    @Autowired
    private StockDailyDataService stockDailyDataService;


    //从上交所查询股票列表入库
    public void initStockListData() throws InterruptedException {
        Integer pageNo = 1;
        Integer totalpage = 1;

        while (pageNo<=totalpage) {
            String url = "http://query.sse.com.cn/security/stock/getStockListData2.do";

            Map<String, String> param = new LinkedHashMap<>();
            param.put("jsonCallBack", "jsonpCallback46666");
            param.put("isPagination", "true");
            param.put("stockCode", "");
            param.put("csrcCode", "");
            param.put("areaName", "");
            param.put("stockType", "1");
            param.put("pageHelp.cacheSize", "1");
            param.put("pageHelp.beginPage", pageNo.toString());
            param.put("pageHelp.pageSize", "25");
            param.put("pageHelp.pageNo", pageNo.toString());

            param.put("_", System.currentTimeMillis() + "");

            Map<String, String> header = new HashMap<>();


            header.put("Referer", "http://www.sse.com.cn/");
            header.put("Host", "query.sse.com.cn");
            header.put("Cookie", "JSESSIONID=0D21DD474A780E0FE2E8B4B316FA0EEF");

            String res = HttpClientUtils.sendGet(url, param, header, false);
            JSONObject jsonObject = JSON.parseObject(res.substring(res.indexOf("(") + 1, res.lastIndexOf(")")));
            JSONObject page = jsonObject.getJSONObject("pageHelp");
            totalpage = page.getInteger("pageCount");
            JSONArray array= page.getJSONArray("data");
            for (Object o : array) {
                JSONObject jo = (JSONObject) o;
                Stocks stocks = new Stocks();
                stocks.setName(jo.getString("COMPANY_ABBR"));
                stocks.setCode(jo.getString("COMPANY_CODE"));
                Stocks stocks1 = stocksService.getOne(new LambdaQueryWrapper<Stocks>().eq(Stocks::getCode, stocks.getCode()));

                if (stocks1 == null) {
                    stocksService.save(stocks);
                }
            }
            pageNo++;
            Thread.sleep(100);
        }
    }

    public Boolean initStockDayData(String stockCode,Integer day){

        String  url = "http://yunhq.sse.com.cn:32041//v1/sh1/dayk/"+stockCode;
        Map<String, String> param = new LinkedHashMap<>();
        param.put("callback", "jQuery112408360187122686309_1612341430024");
        param.put("select", "date,open,high,low,close,volume");
        param.put("begin", -day-1+"");
        param.put("end", "-1");


        param.put("_", System.currentTimeMillis() + "");

        Map<String, String> header = new HashMap<>();


        header.put("Referer", "http://www.sse.com.cn/");
        header.put("Host", "yunhq.sse.com.cn:32041");
        String res = HttpClientUtils.sendGet(url, param, header, false);
        JSONObject jsonObject = JSON.parseObject(res.substring(res.indexOf("(") + 1, res.lastIndexOf(")")));
        JSONArray kline = jsonObject.getJSONArray("kline");
        if(kline!=null){
            for(Object o : kline){
                JSONArray array = (JSONArray)o;
                StockDailyData stockDailyData = new StockDailyData();
                String date = array.getString(0);
                StringBuffer stringBuffer = new StringBuffer(date);
                stringBuffer = stringBuffer.insert(4,"-");
                stringBuffer = stringBuffer.insert(7,"-");
                stockDailyData.setDate(LocalDate.parse(stringBuffer.toString()));//date
                stockDailyData.setOpen(array.getBigDecimal(1));//open
                stockDailyData.setHigh(array.getBigDecimal(2));//high
                stockDailyData.setLow(array.getBigDecimal(3));//low
                stockDailyData.setClose(array.getBigDecimal(4));//close
                stockDailyData.setVol(array.getLong(5));//high
                stockDailyData.setCode(stockCode);
                StockDailyData stockDailyData1 = stockDailyDataService.getOne(new LambdaQueryWrapper<StockDailyData>()
                        .eq(StockDailyData::getCode,stockCode)
                        .eq(StockDailyData::getDate,stockDailyData.getDate()));
                if(stockDailyData1 == null){
                    stockDailyDataService.save(stockDailyData);
                }
            }
        }
        return true;
    }

    @SneakyThrows
    public void init100DayData(){
        List<Stocks> stocks = stocksService.getBaseMapper().selectList(new LambdaQueryWrapper<Stocks>().ge(Stocks::getCode,601886));//查全部
        for(Stocks s:stocks){
            initStockDayData(s.getCode(),100);
            Thread.sleep(100);
        }
    }

    @SneakyThrows
    public void getYesterdayData(){
        List<Stocks> stocks = stocksService.getBaseMapper().selectList(new LambdaQueryWrapper<>());//查全部
        for(Stocks s:stocks){
            initStockDayData(s.getCode(),1);
            Thread.sleep(100);
        }
    }





}
