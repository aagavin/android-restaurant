package ca.aagavin.aaron;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CuisineType extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisine);
        //
        RadioGroup rg = (RadioGroup) findViewById(R.id.pizzaType);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                RadioButton radiobtn = (RadioButton) findViewById(i);
                selectedType(radiobtn.getText().toString());
            }
        });

    }

    public void selectedType(String selectText){
        Intent i = new Intent(this, SelectMenu.class);
        i.putExtra("selection", selectText);
        startActivity(i);
    }
}
