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

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.ViewHolder> {

    Context context;
    private List<Credits.Crew> crewList;

    private List<Credits.Crew> getCrewList() {
        return crewList;
    }

    public void setCrewList(List<Credits.Crew> crewList) {
        this.crewList = crewList;
    }

    public CrewAdapter(Context context, List<Credits.Crew> crew) {
        this.context = context;
    }

    @NonNull
    @Override
    public CrewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie_crew, parent, false);
        return new CrewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CrewAdapter.ViewHolder holder, int position) {

        Credits.Crew crew = crewList.get(position);
        if (crew.getProfile_path() != null) {
            Glide.with(context).load(Const.IMG_URL + crew.getProfile_path()).into(holder.imageview_crew);
        }
        holder.card_name_crew.setText(crew.getName());
        holder.card_job_crew.setText(crew.getJob());
        holder.cardview_crew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, crew.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return crewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageview_crew;
        TextView card_name_crew;
        TextView card_job_crew;
        CardView cardview_crew;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageview_crew = itemView.findViewById(R.id.imageview_crew);
            card_name_crew = itemView.findViewById(R.id.card_name_crew);
            card_job_crew = itemView.findViewById(R.id.card_job_crew);
            cardview_crew = itemView.findViewById(R.id.cardview_crew);
        }
    }

}