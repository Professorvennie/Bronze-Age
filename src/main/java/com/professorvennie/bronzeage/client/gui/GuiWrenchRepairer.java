package com.professorvennie.bronzeage.client.gui;

import com.professorvennie.bronzeage.common.containers.ContainerWrenchRepairer;
import com.professorvennie.bronzeage.lib.Reference;
import com.professorvennie.bronzeage.tileentitys.TileEntityWrenchRepairer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * Created by ProfessorVennie on 2/11/2015 at 5:37 PM.
 */
public class GuiWrenchRepairer extends GuiBasicSteamMachine {

    public GuiWrenchRepairer(EntityPlayer player, TileEntityWrenchRepairer tileEntity) {
        super(new ContainerWrenchRepairer(player.inventory, tileEntity), tileEntity);

        backGround = new ResourceLocation(Reference.MOD_ID, "textures/gui/wrenchRepairer.png");
    }
}
