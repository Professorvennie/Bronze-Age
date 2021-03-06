package com.professorvennie.bronzeage.client.gui;

import com.professorvennie.bronzeage.common.containers.ContainerSteamFurnace;
import com.professorvennie.bronzeage.lib.Reference;
import com.professorvennie.bronzeage.tileentitys.TileEntityBasicSteamMachine;
import com.professorvennie.bronzeage.tileentitys.TileEntitySteamFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ProfessorVennie on 1/18/2015 at 1:26 PM.
 */
public class GuiSteamFurnace extends GuiBasicSteamMachine {

    public GuiSteamFurnace(EntityPlayer player, TileEntitySteamFurnace basicMachine) {
        super(new ContainerSteamFurnace(player.inventory, basicMachine), basicMachine);
        backGround = new ResourceLocation(Reference.MOD_ID, "textures/gui/steamFurnace.png");
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        super.drawGuiContainerBackgroundLayer(p_146976_1_, p_146976_2_, p_146976_3_);

        drawProgressArrow(84, 34);
    }
}