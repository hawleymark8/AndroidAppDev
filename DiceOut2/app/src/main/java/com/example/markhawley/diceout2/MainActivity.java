package com.example.markhawley.diceout2;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //----Variables----

    //roll result
    TextView rollResult;
    //score
    int score;
    int scoreDelta;
    //Random num generator
    Random rand;
    //dice
    int die1;
    int die2;
    int die3;
    //arrayList dice
    ArrayList<Integer> dice;
    //ArrayLists for dice images
    ArrayList<ImageView> diceImageViews;
    //ScoreTest
    TextView scoreText;
    //displaymsg
    String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice(view);
            }
        });

        //Set Score
        score = 0;

        //Greeting
        Toast.makeText(getApplicationContext(), "Welcome to DiceOut!", Toast.LENGTH_SHORT).show();

        //Link instances to widgets in Activity view
        //[Java variable instance] = ([cast/convert widget value this type]) findViewById(R.id.[widget id]);
        rollResult = (TextView) findViewById(R.id.rollResult);
        scoreText = (TextView) findViewById(R.id.scoreText);

        //rand initialized
        rand = new Random();

        //initialize dice arrayList
        dice = new ArrayList();

        //diceImageViews ArrayList
        ImageView die1Image = (ImageView) findViewById(R.id.die1Image);
        ImageView die2Image = (ImageView) findViewById(R.id.die2Image);
        ImageView die3Image = (ImageView) findViewById(R.id.die3Image);

        diceImageViews = new ArrayList<ImageView>();
        diceImageViews.add(die1Image);
        diceImageViews.add(die2Image);
        diceImageViews.add(die3Image);

    }

    public void rollDice(View v) {
        //activated when rollButton is clicked
        rollResult.setText("Clicked!");

//        Prev. Lesson:
       /* int num = rand.nextInt(6) + 1;
        String randomValue = "Number generated: " + num;
        Toast.makeText(getApplicationContext(), randomValue, Toast.LENGTH_SHORT).show();*/

        //Roll Dice
        die1 = rand.nextInt(6) + 1;
        die2 = rand.nextInt(6) + 1;
        die3 = rand.nextInt(6) + 1;

        //Populate ArrayList (clear prev. roll first)
        dice.clear();
        dice.add(die1);
        dice.add(die2);
        dice.add(die3);

        //Scorecalc
        if(die1 == die2 && die2 == die3)
        {
            scoreDelta = die1*100;
            score += scoreDelta;
            msg = "You rolled a triple " + die1 + "! You score " + scoreDelta + " points!";
        }
        else if(die1 == die2 || die2 == die3 || die1 == die3){
            score += 50;
            msg = "You rolled doubles for 50 points!";
        }
        else {
            score += 0;
            msg = "You rolled a " + die1 + ", a " + die2 + ", and a " + die3 + ". Try again!";
        }
        //set scoreText
        String scoreMsg = "Score: " + score;
        scoreText.setText(scoreMsg);

        //set rollResult Text
        rollResult.setText(msg);

        for (int dieOfSet = 0; dieOfSet < 3; dieOfSet++) {
            //pull random int from dice ArrayList at index 0, and set to file name
            String imageName = "die_" + dice.get(dieOfSet) + ".png";

            try {
                //draw dice 1,2,or3 based on image name
                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream, null);
                diceImageViews.get(dieOfSet).setImageDrawable(d);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
