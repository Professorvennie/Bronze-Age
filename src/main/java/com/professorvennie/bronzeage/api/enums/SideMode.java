package com.professorvennie.bronzeage.api.enums;

/**
 * Created by ProfessorVennie on 1/1/2015 at 12:47 PM.
 */
public enum SideMode {

    IMPORT("import"),
    EXPORT("export"),
    BOTH("both"),
    DISABLED("disabled");

    private String tooltip;

    SideMode(String tooltip) {
        this.tooltip = tooltip;
    }

    public String getTooltip() {
        return tooltip;
    }
}
