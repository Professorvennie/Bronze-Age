package com.professorvennie.bronzeage.common.containers;

import com.professorvennie.bronzeage.tileentitys.TileEntitySteamGrinder;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * Created by ProfessorVennie on 3/8/2015 at 4:18 PM.
 */
public class ContainerSteamGrinder extends ContainerBasicMachine {

    public ContainerSteamGrinder(InventoryPlayer inventory, TileEntitySteamGrinder tileEntity) {
        super(tileEntity);

        addPlayersInv(inventory);
    }
}
