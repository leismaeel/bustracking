
package com.example.navigation_drawer.formattedadress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UNM49 {

    @SerializedName("regions")
    @Expose
    private Regions regions;
    @SerializedName("statistical_groupings")
    @Expose
    private List<String> statisticalGroupings = null;

    public Regions getRegions() {
        return regions;
    }

    public void setRegions(Regions regions) {
        this.regions = regions;
    }

    public List<String> getStatisticalGroupings() {
        return statisticalGroupings;
    }

    public void setStatisticalGroupings(List<String> statisticalGroupings) {
        this.statisticalGroupings = statisticalGroupings;
    }

}
