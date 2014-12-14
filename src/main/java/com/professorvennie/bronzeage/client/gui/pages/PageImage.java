package com.professorvennie.bronzeage.client.gui.pages;

import com.professorvennie.bronzeage.api.manual.IGuiManual;
import com.professorvennie.bronzeage.client.gui.GuiManual;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by ProfessorVennie on 11/7/2014 at 8:22 PM.
 */
public class PageImage extends Page {

    private ResourceLocation image;
    private String text;

    public PageImage(int pageNumber, ResourceLocation image, String text) {
        super(pageNumber, "image." + pageNumber);
        this.image = image;
        this.text = text;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void drawScreen(IGuiManual gui, int mx, int my) {
        super.drawScreen(gui, mx, my);
        GuiManual screen = (GuiManual) gui;

        bindTexture(image);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_DST_ALPHA);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        drawElement(screen, screen.getLeft(), screen.getTop(), 0, 0, screen.getWidth(), screen.getHeight());
        GL11.glDisable(GL11.GL_BLEND);

        PageUtil.renderText(screen.getLeft() + screen.getWidth() / 2 + 16, screen.getTop() + screen.getHeight() - 40, screen.getWidth() / 30, screen.getHeight(), text);
    }
}
