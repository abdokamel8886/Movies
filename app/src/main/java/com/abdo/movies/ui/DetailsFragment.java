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
import com.abdo.movies.databinding.FragmentDetailsBinding;
import com.abdo.movies.helpers.Const;
import com.abdo.movies.models.MovieDetailsModel;
import com.abdo.movies.models.MovieModel;
import com.abdo.movies.models.SharedModel;
import com.abdo.movies.network.RetroConnection;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsFragment extends Fragment {

    FragmentDetailsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentDetailsBinding.bind(view);
        getDetails(SharedModel.get_id());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null ;
    }

    private void getDetails(int id){
        RetroConnection.getServices().getMovieDetails(id,Const.API_KEY).enqueue(new Callback<MovieDetailsModel>() {
            @Override
            public void onResponse(Call<MovieDetailsModel> call, Response<MovieDetailsModel> response) {
                initView(response.body());

            }

            @Override
            public void onFailure(Call<MovieDetailsModel> call, Throwable t) {
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame,new ErrorDetailsFragment())
                        .commit();

            }
        });
    }

    private void initView(MovieDetailsModel model){
        binding.detName.setText(model.getTitle());
        binding.detDescription.setText(model.getOverview());
        binding.detLang.setText(model.getOriginal_language());
        binding.detDate.setText(model.getRelease_date());
        binding.detVotAvg.setText(""+model.getVote_average());
        binding.detVotCount.setText(""+model.getVote_count());

        if(model.getBackdrop_path() == null){
            Glide.with(this).load(Const.IMAGE_URL+model.getPoster_path()).into(binding.detImage);
        }
        else {
            Glide.with(this).load(Const.IMAGE_URL+model.getBackdrop_path()).into(binding.detImage);
        }


    }
}