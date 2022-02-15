
/* Purpose: The main activity when user starts the app
Design Rationale: Main activity shows a list of game sessions which are clickable and a button to add
more game sessions
Sources: CMPUT 301 labs

How to pass object between activities:
Published: Sep 07 2020
Author: Pro Grammer
License: None
URL: https://www.youtube.com/watch?v=uMqgf17mx_U

*/

package com.example.rollcount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements AddSessionFragment.OnFragmentInteractionListener{


    // declaring objects required for ListView element
    private ListView gameSessionList;
    private ArrayList<GameSession> gameSessionData;
    private ArrayAdapter<GameSession> gameSessionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameSessionList = findViewById(R.id.session_list);

        // Create lists for name, date, and rolls of GameSession
        String[] names = {"game1", "game2", "game3"};
        String[] date = {"2020-02-15", "2019-11-06", "2021-05-20"};
        Integer[] dice_rolls = {2, 3, 1};

        // Create new GameSession objects in the for loop and add to the new ArrayList
        gameSessionData = new ArrayList<GameSession>();
        for (int i = 0; i < names.length; i++) {
            gameSessionData.add(new GameSession(names[i], date[i], dice_rolls[i]));
        }

        // Set adapter to the custom one created
        gameSessionAdapter = new CustomSessionList(this, gameSessionData);
        gameSessionList.setAdapter(gameSessionAdapter);

        // Add FloatingActionButton and add OnClickListener so that when the button is pressed, AddSessionFragment().show is called
        final FloatingActionButton addSessionButton = findViewById(R.id.add_session_button);
        addSessionButton.setOnClickListener((v) -> {
            new AddSessionFragment().show(getSupportFragmentManager(), "ADD SESSION");
        });

        /*
        // Add FloatingActionButton and add OnClickListener so that when the button is pressed, AddRollFragment().show is called
        final FloatingActionButton addRollButton = findViewById(R.id.add_roll_button);
        addRollButton.setOnClickListener((v) -> {
            new AddRollFragment().show(getSupportFragmentManager(), "ADD ROLL");
        }); */

        gameSessionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            // Go to new activity on item click
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ViewSessionActivity.class);
                intent.putExtra("gameSession", gameSessionAdapter.getItem(i));
                intent.putExtra("dice", gameSessionAdapter.getItem(i).dice);
                intent.putExtra("stats", gameSessionAdapter.getItem(i).dice.stats);
                intent.putIntegerArrayListExtra("diceRollsArray", gameSessionAdapter.getItem(i).dice.getGameRolls());
                startActivity(intent);

                }

            });;

    }

    @Override
    // When ok is pressed in the AddSessionFragment, add new game session
    public void onOkSessionButtonPressed(GameSession newGameSession) {
        gameSessionAdapter.add(newGameSession);
    }
}
