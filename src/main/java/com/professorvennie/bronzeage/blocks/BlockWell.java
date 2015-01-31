package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.api.manual.IPage;
import com.professorvennie.bronzeage.client.gui.GuiWell;
import com.professorvennie.bronzeage.common.containers.ContainerWell;
import com.professorvennie.bronzeage.lib.GuiIds;
import com.professorvennie.bronzeage.tileentitys.TileEntityWell;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by ProfessorVennie on 1/31/2015 at 2:26 PM.
 */
public class BlockWell extends BlockBasicMachine {

    protected BlockWell() {
        super("well");
    }

    @Override
    public int getGuiId() {
        return GuiIds.WELL;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntityWell();
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (world.getTileEntity(x, y, z) instanceof TileEntityWell)
            return new ContainerWell(player.inventory, (TileEntityWell) world.getTileEntity(x, y, z));
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (world.getTileEntity(x, y, z) instanceof TileEntityWell)
            return new GuiWell(player.inventory, (TileEntityWell) world.getTileEntity(x, y, z));
        return null;
    }

    @Override
    public IPage getPage(World world, int x, int y, int z) {
        return null;
    }
}
