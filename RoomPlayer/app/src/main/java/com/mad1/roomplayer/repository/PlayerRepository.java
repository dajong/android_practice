package com.mad1.roomplayer.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.mad1.roomplayer.dao.PlayerDao;
import com.mad1.roomplayer.db.PlayerRoomDatabase;
import com.mad1.roomplayer.model.Player;

import java.util.List;

public class PlayerRepository {
    private PlayerDao mPlayerDao;
    private LiveData<List<Player>> mAllPlayers;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public PlayerRepository(Application application) {
        PlayerRoomDatabase db = PlayerRoomDatabase.getDatabase(application);
        mPlayerDao = db.playerDao();
        mAllPlayers = mPlayerDao.getAlphabetizedPlayers();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Player>> getAllPlayers() {
        return mAllPlayers;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Player player) {
        PlayerRoomDatabase.databaseWriteExecutor.execute(() -> {
            mPlayerDao.insert(player);
        });
    }
}
