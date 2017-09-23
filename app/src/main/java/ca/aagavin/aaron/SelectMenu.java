package ca.aagavin.aaron;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.aagavin.aaron.objects.MenuItem;

// By Gunawan Kartapranata - Own work, CC BY-SA 3.0, https://commons.wikimedia.org/w/index.php?curid=9178305
public class SelectMenu extends AppCompatActivity {

    private Map<String, List<MenuItem>> _resturant;

    public SelectMenu(){
        this._resturant = new HashMap<>();
        this._resturant.put("Chinese", new ArrayList<MenuItem>(){{
            new MenuItem("Spring Rolls", 3.25, "spring-rolls.jpg", "A lighter, more delicate version of egg rolls, made with a flour and water wrapper.");
            new MenuItem("Deep Fried Wontons", 2.45, "wonton.jpg", "Wonton wrappers filled with ground pork and a variety of vegetables and seasonings before deep-frying.");
            new MenuItem("Chow Mein", 7.25, "chow_mein.jpg", "A stir-fried dish consisting of noodles, meat, onions and celery.");
        }});
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_menu);

        String selection = getIntent().getStringExtra("selection");

        List<MenuItem> menu = this._resturant.get(selection);

    }
}
