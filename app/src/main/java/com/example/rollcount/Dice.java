
/* Purpose: Represents a Dice that has a single configuration (e.g. 2d6)
Design Rationale: The dice keeps info such as its rolls and has stats associated with it.
Sources: None
*/

package com.example.rollcount;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Dice implements Parcelable {

    private final Integer numOfRolls;
    private ArrayList<Integer> gameRolls;
    public Stats stats;

    public Dice(Integer numOfRolls) {
        this.numOfRolls = numOfRolls;
        this.gameRolls = new ArrayList<Integer>();
        this.stats = new Stats(gameRolls);
    }

    protected Dice(Parcel in) {
        if (in.readByte() == 0) {
            numOfRolls = null;
        } else {
            numOfRolls = in.readInt();
        }
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public ArrayList<Integer> getGameRolls() {
        return gameRolls;
    }

    public void setGameRolls(ArrayList<Integer> gameRolls) {
        this.gameRolls = gameRolls;
    }

    public String getNdM() {
        return (
                this.numOfRolls + "d6");
    }

    public void addRoll(Integer roll) {
        if (roll > (numOfRolls * 6)) {
            return;
        } else if (roll < numOfRolls) {
            return;
        }

        this.gameRolls.add(roll);
        this.stats.setRolls(gameRolls);
    }

    public void undoRoll() {

        gameRolls.remove(gameRolls.size()-1);
        this.stats.setRolls(gameRolls);
    }

    public String getTotalRolls() {

        return "Total: " + gameRolls.size();
    }



    public static final Creator<Dice> CREATOR = new Creator<Dice>() {
        @Override
        public Dice createFromParcel(Parcel in) {
            return new Dice(in);
        }

        @Override
        public Dice[] newArray(int size) {
            return new Dice[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (numOfRolls == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(numOfRolls);
        }
    }
}
