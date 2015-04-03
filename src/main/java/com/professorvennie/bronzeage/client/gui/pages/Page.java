package com.professorvennie.bronzeage.client.gui.pages;

import com.professorvennie.bronzeage.api.manual.IGuiManual;
import com.professorvennie.bronzeage.api.manual.IPage;
import com.professorvennie.bronzeage.client.gui.GuiManual;
import com.professorvennie.bronzeage.lib.BookData;
import com.professorvennie.bronzeage.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

/**
 * Created by ProfessorVennie on 10/23/2014 at 5:13 PM.
 */
public class Page implements IPage {

    protected static Minecraft minecraft = Minecraft.getMinecraft();
    private int pageNumber;
    private String name;
    protected ItemStack toolTipStack;
    protected IGuiManual gui;

    public Page(IGuiManual gui, int pageNumber, String unlocalizedName) {
        this.gui = gui;
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
            String localName = StatCollector.translateToLocal(getUnlocalizedName());
            minecraft.fontRenderer.drawString(EnumChatFormatting.UNDERLINE + localName, x - (minecraft.fontRenderer.getStringWidth(localName) / 2), y - 10, 0xFF5D00);
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
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        gui.drawTexturedModalRect(gui.getLeft() + x,gui.getTop() + y, u, v, width, height);
    }

    public void renderSlot(GuiManual gui, int x, int y) {
        bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/guiElements.png"));
        drawElement(gui, x, y, 0, 225, 18, 18);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public void renderItem(ItemStack itemStack, int x, int y) {
        RenderItem itemRenderer = new RenderItem();
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        if (itemStack != null)
            itemRenderer.renderItemAndEffectIntoGUI(fontRenderer, minecraft.renderEngine, itemStack, x, y);

        if((gui.getMouseX() >= x && gui.getMouseX() <= x + 16) && (gui.getMouseY() >= y && gui.getMouseY() <= y + 16))
            toolTipStack = itemStack;
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public void renderItem(Object obj, int x, int y){
        if(obj != null){
            if(obj instanceof ItemStack)
                renderItem((ItemStack)obj, x, y);
            if(obj instanceof Item)
                renderItem(new ItemStack((Item)obj), x, y);
            if(obj instanceof Block)
                renderItem(new ItemStack((Block)obj), x, y);
        }
    }
}