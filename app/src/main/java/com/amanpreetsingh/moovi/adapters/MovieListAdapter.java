package com.amanpreetsingh.moovi.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amanpreetsingh.moovi.Constants;
import com.amanpreetsingh.moovi.HttpRequests;
import com.amanpreetsingh.moovi.Movie;
import com.amanpreetsingh.moovi.R;
import com.amanpreetsingh.moovi.RecyclerViewClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by amanpreetsingh on 23/06/16.
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    Context mContext;
    ArrayList<Movie> moviesList;
    RecyclerViewClickListener mClickListener;

    public MovieListAdapter (Context context, ArrayList<Movie> list, RecyclerViewClickListener listener){
        mContext = context;
        moviesList = list;
        mClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new ViewHolder(v, mClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Movie movie = moviesList.get(position);

//        holder.mMovieName.setText(movie.getTitle());
        holder.mMovieName.setText(getMovieNameYearSpan(movie));
        holder.rating.setText("" + movie.getVoteAverage());
        Picasso.with(mContext)
                .load(HttpRequests.getBackdropURL(movie.getBackdropPath(), Constants.PosterSizes.W342))
                .placeholder(R.drawable.image_placeholder)
                .into(holder.mMovieImage);

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RecyclerViewClickListener mClickListener;
        ImageView mMovieImage;
        TextView mMovieName;
        TextView rating;

        public ViewHolder(View view, RecyclerViewClickListener listener) {
            super(view);
            mClickListener = listener;
            view.setOnClickListener(this);
            mMovieImage = (ImageView) view.findViewById(R.id.background_image);
            mMovieName = (TextView) view.findViewById(R.id.name);
            rating = (TextView) view.findViewById(R.id.rating);
        }

        @Override
        public void onClick(View v) {
            mClickListener.onItemClicked(getLayoutPosition());
        }
    }

    public Spannable getMovieNameYearSpan (Movie movie){
        String str = movie.getTitle() + " (" + movie.getYear() + ")";
        Spannable span = new SpannableString(str);
        int spanStartIndex = movie.getTitle().length();
        int spanEndIndex = str.length();
        span.setSpan(new RelativeSizeSpan(0.55f), spanStartIndex, spanEndIndex, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        span.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_OPPOSITE), spanStartIndex, spanEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return span;
    }

}
