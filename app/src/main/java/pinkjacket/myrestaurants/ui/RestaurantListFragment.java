package pinkjacket.myrestaurants.ui;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import pinkjacket.myrestaurants.R;
import pinkjacket.myrestaurants.adapters.RestaurantListAdapter;
import pinkjacket.myrestaurants.models.Restaurant;


public class RestaurantListFragment extends Fragment {
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private RestaurantListAdapter mAdapter;
    public static final String TAG = RestaurantListActivity.class.getSimpleName();
    public ArrayList<Restaurant> restaurants = new ArrayList<>();
    private SharedPreferences mSharedPreferences;
    private String mRecentAddress;
    private SharedPreferences.Editor mEditor;


    public RestaurantListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_list, container, false);
    }

}
