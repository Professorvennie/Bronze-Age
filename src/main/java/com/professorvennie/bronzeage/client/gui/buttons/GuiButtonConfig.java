package com.professorvennie.bronzeage.client.gui.buttons;

import com.professorvennie.bronzeage.api.tiles.ISideConfigurable;
import com.professorvennie.bronzeage.client.helpers.RenderHelper;
import com.professorvennie.bronzeage.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ProfessorVennie on 1/1/2015 at 12:49 PM.
 */
public class GuiButtonConfig extends GuiButton {

    private ISideConfigurable configurable;

    public GuiButtonConfig(int id, int x, int y, ISideConfigurable configurable) {
        super(id, x, y, 22, 22, "");
        this.configurable = configurable;
    }

    @Override
    public void drawButton(Minecraft minecraft, int x, int y) {
        minecraft.renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/guiElements.png"));

        this.enabled = x >= this.xPosition && y >= this.yPosition && x < this.xPosition + this.width && y < this.yPosition + this.height;
        int k = getHoverState(enabled);
        List<String> tooltip = new ArrayList<String>();

        drawTexturedModalRect(xPosition, yPosition, 29, 161, 22, 22);

        if (k == 2) {
            tooltip.clear();
            tooltip.add("tooltip.Config");
            RenderHelper.renderTooltip(x, y, tooltip);
        }
    }
}
