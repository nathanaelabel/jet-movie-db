package com.uc.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uc.moviedb.R;
import com.uc.moviedb.helper.Const;
import com.uc.moviedb.model.Genre;
import com.uc.moviedb.model.NowPlaying;
import com.uc.moviedb.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity {

    private ImageView img_poster_movie_details;
    private TextView lbl_movieId_details
                , lbl_title_movie_details
                , lbl_genre_movie_details
                , lbl_releasedate_movie_details
                , lbl_overview_movie_details;
    private String movie_id = ""
                , image = ""
                , title = ""
                , release_date = ""
                , overview = "";

    private ArrayList<Integer> listGenres;
    private MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        setTitle("Detail Movie");
        Intent intent = getIntent();
        movie_id = intent.getStringExtra("movie_id");
        image = intent.getStringExtra("image");
        title = intent.getStringExtra("title");
        listGenres = intent.getIntegerArrayListExtra("genre_ids");
        release_date = intent.getStringExtra("release_date");
        overview = intent.getStringExtra("overview");

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        lbl_movieId_details = findViewById(R.id.lbl_movieId_details);
        img_poster_movie_details = findViewById(R.id.img_poster_movie_details);
        lbl_title_movie_details = findViewById(R.id.lbl_title_movie_details);
        lbl_genre_movie_details = findViewById(R.id.lbl_genre_movie_details);
        lbl_releasedate_movie_details = findViewById(R.id.lbl_releasedate_movie_details);
        lbl_overview_movie_details= findViewById(R.id.lbl_overview_movie_details);

        lbl_movieId_details.setText(movie_id);
        Glide.with(this).load(Const.IMG_URL + image).into(img_poster_movie_details);
        lbl_title_movie_details.setText(title);
        lbl_releasedate_movie_details.setText(release_date);
        lbl_overview_movie_details.setText(overview);

        movieViewModel.getGenres(listGenres);
        movieViewModel.getResultGetGenres().observe(this, new Observer<List<Genre.Genres>>() {
            @Override
            public void onChanged(List<Genre.Genres> genres) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < genres.size(); i++) {
                    if (i != genres.size() - 1) {
                        stringBuilder.append(genres.get(i).getName() + ", ");
                    } else {
                        stringBuilder.append(genres.get(i).getName());
                    }
                }

//                lbl_genre_movie_details.setText(listGenres);
                lbl_genre_movie_details.setText(stringBuilder);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
