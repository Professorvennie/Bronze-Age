package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.api.manual.IPage;
import com.professorvennie.bronzeage.client.gui.GuiSteamFurnace;
import com.professorvennie.bronzeage.common.containers.ContainerSteamFurnace;
import com.professorvennie.bronzeage.lib.GuiIds;
import com.professorvennie.bronzeage.tileentitys.TileEntitySteamFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by ProfessorVennie on 1/7/2015 at 7:01 PM.
 */
public class BlockSteamFurnace extends BlockBasicMachine {

    protected BlockSteamFurnace() {
        super("steamFurnace");
    }

    @Override
    public int getGuiId() {
        return GuiIds.STEAM_FURNACE;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntitySteamFurnace();
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (world.getTileEntity(x, y, z) instanceof TileEntitySteamFurnace)
            return new ContainerSteamFurnace(player.inventory, (TileEntitySteamFurnace) world.getTileEntity(x, y, z));
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (world.getTileEntity(x, y, z) instanceof TileEntitySteamFurnace)
            return new GuiSteamFurnace(player, (TileEntitySteamFurnace) world.getTileEntity(x, y, z));
        return null;
    }

    @Override
    public IPage getPage(World world, int x, int y, int z) {
        return null;
    }
}
