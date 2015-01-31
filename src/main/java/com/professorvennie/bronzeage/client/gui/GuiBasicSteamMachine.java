package com.professorvennie.bronzeage.client.gui;

import com.professorvennie.bronzeage.BronzeAge;
import com.professorvennie.bronzeage.client.gui.buttons.GuiButtonConfig;
import com.professorvennie.bronzeage.lib.GuiIds;
import com.professorvennie.bronzeage.tileentitys.TileEntityBasicSteamMachine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.inventory.Container;
import org.lwjgl.opengl.GL11;

/**
 * Created by ProfessorVennie on 1/31/2015 at 6:22 PM.
 */
public class GuiBasicSteamMachine extends GuiBase {

    private TileEntityBasicSteamMachine basicSteamMachine;

    public GuiBasicSteamMachine(Container container, TileEntityBasicSteamMachine basicMachine) {
        super(container, basicMachine);
        this.basicSteamMachine = basicMachine;
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
}
