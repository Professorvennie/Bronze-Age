package com.professorvennie.bronzeage.client.gui.buttons;

import com.professorvennie.bronzeage.api.enums.SideMode;
import com.professorvennie.bronzeage.client.helpers.RenderHelper;
import com.professorvennie.bronzeage.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ProfessorVennie on 1/5/2015 at 7:29 PM.
 */
public class GuiButtonSides extends GuiButton {

    private SideMode mode;

    public GuiButtonSides(int id, int x, int y, SideMode mode) {
        super(id, x, y, 16, 16, "");
        this.mode = mode;
    }

    @Override
    public void drawButton(Minecraft minecraft, int x, int y) {
        minecraft.renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/guiElements.png"));

        this.field_146123_n = x >= this.xPosition && y >= this.yPosition && x < this.xPosition + this.width && y < this.yPosition + this.height;
        int k = getHoverState(field_146123_n);
        List<String> tooltip = new ArrayList<String>();

        switch (mode) {
            case IMPORT:
                tooltip.clear();
                tooltip.add("toolTip.config.import");
                break;
            case EXPORT:
                tooltip.clear();
                tooltip.add("toolTip.config.export");
                break;
            case DISABLED:
                tooltip.clear();
                tooltip.add("toolTip.config.disabled");
                break;
            default:
                tooltip.clear();
                tooltip.add("toolTip.config.unknown");
                break;
        }

        if (k == 2)
            RenderHelper.renderTooltip(x, y, tooltip);
    }
}
