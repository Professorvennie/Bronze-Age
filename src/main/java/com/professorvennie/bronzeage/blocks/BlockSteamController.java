package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.tileentitys.TileEntitySteamController;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by ProfessorVennie on 10/21/2014 at 8:00 PM.
 */
public class BlockSteamController extends BlockBase implements ITileEntityProvider {

    protected BlockSteamController() {
        super(Material.iron, "steamController");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntitySteamController();
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {

    }
}
