package com.professorvennie.bronzeage.common.containers;

import com.professorvennie.bronzeage.tileentitys.TileEntitySteamExtractor;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * Created by ProfessorVennie on 3/8/2015 at 4:21 PM.
 */
public class ContainerSteamExtractor extends ContainerBasicMachine {

    public ContainerSteamExtractor(InventoryPlayer inventory, TileEntitySteamExtractor tileEntity) {
        super(tileEntity);
        addPlayersInv(inventory);
    }
}
