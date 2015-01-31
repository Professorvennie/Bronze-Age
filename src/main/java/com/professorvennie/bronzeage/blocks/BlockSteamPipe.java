package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.api.tiles.ISteamHandler;
import com.professorvennie.bronzeage.tileentitys.TileEntitySteamPipe;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

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

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (world.getTileEntity(x, y, z) instanceof TileEntitySteamPipe)
            player.addChatComponentMessage(new ChatComponentText(((TileEntitySteamPipe) world.getTileEntity(x, y, z)).getSteamAmount() + ""));
        return true;
    }

    @Override
    public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ) {
        if (world.getTileEntity(tileX, tileY, tileZ) instanceof ISteamHandler && world.getTileEntity(x, y, z) instanceof TileEntitySteamPipe) {
            TileEntitySteamPipe steamPipe = (TileEntitySteamPipe) world.getTileEntity(x, y, z);
            if (y + 1 == tileY)
                steamPipe.connections[0] = ForgeDirection.UP;
            else
                steamPipe.connections[0] = null;

            if (y - 1 == tileY)
                steamPipe.connections[1] = ForgeDirection.DOWN;
            else
                steamPipe.connections[1] = null;

            if (z + 1 == tileZ)
                steamPipe.connections[2] = ForgeDirection.SOUTH;
            else
                steamPipe.connections[2] = null;

            if (z - 1 == tileZ)
                steamPipe.connections[3] = ForgeDirection.NORTH;
            else
                steamPipe.connections[3] = null;

            if (x + 1 == tileX)
                steamPipe.connections[4] = ForgeDirection.EAST;
            else
                steamPipe.connections[4] = null;

            if (x - 1 == tileX)
                steamPipe.connections[5] = ForgeDirection.WEST;
            else
                steamPipe.connections[5] = null;
        }
        super.onNeighborChange(world, x, y, z, tileX, tileY, tileZ);
    }
}
