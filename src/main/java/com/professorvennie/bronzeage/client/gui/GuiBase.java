package com.professorvennie.bronzeage.client.gui;

import com.professorvennie.bronzeage.BronzeAge;
import com.professorvennie.bronzeage.api.enums.RedstoneMode;
import com.professorvennie.bronzeage.client.gui.buttons.GuiButtonRedStone;
import com.professorvennie.bronzeage.core.network.MessageButton;
import com.professorvennie.bronzeage.core.network.PacketHandler;
import com.professorvennie.bronzeage.lib.Reference;
import com.professorvennie.bronzeage.tileentitys.TileEntityBasicMachine;
import com.professorvennie.bronzeage.tileentitys.TileEntityBasicSidedInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.util.List;

/**
 * Created by ProfessorVennie on 10/23/2014 at 9:48 PM.
 */
public class GuiBase extends GuiContainer {

    public ResourceLocation backGround;
    public ResourceLocation elements = new ResourceLocation(Reference.MOD_ID, "textures/gui/guiElements.png");
    public TileEntityBasicMachine basicSteamMachine;
    protected int mouseX = 0, mouseY = 0;

    public GuiBase(Container container, TileEntityBasicSidedInventory tileEntity) {
        this(container, null);
    }

    public GuiBase(Container container, TileEntityBasicMachine basicMachine) {
        super(container);
        this.basicSteamMachine = basicMachine;
    }

    @Override
    public void initGui() {
        super.initGui();
        buttonList.add(new GuiButtonRedStone(0, guiLeft + xSize + 1, guiTop + ySize - 160, basicSteamMachine));
        //buttonList.add(new GuiButtonConfig(1, guiLeft + xSize + 1, guiTop + ySize - 132, basicSteamMachine));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f(0F, 0.30F, 0.97F, 1F);

        Minecraft.getMinecraft().getTextureManager().bindTexture(elements);

        if (basicSteamMachine != null) {
            GL11.glColor4f(0.97F, 0.00F, 0F, 1F);
            drawTexturedModalRect(guiLeft + 176, guiTop + 3, 0, 93, 28, 28);
            //drawTexturedModalRect(guiLeft + 176, guiTop + 31, 0, 93, 28, 28);
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
        if (basicSteamMachine != null)
            name = StatCollector.translateToLocal("container." + basicSteamMachine.getInventoryName());
        this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                if (button instanceof GuiButtonRedStone) {
                    if (basicSteamMachine != null) {
                        GuiButtonRedStone buttonRedStone = (GuiButtonRedStone) button;
                        switch (basicSteamMachine.getRedStoneMode()) {
                            case low:
                                buttonRedStone.setMode(RedstoneMode.high);
                                basicSteamMachine.setRedstoneMode(RedstoneMode.high);
                                PacketHandler.INSTANCE.sendToServer(new MessageButton(basicSteamMachine.xCoord, basicSteamMachine.yCoord, basicSteamMachine.zCoord, 0));
                                break;
                            case high:
                                buttonRedStone.setMode(RedstoneMode.disabled);
                                basicSteamMachine.setRedstoneMode(RedstoneMode.disabled);
                                PacketHandler.INSTANCE.sendToServer(new MessageButton(basicSteamMachine.xCoord, basicSteamMachine.yCoord, basicSteamMachine.zCoord, 1));
                                break;
                            case disabled:
                                buttonRedStone.setMode(RedstoneMode.low);
                                basicSteamMachine.setRedstoneMode(RedstoneMode.low);
                                PacketHandler.INSTANCE.sendToServer(new MessageButton(basicSteamMachine.xCoord, basicSteamMachine.yCoord, basicSteamMachine.zCoord, 2));
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

    public int getValueScaled(int value, int max, int scale) {
        return (value * scale) / max;
    }

    public void drawToolTipOverArea(int mouseX, int mouseY, int minX, int minY, int maxX, int maxY, List<String> list, FontRenderer font) {
        if (list != null && font != null) {
            if ((mouseX >= minX && mouseX <= maxX) && (mouseY >= minY && mouseY <= maxY))
                drawHoveringText(list, mouseX, mouseY, font);
        }
    }

    public void drawTanks(FluidTank tank, int scale, int x, int y, int width) {
        int j;
        if (tank.getFluid() != null) {
            j = getValueScaled(tank.getFluidAmount(), tank.getCapacity(), scale);
            this.drawFluid(guiLeft + x, guiTop + y - j, tank.getFluid(), width, j);
        }
    }

    public void drawFluid(int x, int y, FluidStack fluid, int width, int height) {
        if (fluid == null || fluid.getFluid() == null)
            return;
        mc.renderEngine.bindTexture(new ResourceLocation("textures/atlas/blocks.png"));
        GL11.glColor3ub((byte) (fluid.getFluid().getColor() >> 16 & 0xFF), (byte) (fluid.getFluid().getColor() >> 8 & 0xFF), (byte) (fluid.getFluid().getColor() & 0xFF));
        drawTiledTexture(x, y, fluid.getFluid().getIcon(fluid), width, height);
    }

    public void drawTiledTexture(int x, int y, IIcon icon, int width, int height) {
        int i = 0;
        int j = 0;

        int drawHeight = 0;
        int drawWidth = 0;

        for (i = 0; i < width; i += 16) {
            for (j = 0; j < height; j += 16) {
                drawWidth = (width - i) < 16 ? (width - i) : 16;
                drawHeight = (height - j) < 16 ? (height - j) : 16;
                drawScaledTexturedModelRectFromIcon(x + i, y + j, icon, drawWidth, drawHeight);
            }
        }
        GL11.glColor4f(1f, 1f, 1f, 1F);
    }

    public void drawScaledTexturedModelRectFromIcon(int x, int y, IIcon icon, int width, int height) {
        if (icon == null)
            return;

        double minU = icon.getMinU();
        double maxU = icon.getMaxU();
        double minV = icon.getMinV();
        double maxV = icon.getMaxV();

        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x, y + height, this.zLevel, minU, minV + (maxV - minV) * height / 16F);
        tessellator.addVertexWithUV(x + width, y + height, this.zLevel, minU + (maxU - minU) * width / 16F, minV + (maxV - minV) * height / 16F);
        tessellator.addVertexWithUV(x + width, y, this.zLevel, minU + (maxU - minU) * width / 16F, minV);
        tessellator.addVertexWithUV(x, y, this.zLevel, minU, minV);
        tessellator.draw();
    }
}
