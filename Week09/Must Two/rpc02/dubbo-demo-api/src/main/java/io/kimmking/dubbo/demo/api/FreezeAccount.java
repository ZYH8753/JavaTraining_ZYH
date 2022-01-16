package io.kimmking.dubbo.demo.api;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@TableName("freeze")
@ToString
@Data
@AllArgsConstructor
public class FreezeAccount implements java.io.Serializable {
    @TableId(type= IdType.AUTO)
    private Long id;

    @TableField(value="user_id")
    private Long userId;

    @TableField(value="dollar_price")
    private int dollarPrice;

    @TableField(value="rmb_price")
    private int rmbPrice;

    public FreezeAccount() {
    }

    public FreezeAccount(Long userId, int dollarPrice, int rmbPrice) {
        this.userId = userId;
        this.dollarPrice = dollarPrice;
        this.rmbPrice = rmbPrice;
    }

}
