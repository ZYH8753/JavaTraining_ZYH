package io.kimmking.dubbo.demo.rmbprovider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("rmb_account")
public class RmbAccount {
    @TableId(type= IdType.AUTO)
    Long id;

    @TableField(value="user_id")
    Long userid;

    @TableField(value="price")
    int price;
}
