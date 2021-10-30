package com.uc.moviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uc.moviedb.R;
import com.uc.moviedb.helper.Const;
import com.uc.moviedb.model.Credits;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder> {

    private Context context;
    private List<Credits.Cast> castList;

    private List<Credits.Cast> getCastList() {
        return castList;
    }

    public void setCastList(List<Credits.Cast> castList) {
        this.castList = castList;
    }

    public CastAdapter(Context context, List<Credits.Cast> cast) {
        this.context = context;
    }

    @NonNull
    @Override
    public CastAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie_cast, parent, false);
        return new CastAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CastAdapter.ViewHolder holder, int position) {

        Credits.Cast cast = castList.get(position);
        if (cast.getProfile_path() != null) {
            Glide.with(context).load(Const.IMG_URL + cast.getProfile_path()).into(holder.imageview_cast);
        }
        holder.card_name_cast.setText(cast.getName());
        holder.card_character_cast.setText(cast.getCharacter());
        holder.cardview_cast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, cast.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return getCastList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageview_cast;
        TextView card_name_cast;
        TextView card_character_cast;
        CardView cardview_cast;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageview_cast = itemView.findViewById(R.id.imageview_cast);
            card_name_cast = itemView.findViewById(R.id.card_name_cast);
            card_character_cast = itemView.findViewById(R.id.card_character_cast);
            cardview_cast = itemView.findViewById(R.id.cardview_cast);
        }
    }

}