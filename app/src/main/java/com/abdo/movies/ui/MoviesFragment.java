package com.abdo.movies.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdo.movies.R;
import com.abdo.movies.adapters.RecyclerMovieAdapter;
import com.abdo.movies.databinding.FragmentMoviesBinding;
import com.abdo.movies.helpers.Const;
import com.abdo.movies.models.MovieDetailsModel;
import com.abdo.movies.models.MovieModel;
import com.abdo.movies.models.SharedModel;
import com.abdo.movies.network.RetroConnection;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesFragment extends Fragment {

    FragmentMoviesBinding binding ;
    RecyclerMovieAdapter adapter = new RecyclerMovieAdapter();
    int resp =0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentMoviesBinding.bind(view);
        getData();
        onclicks();

    }

    private void getData(){
        RetroConnection.getServices().getAllMovies(Const.API_KEY).enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {

                adapter.setList((ArrayList<MovieModel.ResultsDTO>) response.body().getResults());
                binding.recyclerMovies.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame,new ErrorFragment())
                        .commit();

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void onclicks(){


        adapter.setOnItemClick(new RecyclerMovieAdapter.OnItemClick() {
            @Override
            @Nullable
            public void OnClick(int MovieId) {
                    requireActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame,new DetailsFragment(),"details")
                            .addToBackStack("details")
                            .commit();
                    SharedModel.set_id(MovieId);


            }
        });
    }


}