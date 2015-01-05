package com.professorvennie.bronzeage.client.gui.buttons;

import com.professorvennie.bronzeage.api.enums.RedstoneMode;
import com.professorvennie.bronzeage.client.helpers.RenderHelper;
import com.professorvennie.bronzeage.lib.Reference;
import com.professorvennie.bronzeage.tileentitys.TileEntityBasicMachine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ProfessorVennie on 12/14/2014 at 7:17 PM.
 */
public class GuiButtonRedStone extends GuiButton {

    private RedstoneMode mode;
    private TileEntityBasicMachine tile;

    public GuiButtonRedStone(int id, int x, int y, TileEntityBasicMachine tile) {
        super(id, x, y, 18, 18, "");
        this.mode = tile.getRedStoneMode();
        this.tile = tile;
    }

    @Override
    public void drawButton(Minecraft minecraft, int x, int y) {
        minecraft.renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/guiElements.png"));

        this.field_146123_n = x >= this.xPosition && y >= this.yPosition && x < this.xPosition + this.width && y < this.yPosition + this.height;
        int k = getHoverState(field_146123_n);
        List<String> tooltip = new ArrayList<String>();
        //int tooltipY = (tooltip.size() - 1) * 10;

        switch (mode) {
            case low:
                drawTexturedModalRect(xPosition, yPosition, 0, 122, 22, 22);
                tooltip.clear();
                tooltip.add(StatCollector.translateToLocal("tooltip.redStone.low"));
                if (k == 2)
                    RenderHelper.renderTooltip(x, y, tooltip);
                break;
            case high:
                drawTexturedModalRect(xPosition, yPosition, 0, 168, 22, 22);
                tooltip.clear();
                tooltip.add(StatCollector.translateToLocal("tooltip.redStone.high"));
                if (k == 2)
                    RenderHelper.renderTooltip(x, y, tooltip);
                break;
            case disabled:
                drawTexturedModalRect(xPosition, yPosition, 0, 145, 22, 22);
                tooltip.clear();
                tooltip.add(StatCollector.translateToLocal("tooltip.redStone.disabled"));
                if (k == 2)
                    RenderHelper.renderTooltip(x, y, tooltip);
                break;
        }
    }

    public RedstoneMode getMode() {
        return mode;
    }

    public void setMode(RedstoneMode mode) {
        this.mode = mode;
    }
}
