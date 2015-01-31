package com.professorvennie.bronzeage.api.tiles;

import com.professorvennie.bronzeage.api.enums.Upgrades;

import java.util.ArrayList;

/**
 * Created by ProfessorVennie on 1/17/2015 at 9:50 PM.
 */
public interface IUpgradeableTile {

    public void installUpgrade(Upgrades upgrade);

    public boolean hasUpgrade(Upgrades upgrade);

    public ArrayList<Upgrades> getUpgrades();
}
