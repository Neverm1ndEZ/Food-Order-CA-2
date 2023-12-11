package com.nevermind.foodorderapp_ca2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextMobile;
    private ListView listViewFoodItems;
    private Button btnOrder, btnViewOrder;
    private FoodItemAdapter foodItemAdapter;
    private String selectedFoodItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextMobile = findViewById(R.id.editTextMobile);
        listViewFoodItems = findViewById(R.id.listViewFoodItems);
        btnOrder = findViewById(R.id.btnOrder);
        btnViewOrder = findViewById(R.id.btnViewOrder);

        foodItemAdapter = new FoodItemAdapter(this, getFoodItems());
        listViewFoodItems.setAdapter(foodItemAdapter);

        listViewFoodItems.setOnItemClickListener((parent, view, position, id) -> {
            // Get the selected food item
            selectedFoodItem = foodItemAdapter.getItem(position);
            Toast.makeText(MainActivity.this, "Selected: " + foodItemAdapter.getItem(position), Toast.LENGTH_SHORT).show();
        });

        btnOrder.setOnClickListener(v -> {
            String customerName = editTextName.getText().toString().trim();
            String mobileNumber = editTextMobile.getText().toString().trim();

            if (!customerName.isEmpty() && !mobileNumber.isEmpty()) {
                if (selectedFoodItem != null) {
                    Order order = new Order(customerName, mobileNumber, selectedFoodItem);

                    DatabaseManager databaseManager = new DatabaseManager(MainActivity.this);
                    databaseManager.open();

                    long insertedRow = databaseManager.insertOrder(order);

                    if (insertedRow > 0) {
                        Toast.makeText(MainActivity.this, "Order placed successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Failed to place order", Toast.LENGTH_SHORT).show();
                    }

                    databaseManager.close();
                } else {
                    Toast.makeText(MainActivity.this, "Please select a food item", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Please enter customer details", Toast.LENGTH_SHORT).show();
            }
        });

        btnViewOrder.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ViewOrdersActivity.class)));
    }

    private List<String> getFoodItems() {
        List<String> foodItems = new ArrayList<>();
        foodItems.add("Burger");
        foodItems.add("Pizza");
        foodItems.add("Biryani");
        foodItems.add("Choley Bhaturey");
        foodItems.add("Indian Thali");
        foodItems.add("Mushroom Masala");
        foodItems.add("Gobi Manchurian");
        foodItems.add("Chinese");
        return foodItems;
    }
}