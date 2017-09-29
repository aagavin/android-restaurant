package ca.aagavin.aaron;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class CustomerInformation extends AppCompatActivity {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
                    "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                    Pattern.CASE_INSENSITIVE);
    private String _cuisineType;
    private String _totalPrice;
    private String _restaurantName;

    /**
     * Runs when activity starts
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_information);

        this._cuisineType = getIntent().getStringExtra("cuisineType");
        this._totalPrice = getIntent().getStringExtra("totalPrice");
        this._restaurantName = getIntent().getStringExtra("restaurantName");
    }

    /**
     * validates all the user inputs
     * @param view view
     */
    public void button_confirm_click(View view){
        boolean allInputsValid;
        allInputsValid = this._emptyTextValid(R.id.editText_name);
        allInputsValid = this._emptyTextValid(R.id.editText_address);

        allInputsValid = this._emptyTextValid(R.id.editText_cc);
        allInputsValid = this._ccTextValid(R.id.editText_cc);

        allInputsValid = this._emptyTextValid(R.id.editText_email);
        allInputsValid = this._emailTextValid(R.id.editText_email);


        allInputsValid = this._emptyTextValid(R.id.editText_moreInfo);

        allInputsValid = this._emptyTextValid(R.id.editText_phone);
        allInputsValid = this._phoneValid(R.id.editText_phone);


        if(!allInputsValid) {
            Toast.makeText(this, "Not all inputs are valid", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent nextIntent = new Intent(this, OrderActivity.class);
            nextIntent.putExtra("cuisineType",this._cuisineType);
            nextIntent.putExtra("totalPrice", this._totalPrice);
            nextIntent.putExtra("restaurantName", this._restaurantName);
            nextIntent.putExtra("customerName", ((TextView) findViewById(R.id.editText_name)).getText().toString());
            nextIntent.putExtra("customerAddress", ((TextView) findViewById(R.id.editText_address)).getText().toString());
            nextIntent.putExtra("customerCc", ((TextView) findViewById(R.id.editText_cc)).getText().toString());
            nextIntent.putExtra("customerEmail", ((TextView) findViewById(R.id.editText_email)).getText().toString());
            nextIntent.putExtra("customerMoreInfo", ((TextView) findViewById(R.id.editText_moreInfo)).getText().toString());
            nextIntent.putExtra("customerPhone", ((TextView) findViewById(R.id.editText_phone)).getText().toString());
            startActivity(nextIntent);
        }
    }

    /**
     * Hack to fix the back button
     * @param item selected menu item
     * @return bool
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle() == null){
            Intent pervIntent = new Intent(this, SelectMenu.class);
            pervIntent.putExtra("selection", this._cuisineType);
            startActivity(pervIntent);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Checks if an editTest is empty
     * @param id of view
     * @return bool
     */
    private boolean _emptyTextValid(int id){
        TextView textView = (TextView) findViewById(id);
        boolean valid = textView.getText().toString().length()!=0;
        if (!valid){
            textView.setError("Value Can't be empty");
        }
        return valid;
    }

    /**
     * Validates a credit card editText
     * @param id of view
     * @return bool
     */
    private boolean _ccTextValid(int id){
        TextView textView = (TextView) findViewById(id);
        boolean valid = textView.getText().toString().length()==16;
        if (!valid){
            textView.setError("Value needs to be 16 digits");
        }
        return valid;
    }

    /**
     * Validates a phone editText
     * @param id of view
     * @return bool
     */
    private  boolean _phoneValid(int id){
        TextView textView = (TextView) findViewById(id);
        boolean valid = textView.getText().toString().matches("[0-9]+") && textView.getText().length() == 10;
        if (!valid){
            textView.setError("Phone number needs to be 10 digits");
        }
        return valid;
    }

    /**
     * Validates an email editText
     * @param id of view
     * @return bool     * @param id
     * @return
     */
    private boolean _emailTextValid(int id){
        TextView textView = (TextView) findViewById(id);
        boolean valid = VALID_EMAIL_ADDRESS_REGEX.matcher(textView.getText().toString()).find();
        if (!valid){
            textView.setError("Email address not valid");
        }
        return valid;
    }


}
