package com.professorvennie.bronzeage.client.gui;

import com.professorvennie.bronzeage.lib.Reference;
import com.professorvennie.bronzeage.tileentitys.TileEntityBasicSteamMachine;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

/**
 * Created by ProfessorVennie on 1/18/2015 at 1:26 PM.
 */
public class GuiSteamFurnace extends GuiBasicSteamMachine {

    public GuiSteamFurnace(Container container, TileEntityBasicSteamMachine basicMachine) {
        super(container, basicMachine);
        backGround = new ResourceLocation(Reference.MOD_ID, "textures/gui/steamFurnace.png");
    }
}
