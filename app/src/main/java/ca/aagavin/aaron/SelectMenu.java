package ca.aagavin.aaron;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.aagavin.aaron.objects.MenuItem;

// By Gunawan Kartapranata - Own work, CC BY-SA 3.0, https://commons.wikimedia.org/w/index.php?curid=9178305
public class SelectMenu extends AppCompatActivity {

    private Map<String, List<MenuItem>> _restaurant;
    private LinearLayout rootLayout;

    private void _createMenu(){
        this._restaurant = new HashMap<>();
        this._restaurant.put("Chinese", new ArrayList<>(Arrays.asList(
                new MenuItem("Spring Rolls", 3.25, R.drawable.springrolls, "A lighter, more delicate version of egg rolls, made with a flour and water wrapper."),
                new MenuItem("Deep Fried Wontons", 2.45, R.drawable.wonton, "Wonton wrappers filled with ground pork and a variety of vegetables and seasonings before deep-frying."),
                new MenuItem("Chow Mein", 7.25, R.drawable.chowmein, "A stir-fried dish consisting of noodles, meat, onions and celery."),
                new MenuItem("General Tsao's Chicken", 9.95, R.drawable.generaltso, "Chicken cubes coated in cornstarch and deep-fried, cooked with a sauce that includes hoisin sauce, dark soy sauce and chili peppers.")

        )));
//        this._restaurant.put("Italian", new ArrayList<>(Arrays.asList(
//                new MenuItem("General Tsao's Chicken")
//        )));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_menu);

        this._createMenu();
        float density = this.getResources().getDisplayMetrics().density;

        // get root LinearLayout
        rootLayout = (LinearLayout) findViewById(R.id.rootLayout);
        // get user selection from previous section
        String selection = getIntent().getStringExtra("selection");

        // get all menu items from user selection
        for(MenuItem item : this._restaurant.get(selection)){
            LinearLayout container = new LinearLayout(this);

            ImageView img = new ImageView(this);
            img.setLayoutParams(new LinearLayout.LayoutParams(
                    (int)(100 * density),
                    (int)(75*density))
            );
            img.setImageResource(item.getImage());

            CheckBox box = new CheckBox(this);
            box.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            ));
            box.setText(item.getName());

            container.addView(img);
            container.addView(box);

            rootLayout.addView(container);
        }


    }
}
