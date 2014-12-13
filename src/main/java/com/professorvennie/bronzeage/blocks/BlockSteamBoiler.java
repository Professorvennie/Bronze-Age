package com.professorvennie.bronzeage.blocks;


import com.professorvennie.bronzeage.api.manual.IManualEntry;
import com.professorvennie.bronzeage.api.manual.IPage;
import com.professorvennie.bronzeage.lib.BookData;
import com.professorvennie.bronzeage.lib.GuiIds;
import com.professorvennie.bronzeage.tileentitys.TileEntitySteamBoiler;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by ProfessorVennie on 10/22/2014 at 8:09 PM.
 */
public class BlockSteamBoiler extends BlockBasicMachine implements IManualEntry {

    public BlockSteamBoiler() {
        super(Material.iron, "steamBoiler");
        guiId = GuiIds.STEAM_BOILER;
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
    public void renderHUD(World world, Minecraft minecraft, ItemStack wrench, int x, int y, int z) {

    }
}
