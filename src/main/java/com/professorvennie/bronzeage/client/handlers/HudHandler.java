package com.professorvennie.bronzeage.client.handlers;

import com.professorvennie.bronzeage.api.manual.IManualEntry;
import com.professorvennie.bronzeage.api.manual.IPage;
import com.professorvennie.bronzeage.items.ModItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

/**
 * Created by ProfessorVennie on 12/12/2014 at 7:02 PM.
 */
public class HudHandler {

    private final int color = 0xFF5D00;

    @SubscribeEvent
    public void onDrawScreen(RenderGameOverlayEvent.Post event) {
        if (event.type == RenderGameOverlayEvent.ElementType.ALL) {
            Minecraft minecraft = Minecraft.getMinecraft();
            MovingObjectPosition pos = minecraft.objectMouseOver;
            Block block;
            if (minecraft.theWorld.getBlock(pos.blockX, pos.blockY, pos.blockZ) != null)
                block = minecraft.theWorld.getBlock(pos.blockX, pos.blockY, pos.blockZ);
            else
                block = null;

            if (minecraft.thePlayer != null && minecraft.thePlayer.getCurrentEquippedItem() != null && block != null) {
                if (minecraft.thePlayer.getCurrentEquippedItem().getItem() == ModItems.manual && block instanceof IManualEntry && ((IManualEntry)block).getPage(minecraft.theWorld, pos.blockX, pos.blockY, pos.blockZ) != null) {
                    drawManualHud(((IManualEntry) block).getPage(minecraft.theWorld, pos.blockX, pos.blockY, pos.blockZ), event.resolution);
                }
            }
        }
        GL11.glColor4f(1F, 1F, 1F, 1F);
    }

    private void drawManualHud(IPage page, ScaledResolution resolution) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        Minecraft mc = Minecraft.getMinecraft();
        RenderItem renderItem = new RenderItem();
        FontRenderer fontRenderer = mc.fontRenderer;
        ItemStack manual = mc.thePlayer.getCurrentEquippedItem();
        int x = resolution.getScaledWidth() / 2;
        int y = resolution.getScaledHeight() / 2;

        renderItem.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, manual, x - 64, y - 13);
        fontRenderer.drawString(manual.getDisplayName(), x - (fontRenderer.getStringWidth(manual.getDisplayName()) / 2), y - 10, color);
        String string = StatCollector.translateToLocal("bronzeAge.book.shifttoread");
        fontRenderer.drawString(StatCollector.translateToLocal(page.getUnlocalizedName()), x - (fontRenderer.getStringWidth(StatCollector.translateToLocal(page.getUnlocalizedName())) / 2), y + 8, color);
        fontRenderer.drawString(string, x - (fontRenderer.getStringWidth(string) / 2), y + 18, color);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glColor4f(1F, 1F, 1F, 1F);
    }
}
