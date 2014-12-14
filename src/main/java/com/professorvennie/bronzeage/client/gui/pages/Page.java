package com.professorvennie.bronzeage.client.gui.pages;

import com.professorvennie.bronzeage.api.manual.IGuiManual;
import com.professorvennie.bronzeage.api.manual.IPage;
import com.professorvennie.bronzeage.client.gui.GuiManual;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by ProfessorVennie on 10/23/2014 at 5:13 PM.
 */
public class Page implements IPage {

    protected static Minecraft minecraft = Minecraft.getMinecraft();
    private int pageNumber;
    private String name;

    public Page(int pageNumber, String unlocalizedName) {
        this.pageNumber = pageNumber;
        this.name = unlocalizedName;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    @SideOnly(Side.CLIENT)
    public void drawScreen(IGuiManual screen, int mx, int my) {
        if (name != null) {
            int x = screen.getLeft() + (146 / 2);
            int y = screen.getTop();
            minecraft.fontRenderer.drawString(EnumChatFormatting.UNDERLINE + getUnlocalizedName(), x - (minecraft.fontRenderer.getStringWidth(getUnlocalizedName()) / 2), y - 10, 0xFF5D00);
        }
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public void bindTexture(ResourceLocation texture) {
        minecraft.renderEngine.bindTexture(texture);
    }

    public void update() {

    }

    @Override
    public String getUnlocalizedName() {
        return "bronzeAge.page." + name;
    }

    public void drawElement(GuiManual gui, int x, int y, int u, int v, int width, int height) {
        gui.drawTexturedModalRect(x, y, u, v, width, height);
    }
}
