/* Purpose: Represents a single game session
Design Rationale: The game session keeps track of its name, date and has a dice.
Sources: None */

package com.example.rollcount;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;


public class GameSession implements Parcelable {

    private String date;
    private String name;
    public Dice dice;

    public GameSession(String name, String date, Integer rolls) {
        this.date = date;
        this.name = name;
        this.dice = new Dice(rolls);
    }

    public GameSession(String name, Integer rolls) {
        this.date = new Date().toString();
        this.name = name;
        this.dice = new Dice(rolls);
    }


    protected GameSession(Parcel in) {
        date = in.readString();
        name = in.readString();
    }

    public static final Creator<GameSession> CREATOR = new Creator<GameSession>() {
        @Override
        public GameSession createFromParcel(Parcel in) {
            return new GameSession(in);
        }

        @Override
        public GameSession[] newArray(int size) {
            return new GameSession[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        //return dateFormat.format(this.date);
        return this.date;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(date);
        parcel.writeString(name);
    }
}
