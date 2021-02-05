package com.zjh.stock.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * stocks 实体类
 *
 * @author zhujianhao
 * @date 2021-02-03 15:20:49
 */
@Data
@TableName("stocks")
public class Stocks {


    /**
     * 股票代码
     */
    @TableId
    private String code;
    /**
     * 股票名称
     */
    private String name;
    /**
     * 板块
     */
    private String board;
    /**
     *
     */
    private String point;
    /**
     * 投资建议
     */
    private String suggest;

}
