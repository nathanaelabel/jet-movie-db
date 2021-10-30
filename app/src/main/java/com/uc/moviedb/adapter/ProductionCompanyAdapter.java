package com.uc.moviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uc.moviedb.R;
import com.uc.moviedb.helper.Const;
import com.uc.moviedb.model.Movies;

import java.util.List;

public class ProductionCompanyAdapter extends RecyclerView.Adapter<ProductionCompanyAdapter.ViewHolder> {

    private Context context;
    private List<Movies.ProductionCompanies> productionCompaniesList;

    private List<Movies.ProductionCompanies> getProductionCompaniesList() {
        return productionCompaniesList;
    }

    public void setProductionCompaniesList(List<Movies.ProductionCompanies> productionCompaniesList) {
        this.productionCompaniesList = productionCompaniesList;
    }

    public ProductionCompanyAdapter(Context context, List<Movies.ProductionCompanies> productionCompanies) {
        this.context = context;
    }

    @NonNull
    @Override
    public ProductionCompanyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_production_company, parent, false);
        return new ProductionCompanyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductionCompanyAdapter.ViewHolder holder, int position) {
        Movies.ProductionCompanies productionCompany = productionCompaniesList.get(position);
        if (productionCompany.getLogo_path() != null) {
            Glide.with(context).load(Const.IMG_URL + productionCompany.getLogo_path()).into(holder.imageview_production_company);
        }
        holder.cardview_production_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, productionCompany.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productionCompaniesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageview_production_company;
        CardView cardview_production_company;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageview_production_company = itemView.findViewById(R.id.imageview_production_company);
            cardview_production_company = itemView.findViewById(R.id.cardview_production_company);
        }
    }
}
