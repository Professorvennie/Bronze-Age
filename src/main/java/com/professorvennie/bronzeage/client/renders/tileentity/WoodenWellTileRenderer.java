package com.professorvennie.bronzeage.client.renders.tileentity;

import com.professorvennie.bronzeage.lib.Reference;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by ProfessorVennie on 1/31/2015 at 2:31 PM.
 */
public class WoodenWellTileRenderer extends TileEntitySpecialRenderer {

    private float pixel = 1F / 16F;
    private int textureWidth = 16;
    private int textureHeight = 16;

    /*@Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glTranslatef((float) x, (float) y, (float) z);
        Tessellator tessellator = Tessellator.instance;
        this.bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/model/woodenWellPipe.png"));
        tessellator.startDrawingQuads();

        tessellator.addVertexWithUV((9) / 2 * pixel, 0, 1 - (9) / 2 * pixel, 12 * (1F / textureWidth), 15 * (1F / textureHeight));
        tessellator.addVertexWithUV((9) / 2 * pixel, 1, 1 - (9) / 2 * pixel, 12 * (1F / textureWidth), 0 * (1F / textureHeight));
        tessellator.addVertexWithUV((9) / 2 * pixel, 1, (9) / 2 * pixel, 4 * (1F / textureWidth), 0 * (1F / textureHeight));
        tessellator.addVertexWithUV((9) / 2 * pixel, 0, (9) / 2 * pixel, 4 * (1F / textureWidth), 15 * (1F / textureHeight));

        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 0, 1 - (9) / 2 * pixel, 12 * (1F / textureWidth), 15 * (1F / textureHeight));
        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 1, 1 - (9) / 2 * pixel, 12 * (1F / textureWidth), 0 * (1F / textureHeight));
        tessellator.addVertexWithUV((9) / 2 * pixel, 1, 1 - (9) / 2 * pixel, 4 * (1F / textureWidth), 0 * (1F / textureHeight));
        tessellator.addVertexWithUV((9) / 2 * pixel, 0, 1 - (9) / 2 * pixel, 4 * (1F / textureWidth), 15 * (1F / textureHeight));

        tessellator.addVertexWithUV((9) / 2 * pixel, 0, (9) / 2 * pixel, 12 * (1F / textureWidth), 15 * (1F / textureHeight));
        tessellator.addVertexWithUV((9) / 2 * pixel, 1, (9) / 2 * pixel, 12 * (1F / textureWidth), 0 * (1F / textureHeight));
        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 1, (9) / 2 * pixel, 4 * (1F / textureWidth), 0 * (1F / textureHeight));
        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 0, (9) / 2 * pixel, 4 * (1F / textureWidth), 15 * (1F / textureHeight));

        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 0, (9) / 2 * pixel, 12 * (1F / textureWidth), 15 * (1F / textureHeight));
        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 1, (9) / 2 * pixel, 12 * (1F / textureWidth), 0 * (1F / textureHeight));
        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 1, 1 - (9) / 2 * pixel, 4 * (1F / textureWidth), 0 * (1F / textureHeight));
        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 0, 1 - (9) / 2 * pixel, 4 * (1F / textureWidth), 15 * (1F / textureHeight));

        //inside
        tessellator.addVertexWithUV((9) / 2 * pixel, 0, 1 - (9) / 2 * pixel, 12 * (1F / textureWidth), 15 * (1F / textureHeight));
        tessellator.addVertexWithUV((9) / 2 * pixel, 0, (9) / 2 * pixel, 4 * (1F / textureWidth), 15 * (1F / textureHeight));
        tessellator.addVertexWithUV((9) / 2 * pixel, 1, (9) / 2 * pixel, 4 * (1F / textureWidth), 0 * (1F / textureHeight));
        tessellator.addVertexWithUV((9) / 2 * pixel, 1, 1 - (9) / 2 * pixel, 12 * (1F / textureWidth), 0 * (1F / textureHeight));

        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 0, 1 - (9) / 2 * pixel, 12 * (1F / textureWidth), 15 * (1F / textureHeight));
        tessellator.addVertexWithUV((9) / 2 * pixel, 0, 1 - (9) / 2 * pixel, 4 * (1F / textureWidth), 15 * (1F / textureHeight));
        tessellator.addVertexWithUV((9) / 2 * pixel, 1, 1 - (9) / 2 * pixel, 4 * (1F / textureWidth), 0 * (1F / textureHeight));
        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 1, 1 - (9) / 2 * pixel, 12 * (1F / textureWidth), 0 * (1F / textureHeight));

        tessellator.addVertexWithUV((9) / 2 * pixel, 0, (9) / 2 * pixel, 12 * (1F / textureWidth), 15 * (1F / textureHeight));
        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 0, (9) / 2 * pixel, 4 * (1F / textureWidth), 15 * (1F / textureHeight));
        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 1, (9) / 2 * pixel, 4 * (1F / textureWidth), 0 * (1F / textureHeight));
        tessellator.addVertexWithUV((9) / 2 * pixel, 1, (9) / 2 * pixel, 12 * (1F / textureWidth), 0 * (1F / textureHeight));

        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 0, (9) / 2 * pixel, 12 * (1F / textureWidth), 15 * (1F / textureHeight));
        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 0, 1 - (9) / 2 * pixel, 4 * (1F / textureWidth), 15 * (1F / textureHeight));
        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 1, 1 - (9) / 2 * pixel, 4 * (1F / textureWidth), 0 * (1F / textureHeight));
        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 1, (9) / 2 * pixel, 12 * (1F / textureWidth), 0 * (1F / textureHeight));

        tessellator.draw();
        GL11.glPopMatrix();
    }

    public void renderInv() {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((9) / 2 * pixel, 0, 1 - (9) / 2 * pixel, 12 * (1F / textureWidth), 15 * (1F / textureHeight));
        tessellator.addVertexWithUV((9) / 2 * pixel, 1, 1 - (9) / 2 * pixel, 12 * (1F / textureWidth), 0 * (1F / textureHeight));
        tessellator.addVertexWithUV((9) / 2 * pixel, 1, (9) / 2 * pixel, 4 * (1F / textureWidth), 0 * (1F / textureHeight));
        tessellator.addVertexWithUV((9) / 2 * pixel, 0, (9) / 2 * pixel, 4 * (1F / textureWidth), 15 * (1F / textureHeight));

        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 0, 1 - (9) / 2 * pixel, 12 * (1F / textureWidth), 15 * (1F / textureHeight));
        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 1, 1 - (9) / 2 * pixel, 12 * (1F / textureWidth), 0 * (1F / textureHeight));
        tessellator.addVertexWithUV((9) / 2 * pixel, 1, 1 - (9) / 2 * pixel, 4 * (1F / textureWidth), 0 * (1F / textureHeight));
        tessellator.addVertexWithUV((9) / 2 * pixel, 0, 1 - (9) / 2 * pixel, 4 * (1F / textureWidth), 15 * (1F / textureHeight));

        tessellator.addVertexWithUV((9) / 2 * pixel, 0, (9) / 2 * pixel, 12 * (1F / textureWidth), 15 * (1F / textureHeight));
        tessellator.addVertexWithUV((9) / 2 * pixel, 1, (9) / 2 * pixel, 12 * (1F / textureWidth), 0 * (1F / textureHeight));
        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 1, (9) / 2 * pixel, 4 * (1F / textureWidth), 0 * (1F / textureHeight));
        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 0, (9) / 2 * pixel, 4 * (1F / textureWidth), 15 * (1F / textureHeight));

        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 0, (9) / 2 * pixel, 12 * (1F / textureWidth), 15 * (1F / textureHeight));
        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 1, (9) / 2 * pixel, 12 * (1F / textureWidth), 0 * (1F / textureHeight));
        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 1, 1 - (9) / 2 * pixel, 4 * (1F / textureWidth), 0 * (1F / textureHeight));
        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 0, 1 - (9) / 2 * pixel, 4 * (1F / textureWidth), 15 * (1F / textureHeight));

        //inside
        tessellator.addVertexWithUV((9) / 2 * pixel, 0, 1 - (9) / 2 * pixel, 12 * (1F / textureWidth), 15 * (1F / textureHeight));
        tessellator.addVertexWithUV((9) / 2 * pixel, 0, (9) / 2 * pixel, 4 * (1F / textureWidth), 15 * (1F / textureHeight));
        tessellator.addVertexWithUV((9) / 2 * pixel, 1, (9) / 2 * pixel, 4 * (1F / textureWidth), 0 * (1F / textureHeight));
        tessellator.addVertexWithUV((9) / 2 * pixel, 1, 1 - (9) / 2 * pixel, 12 * (1F / textureWidth), 0 * (1F / textureHeight));

        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 0, 1 - (9) / 2 * pixel, 12 * (1F / textureWidth), 15 * (1F / textureHeight));
        tessellator.addVertexWithUV((9) / 2 * pixel, 0, 1 - (9) / 2 * pixel, 4 * (1F / textureWidth), 15 * (1F / textureHeight));
        tessellator.addVertexWithUV((9) / 2 * pixel, 1, 1 - (9) / 2 * pixel, 4 * (1F / textureWidth), 0 * (1F / textureHeight));
        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 1, 1 - (9) / 2 * pixel, 12 * (1F / textureWidth), 0 * (1F / textureHeight));

        tessellator.addVertexWithUV((9) / 2 * pixel, 0, (9) / 2 * pixel, 12 * (1F / textureWidth), 15 * (1F / textureHeight));
        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 0, (9) / 2 * pixel, 4 * (1F / textureWidth), 15 * (1F / textureHeight));
        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 1, (9) / 2 * pixel, 4 * (1F / textureWidth), 0 * (1F / textureHeight));
        tessellator.addVertexWithUV((9) / 2 * pixel, 1, (9) / 2 * pixel, 12 * (1F / textureWidth), 0 * (1F / textureHeight));

        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 0, (9) / 2 * pixel, 12 * (1F / textureWidth), 15 * (1F / textureHeight));
        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 0, 1 - (9) / 2 * pixel, 4 * (1F / textureWidth), 15 * (1F / textureHeight));
        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 1, 1 - (9) / 2 * pixel, 4 * (1F / textureWidth), 0 * (1F / textureHeight));
        tessellator.addVertexWithUV(1 - (9) / 2 * pixel, 1, (9) / 2 * pixel, 12 * (1F / textureWidth), 0 * (1F / textureHeight));
        tessellator.draw();
    }*/
}
