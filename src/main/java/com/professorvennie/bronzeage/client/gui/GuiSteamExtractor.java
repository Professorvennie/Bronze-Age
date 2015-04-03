package com.professorvennie.bronzeage.client.gui;

import com.professorvennie.bronzeage.common.containers.ContainerSteamExtractor;
import com.professorvennie.bronzeage.lib.Reference;
import com.professorvennie.bronzeage.tileentitys.TileEntitySteamExtractor;
import net.minecraft.util.ResourceLocation;

/**
 * Created by ProfessorVennie on 3/8/2015 at 4:22 PM.
 */
public class GuiSteamExtractor extends GuiBasicSteamMachine {

    public GuiSteamExtractor(ContainerSteamExtractor containerSteamExtractor, TileEntitySteamExtractor tileEntity) {
        super(containerSteamExtractor, tileEntity);
        backGround = new ResourceLocation(Reference.MOD_ID, "textures/gui/steamExtractor.png");
    }
}
