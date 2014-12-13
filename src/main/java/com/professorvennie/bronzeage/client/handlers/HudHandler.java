package com.professorvennie.bronzeage.client.handlers;

import com.professorvennie.bronzeage.api.manual.IManualEntry;
import com.professorvennie.bronzeage.api.manual.IPage;
import com.professorvennie.bronzeage.api.wrench.IWrench;
import com.professorvennie.bronzeage.api.wrench.IWrenchHUD;
import com.professorvennie.bronzeage.items.ModItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

/**
 * Created by ProfessorVennie on 12/12/2014 at 7:02 PM.
 */
public class HudHandler {

    @SubscribeEvent
    public void onDrawScreen(RenderGameOverlayEvent.Post event) {
        if (event.type == RenderGameOverlayEvent.ElementType.ALL) {
            Minecraft minecraft = Minecraft.getMinecraft();
            MovingObjectPosition pos = minecraft.objectMouseOver;
            Block block = minecraft.theWorld.getBlock(pos.blockX, pos.blockY, pos.blockZ);
            if (minecraft.thePlayer.getCurrentEquippedItem().getItem() instanceof IWrench && block != null && block instanceof IWrenchHUD) {
                drawSteamTank(minecraft.theWorld.getTileEntity(pos.blockX, pos.blockY, pos.blockZ), event.resolution);
            }
            //todo make a IManual interface
            if (minecraft.thePlayer.getCurrentEquippedItem().getItem() == ModItems.manual && block != null && block instanceof IManualEntry) {
                drawManualHud(((IManualEntry) block).getPage(minecraft.theWorld, pos.blockX, pos.blockY, pos.blockZ), event.resolution);
            }
        }
    }

    private void drawSteamTank(TileEntity tile, ScaledResolution scaledResolution) {

    }

    private void drawManualHud(IPage page, ScaledResolution resolution) {

    }
}
