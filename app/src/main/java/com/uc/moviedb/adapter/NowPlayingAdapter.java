package com.uc.moviedb.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uc.moviedb.R;
import com.uc.moviedb.helper.Const;
import com.uc.moviedb.model.NowPlaying;
import com.uc.moviedb.view.activities.MovieDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.ViewHolder> {

    private Context context;
    private List<NowPlaying.Results> listNowPlaying;
    private List<NowPlaying.Results> getListNowPlaying() {
        return listNowPlaying;
    }
    public void setListNowPlaying(List<NowPlaying.Results> listNowPlaying) {
        this.listNowPlaying = listNowPlaying;
    }

    public NowPlayingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public NowPlayingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_now_playing, parent, false);
        return new NowPlayingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NowPlayingAdapter.ViewHolder holder, int position) {
        final NowPlaying.Results results = getListNowPlaying().get(position);
        holder.lbl_title_card_nowplaying.setText(results.getTitle());
        holder.lbl_overview_card_nowplaying.setText(results.getOverview());
        holder.lbl_releasedate_card_nowplaying.setText(results.getRelease_date());
        Glide.with(context).load(Const.IMG_URL + results.getPoster_path()).into(holder.img_poster_card_nowplaying);

//        holder.cardview_nowplaying.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, MovieDetailsActivity.class);
//                intent.putExtra("movie_id", "" + results.getId());
//                intent.putExtra("image", "" + results.getPoster_path());
//                intent.putExtra("title", "" + results.getTitle());
//                intent.putIntegerArrayListExtra("genre_ids", (ArrayList<Integer>) results.getGenre_ids());
//                intent.putExtra("release_date", "" + results.getRelease_date());
//                intent.putExtra("overview", "" + results.getOverview());
//                context.startActivity(intent);
//
//                Bundle bundle = new Bundle();
//                bundle.putString("movieId", "" + results.getId());
//                Navigation.findNavController(view).navigate(R.id.action_nowPlayingFragment_to_movieDetailsFragment, bundle);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return getListNowPlaying().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_poster_card_nowplaying;
        TextView lbl_title_card_nowplaying;
        TextView lbl_overview_card_nowplaying;
        TextView lbl_releasedate_card_nowplaying;
        CardView cardview_nowplaying;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_poster_card_nowplaying = itemView.findViewById(R.id.img_poster_card_nowplaying);
            lbl_title_card_nowplaying = itemView.findViewById(R.id.lbl_title_card_nowplaying);
            lbl_overview_card_nowplaying = itemView.findViewById(R.id.lbl_overview_card_nowplaying);
            lbl_releasedate_card_nowplaying = itemView.findViewById(R.id.lbl_releasedate_card_nowplaying);
            cardview_nowplaying = itemView.findViewById(R.id.cardview_nowplaying);
        }
    }
}
