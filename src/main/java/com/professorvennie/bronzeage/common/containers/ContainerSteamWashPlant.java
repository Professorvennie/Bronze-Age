package com.professorvennie.bronzeage.common.containers;

import com.professorvennie.bronzeage.tileentitys.TileEntitySteamWashPlant;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * Created by ProfessorVennie on 3/8/2015 at 4:14 PM.
 */
public class ContainerSteamWashPlant extends ContainerBasicMachine {

    public ContainerSteamWashPlant(InventoryPlayer inventory, TileEntitySteamWashPlant tileEntity) {
        super(tileEntity);
        addPlayersInv(inventory);
    }
}
