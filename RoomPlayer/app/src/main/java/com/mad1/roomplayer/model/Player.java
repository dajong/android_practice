package com.mad1.roomplayer.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "player_table")
public class Player {

    public Player(int id, @NonNull String name, @NonNull Integer wins, @NonNull Integer losses, @NonNull Integer ties) {
        this.id = id;
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
    }

    public Player() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public Integer getWins() {
        return wins;
    }

    public void setWins(@NonNull Integer wins) {
        this.wins = wins;
    }

    @NonNull
    public Integer getLosses() {
        return losses;
    }

    public void setLosses(@NonNull Integer losses) {
        this.losses = losses;
    }

    @NonNull
    public Integer getTies() {
        return ties;
    }

    public void setTies(@NonNull Integer ties) {
        this.ties = ties;
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "player_id")
    private int id;

    @NonNull
    @ColumnInfo(name = "player_name")
    private String name;

    @NonNull
    @ColumnInfo(name = "player_wins")
    private Integer wins;

    @NonNull
    @ColumnInfo(name = "player_losses")
    private Integer losses;

    @NonNull
    @ColumnInfo(name = "player_ties")
    private Integer ties;
}
