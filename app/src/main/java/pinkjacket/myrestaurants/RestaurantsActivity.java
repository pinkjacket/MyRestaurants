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

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RestaurantsActivity extends AppCompatActivity {
    @BindView(R.id.locationTextView) TextView mLocationTextView;
    @BindView(R.id.listView) ListView mListView;
//    private String[] restaurants = new String[] {"Over the Moon", "Cafe Nervosa", "Bel Canto", "Jean's", "Cucina", "Tony's Meatball Hutch", "Shake Shack", "Lucky & Son", "Luscious Grape", "Uncle Moe's Family Feedbag", "Los Pollos Hermanos", "Boar's Nest", "Snowclone Coffee", "Cheers", "Legitimate Bakery", "Soup and a Sandwich", "Slammin' Nutt", "Pastabilities"};
//    private String[] cuisines = new String[] {"Charcuterie", "Coffee and pastries", "Italian food", "Coffee and snacks", "Italian food", "Meatballs", "Burgers and shakes", "Breakfast", "Wine", "Family-style dining", "Chicken", "Delicatessen", "Coffee", "Beer", "Baked goods", "Soup and sandwiches", "Donuts", "Pasta"};
    public static final String TAG = RestaurantsActivity.class.getSimpleName();
    public ArrayList<Restaurant> restaurants = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, restaurants);
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
        getRestaurants(location);
    }
    private void getRestaurants(String location){
        final YelpService yelpService = new YelpService();
        yelpService.findRestaurants(location, new Callback(){

            @Override
            public void onFailure(Call call, IOException e){
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException{
                try{
                    String jsonData = response.body().string();
                    if (response.isSuccessful()) {
                        Log.v(TAG, jsonData);
                        restaurants = yelpService.processResults(response);
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
