package ca.aagavin.aaron;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_information);
    }

    public void button_confirm_click(View view){
        boolean allInputsValid;
        allInputsValid = this._nameTextValid();

        Toast.makeText(this, "Valid", Toast.LENGTH_SHORT).show();

    }

    private boolean _nameTextValid(){
        TextView name = (TextView) findViewById(R.id.editText_name);
        boolean valid = name.getText().toString().length()!=0;
        if (!valid){
            name.setError("Name can't be empty");
        }
        return valid;
    }


}
