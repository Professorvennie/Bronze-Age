package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.api.manual.IPage;
import com.professorvennie.bronzeage.client.gui.GuiSteamCharger;
import com.professorvennie.bronzeage.common.containers.ContainerSteamCharger;
import com.professorvennie.bronzeage.lib.GuiIds;
import com.professorvennie.bronzeage.tileentitys.TileEntitySteamCharger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by ProfessorVennie on 2/15/2015 at 3:27 PM.
 */
public class BlockSteamCharger extends BlockBasicMachine {

    protected BlockSteamCharger() {
        super("steamCharger");
    }

    @Override
    public int getGuiId() {
        return GuiIds.STEAMCHARGER;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntitySteamCharger();
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        return new ContainerSteamCharger(player.inventory, (TileEntitySteamCharger)world.getTileEntity(pos));
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        return new GuiSteamCharger(new ContainerSteamCharger(player.inventory, (TileEntitySteamCharger)world.getTileEntity(pos)), (TileEntitySteamCharger)world.getTileEntity(pos));
    }

    @Override
    public IPage getPage(World world, int x, int y, int z) {
        return null;
    }
}
