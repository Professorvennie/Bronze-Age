package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.api.manual.IPage;
import com.professorvennie.bronzeage.client.gui.GuiSteamGrinder;
import com.professorvennie.bronzeage.common.containers.ContainerSteamGrinder;
import com.professorvennie.bronzeage.lib.GuiIds;
import com.professorvennie.bronzeage.tileentitys.TileEntitySteamGrinder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by ProfessorVennie on 3/8/2015 at 4:07 PM.
 */
public class BlockSteamGrinder extends BlockBasicMachine {

    protected BlockSteamGrinder() {
        super("steamGrinder");
    }

    @Override
    public int getGuiId() {
        return GuiIds.STEAMGRINDER;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntitySteamGrinder();
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        return new ContainerSteamGrinder(player.inventory, (TileEntitySteamGrinder)world.getTileEntity(pos));
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        return new GuiSteamGrinder(new ContainerSteamGrinder(player.inventory, (TileEntitySteamGrinder)world.getTileEntity(pos)), (TileEntitySteamGrinder)world.getTileEntity(pos));
    }

    @Override
    public IPage getPage(World world, int x, int y, int z) {
        return null;
    }
}
