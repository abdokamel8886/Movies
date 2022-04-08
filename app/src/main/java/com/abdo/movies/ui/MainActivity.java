package com.abdo.movies.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.abdo.movies.R;
import com.abdo.movies.adapters.RecyclerMovieAdapter;
import com.abdo.movies.databinding.ActivityMainBinding;
import com.abdo.movies.helpers.Const;
import com.abdo.movies.models.MovieModel;
import com.abdo.movies.network.RetroConnection;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.frame.getId(),new MoviesFragment() ,"movies")
                .commit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }



}