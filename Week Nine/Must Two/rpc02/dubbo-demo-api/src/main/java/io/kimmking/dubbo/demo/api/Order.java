package io.kimmking.dubbo.demo.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements java.io.Serializable {
    private long dollarId;

    private long rmbId;

    private int dollarPrice;

    private int rmbPrice;

    private FreezeAccount rmbFreezeAccount;

    private FreezeAccount dollarFreezeAccount;

    public Order(long dollarId, long rmbId, int dollarPrice, int rmbPrice) {
        this.dollarId = dollarId;
        this.rmbId = rmbId;
        this.dollarPrice = dollarPrice;
        this.rmbPrice = rmbPrice;
    }
}
