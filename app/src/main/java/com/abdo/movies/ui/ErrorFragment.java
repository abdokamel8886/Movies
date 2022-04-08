package com.abdo.movies.ui;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdo.movies.R;
import com.abdo.movies.databinding.FragmentErrorBinding;
import com.abdo.movies.helpers.Const;
import com.abdo.movies.models.MovieModel;
import com.abdo.movies.network.RetroConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ErrorFragment extends Fragment {

    FragmentErrorBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_error, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentErrorBinding.bind(view);
        onclicks();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null ;
    }



    private void onclicks(){
        binding.reBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame,new MoviesFragment() ,"movies")
                        .commit();
            }
        });
    }


}