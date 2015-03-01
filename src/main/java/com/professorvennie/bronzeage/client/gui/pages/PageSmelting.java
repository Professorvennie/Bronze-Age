package com.professorvennie.bronzeage.client.gui.pages;

import com.professorvennie.bronzeage.api.manual.IGuiManual;
import com.professorvennie.bronzeage.client.gui.GuiManual;
import com.professorvennie.bronzeage.client.helpers.RenderHelper;
import com.professorvennie.bronzeage.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

import java.awt.*;

/**
 * Created by ProfessorVennie on 10/28/2014 at 7:38 PM.
 */
public class PageSmelting extends Page {

    private ItemStack input, fuels[];
    private int fuelAt, ticksElapsed = 0;

    public PageSmelting(int pageNumber, ItemStack input) {
        super(GuiManual.currentOpenManual, pageNumber, "smelting." + pageNumber);
        this.input = input;
        fuelAt = 0;
        fuels = new ItemStack[]{new ItemStack(Items.coal, 1, 0), new ItemStack(Items.coal, 1, 1), new ItemStack(Items.blaze_rod), new ItemStack(Items.lava_bucket), new ItemStack(Blocks.log, 1, 0), new ItemStack(Blocks.log, 1, 1), new ItemStack(Blocks.log, 1, 2), new ItemStack(Blocks.log, 1, 3), new ItemStack(Blocks.log2, 1, 0), new ItemStack(Blocks.log2, 1, 1), new ItemStack(Blocks.planks, 1, 0), new ItemStack(Blocks.planks, 1, 1), new ItemStack(Blocks.planks, 1, 2), new ItemStack(Blocks.planks, 1, 3), new ItemStack(Blocks.planks, 1, 4), new ItemStack(Blocks.planks, 1, 5)};
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void drawScreen(IGuiManual gui, int mx, int my) {
        super.drawScreen(gui, mx, my);
        GuiManual screen = (GuiManual) gui;
        if (input != null) {
            FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
            fontRenderer.drawString(input.getDisplayName(), screen.getLeft() + screen.getWidth() / 2 - (fontRenderer.getStringWidth(input.getDisplayName()) / 2), screen.getTop() + 10, Color.BLUE.getRGB());

            //render input
            renderSlot(screen, screen.getWidth() / 2 - 34, 40);
            renderItem(input, screen.getLeft() + screen.getWidth() / 2 - 33, screen.getTop() + 41);

            ItemStack output = FurnaceRecipes.smelting().getSmeltingResult(input);
            if (output != null) {
                bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/guiElements.png"));

                //fire
                drawElement(screen, screen.getWidth() / 2 - 33, 59, 0, 193, 14, 14);

                //arrow
                drawElement(screen, screen.getWidth() / 2 - 13, +40, 0, 208, 22, 16);

                //fuel
                renderSlot(screen, screen.getWidth() / 2 - 34, 58 + 18);
                if(fuels[fuelAt].getItem() instanceof ItemBlock)
                    renderItem(fuels[fuelAt], screen.getLeft() + screen.getWidth() / 2 - 33, screen.getTop() + 58 + 19);
                else
                    renderItem(fuels[fuelAt], screen.getLeft() + screen.getWidth() / 2 - 33, screen.getTop() + 58 + 18);

                //output
                renderSlot(screen, screen.getWidth() / 2 + 14, 41);
                renderItem(output, screen.getLeft() + screen.getWidth() / 2 + 15, screen.getTop() + 42);

                String local = StatCollector.translateToLocal("bronzeAge.book.smeltingRecipe");
                GL11.glColor4f(1.0f, 1.0f, 1.0f,1.0f);
                fontRenderer.drawString(local, screen.getLeft() + screen.getWidth() / 2 - (fontRenderer.getStringWidth(local) / 2), screen.getTop() + 100, Color.BLUE.getRGB());
            } else
                fontRenderer.drawStringWithShadow("Invalid recipe", screen.getLeft(), screen.getTop() + 95, Color.BLUE.getRGB());
        }
        if(toolTipStack != null)
            RenderHelper.renderTooltip(gui.getMouseX(), gui.getMouseY(), toolTipStack);
        toolTipStack = null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void update() {
        super.update();
        if(ticksElapsed % 20 == 0) {
            fuelAt++;

            if(fuelAt == fuels.length)
                fuelAt = 0;
        }
        ticksElapsed++;
    }
}
