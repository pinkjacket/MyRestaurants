package pinkjacket.myrestaurants;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.BindView;
import butterknife.ButterKnife;
import pinkjacket.myrestaurants.adapters.FirebaseRestaurantListAdapter;
import pinkjacket.myrestaurants.adapters.FirebaseRestaurantViewHolder;
import pinkjacket.myrestaurants.models.Restaurant;
import pinkjacket.myrestaurants.util.OnStartDragListener;
import pinkjacket.myrestaurants.util.SimpleItemTouchHelperCallback;

public class SavedRestaurantListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_restaurant_list);
    }
}
