package com.professorvennie.bronzeage.client.gui.pages;

import com.professorvennie.bronzeage.api.manual.IGuiManual;
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
import net.minecraft.util.StatCollector;

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
    public void drawScreen(IGuiManual gui, int mx, int my) {
        super.drawScreen(gui, mx, my);
        GuiManual screen = (GuiManual) gui;
        if (input != null) {
            FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
            fontRenderer.drawStringWithShadow(input.getDisplayName(), screen.getLeft() + screen.getWidth() / 2 - (fontRenderer.getStringWidth(input.getDisplayName()) / 2), screen.getTop() + 10, Color.BLUE.getRGB());
            bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/guiElements.png"));
            screen.drawTexturedModalRect(screen.getLeft() + 13, screen.getTop() + 38, 35, 236, 20, 20);
            RenderItem renderItem = new RenderItem();
            renderItem.renderItemAndEffectIntoGUI(fontRenderer, minecraft.renderEngine, input, screen.getLeft() + screen.getWidth() / 2 - 34, screen.getTop() + 40);
            ItemStack output = FurnaceRecipes.smelting().getSmeltingResult(input);
            if (output != null) {
                bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/guiElements.png"));
                int k = getBurnTimeReamingScaled(14);
                screen.drawTexturedModalRect(screen.getLeft() + screen.getWidth() / 2 - 34, screen.getTop() + 60 + 14 - k, 0, 206 - k, 14, k + 2);

                int j = getCookProgressScaled(22);
                screen.drawTexturedModalRect(screen.getLeft() + screen.getWidth() / 2 - 13, screen.getTop() + 40, 0, 208, 22, 22);
                renderItem.renderItemAndEffectIntoGUI(fontRenderer, minecraft.renderEngine, output, screen.getLeft() + screen.getWidth() / 2 + 15, screen.getTop() + 40);

                String local = StatCollector.translateToLocal("bronzeAge.book.smeltingRecipe");
                fontRenderer.drawStringWithShadow(local, screen.getLeft() + screen.getWidth() / 2 - (fontRenderer.getStringWidth(local) / 2), screen.getTop() + 100, 0x0026FF);
            } else
                fontRenderer.drawStringWithShadow("Invalid recipe", screen.getLeft(), screen.getTop() + 95, Color.BLUE.getRGB());
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void update() {
        super.update();
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
