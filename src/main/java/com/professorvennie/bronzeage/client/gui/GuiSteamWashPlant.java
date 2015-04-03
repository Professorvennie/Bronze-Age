package com.professorvennie.bronzeage.client.gui;

import com.professorvennie.bronzeage.common.containers.ContainerSteamWashPlant;
import com.professorvennie.bronzeage.lib.Reference;
import com.professorvennie.bronzeage.tileentitys.TileEntitySteamWashPlant;
import net.minecraft.util.ResourceLocation;


/**
 * Created by ProfessorVennie on 3/8/2015 at 4:16 PM.
 */
public class GuiSteamWashPlant extends GuiBasicSteamMachine {

    public GuiSteamWashPlant(ContainerSteamWashPlant containerSteamWashPlant, TileEntitySteamWashPlant tileEntity) {
        super(containerSteamWashPlant, tileEntity);
        backGround = new ResourceLocation(Reference.MOD_ID, "textures/gui/washPlant.png");
    }
}
