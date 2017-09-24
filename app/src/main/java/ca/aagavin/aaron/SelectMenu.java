package ca.aagavin.aaron;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import ca.aagavin.aaron.objects.MenuItem;

public class SelectMenu extends AppCompatActivity {

    private Map<String, List<MenuItem>> _restaurant;
    private LinearLayout _rootLayout;
    private TextView _totalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_menu);

        this._createMenu();
        _totalText = (TextView) findViewById(R.id.totalText);
        float density = this.getResources().getDisplayMetrics().density;

        // get root LinearLayout
        _rootLayout = (LinearLayout) findViewById(R.id.rootLayout);
        // get user selection from previous section
        String selection = getIntent().getStringExtra("selection");

        // get all menu items from user selection
        for(MenuItem item : this._restaurant.get(selection)){
            this._addRow(density, item);
        }


    }

    /**
     * Adds a row of elements dynamically
     * @param density screen size
     * @param item menu item
     */
    private void _addRow(float density, MenuItem item) {
        // create container layout
        LinearLayout container = new LinearLayout(this);

        // create image view w/props
        ImageView img = new ImageView(this);
        img.setLayoutParams(new LinearLayout.LayoutParams(
                (int)(100 * density),
                (int)(75*density))
        );
        img.setImageResource(item.getImage());

        // create checkBox view w/props
        CheckBox box = new CheckBox(this);
        box.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        box.setText(item.getName()+" ($"+item.getPrice()+")");
        box.setTextSize(5*density);
        box.setOnCheckedChangeListener(_setTotalPrice(item.getPrice()));

        // add image and checkbox to container layout
        container.addView(img);
        container.addView(box);

        // add container layout to root layout
        _rootLayout.addView(container,0);
    }

    /**
     * Handles when a user clicks on a screen
     * @param price price to update by
     * @return OnCheckedChangeListener
     */
    @NonNull
    private CompoundButton.OnCheckedChangeListener _setTotalPrice(final double price) {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                String[] newTotal = _totalText.getText().toString().split("\\$");
                double newValue;
                if (checked){
                    newValue = Double.parseDouble(newTotal[1]) + price;
                }
                else{
                    newValue = Double.parseDouble(newTotal[1]) - price;
                }
                _totalText.setText(String.format(Locale.CANADA ,"%s$%.2f", newTotal[0],newValue));
            }
        };
    }

    private void _createMenu(){
        this._restaurant = new HashMap<>();
        this._restaurant.put("Chinese", new ArrayList<>(Arrays.asList(
                new MenuItem("Spring Rolls", 3.25, R.drawable.springrolls, "A lighter, more delicate version of egg rolls, made with a flour and water wrapper."),
                new MenuItem("Deep Fried Wontons", 2.45, R.drawable.wonton, "Wonton wrappers filled with ground pork and a variety of vegetables and seasonings before deep-frying."),
                new MenuItem("Chow Mein", 7.25, R.drawable.chowmein, "A stir-fried dish consisting of noodles, meat, onions and celery."),
                new MenuItem("General Tsao's Chicken", 9.95, R.drawable.generaltso, "Chicken cubes coated in cornstarch and deep-fried, cooked with a sauce that includes hoisin sauce, dark soy sauce and chili peppers.")

        )));
        this._restaurant.put("Italian", new ArrayList<>(Arrays.asList(
                new MenuItem("Antipasto Platter", 5.34, R.drawable.antipastopatter, "A traditional antipasto of cured meats, olives, peperoncini, mushrooms, anchovies, artichoke hearts, various cheeses"),
                new MenuItem("Calzone", 9.97, R.drawable.calzone, "Salted bread dough, baked in an oven and stuffed with salami or ham, mozzarella, ricotta and Parmesan or pecorino cheese"),
                new MenuItem("Fettuccine Alfredo", 10.15, R.drawable.alfredo, "A pasta dish made from fettuccine noodles tossed with Parmigiano-Reggiano cheese and butter."),
                new MenuItem("Prosciutto Platter", 7.45, R.drawable.prosciutto,"Dry-cured ham that is thinly sliced and served uncooked.")
        )));
        this._restaurant.put("Burgers", new ArrayList<>(Arrays.asList(
                new MenuItem("Buffalo Burger", 13.99, R.drawable.buffaloburgers, "Prepared with meat from Bison, buffalo burgers have less cholesterol, less fat, and fewer calories than beef hamburgers and chicken hamburgers."),
                new MenuItem("Veggie burger", 8.99, R.drawable.veggieburger, "Veggie burger is made with tofu, beans, and an assortment of vegetables."),
                new MenuItem("Cheeseburger", 10.01, R.drawable.cheeseburger, "Hamburger accompanied with melted cheese. The cheese is usually sliced, then added a short time before the hamburger finishes cooking to allow it to melt."),
                new MenuItem("Australasian Burgers", 15.14, R.drawable.newzealand, "Popular regional hamburger in Australia and New Zealand includes canned beetroot and pineapple")
        )));
        this._restaurant.put("Pizza", new ArrayList<>(Arrays.asList(
                new MenuItem("Authentic Neapolitan pizza", 21.13, R.drawable.neapolitanpizza, "Made with San Marzano tomatoes and mozzarella cheese"),
                new MenuItem("Hawaiian pizza", 21.10, R.drawable.pineapplepizza, "Topped with tomato sauce, cheese, pineapple,Canadian bacon and ham"),
                new MenuItem("Meatball pizza", 23.45, R.drawable.panmeatball, "Prepared with meatballs. Increasingly popular in Upstate New York in contemporary times"),
                new MenuItem("Chicago-style deep-dish pizza", 28.11, R.drawable.deepdish, "High edge and a deep surface for large amounts of cheese and chunky tomato sauce topped with pepperoni")
        )));
        this._restaurant.put("Greek", new ArrayList<>(Arrays.asList(
                new MenuItem("Lagana Bread", 6.34, R.drawable.lagana, "Traditional greek flatbread topped with sesame seeds"),
                new MenuItem("Greek salad", 5.33, R.drawable.greeksalad,"Made with pieces of tomatoes, sliced cucumbers, onion, feta cheese"),
                new MenuItem("Gyro",9.45, R.drawable.gyro,"Made outside with pork, or chicken, and cooked on a vertical rotisserie."),
                new MenuItem("Baklava", 5.25, R.drawable.baklava, "A rich, sweet dessert pastry made of layers of filo filled with chopped nuts and sweetened with honey.")
        )));


    }
}
