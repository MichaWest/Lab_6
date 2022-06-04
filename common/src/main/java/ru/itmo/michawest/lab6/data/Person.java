package ru.itmo.michawest.lab6.data;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person implements Serializable {
    private Integer id;
    private String name;
    private Coordinates coordinates;
    private LocalTime creationDate;
    private Double height;
    private Long weight;
    private Color hairColor;
    private Country nationality;
    private Location location;

    public Person(String name, Coordinates coordinates, LocalTime createTime, Double height, Long weight, Color hairColor, Country nationality, Location location) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = createTime;
        this.height = height;
        this.weight = weight;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
    }

    public Person() {
        this.creationDate = LocalTime.now();
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalTime getCreationDate() {
        return creationDate;
    }

    public double getHeight() {
        return height;
    }

    public long getWeight() {
        return weight;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public Location getLocation() {
        return location;
    }

    public boolean addCreationDate(LocalTime cd) {
        if (cd != null) {
            this.creationDate = cd;
            return true;
        } else {
            return false;
        }
    }

    public boolean addId(int newId) {
        this.id = newId;
        return true;
    }

    public boolean addName(String newName) {
        if ((newName != null) && (!newName.isEmpty())) {
            this.name = newName;
            return true;
        } else {
            return false;
        }
    }

    public boolean addHeight(double newHeight) {
        if (newHeight > 0) {
            this.height = newHeight;
            return true;
        } else {
            return false;
        }
    }

    public boolean addWeight(long newWeight) {
        if (newWeight > 0) {
            this.weight = newWeight;
            return true;
        } else {
            return false;
        }
    }

    public boolean addColor(String newColor) {
        newColor = newColor.toLowerCase();
        Pattern r = Pattern.compile("\\w+");
        Matcher m = r.matcher(newColor);
        Color color;
        if (m.find()) {
            int start = m.start();
            int end = m.end();
            newColor = newColor.substring(start, end);
        }
        switch (newColor) {
            case ("red"):
                color = Color.RED;
                this.hairColor = color;
                return true;
            case ("yellow"):
                color = Color.YELLOW;
                this.hairColor = color;
                return true;
            case ("brown"):
                color = Color.BROWN;
                this.hairColor = color;
                return true;
            default:
                System.out.println("Color " + newColor + " doesn't exist");
                return false;
        }
    }

    public boolean addNationality(String newNationality) {
        newNationality = newNationality.toLowerCase();
        Pattern r = Pattern.compile("\\w+");
        Matcher m = r.matcher(newNationality);
        Country land;
        if (m.find()) {
            int start = m.start();
            int end = m.end();
            newNationality = newNationality.substring(start, end);
        }
        switch (newNationality) {
            case ("usa"):
                land = Country.USA;
                this.nationality = land;
                return true;
            case ("china"):
                land = Country.CHINA;
                this.nationality = land;
                return true;
            case ("vatican"):
                land = Country.VATICAN;
                this.nationality = land;
                return true;
            case ("north_korea"):
                land = Country.NORTH_KOREA;
                this.nationality = land;
                return true;
            case ("japan"):
                land = Country.JAPAN;
                this.nationality = land;
                return true;
            default:
                System.out.println("Country " + newNationality + " doesn't exist");
                return false;
        }
    }

    public boolean addCoordinates(float newX, float newY) {
        coordinates = new Coordinates();
        return coordinates.addCoordinates(newX, newY);
    }

    public boolean addLocation(double newX, long newY, double newZ) {
        location = new Location();
        return location.addLocation(newX, newY, newZ);
    }

}
