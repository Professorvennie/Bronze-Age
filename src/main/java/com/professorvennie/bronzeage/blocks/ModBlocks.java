package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.lib.Reference;
import com.professorvennie.bronzeage.tileentitys.*;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;

/**
 * Created by ProfessorVennie on 10/21/2014 at 5:19 PM.
 */
public class ModBlocks {

    public static BlockBasicMachine steamBoilerIdle;
    public static Block steamFurnace;
    public static Block steamExtractor;
    public static Block steamGrinder;
    public static Block steamWashPlant;

    public static Block well;
    public static Block wellPipe;

    public static Block steamReceiver;
    public static Block steamTransmitter;

    public static BlockOre ore;

    public static Block wrenchRepair;
    public static Block charger;

    public static void init() {

        steamBoilerIdle = register(new BlockSteamBoiler());
        GameRegistry.register(new ItemBasicMachine(steamBoilerIdle));
        steamFurnace = register(new BlockSteamFurnace());
        steamExtractor = register(new BlockSteamExtractor());
        steamGrinder = register(new BlockSteamGrinder());
        steamWashPlant = register(new BlockSteamWashPlant());

        well = register(new BlockWell());
        wellPipe = register(new BlockWellPipe());

        steamReceiver = register(new BlockSteamReceiver());
        steamTransmitter = register(new BlockSteamTransmitter());

        wrenchRepair = register(new BlockWrenchRepairer());
        charger = register(new BlockSteamCharger());

        ore = new BlockOre();
        GameRegistry.register(ore);
        BlockOre.ItemBlockOre itemBlockOre = new BlockOre.ItemBlockOre(ore);
        GameRegistry.register(itemBlockOre);
        ore.registerItemModel(itemBlockOre);

        //register();
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
        registerMachine(steamExtractor);
        registerMachine(steamGrinder);
        registerMachine(steamWashPlant);
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
        registerTile(TileEntitySteamWashPlant.class, "washPlant");
        registerTile(TileEntitySteamGrinder.class, "steamGrinder");
        registerTile(TileEntitySteamExtractor.class, "steamExtractor");
    }

    private static void registerTile(Class<? extends TileEntity> tile, String name) {
        GameRegistry.registerTileEntity(tile, Reference.MOD_ID + "_tileEntity" + name);
    }

    private static void registerMachine(Block block) {
        //GameRegistry.registerBlock(block, BlockBasicMachine.ItemBasicMachine.class, block.getUnlocalizedName());
    }

    private static <T extends Block> T register(T block, ItemBlock itemBlock) {
        GameRegistry.register(block);
        GameRegistry.register(itemBlock);

        if (block instanceof BlockBase) {
            ((BlockBase)block).registerItemModel(itemBlock);
        }

        return block;
    }

    private static <T extends Block> T register(T block) {
        ItemBlock itemBlock = new ItemBlock(block);
        itemBlock.setRegistryName(block.getRegistryName());
        return register(block, itemBlock);
    }
}
