package com.architecturecomponents.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.architecturecomponents.R;
import com.architecturecomponents.data.db.v2.repositories.MovieRepository;
import com.architecturecomponents.model.Movie;

import java.util.List;

public class RoomActivity extends AppCompatActivity {
    MovieRepository movieRepository;
    EditText edtName;
    TextView txtContenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        setTitle("RoomActivity");
        edtName = findViewById(R.id.edtName);
        txtContenu = findViewById(R.id.txtContenu);
        movieRepository = new MovieRepository(getBaseContext());
        this.showMovies();

    }



    public void showMovies() {
        List<Movie> movies = movieRepository.getAll();
        String desc = "";

        for (Movie movie : movies)
            desc += movie.getId() + " " + movie.getName() + "\n";

        txtContenu.setText(desc);
    }

    public void addMovie(View view) {
        if (!TextUtils.isEmpty(edtName.getText())) {
            Movie movie = new Movie();
            movie.setName(edtName.getText().toString());
            movieRepository.insert(movie);
            edtName.setText("");
            showMovies();
        } else {
            Toast.makeText(view.getContext(), "Merci de saisir le nom du film", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteMovies(View view) {
        movieRepository.deleteAll();
        showMovies();
    }
}
