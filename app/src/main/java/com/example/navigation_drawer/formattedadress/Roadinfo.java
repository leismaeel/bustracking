
package com.example.navigation_drawer.formattedadress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Roadinfo {

    @SerializedName("drive_on")
    @Expose
    private String driveOn;
    @SerializedName("speed_in")
    @Expose
    private String speedIn;

    public String getDriveOn() {
        return driveOn;
    }

    public void setDriveOn(String driveOn) {
        this.driveOn = driveOn;
    }

    public String getSpeedIn() {
        return speedIn;
    }

    public void setSpeedIn(String speedIn) {
        this.speedIn = speedIn;
    }

}
