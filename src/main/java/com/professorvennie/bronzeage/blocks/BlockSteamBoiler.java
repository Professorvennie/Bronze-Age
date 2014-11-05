package com.professorvennie.bronzeage.blocks;


import com.professorvennie.bronzeage.lib.GuiIds;
import com.professorvennie.bronzeage.tileentitys.TileEntitySteamBoiler;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by ProfessorVennie on 10/22/2014 at 8:09 PM.
 */
public class BlockSteamBoiler extends BlockBasicMachine {

    public BlockSteamBoiler(boolean isActive) {
        super(Material.iron, "steamBoiler", isActive);
        guiId = GuiIds.STEAM_BOILER;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntitySteamBoiler();
    }
}
