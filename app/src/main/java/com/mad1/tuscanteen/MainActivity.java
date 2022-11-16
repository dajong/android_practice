package com.mad1.tuscanteen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mad1.tuscanteen.Contents.FoodSectionUtils;

public class MainActivity extends AppCompatActivity {
    // Default layout is one pane, not two.
    private boolean mTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the toolbar as the app bar.
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setTitle(getTitle());

        // Get the song list as a RecyclerView.
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setAdapter(new FoodSectionRecyclerViewAdapter(FoodSectionUtils.ITEMS));

        // Is the container layout available? If so, set mTwoPane to true.
//        if (findViewById(R.id.song_detail_container) != null) {
//            mTwoPane = true;
//        }
    }
}