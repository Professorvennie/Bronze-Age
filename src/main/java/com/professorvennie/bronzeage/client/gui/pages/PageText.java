package com.professorvennie.bronzeage.client.gui.pages;

import com.professorvennie.bronzeage.api.manual.IGuiManual;
import com.professorvennie.bronzeage.client.gui.GuiManual;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Created by ProfessorVennie on 10/25/2014 at 10:41 PM.
 */
public class PageText extends Page {

    private String text;

    public PageText(int pageNumber, String unlocalizedText) {
        super(pageNumber);
        this.text = unlocalizedText;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void drawScreen(IGuiManual gui, int mx, int my) {
        super.drawScreen(gui, mx, my);
        GuiManual screen = (GuiManual) gui;
        PageUtil.renderText(screen.getLeft() + 16, screen.getTop() + 2, screen.getWidth() - 24, screen.getWidth(), text);
    }
}
