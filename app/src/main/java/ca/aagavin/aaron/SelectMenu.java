package ca.aagavin.aaron;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
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
    private String _selectedRestaurantName;
    private LinearLayout _rootLayout;
    private TextView _totalText;
    private String _cuisineType;

    /**
     * Runs when the activity run
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_menu);

        this._cuisineType = getIntent().getStringExtra("selection");

    }

    /**
     * Handles the button event handler
     * @param view View
     */
    public void buttonCheckoutClick(View view) {
        Intent nextIntent = new Intent(this, CustomerInformation.class);
        nextIntent.putExtra("cuisineType",this._cuisineType);
        nextIntent.putExtra("totalPrice", this._totalText.getText().toString());
        nextIntent.putExtra("restaurantName", this._selectedRestaurantName);
        startActivity(nextIntent);
    }

    /**
     * Handles event when user click on a menu item
     * @param item MenuItem
     * @return bool
     */
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if(item.getTitle()!=null) {
            this._selectedRestaurantName = item.getTitle().toString();
            this._displayMenu(item.getTitle().toString());
        }

        this._totalText = (TextView) findViewById(R.id.totalText);
        this._totalText.setVisibility(View.VISIBLE);
        findViewById(R.id.button_checkout).setVisibility(View.VISIBLE);

        return super.onOptionsItemSelected(item);
    }


    /**
     * Changes the menu based on user input
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        switch (this._cuisineType){
            case "Chinese":
                getMenuInflater().inflate(R.menu.resturantch, menu);
                break;
            case "Italian":
                getMenuInflater().inflate(R.menu.resturantit, menu);
                break;
            case "Burgers":
                getMenuInflater().inflate(R.menu.resturantbn, menu);
                break;
            case "Pizza":
                getMenuInflater().inflate(R.menu.resturantpz, menu);
                break;
            case "Greek":
                getMenuInflater().inflate(R.menu.resturantgk, menu);
                break;
            default:
                break;
        }

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * loops through the restaurant data structure
     * @param restaurantName Name of resturant
     */
    private void _displayMenu(String restaurantName) {
        this._createRestaurantMenu();

        float density = this.getResources().getDisplayMetrics().density;

        // get root LinearLayout
        this._rootLayout = (LinearLayout) findViewById(R.id.rootLayout);

        // get all resturantit items from user selection
        for(MenuItem item : this._restaurant.get(this._cuisineType)){
            if(item.getRestaurantName().equals(restaurantName)) {
                this._addRow(density, item);
            }
        }
    }

    /**
     * Adds a row of elements dynamically
     * @param density screen size
     * @param item restaurant item
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
     * @param price amount to update by
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

    /**
     * Sets up data structure to hold the restaurant
     */
    private void _createRestaurantMenu(){
        this._restaurant = new HashMap<>();
        this._restaurant.put("Chinese", new ArrayList<>(Arrays.asList(
                new MenuItem("Spring Rolls", 3.25, R.drawable.springrolls, "A lighter, more delicate version of egg rolls, made with a flour and water wrapper.", "ManChi's"),
                new MenuItem("Deep Fried Wontons", 2.45, R.drawable.wonton, "Wonton wrappers filled with ground pork and a variety of vegetables and seasonings before deep-frying.", "ManChi's"),
                new MenuItem("Chow Mein", 7.25, R.drawable.chowmein, "A stir-fried dish consisting of noodles, meat, onions and celery.", "HeyChina"),
                new MenuItem("Egg Drop Soup", 7.05, R.drawable.eggdropsoup, "A classic dish -  flavored chicken broth or stock topped with silken threads of egg.", "HeyChina"),
                new MenuItem("Chicken and corn soup", 6.05, R.drawable.chickensoup, "Chicken stock with creamed sweet corn, sweet corn kernels and eggs. The texture of this soup is often thick.", "Tim's Chinese Food"),
                new MenuItem("Bang Bang Ji", 6.05, R.drawable.bangbangji, "Chicken breasts are cut into matchstick sized pieces and served on a sheet of green bean paste. Made with hot chili oil.", "Tim's Chinese Food"),
                new MenuItem("Lo Mein", 6.45, R.drawable.lomein, "Tossed Noodles - unlike chow mein, where the noodles are stir-fried separately, the noodles are tossed and blended with the stir-fry mixture.  They have more of a sauce than chow mein noodles.", "Pearl Harbourfront"),
                new MenuItem("General Tsao's Chicken", 9.95, R.drawable.generaltso, "Chicken cubes coated in cornstarch and deep-fried, cooked with a sauce that includes hoisin sauce, dark soy sauce and chili peppers.", "Pearl Harbourfront")

        )));
        this._restaurant.put("Italian", new ArrayList<>(Arrays.asList(
                new MenuItem("Antipasto Platter", 5.34, R.drawable.antipastopatter, "A traditional antipasto of cured meats, olives, peperoncini, mushrooms, anchovies, artichoke hearts, various cheeses", "Remezzo Italian Bistro"),
                new MenuItem("Calzone", 9.97, R.drawable.calzone, "Salted bread dough, baked in an oven and stuffed with salami or ham, mozzarella, ricotta and Parmesan or pecorino cheese", "Remezzo Italian Bistro"),
                new MenuItem("Fettuccine Alfredo", 10.15, R.drawable.alfredo, "A pasta dish made from fettuccine noodles tossed with Parmigiano-Reggiano cheese and butter.", "Stelvio"),
                new MenuItem("Prosciutto Platter", 7.45, R.drawable.prosciutto,"Dry-cured ham that is thinly sliced and served uncooked.", "Stelvio"),
                new MenuItem("Risotto", 7.45, R.drawable.risotto,"seasonal dishes such as spring or summer risotto, which adds in various herbs and seasonal veggies like asparagus.", "Donatello Restaurant"),
                new MenuItem("Ravioli", 7.45, R.drawable.ravioli,"Includes ricotta and spinach, perfect for the veggie traveler.", "Donatello Restaurant"),
                new MenuItem("Gnocchi", 7.45, R.drawable.gnocchi,"flour dumpling, are created in various flavors and styles. Vegetarians will enjoy the “pomodoro style,” sauce and cheese. Meat lovers can find a slab of fine meat", "Old Spaghetti Factory"),
                new MenuItem("Americano", 7.45, R.drawable.americano,"Known as caffee. We serve all traditional shots of Italian espresso", "Old Spaghetti Factory")
        )));
        this._restaurant.put("Burgers", new ArrayList<>(Arrays.asList(
                new MenuItem("Buffalo Burger", 13.99, R.drawable.buffaloburgers, "Prepared with meat from Bison, buffalo burgers have less cholesterol, less fat, and fewer calories than beef hamburgers and chicken hamburgers.", "Union Burger"),
                new MenuItem("Veggie burger", 8.99, R.drawable.veggieburger, "Veggie burger is made with tofu, beans, and an assortment of vegetables.", "Union Burger"),
                new MenuItem("Cheeseburger", 10.01, R.drawable.cheeseburger, "Hamburger accompanied with melted cheese. The cheese is usually sliced, then added a short time before the hamburger finishes cooking to allow it to melt.", "Museum Tavern"),
                new MenuItem("Australasian Burgers", 15.14, R.drawable.newzealand, "Popular regional hamburger in Australia and New Zealand includes canned beetroot and pineapple", "Museum Tavern"),
                new MenuItem("Angus burger", 12.14, R.drawable.angus, "A hamburger made using beef from Angus cattle", "Woody's Burgers"),
                new MenuItem("Bøfsandwich", 15.14, R.drawable.danishsandwich, "Classic Danish take on a hamburger. It contains the hamburger elements of a cooked ground beef patty placed inside a sliced bread roll", "Woody's Burgers"),
                new MenuItem("Butter burger", 15.14, R.drawable.butterdouble, "Made with a buttered bun, butter as one of the ingredients of the patty or with a pat of butter on top of the burger patty.", "Five Guys"),
                new MenuItem("Sloppy joes", 15.14, R.drawable.sloppyjoe, "A sandwich consisting of ground beef, onions, tomato sauce or ketchup, Worcestershire sauce, and other seasonings, served on a hamburger bun.", "Five Guys")
        )));
        this._restaurant.put("Pizza", new ArrayList<>(Arrays.asList(
                new MenuItem("Neapolitan pizza", 21.13, R.drawable.neapolitanpizza, "Made with San Marzano tomatoes and mozzarella cheese", "Domino's Pizza"),
                new MenuItem("Hawaiian pizza", 21.10, R.drawable.pineapplepizza, "Topped with tomato sauce, cheese, pineapple,Canadian bacon and ham", "Domino's Pizza"),
                new MenuItem("Chicago-style deep-dish pizza", 28.11, R.drawable.deepdish, "High edge and a deep surface for large amounts of cheese and chunky tomato sauce topped with pepperoni", "Magic Oven"),
                new MenuItem("Sicilian Pizza", 28.11, R.drawable.sicilian, "square cut, thick crust pizza from, it should always have a spongier consistency than other pizzas.", "Magic Oven"),
                new MenuItem("Greek Pizza", 28.11, R.drawable.greekpizza, "baked in a round pan that has been heavily coated in olive oil. Lining the pan with oil also allows the bottom of the dough to fry while it bakes.", "Boston Pizza"),
                new MenuItem("California Pizza", 28.11, R.drawable.calpizza, "Single-serving pizza that combines New York and Italian thin crust with toppings from the California cuisine", "Boston Pizza"),
                new MenuItem("Pizza Cone", 28.11, R.drawable.pizzacone, "The latest and greatest way to eat pizza", "Papa John's Pizza")
        )));
        this._restaurant.put("Greek", new ArrayList<>(Arrays.asList(
                new MenuItem("Lagana Bread", 6.34, R.drawable.lagana, "Traditional greek flatbread topped with sesame seeds", "Louis Souvlaki & Grill"),
                new MenuItem("Greek salad", 5.33, R.drawable.greeksalad,"Made with pieces of tomatoes, sliced cucumbers, onion, feta cheese", "Louis Souvlaki & Grill"),
                new MenuItem("Gyro",9.45, R.drawable.gyro,"Made outside with pork, or chicken, and cooked on a vertical rotisserie.", "The Arkadia House"),
                new MenuItem("Baklava", 5.25, R.drawable.baklava, "A rich, sweet dessert pastry made of layers of filo filled with chopped nuts and sweetened with honey.", "Jimmy The Greek"),
                new MenuItem("Calamari",9.45, R.drawable.calamari,"Garnished with lemon and parsley and served with some sort of dipping sauce.", "Jimmy The Greek"),
                new MenuItem("Souvlaki",9.45, R.drawable.souvlaki,"grilled small pieces of meat served on the skewer for eating out of hand.", "The Arkadia House"),
                new MenuItem("Orzo with Shrimp ",9.45, R.drawable.orzo,"risotto cooking of rice for an easy, comforting main dish", "The Souvlaki Hut"),
                new MenuItem("Greek Yogurt",9.45, R.drawable.yaourt,"Yogurt with honey and walnuts.", "The Souvlaki Hut")
        )));


    }

}
