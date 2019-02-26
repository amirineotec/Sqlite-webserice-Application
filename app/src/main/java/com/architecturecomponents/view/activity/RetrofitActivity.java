package com.architecturecomponents.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.architecturecomponents.R;
import com.architecturecomponents.data.remote.retrofit.APIService;
import com.architecturecomponents.data.remote.retrofit.RetroClass;
import com.architecturecomponents.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends AppCompatActivity {

    private TextView txtContenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        txtContenu = findViewById(R.id.txtContenu);
        setTitle("RetrofitActivity");
        getMovies();
    }

    private void getMovies() {
        long startTime = System.currentTimeMillis();
        APIService apiService = RetroClass.getAPIService("http://www.mocky.io");
        String id = "5c6acf9d3300009f3c7f4d4e";
        Call<List<Movie>> configurationResponse = apiService.getMovies(id);
        configurationResponse.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                try {
                    Log.d("onResponse", call.request().url().toString());
                    Log.d("onResponse", response.body().toString());
                    showMovies(response.body());
                    long endTime = System.currentTimeMillis();
                    System.out.println("Retrofit " + (endTime - startTime));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable error) {
                Log.d("onResponse", error.getMessage().toString());
            }
        });
    }

    public void showMovies(List<Movie> movies) {
        for (Movie movie : movies)
            txtContenu.append(movie.getId() + " " + movie.getName() + "\n");
    }
}
