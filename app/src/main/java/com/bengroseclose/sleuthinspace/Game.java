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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends AppCompatActivity {

    String mode;
    int numberOfPlayers;
    int gameTime;
    TextView textViewTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        textViewTimer = (TextView)findViewById(R.id.textView_timer);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if(extras == null){
            Log.d("Error:", "Extras return null. ");
        }
        else {
            mode = extras.getString("com.bengroseclose.sleuthinspace.mode");
            numberOfPlayers = extras.getInt("com.bengroseclose.sleuthinsapce.players");
        }

        beginTheGame();

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

        Players     Hard     Medium     Easy
            3       5/10      10/10     15/10
            4       5/10      10/10     15/10
            5       10/15     15/15     20/15
            6       10/15     15/15     20/15
            7       10/15     15/15     20/15

    Solution: Using a if statement to determine the given players and then use and then use
    a switch statement inside of that to return the given values. This will return an int.
    The numbers return will be correspond to a different, value on the outside that will control
    the flow. Chose this because there are only at max 6 different return values.
 */
    int determindTime(String mode, int numberOfPlayers)
    {
        int retval = -1;

        if(numberOfPlayers <= 4) {
            switch(mode) {
                case "Easy":
                    retval = 1;
                    break;
                case "Mediun":
                    retval = 2;
                    break;
                case "Hard":
                    retval = 3;
                    break;
            }
        }
        if(numberOfPlayers > 4) {
            switch(mode) {
                case "Easy":
                    retval = 4;
                    break;
                case "Mediun":
                    retval = 5;
                    break;
                case "Hard":
                    retval = 6;
                    break;
            }
        }

        return retval;
    }

    /*
        Overview: This is what will appear first on the screen when the user opens to the actual game. Will
        start a count down from 5 seconds and once it finishes a "space ship crashing" sound
        will fire. This will then file right into the start of the firstCountDown()

        Solution: Will use a CountDownTimer that will start from 5 seconds and proceed toward 0.
        Will also use the media player to, play the sound that can be found in the res/raw folder.
     */
    void beginTheGame()
    {
        //final MediaPlayer backgroundSound = MediaPlayer.create(this, R.raw.red_space);
        final MediaPlayer collision = MediaPlayer.create(this, R.raw.collisionsound);

        //backgroundSound.start();

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long tick) {
            int countDown = (int) tick / 1000;
                /*
                    Would like to add an animation to fade in and fade out.
                    See AnimationListener and Android Documentation.
                 */
                Log.d("Count Down Value:", String.valueOf(countDown));
                textViewTimer.setText(String.valueOf(countDown));

            }

            @Override
            public void onFinish() {
                if((gameTime = determindTime(mode, numberOfPlayers)) < 0) {
                    Log.d("gameTime:", String.valueOf(gameTime));
                    Toast.makeText(getBaseContext(), "determindTime function failed.", Toast.LENGTH_LONG).show();
                    openHomePage();
                }
                textViewTimer.setVisibility(View.INVISIBLE);
                collision.start();
                firstCountDown(gameTime);
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

        Time returns

        1 = 15 mins
        2 = 10 mins
        3 = 5  mins
        4 = 20 mins
        5 = 15 mins
        6 = 10 mins

     */
    void firstCountDown(final int time) {
        long millsInFuture;

        switch(time){
            case 1:
                millsInFuture = 900000;
                break;
            case 2:
                millsInFuture = 600000;
                break;
            case 3:
                millsInFuture = 300000;
                break;
            case 4:
                millsInFuture = 1200000;
                break;
            case 5:
                millsInFuture = 900000;
                break;
            case 6:
                millsInFuture = 600000;
                break;
            default:
                millsInFuture = 600000;
                break;
        }

        /*
            Time will be use to determine what the overall times should be set at.
         */
        textViewTimer.setVisibility(View.VISIBLE);


        new CountDownTimer(millsInFuture, 1000) {

            public void onTick(long millisUntilFinished) {
                int totalSeconds = (int) millisUntilFinished / 1000;
                int minutes = (totalSeconds / 60);
                int seconds = (totalSeconds % 60);

                textViewTimer.setText(minutes + ":" + seconds);
            }

            public void onFinish() {
                halftimeCountDown(time);
            }
        }.start();
    }

    void halftimeCountDown(final int time){

        //final MediaPlayer halfTimeAudio = MediaPlayer.create(this, R.raw.collisionsound);
        //halfTimeAudio.start();
        new CountDownTimer(180000, 1000){
            @Override
            public void onTick(long l) {
                int totalSeconds = (int) l / 1000;
                int minutes = (totalSeconds / 60);
                int seconds = (totalSeconds % 60);

                textViewTimer.setText(minutes + ":" + seconds);
            }

            @Override
            public void onFinish() {
                /*
                resume the game and notify the players that it is beginning.
                Also inform that that the communications have come back online.
                 */
                secondCountDown(time);
            }
        }.start();
    }

    /*

        Since in the second countDown Timer, there are only two options, either 10 mins or
        15 mins, that is divided by whether are there are 4 or less players, 10 mins is set.
        Otherwise, it will be set to 15 mins. The time value is translated to less than 3 means
        that there are 4 anything else is greater than 4 making the value to be 15 mins.

    */
    void secondCountDown(int time) {
        long millsInFuture;

        if(time <= 3){
            millsInFuture = 600000;
        }
        else
        {
            millsInFuture = 900000;
        }

        new CountDownTimer(millsInFuture, 1000) {

            public void onTick(long millisUntilFinished) {
                int totalSeconds = (int) millisUntilFinished / 1000;
                int minutes = (totalSeconds / 60);
                int seconds = (totalSeconds % 60);

                textViewTimer.setText(minutes + ":" + seconds);
            }

            public void onFinish() {
                textViewTimer.setText("Game Over");
            }
        }.start();

    }
}


