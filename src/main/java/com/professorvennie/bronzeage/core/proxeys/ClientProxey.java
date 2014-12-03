package com.professorvennie.bronzeage.core.proxeys;

import com.professorvennie.bronzeage.api.manual.IPage;
import com.professorvennie.bronzeage.client.gui.GuiManual;
import com.professorvennie.bronzeage.client.renders.tileentity.TileEntitySteamPipeRenderer;
import com.professorvennie.bronzeage.tileentitys.TileEntitySteamPipe;
import cpw.mods.fml.client.registry.ClientRegistry;

/**
 * Created by ProfessorVennie on 10/21/2014 at 5:22 PM.
 */
public class ClientProxey extends CommonProxey {

    public void registerRenders() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySteamPipe.class, new TileEntitySteamPipeRenderer());
    }

    @Override
    public void setPageToOpen(IPage page) {
        GuiManual.currentOpenManual = new GuiManual(page);
    }
}
