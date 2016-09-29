package com.professorvennie.bronzeage.client.renders.tileentity;

import com.professorvennie.bronzeage.blocks.BlockBasicMachine;
import com.professorvennie.bronzeage.lib.Reference;
import com.professorvennie.bronzeage.tileentitys.TileEntityBasicMachine;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by ProfessorVennie on 3/20/2015 at 4:50 PM.
 */
public class TileEntityBasicSteamMachineRenderer extends TileEntitySpecialRenderer {

    private String texture;
    float pixel = 1F / 16F;
    int textureWidth = 48;
    int textureHeight = 32;

    public TileEntityBasicSteamMachineRenderer(String texture){
        this.texture = texture;
    }

    /*@Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float tick) {
        int metaData = tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);
        BlockBasicMachine basicMachine = (BlockBasicMachine)tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord);
        TileEntityBasicMachine tileEntityBasicMachine = (TileEntityBasicMachine)tile;
        boolean isActive = tileEntityBasicMachine.isActive;
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glTranslatef((float)x, (float)y, (float)z);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        {
            bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/model/" + texture + ".png"));
            if(metaData == 2){
                if(isActive){
                    tessellator.addVertexWithUV(1, 1, 0, 16 * (1F / textureWidth), 0);
                    tessellator.addVertexWithUV(1, 0, 0, 16 * (1F / textureWidth), 16 * (1F / textureHeight));
                    tessellator.addVertexWithUV(0, 0, 0, 32 * (1F / textureWidth), 16 * (1F / textureHeight));
                    tessellator.addVertexWithUV(0, 1, 0, 32 * (1F / textureWidth), 0);
                }else{
                    tessellator.addVertexWithUV(1, 1, 0, 0, 0);
                    tessellator.addVertexWithUV(1, 0, 0, 0, 16 * (1F / textureHeight));
                    tessellator.addVertexWithUV(0, 0, 0, 16 * (1F / textureWidth), 16 * (1F / textureHeight));
                    tessellator.addVertexWithUV(0, 1, 0, 16 * (1F / textureWidth), 0);
                }

                tessellator.addVertexWithUV(0, 0, 1, 16 * (1F / textureWidth), 32 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 1, 1, 16 * (1F / textureWidth), 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 1, 0, 0, 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 0, 0, 0, 32 * (1F / textureHeight));

                tessellator.addVertexWithUV(1, 1, 1, 16 * (1F / textureWidth), 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(1, 0, 1, 16 * (1F / textureWidth), 32 * (1F / textureHeight));
                tessellator.addVertexWithUV(1, 0, 0, 0, 32 * (1F / textureHeight));
                tessellator.addVertexWithUV(1, 1, 0, 0, 16 * (1F / textureHeight));

                //bottom
                tessellator.addVertexWithUV(1, 0, 1, 16 * (1F / textureWidth), 32 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 0, 1, 16 * (1F / textureWidth), 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 0, 0, 0, 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(1, 0, 0, 0, 32 * (1F / textureHeight));

                //top
                tessellator.addVertexWithUV(1, 0, 1, 16 * (1F / textureWidth), 32 * (1F / textureHeight));
                tessellator.addVertexWithUV(1, 1, 1, 16 * (1F / textureWidth), 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 1, 1, 0, 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 0, 1, 0, 32 * (1F / textureHeight));

                tessellator.addVertexWithUV(0, 1, 1, 32 * (1F / textureWidth), 0);
                tessellator.addVertexWithUV(1, 1, 1, 32 * (1F / textureWidth), 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(1, 1, 0, 48 * (1F / textureWidth), 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 1, 0, 48 * (1F / textureWidth), 0);
            }

            if(metaData == 3){
                if(isActive){
                    tessellator.addVertexWithUV(0, 0, 1, 32 * (1F / textureWidth), 16 * (1F / textureHeight));
                    tessellator.addVertexWithUV(1, 0, 1, 16 * (1F / textureWidth), 16 * (1F / textureHeight));
                    tessellator.addVertexWithUV(1, 1, 1, 16 * (1F / textureWidth), 0);
                    tessellator.addVertexWithUV(0, 1, 1, 32 * (1F / textureWidth), 0);
                }else{
                    tessellator.addVertexWithUV(0, 0, 1, 16 * (1F / textureWidth), 16 * (1F / textureHeight));
                    tessellator.addVertexWithUV(1, 0, 1, 0 * (1F / textureWidth), 16 * (1F / textureHeight));
                    tessellator.addVertexWithUV(1, 1, 1, 0 * (1F / textureWidth), 0);
                    tessellator.addVertexWithUV(0, 1, 1, 16 * (1F / textureWidth), 0);
                }

                //back
                tessellator.addVertexWithUV(1, 1, 0, 16 * (1F / textureWidth), 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(1, 0, 0, 16 * (1F / textureWidth), 32 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 0, 0, 0, 32 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 1, 0, 0, 16 * (1F / textureHeight));

                tessellator.addVertexWithUV(0, 0, 1, 16 * (1F / textureWidth), 32 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 1, 1, 16 * (1F / textureWidth), 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 1, 0, 0, 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 0, 0, 0, 32 * (1F / textureHeight));

                tessellator.addVertexWithUV(1, 1, 1, 16 * (1F / textureWidth), 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(1, 0, 1, 16 * (1F / textureWidth), 32 * (1F / textureHeight));
                tessellator.addVertexWithUV(1, 0, 0, 0, 32 * (1F / textureHeight));
                tessellator.addVertexWithUV(1, 1, 0, 0, 16 * (1F / textureHeight));

                //bottom
                tessellator.addVertexWithUV(1, 0, 1, 16 * (1F / textureWidth), 32 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 0, 1, 16 * (1F / textureWidth), 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 0, 0, 0, 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(1, 0, 0, 0, 32 * (1F / textureHeight));

                //top
                tessellator.addVertexWithUV(0, 1, 1, 32 * (1F / textureWidth), 0);
                tessellator.addVertexWithUV(1, 1, 1, 32 * (1F / textureWidth), 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(1, 1, 0, 48 * (1F / textureWidth), 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 1, 0, 48 * (1F / textureWidth), 0);
            }

            if(metaData == 4){
                if(isActive){
                    tessellator.addVertexWithUV(0, 0, 1, 32 * (1F / textureWidth), 16 * (1F / textureHeight));
                    tessellator.addVertexWithUV(0, 1, 1, 32 * (1F / textureWidth), 0 * (1F / textureHeight));
                    tessellator.addVertexWithUV(0, 1, 0, 16 * (1F / textureWidth), 0 * (1F / textureHeight));
                    tessellator.addVertexWithUV(0, 0, 0, 16 * (1F / textureWidth), 16 * (1F / textureHeight));
                }else{
                    tessellator.addVertexWithUV(0, 0, 1, 16 * (1F / textureWidth), 16 * (1F / textureHeight));
                    tessellator.addVertexWithUV(0, 1, 1, 16 * (1F / textureWidth), 0 * (1F / textureHeight));
                    tessellator.addVertexWithUV(0, 1, 0, 0, 0 * (1F / textureHeight));
                    tessellator.addVertexWithUV(0, 0, 0, 0, 16 * (1F / textureHeight));
                }

                //back
                tessellator.addVertexWithUV(1, 1, 0, 16 * (1F / textureWidth), 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(1, 0, 0, 16 * (1F / textureWidth), 32 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 0, 0, 0, 32 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 1, 0, 0, 16 * (1F / textureHeight));

                tessellator.addVertexWithUV(0, 0, 1, 16 * (1F / textureWidth), 32 * (1F / textureHeight));
                tessellator.addVertexWithUV(1, 0, 1, 0 * (1F / textureWidth), 32 * (1F / textureHeight));
                tessellator.addVertexWithUV(1, 1, 1, 0 * (1F / textureWidth), 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 1, 1, 16 * (1F / textureWidth), 16 * (1F / textureHeight));

                tessellator.addVertexWithUV(1, 1, 1, 16 * (1F / textureWidth), 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(1, 0, 1, 16 * (1F / textureWidth), 32 * (1F / textureHeight));
                tessellator.addVertexWithUV(1, 0, 0, 0, 32 * (1F / textureHeight));
                tessellator.addVertexWithUV(1, 1, 0, 0, 16 * (1F / textureHeight));

                //bottom
                tessellator.addVertexWithUV(1, 0, 1, 16 * (1F / textureWidth), 32 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 0, 1, 16 * (1F / textureWidth), 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 0, 0, 0, 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(1, 0, 0, 0, 32 * (1F / textureHeight));

                //top
                tessellator.addVertexWithUV(0, 1, 1, 32 * (1F / textureWidth), 0);
                tessellator.addVertexWithUV(1, 1, 1, 32 * (1F / textureWidth), 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(1, 1, 0, 48 * (1F / textureWidth), 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 1, 0, 48 * (1F / textureWidth), 0);
            }

            if(metaData == 5){
                if(isActive){
                    tessellator.addVertexWithUV(1, 1, 1, 32 * (1F / textureWidth), 0 * (1F / textureHeight));
                    tessellator.addVertexWithUV(1, 0, 1, 32 * (1F / textureWidth), 16 * (1F / textureHeight));
                    tessellator.addVertexWithUV(1, 0, 0, 16 * (1F / textureWidth), 16 * (1F / textureHeight));
                    tessellator.addVertexWithUV(1, 1, 0, 16 * (1F / textureWidth), 0 * (1F / textureHeight));
                }else{
                    tessellator.addVertexWithUV(1, 1, 1, 16 * (1F / textureWidth), 0 * (1F / textureHeight));
                    tessellator.addVertexWithUV(1, 0, 1, 16 * (1F / textureWidth), 16 * (1F / textureHeight));
                    tessellator.addVertexWithUV(1, 0, 0, 0, 16 * (1F / textureHeight));
                    tessellator.addVertexWithUV(1, 1, 0, 0, 0 * (1F / textureHeight));
                }

                //back
                tessellator.addVertexWithUV(1, 1, 0, 16 * (1F / textureWidth), 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(1, 0, 0, 16 * (1F / textureWidth), 32 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 0, 0, 0, 32 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 1, 0, 0, 16 * (1F / textureHeight));

                tessellator.addVertexWithUV(0, 0, 1, 16 * (1F / textureWidth), 32 * (1F / textureHeight));
                tessellator.addVertexWithUV(1, 0, 1, 0 * (1F / textureWidth), 32 * (1F / textureHeight));
                tessellator.addVertexWithUV(1, 1, 1, 0 * (1F / textureWidth), 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 1, 1, 16 * (1F / textureWidth), 16 * (1F / textureHeight));

                tessellator.addVertexWithUV(0, 0, 1, 16 * (1F / textureWidth), 32 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 1, 1, 16 * (1F / textureWidth), 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 1, 0, 0, 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 0, 0, 0, 32 * (1F / textureHeight));

                //bottom
                tessellator.addVertexWithUV(1, 0, 1, 16 * (1F / textureWidth), 32 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 0, 1, 16 * (1F / textureWidth), 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 0, 0, 0, 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(1, 0, 0, 0, 32 * (1F / textureHeight));

                //top
                tessellator.addVertexWithUV(0, 1, 1, 32 * (1F / textureWidth), 0);
                tessellator.addVertexWithUV(1, 1, 1, 32 * (1F / textureWidth), 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(1, 1, 0, 48 * (1F / textureWidth), 16 * (1F / textureHeight));
                tessellator.addVertexWithUV(0, 1, 0, 48 * (1F / textureWidth), 0);
            }
        }
        tessellator.draw();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

    public void renderInv() {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();

        tessellator.addVertexWithUV(1, 1, 1, 16 * (1F / textureWidth), 0 * (1F / textureHeight));
        tessellator.addVertexWithUV(1, 0, 1, 16 * (1F / textureWidth), 16 * (1F / textureHeight));
        tessellator.addVertexWithUV(1, 0, 0, 0, 16 * (1F / textureHeight));
        tessellator.addVertexWithUV(1, 1, 0, 0, 0 * (1F / textureHeight));

        //back
        tessellator.addVertexWithUV(1, 1, 0, 16 * (1F / textureWidth), 16 * (1F / textureHeight));
        tessellator.addVertexWithUV(1, 0, 0, 16 * (1F / textureWidth), 32 * (1F / textureHeight));
        tessellator.addVertexWithUV(0, 0, 0, 0, 32 * (1F / textureHeight));
        tessellator.addVertexWithUV(0, 1, 0, 0, 16 * (1F / textureHeight));

        tessellator.addVertexWithUV(0, 0, 1, 16 * (1F / textureWidth), 32 * (1F / textureHeight));
        tessellator.addVertexWithUV(1, 0, 1, 0 * (1F / textureWidth), 32 * (1F / textureHeight));
        tessellator.addVertexWithUV(1, 1, 1, 0 * (1F / textureWidth), 16 * (1F / textureHeight));
        tessellator.addVertexWithUV(0, 1, 1, 16 * (1F / textureWidth), 16 * (1F / textureHeight));

        tessellator.addVertexWithUV(0, 0, 1, 16 * (1F / textureWidth), 32 * (1F / textureHeight));
        tessellator.addVertexWithUV(0, 1, 1, 16 * (1F / textureWidth), 16 * (1F / textureHeight));
        tessellator.addVertexWithUV(0, 1, 0, 0, 16 * (1F / textureHeight));
        tessellator.addVertexWithUV(0, 0, 0, 0, 32 * (1F / textureHeight));

        //bottom
        tessellator.addVertexWithUV(1, 0, 1, 16 * (1F / textureWidth), 32 * (1F / textureHeight));
        tessellator.addVertexWithUV(0, 0, 1, 16 * (1F / textureWidth), 16 * (1F / textureHeight));
        tessellator.addVertexWithUV(0, 0, 0, 0, 16 * (1F / textureHeight));
        tessellator.addVertexWithUV(1, 0, 0, 0, 32 * (1F / textureHeight));

        //top
        tessellator.addVertexWithUV(0, 1, 1, 32 * (1F / textureWidth), 0);
        tessellator.addVertexWithUV(1, 1, 1, 32 * (1F / textureWidth), 16 * (1F / textureHeight));
        tessellator.addVertexWithUV(1, 1, 0, 48 * (1F / textureWidth), 16 * (1F / textureHeight));
        tessellator.addVertexWithUV(0, 1, 0, 48 * (1F / textureWidth), 0);

        tessellator.draw();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }*/
}