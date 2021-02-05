package com.zjh.stock.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import lombok.Data;

/**
 * stock_daily_data 实体类
 *
 * @author zhujianhao
 * @date 2021-02-03 15:20:49
 */
@Data
@TableName("stock_daily_data")
public class StockDailyData {


    /**
     *
     */
    @TableId
    private String id;
    /**
     * 股票代码
     */
    private String code;
    /**
     *股票名称
     */
    private String name;
    /**
     * 日期
     */
    private LocalDate date;
    /**
     * 开盘
     */
    private BigDecimal open;
    /**
     * 高
     */
    private BigDecimal high;
    /**
     * 低
     */
    private BigDecimal low;
    /**
     * 收
     */
    private BigDecimal close;
    /**
     * 成交量
     */
    private Long vol;

}
