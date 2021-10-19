package com.uc.moviedb.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.uc.moviedb.R;
import com.uc.moviedb.adapter.NowPlayingAdapter;
import com.uc.moviedb.model.NowPlaying;
import com.uc.moviedb.viewmodel.MovieViewModel;

public class NowPlayingActivity extends AppCompatActivity {

    private RecyclerView rv_nowplaying;
    private MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);

        setTitle("Now Playing");
        rv_nowplaying = findViewById(R.id.rv_nowplaying);
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        movieViewModel.getNowPlaying();
        movieViewModel.getResultNowPlaying().observe(this, new Observer<NowPlaying>() {
            @Override
            public void onChanged(NowPlaying nowPlaying) {
                NowPlayingAdapter adapter = new NowPlayingAdapter(NowPlayingActivity.this);
                rv_nowplaying.setLayoutManager(new LinearLayoutManager(NowPlayingActivity.this));
                rv_nowplaying.setAdapter(adapter);
                adapter.setListNowPlaying(nowPlaying.getResults());
            }
        });

    }
}