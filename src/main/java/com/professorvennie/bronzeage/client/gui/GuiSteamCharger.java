package com.professorvennie.bronzeage.client.gui;

import com.professorvennie.bronzeage.common.containers.ContainerSteamCharger;
import com.professorvennie.bronzeage.lib.Reference;
import com.professorvennie.bronzeage.tileentitys.TileEntitySteamCharger;
import net.minecraft.util.ResourceLocation;

/**
 * Created by ProfessorVennie on 2/15/2015 at 3:32 PM.
 */
public class GuiSteamCharger extends GuiBasicSteamMachine {

    public GuiSteamCharger(ContainerSteamCharger containerSteamCharger, TileEntitySteamCharger tileEntity) {
        super(containerSteamCharger, tileEntity);
        backGround = new ResourceLocation(Reference.MOD_ID, "textures/gui/steamCharger.png");
    }
}
