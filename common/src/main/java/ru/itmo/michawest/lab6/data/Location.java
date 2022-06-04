package ru.itmo.michawest.lab6.data;

import java.io.Serializable;

public class Location implements Serializable {
    private Double x;
    private Long y;
    private Double z;

    public boolean addLocation(double nx, long ny, double nz) {
        this.x = nx;
        this.y = ny;
        this.z = nz;
        return true;
    }

    public double getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
