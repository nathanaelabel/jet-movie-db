package com.uc.moviedb.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import com.uc.moviedb.R;
import com.uc.moviedb.helper.Const;
import com.uc.moviedb.model.Movies;
import com.uc.moviedb.viewmodel.MovieViewModel;

public class MainActivity extends AppCompatActivity {

    private MovieViewModel movieViewModel;
    private Button btnHit_main;
    private TextInputLayout til_movieId_main;
    private TextView txt_show_main;
    private ImageView til_imagePoster_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
    }

    private void setListener() {
        btnHit_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String movieId = til_movieId_main.getEditText().getText().toString().trim();

                if (movieId.isEmpty()) {
                    til_movieId_main.setError("Please fill this field!");
                } else {
                    til_movieId_main.setError(null);
                    movieViewModel.getMovieById(movieId);
                    movieViewModel.getResultGetMovieById().observe(MainActivity.this, showResultMovie);
                }
//                movieViewModel.getMovieById("350");
//                movieViewModel.getResultGetMovieById().observe(MainActivity.this, showResultMovie);
            }
        });
    }

    private Observer<Movies> showResultMovie = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {

            if (movies == null) {
                txt_show_main.setText("Movie ID is not available in MovieDB!");
            } else {
                String title = movies.getTitle();
                String image_path = movies.getPoster_path().toString();
                String full_path = Const.IMG_URL + image_path;
                Glide.with(MainActivity.this).load(full_path).into(til_imagePoster_main);
                txt_show_main.setText(title);
            }
        }
    };

    private void initView() {
        movieViewModel = new ViewModelProvider(MainActivity.this).get(MovieViewModel.class);
        btnHit_main = findViewById(R.id.btnHit_main);
        til_movieId_main = findViewById(R.id.til_movieId_main);
        txt_show_main = findViewById(R.id.txt_show_main);
        til_imagePoster_main = findViewById(R.id.til_imagePoster_main);
    }
}