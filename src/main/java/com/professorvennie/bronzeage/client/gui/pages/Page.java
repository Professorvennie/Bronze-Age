package com.professorvennie.bronzeage.client.gui.pages;

import com.professorvennie.bronzeage.api.manual.IGuiManual;
import com.professorvennie.bronzeage.api.manual.IPage;
import com.professorvennie.bronzeage.client.gui.GuiManual;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by ProfessorVennie on 10/23/2014 at 5:13 PM.
 */
public class Page implements IPage {

    protected static Minecraft minecraft = Minecraft.getMinecraft();
    private int pageNumber;

    public Page(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    @SideOnly(Side.CLIENT)
    public void drawScreen(IGuiManual screen, int mx, int my) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public void bindTexture(ResourceLocation texture) {
        minecraft.renderEngine.bindTexture(texture);
    }

    public void update() {

    }

    public void drawElement(GuiManual gui, int x, int y, int u, int v, int width, int height) {
        gui.drawTexturedModalRect(x, y, u, v, width, height);
    }
}
