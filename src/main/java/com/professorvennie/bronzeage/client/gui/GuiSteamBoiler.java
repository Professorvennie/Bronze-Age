package com.professorvennie.bronzeage.client.gui;

import com.professorvennie.bronzeage.common.containers.ContainerSteamBoiler;
import com.professorvennie.bronzeage.tileentitys.TileEntitySteamBoiler;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by ProfessorVennie on 11/13/2014 at 3:28 PM.
 */
public class GuiSteamBoiler extends GuiBase {

    public GuiSteamBoiler(EntityPlayer player, TileEntitySteamBoiler tile) {
        super(new ContainerSteamBoiler(player.inventory, tile), tile);
    }
}
