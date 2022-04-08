package com.abdo.movies.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abdo.movies.R;
import com.abdo.movies.helpers.Const;
import com.abdo.movies.models.MovieModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerMovieAdapter extends RecyclerView.Adapter<RecyclerMovieAdapter.Holder> {


    private ArrayList<MovieModel.ResultsDTO> list;
    private OnItemClick onItemClick ;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setList(ArrayList<MovieModel.ResultsDTO> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie,parent,false);
        return new Holder(view);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0:list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        String url = Const.IMAGE_URL+list.get(position).getPosterPath();

        holder.MovieName.setText(list.get(position).getTitle());

        Glide.with(holder.itemView.getContext())
                .load(url)
                .into(holder.MovieImage);

    }

    class Holder extends RecyclerView.ViewHolder{

        ImageView MovieImage;
        TextView  MovieName;

        public Holder(@NonNull View itemView) {
            super(itemView);
            MovieImage = itemView.findViewById(R.id.image_movie);
            MovieName  = itemView.findViewById(R.id.movie_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClick != null){
                        onItemClick.OnClick(list.get(getLayoutPosition()).getId());
                    }
                }
            });



        }
    }

   public interface OnItemClick{

        void OnClick(int MovieId);

    }

}
