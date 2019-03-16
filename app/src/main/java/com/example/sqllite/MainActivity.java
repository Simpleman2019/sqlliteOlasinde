package com.example.sqllite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("PatientMedicalReport", MODE_PRIVATE, null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS patientData (name VARCHAR, age INT(3), gender VARCHAR, id INTEGER PRIMARY KEY)");
            sqLiteDatabase.execSQL("INSERT INTO PatientData (name, age, gender) VALUES ('Dave',42, 'Male')");
            sqLiteDatabase.execSQL("INSERT INTO PatientData (name, age, gender) VALUES ('Janet',27, 'Female')");
            sqLiteDatabase.execSQL("INSERT INTO PatientData (name, age, gender) VALUES ('Chidi',6, 'Male')");
            sqLiteDatabase.execSQL("INSERT INTO PatientData (name, age, gender) VALUES ('Adams',10, 'Male')");

//            sqLiteDatabase.execSQL("DELETE FROM theNewUsers WHERE id = 2");

            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM PatientData", null);
            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");
            int genderIndex = c.getColumnIndex("gender");
            int idIndex = c.getColumnIndex("id");
            c.moveToFirst();

            while (c != null) {
                Log.i("PatientResults - name", c.getString(nameIndex));
                Log.i("PatientResults - age", Integer.toString(c.getInt(ageIndex)));
                Log.i("PatientResults - gender", Integer.toString(c.getInt(genderIndex)));
                Log.i("PatientResults - id", Integer.toString(c.getInt(idIndex)));

                c.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
