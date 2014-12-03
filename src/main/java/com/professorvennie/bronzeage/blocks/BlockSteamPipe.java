package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.tileentitys.TileEntitySteamPipe;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by ProfessorVennie on 10/21/2014 at 8:00 PM.
 */
public class BlockSteamPipe extends BlockBase implements ITileEntityProvider {

    protected BlockSteamPipe() {
        super(Material.rock, "steamPipe");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntitySteamPipe();
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
}
