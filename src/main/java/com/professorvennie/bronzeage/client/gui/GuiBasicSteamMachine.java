package com.professorvennie.bronzeage.client.gui;

import com.professorvennie.bronzeage.BronzeAge;
import com.professorvennie.bronzeage.client.gui.buttons.GuiButtonConfig;
import com.professorvennie.bronzeage.lib.GuiIds;
import com.professorvennie.bronzeage.tileentitys.TileEntityBasicSteamMachine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.inventory.Container;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ProfessorVennie on 1/31/2015 at 6:22 PM.
 */
public class GuiBasicSteamMachine extends GuiBase {

    private TileEntityBasicSteamMachine basicSteamMachine;
    private boolean drawSteam;

    public GuiBasicSteamMachine(Container container, TileEntityBasicSteamMachine basicMachine, boolean drawSteamTank) {
        super(container, basicMachine);
        this.basicSteamMachine = basicMachine;
        this.drawSteam = drawSteamTank;
    }

    public GuiBasicSteamMachine(Container container, TileEntityBasicSteamMachine basicMachine){
        this(container, basicMachine, true);
    }

    @Override
    public void initGui() {
        super.initGui();
        buttonList.add(new GuiButtonConfig(1, guiLeft + xSize + 1, guiTop + ySize - 132, basicSteamMachine));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        super.drawGuiContainerBackgroundLayer(p_146976_1_, p_146976_2_, p_146976_3_);

        if (basicSteamMachine != null) {
            GL11.glColor4f(0.97F, 0.00F, 0F, 1F);
            Minecraft.getMinecraft().getTextureManager().bindTexture(elements);
            drawTexturedModalRect(guiLeft + 176, guiTop + 31, 0, 93, 28, 28);
        }
        GL11.glColor4f(1F, 1F, 1F, 1F);

        if(drawSteam)
            drawSteamTank(11, 74);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
        super.drawGuiContainerForegroundLayer(p_146979_1_, p_146979_2_);

        if(drawSteam) {
            List<String> text = new ArrayList<String>();
            text.clear();
            text.add("Steam");
            text.add(basicSteamMachine.getSteamAmount() + "/" + basicSteamMachine.getSteamCapacity() + "mB");
            drawToolTipOverArea(mouseX, mouseY, 11, 8, 26, 73, text, fontRendererObj);
        }

    }

    @Override
    protected void actionPerformed(GuiButton button) {
        super.actionPerformed(button);

        switch (button.id) {
            case 1:
                if (button instanceof GuiButtonConfig)
                    Minecraft.getMinecraft().thePlayer.openGui(BronzeAge.INSTANSE, GuiIds.CONFIG, Minecraft.getMinecraft().theWorld, basicSteamMachine.xCoord, basicSteamMachine.yCoord, basicSteamMachine.zCoord);
                break;
        }
    }

    public void drawSteamTank(int x, int y) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(elements);
        int j = getValueScaled(basicSteamMachine.getSteamAmount(), basicSteamMachine.getSteamCapacity(), 66);
        drawElement(x, y - j, 240, 66 - j, 16, j);
    }

    public void drawProgressArrow(int x, int y){
        int j = basicSteamMachine.getProgressScaled(24);
        drawElement(x, y, 51, 6, j + 1, 16);
    }
}
