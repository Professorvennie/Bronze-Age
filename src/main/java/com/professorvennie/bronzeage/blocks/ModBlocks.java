package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.lib.Reference;
import com.professorvennie.bronzeage.tileentitys.TileEntitySteamBoiler;
import com.professorvennie.bronzeage.tileentitys.TileEntitySteamController;
import com.professorvennie.bronzeage.tileentitys.TileEntitySteamPipe;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by ProfessorVennie on 10/21/2014 at 5:19 PM.
 */
public class ModBlocks {

    public static Block steamController;
    public static Block steamPipe;

    public static Block steamBoilerIdle;
    public static Block steamBoilerActive;

    public static Block ore;

    public static void init() {
        steamController = new BlockSteamController();
        steamPipe = new BlockSteamPipe();

        steamBoilerIdle = new BlockSteamBoiler();
        //steamBoilerActive = new BlockSteamBoiler(true);

        ore = new BlockOre();

        register();
        registerTiles();
    }

    private static void register() {
        GameRegistry.registerBlock(steamController, steamController.getUnlocalizedName());
        GameRegistry.registerBlock(steamPipe, steamPipe.getUnlocalizedName());
        registerMachine(steamBoilerIdle);
        //registerMachine(steamBoilerActive);
        GameRegistry.registerBlock(ore, BlockOre.ItemBlockOre.class, ore.getUnlocalizedName());
    }

    private static void registerTiles() {
        registerTile(TileEntitySteamBoiler.class, "SteamBoiler");
        registerTile(TileEntitySteamPipe.class, "SteamPipe");
        registerTile(TileEntitySteamController.class, "SteamController");
    }

    private static void registerTile(Class<? extends TileEntity> tile, String name) {
        GameRegistry.registerTileEntity(tile, Reference.MOD_ID + "_tileEntity" + name);
    }

    private static void registerMachine(Block block) {
        GameRegistry.registerBlock(block, BlockBasicMachine.ItemBasicMachine.class, block.getUnlocalizedName());
    }
}
