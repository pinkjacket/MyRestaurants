package pinkjacket.myrestaurants.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import pinkjacket.myrestaurants.Constants;
import pinkjacket.myrestaurants.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.findRestaurantsButton) Button mFindRestaurantsButton;
    @BindView(R.id.locationEditText) EditText mLocationEditText;
    @BindView(R.id.appNameTextView) TextView mAppNameTextView;
//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
    private DatabaseReference mSearchedLocationReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedLocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        Typeface robotoFont = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        mAppNameTextView.setTypeface(robotoFont);
        mFindRestaurantsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v == mFindRestaurantsButton) {
            String location = mLocationEditText.getText().toString();

            saveLocationToFirebase(location);
//            if(!(location).equals("")) {
//                addToSharedPreferences(location);
//            }
            Intent intent = new Intent(MainActivity.this, RestaurantListActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        }
    }

    public void saveLocationToFirebase(String location){
        mSearchedLocationReference.push().setValue(location);
    }

//    private void addToSharedPreferences(String location){
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
//    }
}