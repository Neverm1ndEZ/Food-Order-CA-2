package com.nevermind.foodorderapp_ca2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {

    private TextView textViewOrderDetails;
    private Button btnViewOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Initialize views
        textViewOrderDetails = findViewById(R.id.textViewOrderDetails);
        btnViewOrder = findViewById(R.id.btnViewOrder);

        // Handle View Order button click
        btnViewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start ViewOrdersActivity
                startActivity(new Intent(OrderActivity.this, ViewOrdersActivity.class));
            }
        });

        // Retrieve and display order details (you should replace this with your order processing logic)
        Intent intent = getIntent();
        if (intent != null) {
            String orderDetails = intent.getStringExtra("ORDER_DETAILS");
            if (orderDetails != null) {
                textViewOrderDetails.setText(orderDetails);
            }
        }
    }
}
