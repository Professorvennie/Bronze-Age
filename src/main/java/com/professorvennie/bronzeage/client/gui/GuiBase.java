package com.professorvennie.bronzeage.client.gui;

import com.professorvennie.bronzeage.BronzeAge;
import com.professorvennie.bronzeage.client.gui.buttons.GuiButtonRedStone;
import com.professorvennie.bronzeage.client.gui.buttons.RedstoneMode;
import com.professorvennie.bronzeage.core.network.MessageButton;
import com.professorvennie.bronzeage.core.network.PacketHandler;
import com.professorvennie.bronzeage.lib.Reference;
import com.professorvennie.bronzeage.tileentitys.TileEntityBasicMachine;
import com.professorvennie.bronzeage.tileentitys.TileEntityBasicSidedInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.util.List;

/**
 * Created by ProfessorVennie on 10/23/2014 at 9:48 PM.
 */
public class GuiBase extends GuiContainer {

    public ResourceLocation backGround;
    public ResourceLocation elements = new ResourceLocation(Reference.MOD_ID, "textures/gui/guiElements.png");
    public TileEntityBasicMachine basicMachine;
    protected int mouseX = 0, mouseY = 0;

    public GuiBase(Container container, TileEntityBasicSidedInventory tileEntity) {
        this(container, null);
    }

    public GuiBase(Container container, TileEntityBasicMachine basicMachine) {
        super(container);
        this.basicMachine = basicMachine;
    }

    @Override
    public void initGui() {
        super.initGui();
        buttonList.add(new GuiButtonRedStone(0, guiLeft + xSize + 1, guiTop + ySize - 160, basicMachine));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f(0F, 0.30F, 0.97F, 1F);

        Minecraft.getMinecraft().getTextureManager().bindTexture(elements);
        //drawTexturedModalRect(guiLeft + 176, guiTop + 3, 0, 0, 28, 89);

        if (basicMachine != null) {
            GL11.glColor4f(0.97F, 0.00F, 0F, 1F);
            drawTexturedModalRect(guiLeft + 176, guiTop + 3, 0, 93, 28, 28);
        }

        GL11.glColor4f(1F, 1F, 1F, 1F);
        if (backGround != null) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(backGround);
            drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
        this.fontRendererObj.drawString(I18n.format("container.inventory", BronzeAge.INSTANSE), 8, this.ySize - 96 + 2, 4210752);
        String name = "";
        if (basicMachine != null)
            name = StatCollector.translateToLocal(basicMachine.getInventoryName());
        this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                if (button instanceof GuiButtonRedStone) {
                    if (basicMachine != null) {
                        GuiButtonRedStone buttonRedStone = (GuiButtonRedStone) button;
                        switch (basicMachine.getRedStoneMode()) {
                            case low:
                                buttonRedStone.setMode(RedstoneMode.high);
                                basicMachine.setRedstoneMode(RedstoneMode.high);
                                PacketHandler.INSTANCE.sendToServer(new MessageButton(basicMachine.xCoord, basicMachine.yCoord, basicMachine.zCoord, 0));
                                break;
                            case high:
                                buttonRedStone.setMode(RedstoneMode.disabled);
                                basicMachine.setRedstoneMode(RedstoneMode.disabled);
                                PacketHandler.INSTANCE.sendToServer(new MessageButton(basicMachine.xCoord, basicMachine.yCoord, basicMachine.zCoord, 1));
                                break;
                            case disabled:
                                buttonRedStone.setMode(RedstoneMode.low);
                                basicMachine.setRedstoneMode(RedstoneMode.low);
                                PacketHandler.INSTANCE.sendToServer(new MessageButton(basicMachine.xCoord, basicMachine.yCoord, basicMachine.zCoord, 2));
                                break;
                        }
                        break;
                    }
                }
        }
    }

    @Override
    public void handleMouseInput() {
        super.handleMouseInput();
        int x = Mouse.getEventX() * this.width / this.mc.displayWidth;
        int y = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;

        mouseX = x - guiLeft;
        mouseY = y - guiTop;
    }

    public void drawElement(int x, int y, int u, int v, int width, int height) {
        this.drawTexturedModalRect(guiLeft + x, guiTop + y, u, v, width, height);
    }

    public void drawSteamTank(int x, int y) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(elements);
        int j = getValueScaled(basicMachine.getSteamAmount(), basicMachine.getSteamCapacity(), 66);
        drawElement(x, y - j, 240, 66 - j, 16, j);
    }

    public int getValueScaled(int value, int max, int scale) {
        return (value * scale) / max;
    }

    public void drawToolTipOverArea(int mouseX, int mouseY, int minX, int minY, int maxX, int maxY, List<String> list, FontRenderer font) {
        if (list != null && font != null) {
            if ((mouseX >= minX && mouseX <= maxX) && (mouseY >= minY && mouseY <= maxY))
                drawHoveringText(list, mouseX, mouseY, font);
        }
    }
}
