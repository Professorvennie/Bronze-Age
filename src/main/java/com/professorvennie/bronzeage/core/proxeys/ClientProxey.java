package com.professorvennie.bronzeage.core.proxeys;

import com.professorvennie.bronzeage.api.manual.IPage;
import com.professorvennie.bronzeage.blocks.ModBlocks;
import com.professorvennie.bronzeage.client.gui.GuiManual;
import com.professorvennie.bronzeage.client.renders.item.WellPipeItemRenderer;
import com.professorvennie.bronzeage.client.renders.tileentity.TileEntitySteamPipeRenderer;
import com.professorvennie.bronzeage.client.renders.tileentity.WoodenWellTileRenderer;
import com.professorvennie.bronzeage.tileentitys.TileEntitySteamPipe;
import com.professorvennie.bronzeage.tileentitys.TileEntityWellPipe;
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

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.wellPipe), new WellPipeItemRenderer());
    }

    @Override
    public void setPageToOpen(IPage page) {
        GuiManual.currentOpenManual = new GuiManual(page);
    }
}
