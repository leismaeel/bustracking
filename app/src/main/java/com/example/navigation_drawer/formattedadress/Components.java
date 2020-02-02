
package com.example.navigation_drawer.formattedadress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Components {

    @SerializedName("ISO_3166-1_alpha-2")
    @Expose
    private String iSO31661Alpha2;
    @SerializedName("ISO_3166-1_alpha-3")
    @Expose
    private String iSO31661Alpha3;
    @SerializedName("_type")
    @Expose
    private String type;
    @SerializedName("continent")
    @Expose
    private String continent;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("country_code")
    @Expose
    private String countryCode;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("state_code")
    @Expose
    private String stateCode;
    @SerializedName("state_district")
    @Expose
    private String stateDistrict;
    @SerializedName("village")
    @Expose
    private String village;

    public String getISO31661Alpha2() {
        return iSO31661Alpha2;
    }

    public void setISO31661Alpha2(String iSO31661Alpha2) {
        this.iSO31661Alpha2 = iSO31661Alpha2;
    }

    public String getISO31661Alpha3() {
        return iSO31661Alpha3;
    }

    public void setISO31661Alpha3(String iSO31661Alpha3) {
        this.iSO31661Alpha3 = iSO31661Alpha3;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateDistrict() {
        return stateDistrict;
    }

    public void setStateDistrict(String stateDistrict) {
        this.stateDistrict = stateDistrict;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

}
