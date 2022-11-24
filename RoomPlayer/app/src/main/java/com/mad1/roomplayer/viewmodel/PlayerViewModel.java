package com.mad1.roomplayer.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mad1.roomplayer.model.Player;
import com.mad1.roomplayer.repository.PlayerRepository;

import java.util.List;

public class PlayerViewModel extends AndroidViewModel {
    private PlayerRepository mRepository;

    private final LiveData<List<Player>> mAllPlayers;

    public PlayerViewModel (Application application) {
        super(application);
        mRepository = new PlayerRepository(application);
        mAllPlayers = mRepository.getAllPlayers();
    }

    LiveData<List<Player>> getAllPlayers() { return mAllPlayers; }

    public void insert(Player word) { mRepository.insert(word); }
}
