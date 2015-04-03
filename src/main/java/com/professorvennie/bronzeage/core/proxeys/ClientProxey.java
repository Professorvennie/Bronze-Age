package com.professorvennie.bronzeage.core.proxeys;

import com.professorvennie.bronzeage.api.manual.IPage;
import com.professorvennie.bronzeage.blocks.ModBlocks;
import com.professorvennie.bronzeage.client.gui.GuiManual;
import com.professorvennie.bronzeage.client.renders.item.BasicMachineItemRenderer;
import com.professorvennie.bronzeage.client.renders.item.WellPipeItemRenderer;
import com.professorvennie.bronzeage.client.renders.tileentity.TileEntityBasicSteamMachineRenderer;
import com.professorvennie.bronzeage.client.renders.tileentity.TileEntitySteamPipeRenderer;
import com.professorvennie.bronzeage.client.renders.tileentity.WoodenWellTileRenderer;
import com.professorvennie.bronzeage.tileentitys.*;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * Created by ProfessorVennie on 10/21/2014 at 5:22 PM.
 */
public class ClientProxey extends CommonProxey {

    public void registerRenders() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySteamPipe.class, new TileEntitySteamPipeRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWellPipe.class, new WoodenWellTileRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySteamBoiler.class, new TileEntityBasicSteamMachineRenderer("steamBoiler"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySteamFurnace.class, new TileEntityBasicSteamMachineRenderer("steamFurnace"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWell.class, new TileEntityBasicSteamMachineRenderer("well"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySteamCharger.class, new TileEntityBasicSteamMachineRenderer("steamCharger"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySteamExtractor.class, new TileEntityBasicSteamMachineRenderer("steamExtractor"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySteamGrinder.class, new TileEntityBasicSteamMachineRenderer("steamGrinder"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySteamWashPlant.class, new TileEntityBasicSteamMachineRenderer("steamWashPlant"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWrenchRepairer.class, new TileEntityBasicSteamMachineRenderer("wrenchRepairer"));

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.wellPipe), new WellPipeItemRenderer());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.steamBoilerIdle), new BasicMachineItemRenderer("steamBoiler"));
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.steamFurnace), new BasicMachineItemRenderer("steamFurnace"));
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.well), new BasicMachineItemRenderer("well"));
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.charger), new BasicMachineItemRenderer("steamCharger"));
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.steamGrinder), new BasicMachineItemRenderer("steamGrinder"));
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.steamExtractor), new BasicMachineItemRenderer("steamExtractor"));
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.steamWashPlant), new BasicMachineItemRenderer("steamWashPlant"));
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.wrenchRepair), new BasicMachineItemRenderer("wrenchRepairer"));
    }

    @Override
    public void setPageToOpen(IPage page) {
        GuiManual.currentOpenManual = new GuiManual(page);
    }
}
