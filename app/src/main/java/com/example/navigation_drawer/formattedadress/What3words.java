
package com.example.navigation_drawer.formattedadress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class What3words {

    @SerializedName("words")
    @Expose
    private String words;

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

}
