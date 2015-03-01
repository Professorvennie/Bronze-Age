package com.professorvennie.bronzeage.client.gui;

import com.professorvennie.bronzeage.common.containers.ContainerSteamBoiler;
import com.professorvennie.bronzeage.lib.Reference;
import com.professorvennie.bronzeage.tileentitys.TileEntitySteamBoiler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ProfessorVennie on 11/13/2014 at 3:28 PM.
 */
public class GuiSteamBoiler extends GuiBasicSteamMachine {

    private TileEntitySteamBoiler steamBoiler;

    public GuiSteamBoiler(EntityPlayer player, TileEntitySteamBoiler tile) {
        super(new ContainerSteamBoiler(player.inventory, tile), tile);
        backGround = new ResourceLocation(Reference.MOD_ID, "textures/gui/steamBoiler.png");
        steamBoiler = tile;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
        super.drawGuiContainerForegroundLayer(p_146979_1_, p_146979_2_);
        List<String> text = new ArrayList<String>();
        text.clear();
        text.add("Water");
        text.add(steamBoiler.getWaterAmount() + "/" + steamBoiler.getWaterCapacity() + "mB");
        drawToolTipOverArea(mouseX, mouseY, 11, 8, 26, 73, text, fontRendererObj);

        text.clear();
        text.add("Steam");
        text.add(steamBoiler.getSteamAmount() + "/" + steamBoiler.getSteamCapacity() + "mB");
        drawToolTipOverArea(mouseX, mouseY, 149, 8, 164, 73, text, fontRendererObj);

        text.clear();
        text.add("Temperature");
        text.add(steamBoiler.temp + "F / " + steamBoiler.maxTemp + "F");
        drawToolTipOverArea(mouseX, mouseY, 53, 63, 122, 68, text, fontRendererObj);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        super.drawGuiContainerBackgroundLayer(p_146976_1_, p_146976_2_, p_146976_3_);
        Minecraft.getMinecraft().getTextureManager().bindTexture(elements);
        if (this.steamBoiler.isBurning()) {
            int k = this.steamBoiler.getBurnTimeReamingScaled(12);
            drawTexturedModalRect(guiLeft + 81, guiTop + 38 - k, 0, 206 - k, 14, k + 2);
        }

        int j = getValueScaled(steamBoiler.getWaterAmount(), steamBoiler.getWaterCapacity(), 66);
        drawElement(11, 74 - j, 176, 80 - j, 16, j);

        j = steamBoiler.getTempScaled(70);
        drawElement(53, 63, 51, 0, j + 1, 6);

        drawSteamTank(149, 74);
    }
}
