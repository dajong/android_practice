package com.mad1.playerapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mad1.playerapp.data.Player;

import java.util.ArrayList;

public class PlayerDB {
    // database constants
    public static final String DB_NAME = "player.db";
    public static final int DB_VERSION = 1;

    // task table constants
    public static final String PLAYER_TABLE = "player";

    public static final String PLAYER_ID = "player_id";
    public static final int PLAYER_ID_COL = 0;

    public static final String PLAYER_NAME = "name";
    public static final int PLAYER_NAME_COL = 1;

    public static final String PLAYER_WINS = "wins";
    public static final int PLAYER_WINS_COL = 2;

    public static final String PLAYER_LOSSES = "losses";
    public static final int PLAYER_LOSSES_COL = 3;

    public static final String PLAYER_TIES = "ties";
    public static final int PLAYER_TIES_COL = 4;

    // Creating table
    public static final String CREATE_PLAYER_TABLE =
            "CREATE TABLE " + PLAYER_TABLE + " (" +
                    PLAYER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PLAYER_NAME + " TEXT NOT NULL, " +
                    PLAYER_WINS + " TEXT, " +
                    PLAYER_LOSSES + " TEXT, " +
                    PLAYER_TIES + " TEXT);";

    // Drop Table
    public static final String DROP_PLAYER_TABLE =
            "DROP TABLE IF EXISTS " + PLAYER_TABLE;

    private static class DBHelper extends SQLiteOpenHelper {


        public DBHelper(Context context, String name,
                        SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_PLAYER_TABLE);
            // insert default lists
            db.execSQL("INSERT INTO player VALUES (1, 'Ray', 0, 0, 0)");
            db.execSQL("INSERT INTO player VALUES (2, 'Joel', 0, 0, 0)");
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.d("Players", "Upgrading db from version "
                    + oldVersion + "to " + newVersion);
            db.execSQL(PlayerDB.DROP_PLAYER_TABLE);
            onCreate(db);
        }
    }

    // database object and database helper object
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    // constructor
    public PlayerDB(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    // private methods
    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void openWriteableDB() {
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB() {
        if (db != null)
            db.close();
    }

    public ArrayList<Player> getPlayers() {
        this.openReadableDB();
        Cursor cursor = db.query(PLAYER_TABLE, null,
                null, null,
                null, null, null);
        ArrayList<Player> players = new ArrayList<Player>();
        while (cursor.moveToNext()) {
            players.add(getPlayerFromCursor(cursor));
        }
        if (cursor != null)
            cursor.close();
        this.closeDB();
        return players;
    }



    public Player getPlayer(int id) {
        String where = PLAYER_ID + "= ?";
        String[] whereArgs = { Integer.toString(id) };
        this.openReadableDB();
        Cursor cursor = db.query(PLAYER_TABLE,
                null, where, whereArgs, null, null, null);
        cursor.moveToFirst();
        Player player = getPlayerFromCursor(cursor);
        if (cursor != null)
            cursor.close();
        this.closeDB();
        return player;
    }

    private static Player getPlayerFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0){
            return null;
        }
        else {
            try {
                Player player = new Player(
                        cursor.getInt(PLAYER_ID_COL),
                        cursor.getString(PLAYER_NAME_COL),
                        cursor.getInt(PLAYER_WINS_COL),
                        cursor.getInt(PLAYER_LOSSES_COL),
                        cursor.getInt(PLAYER_TIES_COL));
                return player;
            }
            catch(Exception e) {
                return null;
            }
        }
    }

    public long insertPlayer(Player player) {
        ContentValues cv = new ContentValues();
        cv.put(PLAYER_ID, player.getPlayer_id());
        cv.put(PLAYER_NAME, player.getName());
        cv.put(PLAYER_WINS, player.getWins());
        cv.put(PLAYER_LOSSES, player.getLosses());
        cv.put(PLAYER_TIES, player.getTies());
        this.openWriteableDB();
        long rowID = db.insert(PLAYER_TABLE, null, cv);
        this.closeDB();
        return rowID;
    }

    public int updateTask(Player player) {
        ContentValues cv = new ContentValues();
        cv.put(PLAYER_ID, player.getPlayer_id());
        cv.put(PLAYER_NAME, player.getName());
        cv.put(PLAYER_WINS, player.getWins());
        cv.put(PLAYER_LOSSES, player.getLosses());
        cv.put(PLAYER_TIES, player.getTies());
        String where = PLAYER_ID + "= ?";
        String[] whereArgs = { String.valueOf(player.getPlayer_id()) };
        this.openWriteableDB();
        int rowCount = db.update(
                PLAYER_TABLE, cv, where,whereArgs);
        this.closeDB();
        return rowCount;
    }

}
