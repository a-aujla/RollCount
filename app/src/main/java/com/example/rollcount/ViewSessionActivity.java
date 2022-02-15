/* Purpose: The view session activity that displays info about the GameSession clicked
Design Rationale: Has info about the game session and shows stats. Has buttons to add roll, undo
roll, edit game session, and delete game session

Sources: CMPUT 301 labs

How to save data:
Published:Nov 6, 2017
Author: Coding in Flow
License: None
URL: https://www.youtube.com/watch?v=jcliHGR3CHo&t=1s
*/

package com.example.rollcount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ShareActionProvider;
import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ViewSessionActivity extends AppCompatActivity implements AddRollFragment.OnFragmentInteractionListener, EditSessionFragment.OnFragmentInteractionListener{

    private GameSession gameSession;
    private Dice dice;
    private Stats stats;
    private TextView totalRolls;
    private TextView min;
    private TextView max;
    private TextView avg;
    private TextView histogram;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_session);

        saveData();

        if (!loadData()) {
                this.gameSession = getIntent().getParcelableExtra("gameSession");
                this.dice = getIntent().getParcelableExtra("dice");
                this.stats = getIntent().getParcelableExtra("stats");
                this.dice.setStats(this.stats);
                ArrayList<Integer> diceRolls = getIntent().getIntegerArrayListExtra("diceRollsArray");
                stats.setRolls(diceRolls);
                dice.setGameRolls(diceRolls);
        }

        // get TextView objects
        totalRolls = findViewById(R.id.total_rolls_text);
        min = findViewById(R.id.min_text);
        max = findViewById(R.id.max_text);
        avg = findViewById(R.id.avg_text);
        histogram = findViewById(R.id.histogram_text);

        // Set TextView's to stats
        totalRolls.setText(dice.getTotalRolls());
        min.setText(stats.getMin());
        max.setText(stats.getMax());
        avg.setText(stats.getAvg());
        histogram.setText(stats.getHistogram());

        // Add roll button
        Button addRollButton = (Button) findViewById(R.id.add_roll_button);
        addRollButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new AddRollFragment().show(getSupportFragmentManager(), "ADD ROLL");
            }
        });

        // Edit button
        Button editSessionButton = (Button) findViewById(R.id.edit_session_button);
        editSessionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new EditSessionFragment().show(getSupportFragmentManager(), "EDIT SESSION");
            }
        });

        // Delete session button
        Button deleteSessionButton = (Button) findViewById(R.id.delete_session_button);
        deleteSessionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // what to do on click oof delete session button
            }
        });

        // Undo roll button
        Button undoRollButton = (Button) findViewById(R.id.undo_roll_button);
        undoRollButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dice.undoRoll();
                updateLayout();
            }
        });
    }


    @Override
    public void onOkRollButtonPressed(Integer roll) {
        dice.addRoll(roll);
        updateLayout();
    }

    @Override
    public void onOkEditSessionButtonPressed(String date, String name) {
        gameSession.setDate(date);
        gameSession.setName(name);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    public void updateLayout() {
        // Update the view
        totalRolls.setText(dice.getTotalRolls());
        min.setText(stats.getMin());
        max.setText(stats.getMax());
        avg.setText(stats.getAvg());
        histogram.setText(stats.getHistogram());
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String session = gson.toJson(gameSession);
        String diceString = gson.toJson(dice);
        String statsString = gson.toJson(stats);
        String diceRollsArray = gson.toJson(new ArrayList<Integer>());
        if (dice != null) {
            diceRollsArray = gson.toJson(dice.getGameRolls()); }
        editor.putString("game session", session);
        editor.putString("dice", diceString);
        editor.putString("statsString", statsString);
        editor.putString("diceRollsArray", diceRollsArray);
        editor.apply();
    }

    private Boolean loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String session = sharedPreferences.getString("game session", null);
        String diceString = sharedPreferences.getString("dice", null);
        String statString = sharedPreferences.getString("statString", null);
        String diceRollsArray = sharedPreferences.getString("diceRollsArray", null);
        Type sessionType = new TypeToken<GameSession>() {}.getType();
        Type diceType = new TypeToken<Dice>() {}.getType();
        Type statType = new TypeToken<Stats>() {}.getType();
        Type arrayType = new TypeToken<ArrayList<Integer>>() {}.getType();
        gameSession = gson.fromJson(session, sessionType);
        dice = gson.fromJson(diceString, diceType);
        stats = gson.fromJson(statString, statType);
        ArrayList <Integer> diceRolls = gson.fromJson(diceRollsArray, arrayType);

        if (gameSession == null) {
            return false;
        } else {
            stats.setRolls(diceRolls);
            dice.setGameRolls(diceRolls);
            return true;
        }
    }

}