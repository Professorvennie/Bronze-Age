package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.tileentitys.TileEntitySteamReceiver;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by ProfessorVennie on 1/31/2015 at 9:04 PM.
 */
public class BlockSteamReceiver extends BlockBase implements ITileEntityProvider {

    protected BlockSteamReceiver() {
        super(Material.IRON, "steamReceiver");
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntitySteamReceiver();
    }
}
