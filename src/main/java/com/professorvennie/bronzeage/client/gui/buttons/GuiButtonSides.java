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

    private SideMode mode;
    private ForgeDirection direction;

    public GuiButtonSides(int id, int x, int y, SideMode mode, ForgeDirection direction) {
        super(id, x, y, 16, 16, "");
        this.mode = mode;
        this.direction = direction;
    }

    @Override
    public void drawButton(Minecraft minecraft, int x, int y) {
        minecraft.renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/guiElements.png"));

        this.field_146123_n = x >= this.xPosition && y >= this.yPosition && x < this.xPosition + this.width && y < this.yPosition + this.height;
        int k = getHoverState(field_146123_n);
        List<String> tooltip = new ArrayList<String>();

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

        tooltip.clear();
        tooltip.add(direction + ": " + StatCollector.translateToLocal("toolTip.config." + mode.getTooltip()));

        if (k == 2)
            RenderHelper.renderTooltip(x, y, tooltip);
    }

    public ForgeDirection getDirection() {
        return direction;
    }

    public SideMode getMode() {
        return mode;
    }

    public void setMode(SideMode mode) {
        this.mode = mode;
    }
}