package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    private int quantity = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        submitOrder(null);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        boolean hasChocolate = chocolate.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);

        EditText name = (EditText) findViewById(R.id.name_field);
        String priceMessage = createOrderSummary(price,
                hasWhippedCream,
                hasChocolate,
                name.getText().toString());
        displayMessage(priceMessage);
    }

    /**
     * Calculates the price of the order.
     *
     //* @param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int pricePerCup = 5;
        if (hasWhippedCream) {
            pricePerCup += 1;
        }
        if (hasChocolate) {
            pricePerCup += 2;
        }
        int price = quantity * pricePerCup;
        return price;
    }

    /**
     * Create summary of the order
     * @param price of the order
     * @return order summary
     */
    private String createOrderSummary(int price,
                                      boolean hasWhippedCream,
                                      boolean hasChocolate,
                                      String name) {
        return getString(R.string.order_summary_name, name) +
                "\n" + getString(R.string.order_summary_whipped_cream, hasWhippedCream) +
                "\n" + getString(R.string.order_summary_chocolate, hasChocolate) +
                "\n" + getString(R.string.order_summary_quantity, quantity) +
                "\n" + getString(R.string.order_summary_price, price) +
                "\n" + getString(R.string.thank_you);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * Increments the input of quantity
     * @param view
     */
    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * Decrements the input of quantity
     * @param view
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_LONG).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }
}