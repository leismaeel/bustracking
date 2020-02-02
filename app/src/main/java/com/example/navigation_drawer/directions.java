package com.example.navigation_drawer;

public class directions {
    public  double lat;
    public  double lon;
    directions(){}

    public directions(double lattitude, double longitude) {
        this.lat = lattitude;
        this.lon = longitude;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
