package com.mad1.roomplayer.viewmodel;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.mad1.roomplayer.model.Player;

public class PlayerListAdapter extends ListAdapter<Player, PlayerViewHolder> {
    public PlayerListAdapter(@NonNull DiffUtil.ItemCallback<Player> diffCallback) {
        super(diffCallback);
    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return PlayerViewHolder.create(parent);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        Player current = getItem(position);
        holder.bind(current.getName());
    }

    public static class WordDiff extends DiffUtil.ItemCallback<Player> {

        @Override
        public boolean areItemsTheSame(@NonNull Player oldItem, @NonNull Player newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Player oldItem, @NonNull Player newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }
}
