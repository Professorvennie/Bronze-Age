package com.professorvennie.bronzeage.client.gui.pages;

import com.professorvennie.bronzeage.api.manual.IGuiManual;
import com.professorvennie.bronzeage.client.gui.GuiManual;
import com.professorvennie.bronzeage.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

/**
 * Created by ProfessorVennie on 10/25/2014 at 4:09 PM.
 */
public class PageCover extends Page {

    private String title, author;

    public PageCover(IGuiManual gui, int pageNumber, String title, String author) {
        super(gui, pageNumber, null);
        this.title = title;
        this.author = author;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void drawScreen(IGuiManual gui, int mx, int my) {
        GuiManual screen = (GuiManual) gui;
        super.drawScreen(gui, mx, my);
        bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/bookCover.png"));
        screen.drawTexturedModalRect(screen.getLeft(), screen.getTop(), 0, 0, screen.getWidth(), screen.getHeight());
        String localizedTitle = StatCollector.translateToLocal(title);
        String localizedAuthor = StatCollector.translateToLocal(author);
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        fontRenderer.drawStringWithShadow(localizedTitle, screen.getLeft() + (fontRenderer.getStringWidth(localizedTitle) / 2) - 20, screen.getTop() + 10, 0x62a48);
        fontRenderer.drawStringWithShadow(localizedAuthor, screen.getLeft() + (fontRenderer.getStringWidth(localizedAuthor) / 2) - 29, screen.getTop() + 50, 0x62a48);
    }
}
