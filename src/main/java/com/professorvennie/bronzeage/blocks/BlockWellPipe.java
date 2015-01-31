package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.tileentitys.TileEntityWellPipe;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by ProfessorVennie on 1/31/2015 at 2:29 PM.
 */
public class BlockWellPipe extends BlockBase implements ITileEntityProvider {

    protected BlockWellPipe() {
        super(Material.wood, "wellPipe");
        float pixel = 1F / 16F;
        setBlockBounds((9) / 2 * pixel, 0, (9) / 2 * pixel, 1 - (9) / 2 * pixel, 1, 1 - (9) / 2 * pixel);
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityWellPipe();
    }
}
