package ca.aagavin.aaron;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    private String _cuisineType;
    private String _totalPrice;
    private String _restaurantName;
    private String _customerName;
    private String _customerAddress;
    private String _customerCc;
    private String _customerEmail;
    private String _customerMoreInfo;
    private String _customerPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        this._cuisineType = getIntent().getStringExtra("cuisineType");
        this._totalPrice = getIntent().getStringExtra("totalPrice");
        this._restaurantName = getIntent().getStringExtra("restaurantName");
        this._customerName = getIntent().getStringExtra("customerName");
        this._customerAddress = getIntent().getStringExtra("customerAddress");
        this._customerCc = getIntent().getStringExtra("customerCc");
        this._customerEmail = getIntent().getStringExtra("customerEmail");
        this._customerMoreInfo = getIntent().getStringExtra("customerMoreInfo");
        this._customerPhone = getIntent().getStringExtra("customerPhone");


        _setData();
    }

    private void _setData() {
        ((TextView) findViewById(R.id.textView_cuisineType)).setText(this._cuisineType);
        ((TextView) findViewById(R.id.textView_totalPrice)).setText(this._totalPrice);
        ((TextView) findViewById(R.id.textView_restaurantName)).setText(this._restaurantName);
        ((TextView) findViewById(R.id.textView_customerName)).setText(this._customerName);
        ((TextView) findViewById(R.id.textView_customerAddress)).setText(this._customerAddress);
        ((TextView) findViewById(R.id.textView_customerCc)).setText(this._customerCc);
        ((TextView) findViewById(R.id.textView_customerEmail)).setText(this._customerEmail);
        ((TextView) findViewById(R.id.textView_customerMoreInfo)).setText(this._customerMoreInfo);
        ((TextView) findViewById(R.id.textView_customerPhone)).setText(this._customerPhone);
    }

    public void submitOrderClick(View view){
        startActivity(new Intent(this, MainActivity.class));
    }
}
