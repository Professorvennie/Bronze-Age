package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.api.manual.IPage;
import com.professorvennie.bronzeage.client.gui.GuiWrenchRepairer;
import com.professorvennie.bronzeage.common.containers.ContainerWrenchRepairer;
import com.professorvennie.bronzeage.lib.GuiIds;
import com.professorvennie.bronzeage.tileentitys.TileEntityWrenchRepairer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by ProfessorVennie on 2/11/2015 at 5:27 PM.
 */
public class BlockWrenchRepairer extends BlockBasicMachine{

    protected BlockWrenchRepairer() {
        super("wrenchRepairer");
    }

    @Override
    public int getGuiId() {
        return GuiIds.WRENCHREPAIRER;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntityWrenchRepairer();
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(world.getTileEntity(x, y, z) instanceof TileEntityWrenchRepairer)
            return new ContainerWrenchRepairer(player.inventory, (TileEntityWrenchRepairer)world.getTileEntity(x, y, z));
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(world.getTileEntity(x, y, z) instanceof TileEntityWrenchRepairer)
            return new GuiWrenchRepairer(player, (TileEntityWrenchRepairer)world.getTileEntity(x, y, z));
        return null;
    }

    @Override
    public IPage getPage(World world, int x, int y, int z) {
        return null;
    }
}
