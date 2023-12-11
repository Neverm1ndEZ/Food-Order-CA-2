package com.nevermind.foodorderapp_ca2;

// DatabaseManager.java

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertOrder(Order order) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_CUSTOMER_NAME, order.getCustomerName());
        values.put(DatabaseHelper.COLUMN_MOBILE_NUMBER, order.getMobileNumber());
        values.put(DatabaseHelper.COLUMN_FOOD_ITEM, order.getFoodItem());

        return database.insert(DatabaseHelper.TABLE_ORDERS, null, values);
    }

    @SuppressLint("Range")
    // Inside your DatabaseManager class
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = database.query(
                    DatabaseHelper.TABLE_ORDERS,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String customerName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CUSTOMER_NAME));
                    String mobileNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_MOBILE_NUMBER));
                    String foodItem = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FOOD_ITEM));

                    Order order = new Order(customerName, mobileNumber, foodItem);
                    orders.add(order);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return orders;
    }

}
