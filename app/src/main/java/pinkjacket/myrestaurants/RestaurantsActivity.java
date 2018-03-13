package pinkjacket.myrestaurants;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RestaurantsActivity extends AppCompatActivity {
    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.listView) ListView mListView;
    private String[] restaurants = new String[] {"Over the Moon", "Cafe Nervosa", "Bel Canto", "Jean's", "Cucina", "Tony's Meatball Hutch", "Shake Shack", "Lucky & Son", "Luscious Grape", "Uncle Moe's Family Feedbag", "Los Pollos Hermanos", "Boar's Nest", "Snowclone Coffee", "Cheers", "Legitimate Bakery", "Soup and a Sandwich", "Slammin' Nutt", "Pastabilities"};
    private String[] cuisines = new String[] {"Charcuterie", "Coffee and pastries", "Italian food", "Coffee and snacks", "Italian food", "Meatballs", "Burgers and shakes", "Breakfast", "Wine", "Family-style dining", "Chicken", "Delicatessen", "Coffee", "Beer", "Baked goods", "Soup and sandwiches", "Donuts", "Pasta"};
    public static final String TAG = RestaurantsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        ButterKnife.bind(this);

        MyRestaurantsArrayAdapter adapter = new MyRestaurantsArrayAdapter(this, android.R.layout.simple_list_item_1, restaurants, cuisines);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                String restaurant = ((TextView)view).getText().toString();
                Toast.makeText(RestaurantsActivity.this, restaurant, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here are all the restaurants near: " + location);
    }
}
