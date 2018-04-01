package com.personal.dominykasviecas.popularmovies.feature.landing;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.personal.dominykasviecas.popularmovies.App;
import com.personal.dominykasviecas.popularmovies.BuildConfig;
import com.personal.dominykasviecas.popularmovies.R;
import com.personal.dominykasviecas.popularmovies.data.movie.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

class MovieViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.image_grid_main_item)
    ImageView image;

    @NonNull
    private Context context;

    MovieViewHolder(@NonNull Context context, @NonNull View itemView) {
        super(itemView);

        this.context = context;
        ButterKnife.bind(this, itemView);
    }

    void bind(@NonNull Movie movie) {
        Picasso.with(context).load(BuildConfig.TmdbImageUrl + movie.getPosterPath()).into(image);
    }
}