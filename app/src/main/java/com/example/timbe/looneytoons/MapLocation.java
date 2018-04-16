package com.example.timbe.looneytoons;

public class MapLocation implements Comparable<MapLocation> {

    private String name;
    private double lat;
    private double lng;
    private String address;

    public MapLocation(String name, double lat, double lng, String address) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("MapLocation Name should not be empty or null");
        }
        if (address == null || address.equals("")) {
            throw new IllegalArgumentException("MapLocation Address should not be emtpy or null");
        }

        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.address = address;
    }

    public String getName() {
        return this.name;
    }

    public double getLat() {
        return this.lat;
    }

    public double getLng() {
        return this.lng;
    }

    public String getAddress() {
        return this.address;
    }


    /**
     * Used to compare the two MapLocations for sorting
     *
     * @param otherLocation the other location campared against
     *
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object
     */
    @Override
    public int compareTo(MapLocation otherLocation) {
        return this.getName().compareTo(otherLocation.getName());
    }

}
