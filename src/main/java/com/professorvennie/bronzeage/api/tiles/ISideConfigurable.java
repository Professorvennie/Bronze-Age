package com.professorvennie.bronzeage.api.tiles;

import com.professorvennie.bronzeage.api.enums.SideMode;
import net.minecraft.util.EnumFacing;

/**
 * Created by ProfessorVennie on 1/1/2015 at 12:48 PM.
 */
public interface ISideConfigurable {

    public SideMode getModeOnSide(EnumFacing side);

    public void changeMode(EnumFacing side);

    public void setModeOnSide(EnumFacing direction, SideMode mode);

    public void overrideConfig(SideMode sideMode[]);

    public SideMode getTankModeOnSide(EnumFacing side);

    public void changeTankMode(EnumFacing side);

    public void setTankModeOnSide(EnumFacing direction, SideMode mode);
}
