/* Purpose: Represents edit session Fragment
Design Rationale: When edit session button is clicked, this fragment allows user to change date and
name
Sources: CMPUT 301 labs
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

public class EditSessionFragment extends DialogFragment {
    private EditText sessionName;
    private EditText sessionDate;
    private OnFragmentInteractionListener editSessionListener;

    public interface OnFragmentInteractionListener {
        void onOkEditSessionButtonPressed(String date, String name);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof EditSessionFragment.OnFragmentInteractionListener) {
            editSessionListener = (EditSessionFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");

        }
    }

    @NonNull
    @Override
    // Dialog is the window that pops up when the button is clicked
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        // Make view edit_session_fragment_layout
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.edit_session_fragment_layout, null);

        // Get name, date, diceRolls and diceSides from editText boxes
        sessionName = view.findViewById(R.id.game_name_edit_text);
        sessionDate = view.findViewById(R.id.date_edit_text);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Edit Game Session")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = sessionName.getText().toString();
                        String date = sessionDate.getText().toString();
                        editSessionListener.onOkEditSessionButtonPressed(date, name);
                    }
                }).create(); }
}


