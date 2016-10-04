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
    public static BlockBasicMachine steamFurnace;
    public static BlockBasicMachine steamExtractor;
    public static BlockBasicMachine steamGrinder;
    public static BlockBasicMachine steamWashPlant;

    public static Block well;
    public static Block wellPipe;

    public static Block steamReceiver;
    public static Block steamTransmitter;

    public static BlockOre ore;

    public static BlockBasicMachine wrenchRepair;
    public static BlockBasicMachine charger;

    public static void init() {

        steamBoilerIdle = new BlockSteamBoiler();
        ItemBasicMachine basicMachineBoiler = new ItemBasicMachine(steamBoilerIdle);
        GameRegistry.register(steamBoilerIdle);
        GameRegistry.register(basicMachineBoiler);
        steamBoilerIdle.registerItemModel(basicMachineBoiler);

        steamFurnace = new BlockSteamFurnace();
        ItemBasicMachine furnace = new ItemBasicMachine(steamFurnace);
        GameRegistry.register(steamFurnace);
        GameRegistry.register(furnace);
        steamFurnace.registerItemModel(furnace);


        steamExtractor = new BlockSteamExtractor();
        ItemBasicMachine extractor = new ItemBasicMachine(steamExtractor);
        GameRegistry.register(steamExtractor);
        GameRegistry.register(extractor);
        steamExtractor.registerItemModel(extractor);

        steamGrinder = new BlockSteamGrinder();
        ItemBasicMachine grinder = new ItemBasicMachine(steamGrinder);
        GameRegistry.register(steamGrinder);
        GameRegistry.register(grinder);
        steamGrinder.registerItemModel(grinder);

        charger = new BlockSteamCharger();
        ItemBasicMachine chargerItem = new ItemBasicMachine(charger);
        GameRegistry.register(charger);
        GameRegistry.register(chargerItem);
        charger.registerItemModel(chargerItem);

        steamWashPlant = new BlockSteamWashPlant();
        ItemBasicMachine washPlant = new ItemBasicMachine(steamWashPlant);
        GameRegistry.register(steamWashPlant);
        GameRegistry.register(washPlant);
        steamWashPlant.registerItemModel(washPlant);

        well = register(new BlockWell());
        wellPipe = register(new BlockWellPipe());

        steamReceiver = register(new BlockSteamReceiver());
        steamTransmitter = register(new BlockSteamTransmitter());


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
