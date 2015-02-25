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
        super(new ContainerWrenchRepairer(player.inventory, tileEntity), tileEntity, false);

        backGround = new ResourceLocation(Reference.MOD_ID, "textures/gui/wrenchRepairer.png");
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        super.drawGuiContainerBackgroundLayer(p_146976_1_, p_146976_2_, p_146976_3_);

        drawProgressArrow(102, 48);
    }
}
