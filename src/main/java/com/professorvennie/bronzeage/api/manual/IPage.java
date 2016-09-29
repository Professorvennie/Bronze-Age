package com.professorvennie.bronzeage.api.manual;


import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by ProfessorVennie on 11/9/2014 at 6:53 PM.
 */
public interface IPage {

    public int getPageNumber();

    @SideOnly(Side.CLIENT)
    public void drawScreen(IGuiManual gui, int mx, int my);

    public void update();

    public String getUnlocalizedName();
}
