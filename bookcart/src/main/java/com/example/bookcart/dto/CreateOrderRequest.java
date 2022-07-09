package com.example.bookcart.dto;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class CreateOrderRequest {
    @NotEmpty
    private List<BuyItem> buyItemList;

    public List<BuyItem> getBuyItemsList() {
        return buyItemList;
    }

    public void setBuyItemsList(List<BuyItem> buyItemList) {
        this.buyItemList = buyItemList;
    }

}
