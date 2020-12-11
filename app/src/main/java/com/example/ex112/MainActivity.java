package com.example.ex112;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 *  * @author		Shahar Yani
 *  * @version  	1.0
 *  * @since		3/12/2020
 *
 *  * This MainActivity.class uses a SharedPreference file to display the user changes throughout
 *  his using the application. This class also has a menu to move to CreditsActivity.class
 *  */
public class MainActivity extends AppCompatActivity {

    EditText eT;
    TextView score;
    int counter;
    String input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eT = findViewById(R.id.eT);
        score = findViewById(R.id.score);

        SharedPreferences details = getSharedPreferences("PREFS_DETAILS",MODE_PRIVATE);
        counter = details.getInt("Score",0);
        score.setText(String.valueOf(counter));
        input = details.getString("Name","");
        if (input.equals("")) eT.setText("");
        else eT.setText(input);
    }

    public void exitActivity(View view) {
        input = eT.getText().toString();
        counter = Integer.parseInt(score.getText().toString());
        SharedPreferences details = getSharedPreferences("PREFS_DETAILS",MODE_PRIVATE);
        SharedPreferences.Editor editor=details.edit();
        editor.putInt("Score",counter);
        editor.putString("Name",input);
        editor.apply();
        finish();
    }

    public void resetCounter(View view) {
        counter = 0;
        eT.setText("");
        score.setText(String.valueOf(counter));
    }

    public void updateCounter(View view) {
        counter ++;
        score.setText(String.valueOf(counter));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String title = item.getTitle().toString();
        if (title.equals("Credits")){
            Intent si = new Intent(this,CreditsActivity.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }
}