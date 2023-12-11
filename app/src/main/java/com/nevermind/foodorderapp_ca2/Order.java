package com.nevermind.foodorderapp_ca2;

public class Order {
    private String customerName;
    private String mobileNumber;
    private String foodItem;

    public Order(String customerName, String mobileNumber, String foodItem) {
        this.customerName = customerName;
        this.mobileNumber = mobileNumber;
        this.foodItem = foodItem;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getFoodItem() {
        return foodItem;
    }
}
