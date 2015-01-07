package com.professorvennie.bronzeage.api.enums;

/**
 * Created by ProfessorVennie on 1/6/2015 at 9:08 PM.
 */
public enum MachineType {

    SteamBoiler(224, 221);


    private int u, v;

    private MachineType(int u, int v) {
        this.u = u;
        this.v = v;
    }

    public int getU() {
        return u;
    }

    public int getV() {
        return v;
    }
}
