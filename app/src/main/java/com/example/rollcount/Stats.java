
/* Purpose: Represents statistics of a game session
Design Rationale: Calculates the statistics of a game session. Dice passes its rollsArray to this
stats class and this class calculates min, max, average and histogram from it

How to create the histogram:
Published June 06 2021
Author:akshitsaxenaa09
License: None
Url: https://www.geeksforgeeks.org/java-program-to-replace-multiple-characters-in-a-string/
*/

package com.example.rollcount;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stats implements Parcelable {

    private ArrayList<Integer> rolls;

    public Stats(ArrayList<Integer> gameRolls) {

        this.rolls = gameRolls;
    }

    protected Stats(Parcel in) {
    }

    public static final Creator<Stats> CREATOR = new Creator<Stats>() {
        @Override
        public Stats createFromParcel(Parcel in) {
            return new Stats(in);
        }

        @Override
        public Stats[] newArray(int size) {
            return new Stats[size];
        }
    };

    public String getAvg() {
        // check if no rolls have been inputted yet
        if (rolls.isEmpty()) {
            return "Average: No rolls inputted";
        }
        // Find average
        int total = 0;
        for (int i = 0; i < rolls.size(); i++) {
            total = total + rolls.get(i);
        }
        return ("Average: "+ (total / rolls.size()));
    }

    public String getMin() {
        // check if no rolls have been inputted yet
        if (rolls.isEmpty()) {
            return "Min: No rolls inputted";
        }
        // Find min
        int min = rolls.get(0);
        for (int i = 1; i < rolls.size(); i++) {
            if (rolls.get(i) < min) {
                min = rolls.get(i);
            }
        }
        return ("Min: "+ min);
    }

    public String getMax() {
        // check if no rolls have been inputted yet
        if (rolls.isEmpty()) {
            return "Max: No rolls inputted";
        }
        // Find max
        int max = rolls.get(0);
        for (int i = 1; i < rolls.size(); i++) {
            if (rolls.get(i) > max) {
                max = rolls.get(i);
            }
        }
        return ("Max: " + max);
    }

    public String getHistogram() {
        // check if no rolls have been inputted yet
        if (rolls.isEmpty()) {
            return "Histogram: No rolls inputted";
        }
        String histogram = "";

        // Get totals for each dice roll
        int one = 0, two = 0, three = 0, four = 0, five = 0, six = 0, sev = 0, eight = 0;
        int nine = 0, ten = 0, eleven = 0, twelve = 0, thirteen = 0, fourteen = 0, fifteen = 0;
        int sixteen = 0, seventeen = 0, eighteen = 0;
        for (int i = 0; i < rolls.size(); i++) {
            switch (rolls.get(i)) {
                case 1:
                    one++;
                    break;
                case 2:
                    two++;
                    break;
                case 3:
                    three++;
                    break;
                case 4:
                    four++;
                    break;
                case 5:
                    five++;
                    break;
                case 6:
                    six++;
                    break;
                case 7:
                    sev++;
                    break;
                case 8:
                    eight++;
                    break;
                case 9:
                    nine++;
                    break;
                case 10:
                    ten++;
                    break;
                case 11:
                    eleven++;
                    break;
                case 12:
                    twelve++;
                    break;
                case 13:
                    thirteen++;
                    break;
                case 14:
                    fourteen++;
                    break;
                case 15:
                    fifteen++;
                    break;
                case 16:
                    sixteen++;
                    break;
                case 17:
                    seventeen++;
                    break;
                case 18:
                    eighteen++;
                    break;

            }
        }
        int total = one + two + three + four + five + six + sev + eight + nine + ten + eleven + twelve + thirteen + fourteen + fifteen + sixteen + seventeen + eighteen;
        List<Integer> totals = Arrays.asList(one, two, three, four, five, six, sev, eight, nine, ten , eleven , twelve , thirteen , fourteen , fifteen , sixteen , seventeen , eighteen);

        // Add to histogram string for dice rolls
        for (int i = 0; i < 18; i++) {
            histogram = histogram + (i+1) + " (" + totals.get(i) + ") ";

            // While j < (roll / total dice rolls) add # symbol to histogram
            for (int j = 0; j < totals.get(i)/total; j++) {
                histogram = histogram.replace("\0", "#");
            }
            histogram = histogram + "\n";
        }
        return "Histogram:\n" + histogram;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public void setRolls(ArrayList<Integer> rolls) {
        this.rolls = rolls;
    }
}
