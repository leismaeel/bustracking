
package com.example.navigation_drawer.formattedadress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Timezone {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("now_in_dst")
    @Expose
    private Integer nowInDst;
    @SerializedName("offset_sec")
    @Expose
    private Integer offsetSec;
    @SerializedName("offset_string")
    @Expose
    private String offsetString;
    @SerializedName("short_name")
    @Expose
    private String shortName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNowInDst() {
        return nowInDst;
    }

    public void setNowInDst(Integer nowInDst) {
        this.nowInDst = nowInDst;
    }

    public Integer getOffsetSec() {
        return offsetSec;
    }

    public void setOffsetSec(Integer offsetSec) {
        this.offsetSec = offsetSec;
    }

    public String getOffsetString() {
        return offsetString;
    }

    public void setOffsetString(String offsetString) {
        this.offsetString = offsetString;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

}
