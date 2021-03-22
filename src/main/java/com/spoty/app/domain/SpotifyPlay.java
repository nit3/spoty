package com.spoty.app.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class SpotifyPlay {

    private int count;
    private int max;
    private String song;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }


}
