package com.uc.moviedb.view.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.uc.moviedb.R;
import com.uc.moviedb.adapter.CastAdapter;
import com.uc.moviedb.adapter.CrewAdapter;
import com.uc.moviedb.adapter.ProductionCompanyAdapter;
import com.uc.moviedb.helper.Const;
import com.uc.moviedb.model.Credits;
import com.uc.moviedb.model.Movies;
import com.uc.moviedb.viewmodel.MovieViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieDetailsFragment newInstance(String param1, String param2) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            progressDialog = ProgressDialog.show(getActivity(), "", "Please Wait...", true);
//            progressDialog = ProgressDialog.show(getActivity(), .getRootView().getContext()).inflate(R.layout.custom_loading, null);
            progressDialog.show();
        }
    }

    private TextView lbl_movie_id, lbl_title, lbl_tagline, lbl_popularity, lbl_vote,
            lbl_releasedate, lbl_genre, lbl_overview;
    private ImageView img_backdrop, img_poster, img_company;
    private MovieViewModel viewModel;
    private RecyclerView rv_cast, rv_crew, rv_productionCompany;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        lbl_title = view.findViewById(R.id.lbl_title_movie_details);
        lbl_tagline = view.findViewById(R.id.lbl_tagline_movie_details);
        lbl_popularity = view.findViewById(R.id.lbl_popularity_movie_details);
        lbl_vote = view.findViewById(R.id.lbl_rating_movie_details);
        lbl_releasedate = view.findViewById(R.id.lbl_releasedate_movie_details);
        lbl_genre = view.findViewById(R.id.lbl_genre_movie_details);
        lbl_overview = view.findViewById(R.id.lbl_overview_movie_details);
        img_backdrop = view.findViewById(R.id.img_backdrop_movie_details);
        img_poster = view.findViewById(R.id.img_poster_movie_details);
        rv_cast = view.findViewById(R.id.rv_cast_movie_details);
        rv_crew = view.findViewById(R.id.rv_crew_movie_details);
        rv_productionCompany = view.findViewById(R.id.rv_production_company_movie_details);
        viewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        String movieId = getArguments().getString("movieId");
        viewModel.getMovieById(movieId);
        viewModel.getResultGetMovieById().observe(getActivity(), showResultMovie);
        viewModel.getCredits(movieId);
        viewModel.getResultGetCredits().observe(getActivity(), showResultCredits);
//      lbl_movie_id.setText(movieId);
        return view;
    }

    private final Observer<Movies> showResultMovie = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {

            lbl_title.setText(movies.getTitle());
            lbl_tagline.setText(movies.getTagline());
            lbl_popularity.setText(String.valueOf(movies.getPopularity()));
            String vote = movies.getVote_average() + " (" + movies.getVote_count() + ")";
            lbl_vote.setText(vote);
            String releaseDateRunTime = movies.getRelease_date() + "  •  " + movies.getRuntime() + "m";
            lbl_releasedate.setText(releaseDateRunTime);
            lbl_overview.setText(movies.getOverview());

            String poster_path = Const.IMG_URL + movies.getPoster_path().toString();
            Glide.with(getActivity()).load(poster_path).into(img_poster);
            String backdrop_path = Const.IMG_URL + movies.getBackdrop_path().toString();
            Glide.with(getActivity()).load(backdrop_path).into(img_backdrop);

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < movies.getGenres().size(); i++) {
                if (i != movies.getGenres().size() - 1) {
                    stringBuilder.append(movies.getGenres().get(i).getName() + ", ");
                } else {
                    stringBuilder.append(movies.getGenres().get(i).getName());
                }
            }
            lbl_genre.setText(stringBuilder);

            ProductionCompanyAdapter adapter = new ProductionCompanyAdapter(getActivity(), movies.getProduction_companies());
            adapter.setProductionCompaniesList(movies.getProduction_companies());
            rv_productionCompany.setAdapter(adapter);
            rv_productionCompany.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            progressDialog.dismiss();
        }
    };

    private Observer<Credits> showResultCredits = new Observer<Credits>() {
        @Override
        public void onChanged(Credits credits) {
            CastAdapter adapterCast = new CastAdapter(getActivity(), credits.getCast());
            adapterCast.setCastList(credits.getCast());
            rv_cast.setAdapter(adapterCast);
            rv_cast.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

            CrewAdapter adapterCrew = new CrewAdapter(getActivity(), credits.getCrew());
            adapterCrew.setCrewList(credits.getCrew());
            rv_crew.setAdapter(adapterCrew);
            rv_crew.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        }
    };

}