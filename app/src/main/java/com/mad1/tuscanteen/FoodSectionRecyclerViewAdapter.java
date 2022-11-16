package com.mad1.tuscanteen;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mad1.tuscanteen.Contents.FoodSectionUtils;
import com.mad1.tuscanteen.databinding.FoodSectionListBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link com.mad1.tuscanteen.Contents.FoodSectionUtils.FoodSection}.
 * TODO: Replace the implementation with code for your data type.
 */
public class FoodSectionRecyclerViewAdapter extends RecyclerView.Adapter<FoodSectionRecyclerViewAdapter.ViewHolder> {

    private final List<FoodSectionUtils.FoodSection> mValues;

    public FoodSectionRecyclerViewAdapter(List<FoodSectionUtils.FoodSection> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FoodSectionListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public FoodSectionUtils.FoodSection mItem;

        public ViewHolder(FoodSectionListBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}