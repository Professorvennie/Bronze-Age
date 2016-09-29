package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.api.manual.IPage;
import com.professorvennie.bronzeage.client.gui.GuiSteamExtractor;
import com.professorvennie.bronzeage.common.containers.ContainerSteamExtractor;
import com.professorvennie.bronzeage.lib.GuiIds;
import com.professorvennie.bronzeage.tileentitys.TileEntitySteamExtractor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by ProfessorVennie on 3/8/2015 at 4:06 PM.
 */
public class BlockSteamExtractor extends BlockBasicMachine {

    protected BlockSteamExtractor() {
        super("steamExtractor");
    }

    @Override
    public int getGuiId() {
        return GuiIds.STEAMEXTRACTOR;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntitySteamExtractor();
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        return new ContainerSteamExtractor(player.inventory, (TileEntitySteamExtractor)world.getTileEntity(pos));
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        return new GuiSteamExtractor(new ContainerSteamExtractor(player.inventory, (TileEntitySteamExtractor)world.getTileEntity(pos)), (TileEntitySteamExtractor)world.getTileEntity(pos));
    }

    @Override
    public IPage getPage(World world, int x, int y, int z) {
        return null;
    }
}
