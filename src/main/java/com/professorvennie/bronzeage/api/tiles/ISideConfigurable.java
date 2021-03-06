package com.professorvennie.bronzeage.api.tiles;

import com.professorvennie.bronzeage.api.enums.SideMode;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by ProfessorVennie on 1/1/2015 at 12:48 PM.
 */
public interface ISideConfigurable {

    public SideMode getModeOnSide(ForgeDirection side);

    public void changeMode(ForgeDirection side);

    public void setModeOnSide(ForgeDirection direction, SideMode mode);

    public void overrideConfig(SideMode sideMode[]);

    public SideMode getTankModeOnSide(ForgeDirection side);

    public void changeTankMode(ForgeDirection side);

    public void setTankModeOnSide(ForgeDirection direction, SideMode mode);
}
