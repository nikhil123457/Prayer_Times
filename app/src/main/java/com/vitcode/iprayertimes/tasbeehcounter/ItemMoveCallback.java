package com.vitcode.iprayertimes.tasbeehcounter;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class ItemMoveCallback extends ItemTouchHelper.Callback {
    private final ItemTouchHelperContract mAdapter;

    public interface ItemTouchHelperContract {
        void onRowClear(TasbihAdapter.ViewHolder viewHolder);

        void onRowMoved(int i, int i2);

        void onRowSelected(TasbihAdapter.ViewHolder viewHolder);
    }

    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    public boolean isLongPressDragEnabled() {
        return false;
    }

    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
    }

    public ItemMoveCallback(ItemTouchHelperContract itemTouchHelperContract) {
        this.mAdapter = itemTouchHelperContract;
    }

    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(3, 0);
    }

    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        this.mAdapter.onRowMoved(viewHolder.getAdapterPosition(), viewHolder2.getAdapterPosition());
        return true;
    }

    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
        if (i != 0 && (viewHolder instanceof TasbihAdapter.ViewHolder)) {
            this.mAdapter.onRowSelected((TasbihAdapter.ViewHolder) viewHolder);
        }
        super.onSelectedChanged(viewHolder, i);
    }

    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if (viewHolder instanceof TasbihAdapter.ViewHolder) {
            this.mAdapter.onRowClear((TasbihAdapter.ViewHolder) viewHolder);
        }
    }
}
