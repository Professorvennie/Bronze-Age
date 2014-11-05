package com.professorvennie.bronzeage.tileentitys;

import com.professorvennie.bronzeage.lib.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by ProfessorVennie on 10/22/2014 at 8:01 PM.
 */
public class RegisterTiles {

    public static void register() {
        GameRegistry.registerTileEntity(TileEntitySteamPipe.class, Reference.MOD_ID + "_SteamPipe");
        GameRegistry.registerTileEntity(TileEntitySteamController.class, Reference.MOD_ID + "_SteamController");
        GameRegistry.registerTileEntity(TileEntitySteamBoiler.class, Reference.MOD_ID + "_SteamBoiler");
    }
}
