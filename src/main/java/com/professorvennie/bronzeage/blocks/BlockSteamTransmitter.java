package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.tileentitys.TileEntitySteamTransmitter;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by ProfessorVennie on 1/31/2015 at 9:05 PM.
 */
public class BlockSteamTransmitter extends BlockBase implements ITileEntityProvider {

    protected BlockSteamTransmitter() {
        super(Material.IRON, "steamTransmitter");
    }

   /* @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
        super.onBlockPlacedBy(world, x, y, z, entity, itemStack);

        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            if (world.getTileEntity(x, y, z) instanceof TileEntitySteamTransmitter) {
                TileEntitySteamTransmitter te = (TileEntitySteamTransmitter) world.getTileEntity(x, y, z);

                // todo: allow the owner to unlock and prevent other from
                // stealing the lock
                if (te.owner == null) {
                    te.owner = player.getPersistentID();
                }

            }
        }

    }*/

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntitySteamTransmitter();
    }
}
