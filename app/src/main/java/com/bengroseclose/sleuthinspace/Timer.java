/*
    Class: Timer
        Description: This class will be called when start is select on the MainActivity page.
        This will use a count down timer to display a count down of the time left in the game
        In the onCreate method I will call the CountDownTimer constructor. I have a method called
        determindSeconds that will be called within the creation of the CountDownTimer that will
        return different value of type long. This value will be base of information receive from
        SettingsPage.
 */

package com.bengroseclose.sleuthinspace;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class Timer extends AppCompatActivity {

    private int part = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        final TextView textView_countdown_timer = (TextView)findViewById(R.id.textView_countdown_timer);
        long countDownInterval = 1000;

        //MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.collisionsound);
       // mediaPlayer.start();

        /*
            This is the CountDownTimer, within it there is a onTick class that will display the new
            time remaining in the game. Additionally this will call method that contain different
            events that are set to occur at different times of the game.
         */
        new CountDownTimer(determindTime(), 1000) {


            public void onTick(long millisUntilFinished) {
                int totalSeconds = (int)millisUntilFinished / 1000;
                int minutes = (totalSeconds / 60);
                int seconds = (totalSeconds % 60);



                if(totalSeconds == 60){
                    // event occurs
                }

                textView_countdown_timer.setText(minutes + ":" + seconds);
            }

            public void onFinish() {
                textView_countdown_timer.setText("Done!");
            }
        }.start();

    }
    /*
        Simply creates the menu bar at the top of the screen
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_bar, menu);
        return true;
    }

    /*
        Allows the user to select on of the icons in the app bar and be moved to a different
        activity.
    */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                openSettingPage();
                return true;

            case R.id.action_leaderboard:

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

    // Used in onOptionItemSelect to call the SettingsPage
    void openSettingPage() {
        Intent intent = new Intent(this, SettingsPage.class);
        startActivity(intent);
    }

    // Used in onOptionItemSelect to call the LeaderboardPage
    void openLeaderboardPage(){
        Intent intent = new Intent(this, Leaderboard.class);
        startActivity(intent);
    }

    // Used in onOptionItemSelect to call the HomePage
    void openHomePage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // This will take into consideration the items from the settings page somehow.
    long determindTime(){
        // This should also determine the starting voice from the 'Computer'
        // facture in settings but for now returns 15 mins for part 1 then 20 mins in part 2
        if(part == 1){
            return 900000;
        }
        else{
            return 1200000;
        }
    }

}

