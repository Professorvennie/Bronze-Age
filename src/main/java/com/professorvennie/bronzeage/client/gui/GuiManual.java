package com.professorvennie.bronzeage.client.gui;

import com.professorvennie.bronzeage.api.BronzeAgeAPI;
import com.professorvennie.bronzeage.api.manual.IGuiManual;
import com.professorvennie.bronzeage.api.manual.IPage;
import com.professorvennie.bronzeage.client.gui.pages.Page;
import com.professorvennie.bronzeage.client.gui.pages.PageCover;
import com.professorvennie.bronzeage.lib.BookData;
import com.professorvennie.bronzeage.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ProfessorVennie on 10/23/2014 at 5:10 PM.
 */
public class GuiManual extends GuiScreen implements IGuiManual {

    public static ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/gui/Book.png");
    public static ItemStack currentItemStack;
    public static GuiManual currentOpenManual = new GuiManual(BookData.pageCover);
    private List<IPage> pages = new ArrayList<IPage>();
    private Page currentPage;
    private int currentPageNumber, guiWidth = 146, guiHeight = 180, left, top;
    private GuiButtonNextPage buttonNextPage, buttonPerviousPage;

    public GuiManual(IPage currentPage) {
        this.pages = BronzeAgeAPI.getPages();
        this.currentPage = (Page) currentPage;
        this.currentPageNumber = currentPage.getPageNumber();
    }

    @Override
    public void initGui() {
        super.initGui();
        left = width / 2 - 146 / 2;
        top = height / 2 - 180 / 2;
        buttonPerviousPage = new GuiButtonNextPage(0, left, top + 180 - 10, false);
        buttonNextPage = new GuiButtonNextPage(1, left + 146 - 18, top + 180 - 10, true);
        buttonList.add(buttonNextPage);
        buttonList.add(buttonPerviousPage);
    }

    @Override
    public void drawScreen(int mx, int my, float idk) {
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        drawTexturedModalRect(left, top, 0, 0, guiWidth, guiHeight);
        for (IPage p : pages) {
            Page page = (Page) p;
            if (currentPage instanceof PageCover && page == currentPage) {
                page.drawScreen(this, mx, my);
                if (currentPageNumber > 0) {
                    buttonNextPage.drawButton(mc, mx, my);
                    buttonPerviousPage.drawButton(mc, mx, my);
                } else if (currentPageNumber == 0) {
                    buttonNextPage.drawButton(mc, mx, my);
                }
            } else {
                if (currentPageNumber > 0) {
                    buttonNextPage.drawButton(mc, mx, my);
                    buttonPerviousPage.drawButton(mc, mx, my);
                } else if (currentPageNumber == 0) {
                    buttonNextPage.drawButton(mc, mx, my);
                }
                if (page == currentPage)
                    page.drawScreen(this, mx, my);
            }
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.equals(buttonNextPage)) {
            if (currentPage.getPageNumber() + 1 < pages.size()) {
                currentPageNumber++;
                this.currentPage = (Page) pages.get(currentPageNumber);
            }
        } else if (button.equals(buttonPerviousPage)) {
            if (currentPageNumber > 0) {
                currentPageNumber--;
                this.currentPage = (Page) pages.get(currentPageNumber);
            }
        }
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        if (currentItemStack.getTagCompound() != null) {
            currentItemStack.getTagCompound().setInteger("CurrentPage", currentPageNumber);
        } else {
            currentItemStack.setTagCompound(new NBTTagCompound());
            currentItemStack.getTagCompound().setInteger("CurrentPage", currentPageNumber);
        }
    }

    @Override
    public void updateScreen() {
        pages.get(currentPageNumber).update();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    public int getLeft() {
        return left;
    }

    public int getTop() {
        return top;
    }

    public int getWidth() {
        return guiWidth;
    }

    public int getHeight() {
        return guiHeight;
    }
}
