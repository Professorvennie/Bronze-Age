package com.professorvennie.bronzeage.client.gui;

import com.professorvennie.bronzeage.BronzeAge;
import com.professorvennie.bronzeage.blocks.BlockBasicMachine;
import com.professorvennie.bronzeage.client.gui.buttons.GuiButtonBack;
import com.professorvennie.bronzeage.client.gui.buttons.GuiButtonSides;
import com.professorvennie.bronzeage.common.containers.ContainerFake;
import com.professorvennie.bronzeage.lib.Reference;
import com.professorvennie.bronzeage.tileentitys.TileEntityBasicMachine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

/**
 * Created by ProfessorVennie on 1/2/2015 at 1:12 AM.
 */
public class GuiConfig extends GuiContainer {

    protected int mouseX = 0, mouseY = 0;
    private TileEntityBasicMachine basicMachine;
    private BlockBasicMachine blockBasicMachine;

    public GuiConfig(EntityPlayer player, TileEntityBasicMachine tileEntity, BlockBasicMachine block) {
        super(new ContainerFake(player.inventory));
        this.basicMachine = tileEntity;
        this.blockBasicMachine = block;
    }

    @Override
    public void initGui() {
        super.initGui();
        buttonList.add(new GuiButtonBack(0, guiLeft + xSize + 1, guiTop + ySize - 160));
        buttonList.add(new GuiButtonSides(1, guiLeft + (xSize / 2 - 8), guiTop + (ySize / 2 - (8 + 80 / 2)), basicMachine.getModeOnSide(ForgeDirection.NORTH), ForgeDirection.NORTH));
        buttonList.add(new GuiButtonSides(1, guiLeft + (xSize / 2 - 8), guiTop + (ySize / 2 - (8 + 80 / 2 + 18)), basicMachine.getModeOnSide(ForgeDirection.UP), ForgeDirection.UP));
        buttonList.add(new GuiButtonSides(1, guiLeft + (xSize / 2 - 8), guiTop + (ySize / 2 - (8 + 80 / 2 - 18)), basicMachine.getModeOnSide(ForgeDirection.DOWN), ForgeDirection.DOWN));
        buttonList.add(new GuiButtonSides(1, guiLeft + (xSize / 2 - 8 - 18), guiTop + (ySize / 2 - (8 + 80 / 2)), basicMachine.getModeOnSide(ForgeDirection.EAST), ForgeDirection.EAST));
        buttonList.add(new GuiButtonSides(1, guiLeft + (xSize / 2 - 8 + 18), guiTop + (ySize / 2 - (8 + 80 / 2)), basicMachine.getModeOnSide(ForgeDirection.WEST), ForgeDirection.WEST));
        buttonList.add(new GuiButtonSides(1, guiLeft + (xSize / 2 - 8 + 18), guiTop + (ySize / 2 - (8 + 80 / 2 - 18)), basicMachine.getModeOnSide(ForgeDirection.SOUTH), ForgeDirection.SOUTH));


    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/guiBlank.png"));
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/guiElements.png"));
        GL11.glColor4f(0.97F, 0.00F, 0F, 1F);
        drawTexturedModalRect(guiLeft + 176, guiTop + 3, 0, 93, 28, 28);
        GL11.glColor4f(1F, 1F, 1F, 1F);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button instanceof GuiButtonBack) {
            Minecraft.getMinecraft().thePlayer.openGui(BronzeAge.INSTANSE, blockBasicMachine.getGuiId(), Minecraft.getMinecraft().theWorld, basicMachine.xCoord, basicMachine.yCoord, basicMachine.zCoord);
        } else if (button instanceof GuiButtonSides) {
            basicMachine.changeMode(((GuiButtonSides) button).getDirection());
            ((GuiButtonSides) button).setMode(basicMachine.getModeOnSide(((GuiButtonSides) button).getDirection()));
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
}