package com.professorvennie.bronzeage.client.gui;

import com.professorvennie.bronzeage.BronzeAge;
import com.professorvennie.bronzeage.blocks.BlockBasicMachine;
import com.professorvennie.bronzeage.client.gui.buttons.GuiButtonBack;
import com.professorvennie.bronzeage.client.gui.buttons.GuiButtonSides;
import com.professorvennie.bronzeage.common.containers.ContainerFake;
import com.professorvennie.bronzeage.core.network.MessageConfigUpdate;
import com.professorvennie.bronzeage.core.network.PacketHandler;
import com.professorvennie.bronzeage.lib.Reference;
import com.professorvennie.bronzeage.tileentitys.TileEntityBasicMachine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

/**
 * Created by ProfessorVennie on 1/2/2015 at 1:12 AM.
 */
public class GuiConfig extends GuiContainer {

    protected int mouseX = 0, mouseY = 0;
    private TileEntityBasicMachine basicMachine;
    private BlockBasicMachine blockBasicMachine;
    private GuiButtonSides north, south, east, west, up, down;

    public GuiConfig(EntityPlayer player, TileEntityBasicMachine tileEntity, BlockBasicMachine block) {
        super(new ContainerFake(player.inventory, tileEntity));
        this.basicMachine = tileEntity;
        this.blockBasicMachine = block;
    }

    @Override
    public void initGui() {
        super.initGui();
        north = new GuiButtonSides(1, guiLeft + (xSize / 2 - 8), guiTop + (ySize / 2 - (8 + 80 / 2)), basicMachine.getModeOnSide(ForgeDirection.NORTH), basicMachine.getTankModeOnSide(ForgeDirection.NORTH), ForgeDirection.NORTH);
        up = new GuiButtonSides(1, guiLeft + (xSize / 2 - 8), guiTop + (ySize / 2 - (8 + 80 / 2 + 18)), basicMachine.getModeOnSide(ForgeDirection.UP), basicMachine.getTankModeOnSide(ForgeDirection.UP), ForgeDirection.UP);
        down = new GuiButtonSides(1, guiLeft + (xSize / 2 - 8), guiTop + (ySize / 2 - (8 + 80 / 2 - 18)), basicMachine.getModeOnSide(ForgeDirection.DOWN), basicMachine.getTankModeOnSide(ForgeDirection.DOWN), ForgeDirection.DOWN);
        east = new GuiButtonSides(1, guiLeft + (xSize / 2 - 8 - 18), guiTop + (ySize / 2 - (8 + 80 / 2)), basicMachine.getModeOnSide(ForgeDirection.EAST), basicMachine.getTankModeOnSide(ForgeDirection.EAST), ForgeDirection.EAST);
        west = new GuiButtonSides(1, guiLeft + (xSize / 2 - 8 + 18), guiTop + (ySize / 2 - (8 + 80 / 2)), basicMachine.getModeOnSide(ForgeDirection.WEST), basicMachine.getTankModeOnSide(ForgeDirection.WEST), ForgeDirection.WEST);
        south = new GuiButtonSides(1, guiLeft + (xSize / 2 - 8 + 18), guiTop + (ySize / 2 - (8 + 80 / 2 - 18)), basicMachine.getModeOnSide(ForgeDirection.SOUTH), basicMachine.getTankModeOnSide(ForgeDirection.SOUTH), ForgeDirection.SOUTH);

        buttonList.add(new GuiButtonBack(0, guiLeft + xSize + 1, guiTop + ySize - 160));
        buttonList.add(north);
        buttonList.add(south);
        buttonList.add(east);
        buttonList.add(west);
        buttonList.add(up);
        buttonList.add(down);
    }

    @Override
    public void drawScreen(int x, int y, float p_73863_3_) {
        super.drawScreen(x, y, p_73863_3_);
        for (int i = 0; i < buttonList.size(); i++) {
            if (buttonList.get(i) instanceof GuiButtonSides)
                ((GuiButtonSides) buttonList.get(i)).renderToolTip(x, y);
        }
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

        GL11.glColor4f(0.0f, 0.5F, 1.0f, 1F);
        drawTexturedModalRect(guiLeft + 176, guiTop + 31, 0, 93, 28, 28);
        GL11.glColor4f(1F, 1F, 1F, 1F);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button instanceof GuiButtonBack) {
            Minecraft.getMinecraft().thePlayer.openGui(BronzeAge.INSTANSE, blockBasicMachine.getGuiId(), Minecraft.getMinecraft().theWorld, basicMachine.xCoord, basicMachine.yCoord, basicMachine.zCoord);
        } else if (button instanceof GuiButtonSides) {
            if (!(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                GuiButtonSides buttonSides = (GuiButtonSides) button;
                basicMachine.changeMode(buttonSides.getDirection());
                PacketHandler.INSTANCE.sendToServer(new MessageConfigUpdate(basicMachine.xCoord, basicMachine.yCoord, basicMachine.zCoord, buttonSides.getDirection(), false));
                buttonSides.setSideMode(basicMachine.getModeOnSide(buttonSides.getDirection()));
            } else {
                GuiButtonSides buttonSides = (GuiButtonSides) button;
                basicMachine.changeTankMode(buttonSides.getDirection());
                PacketHandler.INSTANCE.sendToServer(new MessageConfigUpdate(basicMachine.xCoord, basicMachine.yCoord, basicMachine.zCoord, buttonSides.getDirection(), true));
                buttonSides.setTankMode(basicMachine.getTankModeOnSide(buttonSides.getDirection()));
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
}