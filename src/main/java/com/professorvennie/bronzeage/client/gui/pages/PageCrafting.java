package com.professorvennie.bronzeage.client.gui.pages;

import com.professorvennie.bronzeage.client.gui.GuiManual;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;

/**
 * Created by ProfessorVennie on 10/28/2014 at 7:36 PM.
 */
public class PageCrafting extends Page {

    private ItemStack input;

    public PageCrafting(int pageNumber, ItemStack input) {
        super(pageNumber);
        this.input = input;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void drawScreen(GuiManual screen, int mx, int my) {
        super.drawScreen(screen, mx, my);
    }
}
