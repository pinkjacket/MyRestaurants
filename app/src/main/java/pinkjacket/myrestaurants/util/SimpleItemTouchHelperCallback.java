package pinkjacket.myrestaurants.util;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback{
    private final ItemTouchHelperAdapter mAdapter;

    //passes gesture to firebase for interpretation

    public SimpleItemTouchHelperCallback(ItemTouchHelperAdapter adapter){
        mAdapter = adapter;
    }

    // enable drag gestures below

    @Override
    public boolean isLongPressDragEnabled(){
        return true;
    }

    //enable swipe gestures below

    @Override
    public boolean isItemViewSwipeEnabled(){
        return true;
    }

    //specific movement directions below

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder){
        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    //firebase notified when items are moved, updates restaurants accordingly

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder source, RecyclerView.ViewHolder target){
        if(source.getItemViewType() != target.getItemViewType()){
            return false;
        }
        mAdapter.onItemMove(source.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    // notifies the adapter when an item is dismissed, passes to firebase for deletion

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i){
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }
}
