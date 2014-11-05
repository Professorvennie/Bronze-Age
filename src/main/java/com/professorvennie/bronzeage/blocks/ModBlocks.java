package com.professorvennie.bronzeage.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

/**
 * Created by ProfessorVennie on 10/21/2014 at 5:19 PM.
 */
public class ModBlocks {

    public static Block steamController;
    public static Block steamPipe;

    public static Block steamBoilerIdle;
    public static Block steamBoilerActive;

    public static void init() {
        steamController = new BlockSteamController();
        steamPipe = new BlockSteamPipe();

        steamBoilerIdle = new BlockSteamBoiler(false);
        steamBoilerActive = new BlockSteamBoiler(true);

        register();
    }

    private static void register() {
        GameRegistry.registerBlock(steamController, steamController.getUnlocalizedName());
        GameRegistry.registerBlock(steamPipe, steamPipe.getUnlocalizedName());
        GameRegistry.registerBlock(steamBoilerIdle, steamBoilerIdle.getUnlocalizedName());
        GameRegistry.registerBlock(steamBoilerActive, steamBoilerActive.getUnlocalizedName());
    }
}
