package edu.uga.cs.ei.moviepick;

import java.util.ArrayList;
import java.util.List;

public class Theater {

    private String name;
    private String address;

    public Theater() {
        this.name = null;
        this.address = null;
    }

    public Theater(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
