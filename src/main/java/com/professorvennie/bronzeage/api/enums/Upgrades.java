package com.professorvennie.bronzeage.api.enums;

/**
 * Created by ProfessorVennie on 1/31/2015 at 12:29 PM.
 */
public enum Upgrades {

    AUTO_EJECT("AutoEject"),
    AUTO_PULL("AutoPull"),
    SPEED("Speed");

    private String name;

    private Upgrades(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
