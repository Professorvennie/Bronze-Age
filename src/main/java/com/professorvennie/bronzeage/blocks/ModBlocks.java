package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.lib.Reference;
import com.professorvennie.bronzeage.tileentitys.TileEntitySteamBoiler;
import com.professorvennie.bronzeage.tileentitys.TileEntitySteamController;
import com.professorvennie.bronzeage.tileentitys.TileEntitySteamFurnace;
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
    public static Block steamFurnace;

    public static Block ore;

    public static void init() {
        steamController = new BlockSteamController();
        steamPipe = new BlockSteamPipe();

        steamBoilerIdle = new BlockSteamBoiler();
        steamFurnace = new BlockSteamFurnace();

        ore = new BlockOre();

        register();
        registerTiles();
    }

    private static void register() {
        GameRegistry.registerBlock(steamController, steamController.getUnlocalizedName());
        GameRegistry.registerBlock(steamPipe, steamPipe.getUnlocalizedName());
        registerMachine(steamBoilerIdle);
        registerMachine(steamFurnace);
        GameRegistry.registerBlock(ore, BlockOre.ItemBlockOre.class, ore.getUnlocalizedName());
    }

    private static void registerTiles() {
        registerTile(TileEntitySteamBoiler.class, "SteamBoiler");
        registerTile(TileEntitySteamPipe.class, "SteamPipe");
        registerTile(TileEntitySteamController.class, "SteamController");
        registerTile(TileEntitySteamFurnace.class, "SteamFurnace");
    }

    private static void registerTile(Class<? extends TileEntity> tile, String name) {
        GameRegistry.registerTileEntity(tile, Reference.MOD_ID + "_tileEntity" + name);
    }

    private static void registerMachine(Block block) {
        GameRegistry.registerBlock(block, BlockBasicMachine.ItemBasicMachine.class, block.getUnlocalizedName());
    }
}
