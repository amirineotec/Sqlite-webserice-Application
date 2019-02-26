package com.architecturecomponents.data.db.v1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    //todo paramétre genéral

    // Database Information
    static final String DB_NAME = "MOVIES-DB-V1.DB";

    // version de la base de donnée
    static final int DB_VERSION = 1;


    //todo création de la table movie
    // Nom de la table
    public static final String TABLE_NAME = "Movie";

    // Nom des colonnes
    public static final String ID = "id";
    public static final String NAME = "name";


    // Requete pour la création de la table
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL);";




    //todo Constructeur dataBaseHelper
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}