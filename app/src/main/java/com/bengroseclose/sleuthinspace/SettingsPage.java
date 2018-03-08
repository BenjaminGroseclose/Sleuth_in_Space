/*
    This Setting Page will be opened before the game begins and ask the user to enter some
    required information. How many players, the level of difficulty {Easy, Medium, Hard}, then
    hopefully implement a QR code scanner what may be attached to each of the cards.
    Will prompt the user to use the QR code scanner or there will be a manual entry option.

    Either way there will be the same start button found at the bottom of the page that will
    offically begin the game.
 */

package com.bengroseclose.sleuthinspace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SettingsPage extends AppCompatActivity {

    public static final String EXTRA_MODE = "com.bengroseclose.sleuthinspace.MESSAGE";
    public static final String EXTRA_NUMBEROFPLAYERS = "com.bengroseclose.sleuthinspace.MESSAGE";

    int numberOfPlayers;
    String mode;
    Spinner spinner_number_of_players;
    Spinner spinner_mode;
    Button button_startgame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        spinner_number_of_players = (Spinner) findViewById(R.id.spinner_number_of_players);
        spinner_mode = (Spinner) findViewById(R.id.spinner_mode);
        button_startgame = (Button) findViewById(R.id.button_startgame);

        initPage();

        button_startgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });

    }

    /*
        This function will be called when the user presses the start button. Will take the given
        values from the game mode spinner and the number of players spinner. Save those in an
        intent then start the game activity.
     */
    void startGame()
    {

        //int TYPE = Integer.parseInt(userInputSpinner.getSelectedItem().toString());
        numberOfPlayers = Integer.parseInt(spinner_number_of_players.getSelectedItem().toString());
        mode = spinner_mode.getSelectedItem().toString();

        Log.d("mode", mode);
        Log.d("number", String.valueOf(numberOfPlayers));

        Intent intent = new Intent(SettingsPage.this, Game.class);

        intent.putExtra("com.bengroseclose.sleuthinspace.mode", mode);
        intent.putExtra("com.bengroseclose.sleuthinsapce.players", numberOfPlayers);
        startActivity(intent);

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

    void initPage(){

        ArrayAdapter<CharSequence> adapter_number_of_players = ArrayAdapter.createFromResource(this,
                R.array.number_of_players_array, R.layout.spinner_item);
        adapter_number_of_players.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_number_of_players.setAdapter(adapter_number_of_players);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.mode_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_mode.setAdapter(adapter);

    }
}
