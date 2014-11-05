package com.professorvennie.bronzeage.core.handlers;

import com.professorvennie.bronzeage.client.gui.GuiManual;
import com.professorvennie.bronzeage.common.containers.ContainerSteamBoiler;
import com.professorvennie.bronzeage.lib.GuiIds;
import com.professorvennie.bronzeage.tileentitys.TileEntitySteamBoiler;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by ProfessorVennie on 10/21/2014 at 5:41 PM.
 */
public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);

        switch (ID) {
            case GuiIds.STEAM_BOILER:
                if (tile instanceof TileEntitySteamBoiler)
                    return new ContainerSteamBoiler(player.inventory, (TileEntitySteamBoiler) tile);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);

        switch (ID) {
            case GuiIds.MANUAL:
                return new GuiManual(player.getCurrentEquippedItem(), player);
        }
        return null;
    }
}
