package com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes;

public class Estado {
    public Estado(String name, int state) {
        this.name = name;
        this.state = state;
    }

    public Estado() {
        this.name = "";
        this.state = 0;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    private String name;

    @Override
    public String toString() {
        return getName();
    }

    private int state;
}
