package com.zyh.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@TableName("order_list")
public class Order {
    @TableId(type=IdType.AUTO)
    Integer id;
    @TableField(value="order_id")
    String orderId;
    @TableField(value="user_id")
    Integer userId;
}
