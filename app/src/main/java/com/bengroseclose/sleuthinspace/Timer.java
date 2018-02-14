/*
    Class: Timer
        Description: This class will be called when start is select on the MainActivity page.
        This will use a count down timer to display a count down of the time left in the game
        In the onCreate method I will call the CountDownTimer constructor. I have a method called
        determindTime that will be called within the creation of the CountDownTimer that will
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

    final TextView textView_mainCountDown_Timer =
            (TextView)findViewById(R.id.textView_countdown_timer);
    final TextView textView_openingCountDown_Timer =
            (TextView) findViewById(R.id.textView_opening_timer);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        opening();

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
        activity. Will make calls, to each of the open<pagename>() methods.
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

    /*
        Opens the Setting page
     */
    void openSettingPage() {
        Intent intent = new Intent(this, SettingsPage.class);
        startActivity(intent);
    }

    /*
        Opens the LeaderboardPage, 2/13/18 Leadersboard page is not developed yet.
     */
    void openLeaderboardPage(){
        Intent intent = new Intent(this, Leaderboard.class);
        startActivity(intent);
    }

    /*
        Opens the Home Page
     */
    void openHomePage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /*
        Using data from the settings page, determine the started time based of parameters from
        setting page. Should return only three different times.

            Time 1:
            Time 2:
            Time 3:

        Solution:
     */
    long determindTime()
    {
        return 1;
    }

    /*
        Overview: This is what will appear first on the screen when the user opens to the actual game. Will
        start a count down from 5 seconds and once it finishes a "space ship crashing" sound
        will fire. This will then file right into the start of the firstCountDown()

        Solution: Will use a CountDownTimer that will start from 5 seconds and proceed toward 0.
        Will also use the media player to, play the sound that can be found in the res/raw folder.
     */
    void opening()
    {
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.collisionsound);

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {
                int totalseconds = (int)l / 1000;

                /*
                    Would like to add an animation to fade in and fade out.
                    See AnimationListener and Android Documentation.
                 */
                textView_openingCountDown_Timer.setText(totalseconds);
            }

            @Override
            public void onFinish() {
                mediaPlayer.start();
            }
        }.start();
    }

    /*
        Will be the countDown portion for the first part of the game. After this is finished will
        fire the mid game portion and demand a player to attempt to solve.

        Solution: First create a variable associated with the textView element on the screen.
        Then create a new CountDownTimer, that has a duration based of what is returned from the
        determineTime() method. Every tick will update the textView element and when finished will
        exit.
     */
    void firstCountDown() {
        long countDownInterval = 1000;

        new CountDownTimer(6000000, 1000) {

            public void onTick(long millisUntilFinished) {
                int totalSeconds = (int) millisUntilFinished / 1000;
                int minutes = (totalSeconds / 60);
                int seconds = (totalSeconds % 60);

                textView_mainCountDown_Timer.setText(minutes + ":" + seconds);
            }

            public void onFinish() {
                textView_mainCountDown_Timer.setText("Times Up!");
            }
        }.start();
    }
}

