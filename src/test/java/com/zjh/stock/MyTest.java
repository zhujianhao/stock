package com.zjh.stock;

import com.zjh.stock.util.HttpClientUtils;
import org.junit.jupiter.api.Test;
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

    @Test
    public void testHttp(){
        String url = "http://query.sse.com.cn/security/stock/getStockListData2.do";

        Map<String,String> param = new LinkedHashMap<>();
        param.put("jsonCallBack","jsonpCallback46666");
        param.put("isPagination","true");
        param.put("stockCode","");
        param.put("csrcCode","");
        param.put("areaName","");
        param.put("stockType","1");
        param.put("pageHelp.cacheSize","1");
        param.put("pageHelp.beginPage","1");
        param.put("pageHelp.pageSize","25");
        param.put("pageHelp.pageNo","1");

        param.put("_",System.currentTimeMillis()+"");

        Map<String,String> header = new HashMap<>();


        header.put("Referer","http://www.sse.com.cn/");
        header.put("Host","query.sse.com.cn");
        header.put("Cookie","JSESSIONID=0D21DD474A780E0FE2E8B4B316FA0EEF");

        String res = HttpClientUtils.sendGet(url,param,header,false);
        System.out.println(res);

    }
}
