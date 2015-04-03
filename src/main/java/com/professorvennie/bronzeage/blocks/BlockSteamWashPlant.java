package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.api.manual.IPage;
import com.professorvennie.bronzeage.client.gui.GuiSteamWashPlant;
import com.professorvennie.bronzeage.common.containers.ContainerSteamWashPlant;
import com.professorvennie.bronzeage.lib.GuiIds;
import com.professorvennie.bronzeage.tileentitys.TileEntitySteamWashPlant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by ProfessorVennie on 3/8/2015 at 4:08 PM.
 */
public class BlockSteamWashPlant extends BlockBasicMachine {

    protected BlockSteamWashPlant() {
        super("steamWashPlant");
    }

    @Override
    public int getGuiId() {
        return GuiIds.STEAMWASHPLANT;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntitySteamWashPlant();
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return new ContainerSteamWashPlant(player.inventory, (TileEntitySteamWashPlant)world.getTileEntity(x, y, z));
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return new GuiSteamWashPlant(new ContainerSteamWashPlant(player.inventory, (TileEntitySteamWashPlant)world.getTileEntity(x, y, z)), (TileEntitySteamWashPlant)world.getTileEntity(x, y, z));
    }

    @Override
    public IPage getPage(World world, int x, int y, int z) {
        return null;
    }
}
