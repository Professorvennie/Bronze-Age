package com.professorvennie.bronzeage.tileentitys;

import net.minecraft.tileentity.TileEntity;

/**
 * Created by ProfessorVennie on 10/21/2014 at 7:54 PM.
 */
public class TileEntityMod extends TileEntity {

    private String customName;

    public void setCustomName(String displayName) {
        this.customName = displayName;
    }
}
