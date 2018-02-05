package com.bengroseclose.sleuthinspace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SettingsPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Spinner spinner_number_of_players = (Spinner) findViewById(R.id.spinner_number_of_players);
        ArrayAdapter<CharSequence> adapter_number_of_players = ArrayAdapter.createFromResource(this,
                R.array.mode_array, R.layout.spinner_item);
        adapter_number_of_players.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_number_of_players.setAdapter(adapter_number_of_players);


        Spinner spinner_mode = (Spinner) findViewById(R.id.spinner_mode);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.mode_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_mode.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:

                return true;

            case R.id.action_leaderboard:
                //openLeaderboardPage();
                return true;
            case R.id.action_home:
                openHomePage();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    void openSettingPage() {
        Intent intent = new Intent(this, SettingsPage.class);
        startActivity(intent);
    }

    void openLeaderboardPage(){
        Intent intent = new Intent(this, Leaderboard.class);
        startActivity(intent);
    }

    void openHomePage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
