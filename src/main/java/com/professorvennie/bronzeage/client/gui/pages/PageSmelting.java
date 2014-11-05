package com.professorvennie.bronzeage.client.gui.pages;

import com.professorvennie.bronzeage.client.gui.GuiManual;
import com.professorvennie.bronzeage.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

/**
 * Created by ProfessorVennie on 10/28/2014 at 7:38 PM.
 */
public class PageSmelting extends Page {

    private ItemStack input;
    @SideOnly(Side.CLIENT)
    private int burnTime, cookTime, speed = 100;

    public PageSmelting(int pageNumber, ItemStack input) {
        super(pageNumber);
        this.input = input;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void drawScreen(GuiManual screen, int mx, int my) {
        super.drawScreen(screen, mx, my);
        if (input != null) {
            FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
            fontRenderer.drawStringWithShadow(input.getDisplayName(), screen.getLeft() + fontRenderer.getStringWidth(input.getDisplayName()), screen.getTop() + 10, Color.BLUE.getRGB());
            bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/guiElements.png"));
            screen.drawTexturedModalRect(screen.getLeft() + 13, screen.getTop() + 38, 35, 236, 20, 20);
            RenderItem renderItem = new RenderItem();
            renderItem.renderItemAndEffectIntoGUI(fontRenderer, minecraft.renderEngine, input, screen.getLeft() + screen.getGuiWidth() / 2 - 27, screen.getTop() + 40);
            ItemStack output = FurnaceRecipes.smelting().getSmeltingResult(input);
            if (output != null) {
                bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/guiElements.png"));
                //screen.drawTexturedModalRect(screen.getLeft() + 16, screen.getTop() + 60, 0, 193, 14, 14);
                //screen.drawTexturedModalRect(screen.getLeft() + 35, screen.getTop() + 40, 13, 240, 22, 16);
                //screen.drawTexturedModalRect(screen.getLeft() + 58, screen.getTop() + 38, 35, 236, 20, 20);
                int k = getBurnTimeReamingScaled(14);
                screen.drawTexturedModalRect(screen.getLeft() + screen.getGuiWidth() / 2 - 27, screen.getTop() + 60 + 14 - k, 0, 206 - k, 14, k + 2);

                int j = getCookProgressScaled(22);
                screen.drawTexturedModalRect(screen.getLeft() + screen.getGuiWidth() / 2 - 3, screen.getGuiWidth() / 2 - 27, 0, 223, 22, 22);
                renderItem.renderItemAndEffectIntoGUI(fontRenderer, minecraft.renderEngine, output, screen.getLeft() + screen.getGuiWidth() / 2 + 10, screen.getTop() + 40);
            } else
                fontRenderer.drawStringWithShadow("Invalid recipe", screen.getLeft(), screen.getTop() + 95, screen.getGuiWidth());
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void update() {
        super.update();
        //System.out.println(burnTime);
        if (burnTime == 0)
            burnTime = 100;
        if (burnTime > 0)
            burnTime--;
        if (cookTime == 0)
            cookTime++;
        if (cookTime == speed)
            cookTime = 0;
    }

    public int getBurnTimeReamingScaled(int i) {
        if (this.burnTime == 0) {
            this.burnTime = speed;
        }
        return this.burnTime * i / this.burnTime;
    }

    public int getCookProgressScaled(int scale) {
        if (cookTime != 0)
            return this.cookTime * scale / this.speed;
        return 0;
    }
}
