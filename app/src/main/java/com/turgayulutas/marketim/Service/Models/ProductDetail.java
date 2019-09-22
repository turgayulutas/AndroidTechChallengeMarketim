
package com.turgayulutas.marketim.Service.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetail {

    @SerializedName("orderDetail")
    @Expose
    private String orderDetail;
    @SerializedName("summaryPrice")
    @Expose
    private Double summaryPrice;

    public String getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(String orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Double getSummaryPrice() {
        return summaryPrice;
    }

    public void setSummaryPrice(Double summaryPrice) {
        this.summaryPrice = summaryPrice;
    }

}
