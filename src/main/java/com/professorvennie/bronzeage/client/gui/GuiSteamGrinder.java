package com.professorvennie.bronzeage.client.gui;

import com.professorvennie.bronzeage.common.containers.ContainerSteamGrinder;
import com.professorvennie.bronzeage.lib.Reference;
import com.professorvennie.bronzeage.tileentitys.TileEntitySteamGrinder;
import net.minecraft.util.ResourceLocation;

/**
 * Created by ProfessorVennie on 3/8/2015 at 4:19 PM.
 */
public class GuiSteamGrinder extends GuiBasicSteamMachine {

    public GuiSteamGrinder(ContainerSteamGrinder containerSteamGrinder, TileEntitySteamGrinder tileEntity) {
        super(containerSteamGrinder, tileEntity);
        backGround = new ResourceLocation(Reference.MOD_ID, "textures/gui/steamGrinder.png");
    }
}
