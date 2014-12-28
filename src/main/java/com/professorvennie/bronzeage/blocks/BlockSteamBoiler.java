package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.api.manual.IPage;
import com.professorvennie.bronzeage.client.gui.GuiSteamBoiler;
import com.professorvennie.bronzeage.common.containers.ContainerSteamBoiler;
import com.professorvennie.bronzeage.lib.BookData;
import com.professorvennie.bronzeage.lib.GuiIds;
import com.professorvennie.bronzeage.tileentitys.TileEntitySteamBoiler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by ProfessorVennie on 10/22/2014 at 8:09 PM.
 */
public class BlockSteamBoiler extends BlockBasicMachine {

    public BlockSteamBoiler() {
        super("steamBoiler");
    }

    @Override
    public int getGuiId() {
        return GuiIds.STEAM_BOILER;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntitySteamBoiler();
    }

    @Override
    public IPage getPage(World world, int x, int y, int z) {
        return BookData.basics;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (world.getTileEntity(x, y, z) instanceof TileEntitySteamBoiler)
            return new ContainerSteamBoiler(player.inventory, (TileEntitySteamBoiler) world.getTileEntity(x, y, z));
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (world.getTileEntity(x, y, z) instanceof TileEntitySteamBoiler)
            return new GuiSteamBoiler(player, (TileEntitySteamBoiler) world.getTileEntity(x, y, z));
        return null;
    }
}
