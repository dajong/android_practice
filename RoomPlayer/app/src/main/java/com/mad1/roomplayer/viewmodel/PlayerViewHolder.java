package com.mad1.roomplayer.viewmodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mad1.roomplayer.R;

public class PlayerViewHolder extends RecyclerView.ViewHolder{

    private final TextView playerItemView;

    private PlayerViewHolder(View itemView) {
        super(itemView);
        playerItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text) {
        playerItemView.setText(text);
    }

    public static PlayerViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new PlayerViewHolder(view);
    }
}
