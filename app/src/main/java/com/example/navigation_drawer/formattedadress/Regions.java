
package com.example.navigation_drawer.formattedadress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Regions {

    @SerializedName("ASIA")
    @Expose
    private String aSIA;
    @SerializedName("PK")
    @Expose
    private String pK;
    @SerializedName("SOUTHERN_ASIA")
    @Expose
    private String sOUTHERNASIA;
    @SerializedName("WORLD")
    @Expose
    private String wORLD;

    public String getASIA() {
        return aSIA;
    }

    public void setASIA(String aSIA) {
        this.aSIA = aSIA;
    }

    public String getPK() {
        return pK;
    }

    public void setPK(String pK) {
        this.pK = pK;
    }

    public String getSOUTHERNASIA() {
        return sOUTHERNASIA;
    }

    public void setSOUTHERNASIA(String sOUTHERNASIA) {
        this.sOUTHERNASIA = sOUTHERNASIA;
    }

    public String getWORLD() {
        return wORLD;
    }

    public void setWORLD(String wORLD) {
        this.wORLD = wORLD;
    }

}
