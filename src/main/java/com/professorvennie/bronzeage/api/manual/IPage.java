package com.professorvennie.bronzeage.api.manual;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Created by ProfessorVennie on 11/9/2014 at 6:53 PM.
 */
public interface IPage {

    public int getPageNumber();

    @SideOnly(Side.CLIENT)
    public void drawScreen(IGuiManual gui, int mx, int my);

    public void update();
}
