package com.professorvennie.bronzeage.core.handlers;

import com.professorvennie.bronzeage.blocks.BlockBasicMachine;
import com.professorvennie.bronzeage.client.gui.GuiConfig;
import com.professorvennie.bronzeage.client.gui.GuiManual;
import com.professorvennie.bronzeage.common.containers.ContainerFake;
import com.professorvennie.bronzeage.lib.GuiIds;
import com.professorvennie.bronzeage.tileentitys.TileEntityBasicMachine;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ProfessorVennie on 10/21/2014 at 5:41 PM.
 */
public class GuiHandler implements IGuiHandler {

    private Map<Integer, IGuiHandler> guiHandlers = new HashMap<Integer, IGuiHandler>();

    public void registerHandler(int id, IGuiHandler handler) {
        if (guiHandlers.get(id) == null)
            guiHandlers.put(id, handler);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (guiHandlers.get(ID) != null)
            return guiHandlers.get(ID).getServerGuiElement(ID, player, world, x, y, z);
        if (ID == GuiIds.CONFIG && world.getTileEntity(x, y, z) instanceof TileEntityBasicMachine)
            return new ContainerFake(player.inventory, (TileEntityBasicMachine) world.getTileEntity(x, y, z));
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GuiIds.CONFIG && world.getTileEntity(x, y, z) instanceof TileEntityBasicMachine)
            return new GuiConfig(player, (TileEntityBasicMachine) world.getTileEntity(x, y, z), (BlockBasicMachine) world.getBlock(x, y, z));
        else if (ID == GuiIds.MANUAL) {
            GuiManual manual = GuiManual.currentOpenManual;
            GuiManual.currentItemStack = player.getCurrentEquippedItem();
            if (GuiManual.currentItemStack == null)
                return null;
            return manual;
        }
        if (guiHandlers.get(ID) != null)
            return guiHandlers.get(ID).getClientGuiElement(ID, player, world, x, y, z);
        return null;
    }
}
