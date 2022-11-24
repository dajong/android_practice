package com.mad1.roomplayer.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mad1.roomplayer.dao.PlayerDao;
import com.mad1.roomplayer.model.Player;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Player.class}, version = 1, exportSchema = false)
public abstract class PlayerRoomDatabase extends RoomDatabase {
    public abstract PlayerDao playerDao();

    private static volatile PlayerRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static PlayerRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PlayerRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PlayerRoomDatabase.class, "player_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
