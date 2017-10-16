
package br.com.xxnickfuryxx.qrstarwars.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Person implements Serializable{


    @SerializedName("name")
    private String name;
    @SerializedName("height")
    private String height;
    @SerializedName("mass")
    private String mass;
    @SerializedName("hair_color")
    private String hairColor;
    @SerializedName("skin_color")
    private String skinColor;
    @SerializedName("eye_color")
    private String eyeColor;
    @SerializedName("birth_year")
    private String birthYear;
    @SerializedName("gender")
    private String gender;
    @SerializedName("homeworld")
    private String homeworld;
    @SerializedName("films")
    private List<String> films = null;
    @SerializedName("species")
    private List<String> species = null;
    @SerializedName("vehicles")
    private List<String> vehicles = null;
    @SerializedName("starships")
    private List<String> starships = null;
    @SerializedName("created")
    private String created;
    @SerializedName("edited")
    private String edited;
    @SerializedName("url")
    private String url;
    @SerializedName("latitude")
    private double latitude;
    @SerializedName("longitude")
    private double longitude;
    @SerializedName("userCapture")
    private String userCapture;
    @SerializedName("dateCapture")
    private String dateCapture;

    public String getDateCapture() {
        return dateCapture;
    }

    public void setDateCapture(String dateCapture) {
        this.dateCapture = dateCapture;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getUserCapture() {
        return userCapture;
    }

    public void setUserCapture(String userCapture) {
        this.userCapture = userCapture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    public List<String> getSpecies() {
        return species;
    }

    public void setSpecies(List<String> species) {
        this.species = species;
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<String> vehicles) {
        this.vehicles = vehicles;
    }

    public List<String> getStarships() {
        return starships;
    }

    public void setStarships(List<String> starships) {
        this.starships = starships;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
