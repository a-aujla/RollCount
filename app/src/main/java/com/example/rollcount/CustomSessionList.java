/* Purpose: Custom adapter to store GameSession
Design Rationale: This adapter stores GameSessions so that they can be displayed in list view
Sources: CMPUT 301 labs
 */

package com.example.rollcount;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomSessionList extends ArrayAdapter<GameSession> {
    private ArrayList<GameSession> gameSessions;
    private Context context;

    // Constructor
    public CustomSessionList(Context context, ArrayList<GameSession> gameSessions) {

        // make call to parent class (ArrayAdapter)
        super(context, 0, gameSessions);
        this.gameSessions = gameSessions;
        this.context = context;
    }

    /* getView method allows array adapter to make sense of how to fit the data in gameSession list
    into corresponding fields in list view */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        // Inflate view if null
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.content, parent, false);
        }

        // Creates GameSession object corresponding to position of content file
        GameSession gameSession = gameSessions.get(position);

        // Create TextView objects for each attribute of GameSession that is going to be displayed
        TextView sessionName = view.findViewById(R.id.name_text);
        TextView sessionDate = view.findViewById(R.id.date_text);
        TextView sessionRoll = view.findViewById(R.id.dice_roll_text);

        // Set text of TextView objects to name, date, and dice roll of the GameSession
        sessionName.setText(gameSession.getName());
        sessionDate.setText(gameSession.getDate());
        sessionRoll.setText(gameSession.dice.getNdM());

        return view;
    }
}
