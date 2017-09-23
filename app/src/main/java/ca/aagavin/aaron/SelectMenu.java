package ca.aagavin.aaron;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SelectMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_menu);

        String selection = getIntent().getStringExtra("selection");

    }
}
