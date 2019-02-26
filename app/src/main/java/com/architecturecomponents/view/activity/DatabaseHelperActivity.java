package com.architecturecomponents.view.activity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.architecturecomponents.R;
import com.architecturecomponents.data.db.v1.DBManager;
import com.architecturecomponents.model.Movie;

import java.util.List;

public class DatabaseHelperActivity extends AppCompatActivity {
    private DBManager dbManager;
    private EditText edtName;
    private TextView txtContenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_helper);
        setTitle("DatabaseHelperActivity");
        edtName = findViewById(R.id.edtName);
        txtContenu = findViewById(R.id.txtContenu);
        dbManager = new DBManager(this);
        dbManager.open();
        showMovies();
    }

    public void addMovie(View view) {
        if (!TextUtils.isEmpty(edtName.getText())) {
            dbManager.insert(edtName.getText().toString());
            edtName.setText("");
            showMovies();
        } else {
            Toast.makeText(view.getContext(), "Merci de saisir le nom du film", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteMovies(View view) {
        dbManager.deleteAll();
        showMovies();
    }

    public void showMovies() {
        Cursor moviesCursor = dbManager.fetch();
        String desc = "";

        for (moviesCursor.moveToFirst(); !moviesCursor.isAfterLast(); moviesCursor.moveToNext()) {
            desc += moviesCursor.getString(0) + " " + moviesCursor.getString(1) + "\n";

        }
        txtContenu.setText(desc);
    }

}
