package com.professorvennie.bronzeage.client.renders.item;

import com.professorvennie.bronzeage.client.renders.tileentity.TileEntityBasicSteamMachineRenderer;
import com.professorvennie.bronzeage.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by ProfessorVennie on 4/3/2015 at 5:16 PM.
 */
public class BasicMachineItemRenderer /*implements IItemRenderer */{

    private String texture;

    public BasicMachineItemRenderer(String texture){
        this.texture = texture;
    }

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
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/model/" + texture + ".png"));
        new TileEntityBasicSteamMachineRenderer(texture).renderInv();
        GL11.glEnable(GL11.GL_LIGHTING);
    }*/
}
