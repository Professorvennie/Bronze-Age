package com.professorvennie.bronzeage.client.gui.buttons;

import com.professorvennie.bronzeage.api.enums.SideMode;
import com.professorvennie.bronzeage.client.helpers.RenderHelper;
import com.professorvennie.bronzeage.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ProfessorVennie on 1/5/2015 at 7:29 PM.
 */
public class GuiButtonSides extends GuiButton {

    private SideMode sideMode;
    private SideMode tankMode;
    private ForgeDirection direction;

    public GuiButtonSides(int id, int x, int y, SideMode modeSide, SideMode tankMode, ForgeDirection direction) {
        super(id, x, y, 16, 16, "");
        this.sideMode = modeSide;
        this.tankMode = tankMode;
        this.direction = direction;
    }

    @Override
    public void drawButton(Minecraft minecraft, int x, int y) {
        minecraft.renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/guiElements.png"));

        switch (direction) {
            case NORTH:
                drawTexturedModalRect(xPosition, yPosition, 224, 241, 16, 16);
                break;
            case UP:
                drawTexturedModalRect(xPosition, yPosition, 240, 240, 16, 16);
                break;
            default:
                drawTexturedModalRect(xPosition, yPosition, 240, 224, 16, 16);
                break;
        }


    }

    public void renderToolTip(int x, int y) {
        this.field_146123_n = x >= this.xPosition && y >= this.yPosition && x < this.xPosition + this.width && y < this.yPosition + this.height;
        int k = getHoverState(field_146123_n);
        List<String> tooltip = new ArrayList<String>();

        tooltip.clear();
        tooltip.add("Sides:");
        tooltip.add(direction + ": " + StatCollector.translateToLocal("toolTip.config." + sideMode.getTooltip()));
        tooltip.add("Tanks");
        tooltip.add(direction + ": " + StatCollector.translateToLocal("toolTip.config." + tankMode.getTooltip()));

        if (k == 2)
            RenderHelper.renderTooltip(x, y, tooltip);
    }

    public ForgeDirection getDirection() {
        return direction;
    }

    public SideMode getSideMode() {
        return sideMode;
    }

    public void setSideMode(SideMode sideMode) {
        this.sideMode = sideMode;
    }

    public SideMode getTankMode() {
        return tankMode;
    }

    public void setTankMode(SideMode tankMode) {
        this.tankMode = tankMode;
    }
}