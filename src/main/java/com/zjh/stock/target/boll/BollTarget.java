package com.zjh.stock.target.boll;

import com.zjh.stock.entity.StockDailyData;
import com.zjh.stock.exception.MyException;
import jdk.internal.org.objectweb.asm.tree.analysis.BasicValue;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

/**
 * @author zhujianhao
 * @date 2021/2/4 14:51
 * @modify
 */
public class BollTarget {

    private Integer width = 2;

    private Integer dataNum = 20;

    private List<StockDailyData> data;

    private List<BigDecimal> up = new ArrayList<>();

    private List<BigDecimal> mb = new ArrayList<>();

    private List<BigDecimal> dn = new ArrayList<>();

    public  BollTarget(List<StockDailyData> data){
        this.data = data;
        checkData();
        init();
    }

    public  BollTarget(List<StockDailyData> data,Integer width,Integer datanum){
       this.data = data;
       this.width = width==null?2:width;
       this.dataNum = datanum==null?20:datanum;
       checkData();
       init();
    }
    private Boolean checkData(){
        if(CollectionUtils.isEmpty(data)||data.size()<dataNum){
            return false;
        }
        return true;
    }
    private void init(){

        calculateBoll();
    }

    private void calculateBoll(){
        Integer index = dataNum-1;//记录正在计算的日期
        for(int i = 0;i<=(data.size()-dataNum);i++){
            BigDecimal total = BigDecimal.valueOf(0);
            for(int j = i;j<=index;j++){
                total=  total.add(data.get(j).getClose());
            }

            BigDecimal avg = total.divide(BigDecimal.valueOf(dataNum));
            this.mb.add(avg);
            //计算方差
            BigDecimal fc = BigDecimal.valueOf(0);
            for(int j = i;j<=index;j++){
                fc =fc.add(data.get(j).getClose().subtract(avg).multiply(data.get(j).getClose().subtract(avg)));
            }

            //计算标准差

            Float sd = fc.divide(BigDecimal.valueOf(dataNum)).floatValue();
            Double dsd = Math.sqrt(sd);
            BigDecimal standardDeviation = BigDecimal.valueOf(dsd);

            this.up.add(avg.add(standardDeviation.multiply(BigDecimal.valueOf(width))));
            this.dn.add(avg.subtract(standardDeviation.multiply(BigDecimal.valueOf(width))));
            index++;
        }
    }




}
