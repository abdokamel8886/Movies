package com.abdo.movies.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdo.movies.R;
import com.abdo.movies.databinding.FragmentErrorBinding;
import com.abdo.movies.databinding.FragmentErrorDetailsBinding;
import com.abdo.movies.helpers.Const;
import com.abdo.movies.models.MovieModel;
import com.abdo.movies.network.RetroConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ErrorDetailsFragment extends Fragment {

    FragmentErrorDetailsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_error_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentErrorDetailsBinding.bind(view);
        onclicks();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null ;
    }

    private void Validate(){
        RetroConnection.getServices().getAllMovies(Const.API_KEY).enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame,new DetailsFragment())
                        .commit();

            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });
    }

    private void onclicks(){
        binding.detReBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validate();
            }
        });
    }

}