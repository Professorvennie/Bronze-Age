package com.professorvennie.bronzeage.client.renders.item;

import com.professorvennie.bronzeage.client.renders.tileentity.WoodenWellTileRenderer;
import com.professorvennie.bronzeage.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by ProfessorVennie on 1/31/2015 at 2:31 PM.
 */
public class WellPipeItemRenderer /*implements IItemRenderer */{

    /*@Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glScalef(1.2F, 1.2F, 1.2F);
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/model/woodenWellPipe.png"));
        new WoodenWellTileRenderer().renderInv();
        GL11.glScalef(-1.2F, -1.2F, -1.2F);
        GL11.glEnable(GL11.GL_LIGHTING);
    }*/
}
