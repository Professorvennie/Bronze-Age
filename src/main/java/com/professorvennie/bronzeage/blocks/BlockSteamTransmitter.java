package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.tileentitys.TileEntitySteamTransmitter;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by ProfessorVennie on 1/31/2015 at 9:05 PM.
 */
public class BlockSteamTransmitter extends BlockBase implements ITileEntityProvider {

    protected BlockSteamTransmitter() {
        super(Material.iron, "steamTransmitter");
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {

    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntitySteamTransmitter();
    }
}
