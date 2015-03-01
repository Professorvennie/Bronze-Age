package com.professorvennie.bronzeage.client.gui.pages;

import com.professorvennie.bronzeage.api.manual.IGuiManual;
import com.professorvennie.bronzeage.client.gui.GuiManual;
import com.professorvennie.bronzeage.client.helpers.RenderHelper;
import com.professorvennie.bronzeage.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ProfessorVennie on 10/28/2014 at 7:36 PM.
 */
public class PageCrafting extends Page {

    private ItemStack output;
    private Object[] items;

    public PageCrafting(int pageNumber, ItemStack output) {
        super(GuiManual.currentOpenManual, pageNumber, "crafting." + pageNumber);
        this.output = output;
        items = getItemShapedRecipe(output);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void drawScreen(IGuiManual gui, int mx, int my) {
        super.drawScreen(gui, mx, my);
        GuiManual screen = (GuiManual) gui;

        if (items.length > 0) {
            renderSlot(screen, screen.getWidth() / 2 - 9, 15);
            renderItem(output, screen.getLeft() + screen.getWidth() / 2 - 8, screen.getTop() + 16);
            FontRenderer fontRenderer = minecraft.fontRenderer;
            if (items[0] != null) {
                renderSlot(screen, screen.getWidth() / 2 - (9 + 18), 15 + 20);
                renderItem(items[0], screen.getLeft() + screen.getWidth() / 2 - (8 + 18), screen.getTop() + 36);
            }
            if (items[1] != null) {
                renderSlot(screen, screen.getWidth() / 2 - 9, 15 + 20);
                renderItem(items[1], screen.getLeft() + screen.getWidth() / 2 - (8), screen.getTop() + 36);
            }
            if (items[2] != null) {
                renderSlot(screen, screen.getWidth() / 2 + 9, 15 + 20);
                renderItem(items[2], screen.getLeft() + screen.getWidth() / 2 + (10), screen.getTop() + 36);
            }
            if (items[3] != null) {
                renderSlot(screen, screen.getWidth() / 2 - (9 + 18), 15 + 38);
                renderItem(items[3], screen.getLeft() + screen.getWidth() / 2 - (8 + 18), screen.getTop() + (38 + 16));
            }
            if (items[4] != null) {
                renderSlot(screen, screen.getWidth() / 2 - 9, 15 + 38);
                renderItem(items[4], screen.getLeft() + screen.getWidth() / 2 - (8), screen.getTop() + (38 + 16));
            }
            if (items[5] != null) {
                renderSlot(screen, screen.getWidth() / 2 + 9, 15 + 38);
                renderItem(items[5], screen.getLeft() + screen.getWidth() / 2 + 10, screen.getTop() + (38 + 16));
            }
            if (items[6] != null) {
                renderSlot(screen, screen.getWidth() / 2 - (9 + 18), 15 + (38 + 18));
                renderItem(items[6], screen.getLeft() + screen.getWidth() / 2 - (8 + 18), screen.getTop() + (15 + (38 + 19)));
            }
            if (items[7] != null) {
                renderSlot(screen, screen.getWidth() / 2 - 9, 15 + (38 + 18));
                renderItem(items[7], screen.getLeft() + screen.getWidth() / 2 - (8), screen.getTop() + (15 + (38 + 19)));
            }
            if (items[8] != null) {
                renderSlot(screen, screen.getWidth() / 2 + 9, 15 + (38 + 18));
                renderItem(items[8], screen.getLeft() + screen.getWidth() / 2 + 10, screen.getTop() + (15 + (38 + 19)));
            }
            fontRenderer.drawString(output.getDisplayName(), screen.getLeft() + screen.getWidth() / 2 - fontRenderer.getStringWidth(output.getDisplayName()) / 2, screen.getTop() + 100, 0x0026FF);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        }
        if(toolTipStack != null)
            RenderHelper.renderTooltip(gui.getMouseX(), gui.getMouseY(), toolTipStack);
        toolTipStack = null;
    }

    private Object[] getItemShapedRecipe(ItemStack stack) {
        Object[] finalRecipe = new Object[9];
        if (stack != null) {
            List<IRecipe> possibleRecipe = new ArrayList<IRecipe>();

            for (Object recipe : CraftingManager.getInstance().getRecipeList()) {
                if (recipe instanceof IRecipe) {
                    ItemStack output = ((IRecipe) recipe).getRecipeOutput();
                    if (output != null && stack.getUnlocalizedName().equals(output.getUnlocalizedName()) && stack.getItemDamage() == output.getItemDamage())
                        possibleRecipe.add((IRecipe) recipe);
                }
            }

            if (!possibleRecipe.isEmpty()) {
                IRecipe main = possibleRecipe.get(0);

                try {
                    Field[] fields = main.getClass().getDeclaredFields();
                    if (main instanceof ShapedRecipes) {
                        Field inputs = fields[2];
                        if (inputs.getType().isArray()) {
                            for (int i = 0; i < Array.getLength(inputs.get(main)); i++) {
                                if ((Array.get(inputs.get(main), i) instanceof ItemStack) && i < finalRecipe.length)
                                    finalRecipe[i] = Array.get(inputs.get(main), i);
                            }
                        }
                    } else if (main instanceof ShapelessRecipes) {
                        Field inputs = fields[1];
                        if (List.class.isAssignableFrom(inputs.getType())) {
                            inputs.setAccessible(true);
                            @SuppressWarnings("unchecked")
                            List<ItemStack> items = getField(ShapelessRecipes.class, List.class, main, 1);
                            if (items != null) {
                                for (int i = 0; i < items.size(); i++) {
                                    if (i < finalRecipe.length)
                                        finalRecipe[i] = items.get(i);
                                }
                            }
                        }
                    } else if (main instanceof ShapedOreRecipe || main instanceof ShapelessOreRecipe) {
                        Object[] inputs = null;
                        if (main instanceof ShapedOreRecipe)
                            inputs = ((ShapedOreRecipe) main).getInput();
                        else
                            inputs = ((ShapelessOreRecipe) main).getInput().toArray();

                        for (int i = 0; i < inputs.length; i++) {
                            Object obj = inputs[i];
                            if (obj instanceof ArrayList<?>)
                                finalRecipe[i] = ((ArrayList<?>) obj).get(0);
                            else
                                finalRecipe[i] = obj;
                        }
                    } else if (Class.forName("ic2.core.AdvRecipe").isAssignableFrom(main.getClass())
                            || Class.forName("ic2.core.AdvShapelessRecipe").isAssignableFrom(main.getClass())) {
                        Field inputs = fields[2];
                        if (inputs.getType().isArray()) {
                            for (int i = 0; i < Array.getLength(inputs.get(main)); i++) {
                                if (i < finalRecipe.length)
                                    finalRecipe[i] = Array.get(inputs.get(main), i);
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        return finalRecipe;
    }

    private <T> T getField(Class<?> class1, Class<T> fieldType, Object instance, int i) {
        try {
            Field[] fields = class1.getDeclaredFields();
            Field field = fields[i];
            field.setAccessible(true);
            return (T) field.get(instance);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}