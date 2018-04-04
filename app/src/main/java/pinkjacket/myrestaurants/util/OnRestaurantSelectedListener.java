package pinkjacket.myrestaurants.util;

import java.util.ArrayList;

import pinkjacket.myrestaurants.models.Restaurant;

public interface OnRestaurantSelectedListener {
    public void onRestaurantSelected(Integer position, ArrayList<Restaurant> restaurants, String source);
}
