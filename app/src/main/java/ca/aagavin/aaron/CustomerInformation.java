package ca.aagavin.aaron;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class CustomerInformation extends AppCompatActivity {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile(
                    "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                    Pattern.CASE_INSENSITIVE
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_information);
    }

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
            Intent i = new Intent(this, OrderActivity.class);

            startActivity(i);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle() == null){
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Nothing 222");
            alertDialog.show();
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    private boolean _emptyTextValid(int id){
        TextView textView = (TextView) findViewById(id);
        boolean valid = textView.getText().toString().length()!=0;
        if (!valid){
            textView.setError("Value Can't be empty");
        }
        return valid;
    }

    private boolean _ccTextValid(int id){
        TextView textView = (TextView) findViewById(id);
        boolean valid = textView.getText().toString().length()==16;
        if (!valid){
            textView.setError("Value needs to be 16 digits");
        }
        return valid;
    }

    private  boolean _phoneValid(int id){
        TextView textView = (TextView) findViewById(id);
        boolean valid = textView.getText().toString().matches("[0-9]+") && textView.getText().length() == 10;
        if (!valid){
            textView.setError("Phone number needs to be 10 digits");
        }
        return valid;
    }

    private boolean _emailTextValid(int id){
        TextView textView = (TextView) findViewById(id);
        boolean valid = VALID_EMAIL_ADDRESS_REGEX.matcher(textView.getText().toString()).find();
        if (!valid){
            textView.setError("Email address not valid");
        }
        return valid;
    }


}
