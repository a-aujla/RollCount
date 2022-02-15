/* Purpose: Represents add session Fragment
Design Rationale: When add session button is clicked, this fragment allows user to add dice roll

Sources:
- CMPUT 301 labs

-How to check if EditText field was empty:
Date: June 19 2011
Author:cvaldemar
License: None
URL: https://stackoverflow.com/questions/6290531/how-do-i-check-if-my-edittext-fields-are-empty
 */


package com.example.rollcount;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddSessionFragment extends DialogFragment {
    private EditText sessionName;
    private EditText sessionDate;
    private EditText diceRolls;
    private OnFragmentInteractionListener addSessionListener;

    public interface OnFragmentInteractionListener {
        void onOkSessionButtonPressed(GameSession newGameSession);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            addSessionListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");

        }
    }

    @NonNull
    @Override
    // Dialog is the window that pops up when the button is clicked
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        // Make view add_session_fragment_layout
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.add_session_fragment_layout, null);

        // Get name, date, diceRolls and diceSides from editText boxes
        sessionName = view.findViewById(R.id.game_name_edit_text);
        sessionDate = view.findViewById(R.id.date_edit_text);
        diceRolls = view.findViewById(R.id.dice_rolls_edit_text);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add/Edit Game Session")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = sessionName.getText().toString();
                        String date = sessionDate.getText().toString();
                        Integer rolls = Integer.parseInt(diceRolls.getText().toString());
                        if (date.matches("")) {
                            addSessionListener.onOkSessionButtonPressed(new GameSession(name,rolls));
                        } else {
                            addSessionListener.onOkSessionButtonPressed(new GameSession(name,date,rolls));
                        }
                    }
                }).create(); }
}
