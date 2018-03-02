package com.captechventures.techchallenge3.model;

import org.parceler.Parcel;

/**
 * Created by mluansing on 10/2/17.
 */

@Parcel
public class ZipCodeEntry {

    String ZipCode;
    Double Latitude, Longitude;
    String City, State, County, Type;
    Boolean Preferred;
    String WorldRegion, Country, LocationText, Location;
    Integer Population, HousingUnits, Income;
    Double LandArea, WaterArea;
    Boolean Decommissioned;
    String MilitaryRestrictionCodes;

    public ZipCodeEntry() {} // default constructor

    public ZipCodeEntry(String zipCode, Double latitude, Double longitude, String city, String state, String county, String type, Boolean preferred, String worldRegion, String country, String locationText, String location, Integer population, Integer housingUnits, Integer income, Double landArea, Double waterArea, Boolean decommissioned, String militaryRestrictionCodes) {
        ZipCode = zipCode;
        Latitude = latitude;
        Longitude = longitude;
        City = city;
        State = state;
        County = county;
        Type = type;
        Preferred = preferred;
        WorldRegion = worldRegion;
        Country = country;
        LocationText = locationText;
        Location = location;
        Population = population;
        HousingUnits = housingUnits;
        Income = income;
        LandArea = landArea;
        WaterArea = waterArea;
        Decommissioned = decommissioned;
        MilitaryRestrictionCodes = militaryRestrictionCodes;
    }

    // GETTERS AND SETTERS
    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCounty() {
        return County;
    }

    public void setCounty(String county) {
        County = county;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Boolean getPreferred() {
        return Preferred;
    }

    public void setPreferred(Boolean preferred) {
        Preferred = preferred;
    }

    public String getWorldRegion() {
        return WorldRegion;
    }

    public void setWorldRegion(String worldRegion) {
        WorldRegion = worldRegion;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getLocationText() {
        return LocationText;
    }

    public void setLocationText(String locationText) {
        LocationText = locationText;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public Integer getPopulation() {
        return Population;
    }

    public void setPopulation(Integer population) {
        Population = population;
    }

    public Integer getHousingUnits() {
        return HousingUnits;
    }

    public void setHousingUnits(Integer housingUnits) {
        HousingUnits = housingUnits;
    }

    public Integer getIncome() {
        return Income;
    }

    public void setIncome(Integer income) {
        Income = income;
    }

    public Double getLandArea() {
        return LandArea;
    }

    public void setLandArea(Double landArea) {
        LandArea = landArea;
    }

    public Double getWaterArea() {
        return WaterArea;
    }

    public void setWaterArea(Double waterArea) {
        WaterArea = waterArea;
    }

    public Boolean getDecommissioned() {
        return Decommissioned;
    }

    public void setDecommissioned(Boolean decommissioned) {
        Decommissioned = decommissioned;
    }

    public String getMilitaryRestrictionCodes() {
        return MilitaryRestrictionCodes;
    }

    public void setMilitaryRestrictionCodes(String militaryRestrictionCodes) {
        MilitaryRestrictionCodes = militaryRestrictionCodes;
    }

    @Override
    public String toString() {
        return "ZipCodeEntry{" +
                "ZipCode='" + ZipCode + '\'' +
                ", Latitude=" + Latitude +
                ", Longitude=" + Longitude +
                ", City='" + City + '\'' +
                ", State='" + State + '\'' +
                ", County='" + County + '\'' +
                ", Type='" + Type + '\'' +
                ", Preferred=" + Preferred +
                ", WorldRegion='" + WorldRegion + '\'' +
                ", Country='" + Country + '\'' +
                ", LocationText='" + LocationText + '\'' +
                ", Location='" + Location + '\'' +
                ", Population=" + Population +
                ", HousingUnits=" + HousingUnits +
                ", Income=" + Income +
                ", LandArea=" + LandArea +
                ", WaterArea=" + WaterArea +
                ", Decommissioned=" + Decommissioned +
                ", MilitaryRestrictionCodes='" + MilitaryRestrictionCodes + '\'' +
                '}';
    }

}
