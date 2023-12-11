package com.nevermind.foodorderapp_ca2;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class ViewOrdersActivity extends AppCompatActivity {

    private ListView listViewOrders;
    private OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);
        listViewOrders = findViewById(R.id.listViewOrders);
        orderAdapter = new OrderAdapter(this, getOrderListFromDatabase());
        listViewOrders.setAdapter(orderAdapter);
    }

    private List<Order> getOrderListFromDatabase() {
        DatabaseManager databaseManager = new DatabaseManager(this);
        try {
            databaseManager.open();
            return databaseManager.getAllOrders();
        } finally {
            databaseManager.close();
        }
    }
}
