package com.mad1.roomplayer.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mad1.roomplayer.model.Player;

import java.util.List;

@Dao
public interface PlayerDao {
    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Player word);

    @Query("DELETE FROM player_table")
    void deleteAll();

    @Query("SELECT * FROM player_table ORDER BY player_name ASC")
    LiveData<List<Player>> getAlphabetizedPlayers();
}
