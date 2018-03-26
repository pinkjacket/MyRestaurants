package pinkjacket.myrestaurants.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import pinkjacket.myrestaurants.Constants;
import pinkjacket.myrestaurants.R;
import pinkjacket.myrestaurants.adapters.RestaurantListAdapter;
import pinkjacket.myrestaurants.models.Restaurant;
import pinkjacket.myrestaurants.services.YelpService;

public class RestaurantListActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private RestaurantListAdapter mAdapter;
//    private String[] restaurants = new String[] {"Over the Moon", "Cafe Nervosa", "Bel Canto", "Jean's", "Cucina", "Tony's Meatball Hutch", "Shake Shack", "Lucky & Son", "Luscious Grape", "Uncle Moe's Family Feedbag", "Los Pollos Hermanos", "Boar's Nest", "Snowclone Coffee", "Cheers", "Legitimate Bakery", "Soup and a Sandwich", "Slammin' Nutt", "Pastabilities"};
//    private String[] cuisines = new String[] {"Charcuterie", "Coffee and pastries", "Italian food", "Coffee and snacks", "Italian food", "Meatballs", "Burgers and shakes", "Breakfast", "Wine", "Family-style dining", "Chicken", "Delicatessen", "Coffee", "Beer", "Baked goods", "Soup and sandwiches", "Donuts", "Pasta"};
    public static final String TAG = RestaurantListActivity.class.getSimpleName();
    public ArrayList<Restaurant> restaurants = new ArrayList<>();
//    private SharedPreferences mSharedPreferences;
//    private String mRecentAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        getRestaurants(location);
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
//        if(mRecentAddress !=null){
//            getRestaurants(mRecentAddress);
//        }
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
                restaurants = yelpService.processResults(response);
                RestaurantListActivity.this.runOnUiThread(new Runnable(){

                    @Override
                    public void run(){
                        mAdapter = new RestaurantListAdapter(getApplicationContext(), restaurants);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(RestaurantListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                        //old code to log api results, not needed now but I don't want to get rid of it yet
//                        for (Restaurant restaurant : restaurants) {
//                            Log.d(TAG, "Name: " + restaurant.getName());
//                            Log.d(TAG, "Phone: " + restaurant.getPhone());
//                            Log.d(TAG, "Website: " + restaurant.getWebsite());
//                            Log.d(TAG, "Image url: " + restaurant.getImageUrl());
//                            Log.d(TAG, "Rating: " + Double.toString(restaurant.getRating()));
//                            Log.d(TAG, "Address: " + android.text.TextUtils.join(", ", restaurant.getAddress()));
//                            Log.d(TAG, "Categories: " + restaurant.getCategories().toString());
                        }
                });
            }
        });
    }
}
