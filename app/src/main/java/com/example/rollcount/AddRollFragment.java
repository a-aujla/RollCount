/* Purpose: Represents add roll Fragment
Design Rationale: When add roll button is clicked, this fragment allows user to add dice roll
Sources: CMPUT 301 Lab 3 and Listy City
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

public class AddRollFragment extends DialogFragment {
    private EditText roll;
    private OnFragmentInteractionListener addRollListener;

    public interface OnFragmentInteractionListener {
        void onOkRollButtonPressed(Integer roll);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            addRollListener = (OnFragmentInteractionListener) context;
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.add_roll_fragment_layout, null);

        // Get roll from editText boxes
        roll = view.findViewById(R.id.new_roll_edit_text);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add Roll")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Integer new_roll = Integer.parseInt(roll.getText().toString());
                        addRollListener.onOkRollButtonPressed(new_roll);
                    }
                }).create(); }
}
