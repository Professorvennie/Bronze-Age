package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.lib.Reference;
import com.professorvennie.bronzeage.tileentitys.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by ProfessorVennie on 10/21/2014 at 5:19 PM.
 */
public class ModBlocks {

    public static Block steamBoilerIdle;
    public static Block steamFurnace;

    public static Block well;
    public static Block wellPipe;

    public static Block steamReceiver;
    public static Block steamTransmitter;

    public static Block ore;

    public static Block wrenchRepair;
    public static Block charger;

    public static void init() {

        steamBoilerIdle = new BlockSteamBoiler();
        steamFurnace = new BlockSteamFurnace();

        well = new BlockWell();
        wellPipe = new BlockWellPipe();

        steamReceiver = new BlockSteamReceiver();
        steamTransmitter = new BlockSteamTransmitter();

        wrenchRepair = new BlockWrenchRepairer();
        charger = new BlockSteamCharger();

        ore = new BlockOre();

        register();
        registerTiles();
    }

    private static void register() {
        GameRegistry.registerBlock(steamReceiver, steamReceiver.getUnlocalizedName());
        GameRegistry.registerBlock(steamTransmitter, steamTransmitter.getUnlocalizedName());
        registerMachine(steamBoilerIdle);
        registerMachine(steamFurnace);
        registerMachine(well);
        registerMachine(wrenchRepair);
        registerMachine(charger);
        GameRegistry.registerBlock(wellPipe, wellPipe.getUnlocalizedName());
        GameRegistry.registerBlock(ore, BlockOre.ItemBlockOre.class, ore.getUnlocalizedName());
    }

    private static void registerTiles() {
        registerTile(TileEntitySteamBoiler.class, "SteamBoiler");
        registerTile(TileEntitySteamPipe.class, "SteamPipe");
        registerTile(TileEntitySteamController.class, "SteamController");
        registerTile(TileEntitySteamFurnace.class, "SteamFurnace");
        registerTile(TileEntityWell.class, "Well");
        registerTile(TileEntityWellPipe.class, "WellPipe");
        registerTile(TileEntitySteamTransmitter.class, "SteamTransmitter");
        registerTile(TileEntitySteamReceiver.class, "SteamReceiver");
        registerTile(TileEntityWrenchRepairer.class, "wrenchRepairer");
        registerTile(TileEntitySteamCharger.class, "steamCharger");
    }

    private static void registerTile(Class<? extends TileEntity> tile, String name) {
        GameRegistry.registerTileEntity(tile, Reference.MOD_ID + "_tileEntity" + name);
    }

    private static void registerMachine(Block block) {
        GameRegistry.registerBlock(block, BlockBasicMachine.ItemBasicMachine.class, block.getUnlocalizedName());
    }
}
