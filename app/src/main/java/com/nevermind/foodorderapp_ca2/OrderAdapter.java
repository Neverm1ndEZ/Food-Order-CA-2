package com.nevermind.foodorderapp_ca2;

// OrderAdapter.java
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class OrderAdapter extends ArrayAdapter<Order> {

    public OrderAdapter(Context context, List<Order> orders) {
        super(context, 0, orders);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Order order = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_order, parent, false);
        }

        TextView textViewCustomerName = convertView.findViewById(R.id.textViewCustomerName);
        TextView textViewMobileNumber = convertView.findViewById(R.id.textViewMobileNumber);
        TextView textViewFoodItem = convertView.findViewById(R.id.textViewFoodItem);

        if (order != null) {
            textViewCustomerName.setText("Customer: " + order.getCustomerName());
            textViewMobileNumber.setText("Mobile: " + order.getMobileNumber());
            textViewFoodItem.setText("Food Item: " + order.getFoodItem());
        }

        return convertView;
    }
}
