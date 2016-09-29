package com.professorvennie.bronzeage.common.containers;

import com.professorvennie.bronzeage.api.steam.SteamTank;
import com.professorvennie.bronzeage.tileentitys.TileEntityBasicSteamMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by ProfessorVennie on 2/15/2015 at 3:35 PM.
 */
public class ContainerBasicMachine extends Container {

    protected TileEntityBasicSteamMachine tile;
    private int lastSteamAmount, lastProgress;

    public ContainerBasicMachine(TileEntityBasicSteamMachine tile){
        this.tile = tile;
    }

    public void addDefaultSteamTankSlots(){
        addSlotToContainer(new Slot(tile, 0, 33, 9));
        addSlotToContainer(new Slot(tile, 1, 33, 58));
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tile.isUseableByPlayer(player);
    }

    public void addPlayersInv(InventoryPlayer inventory){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); i++) {
            IContainerListener icrafting = (IContainerListener) this.listeners.get(i);

            if (lastSteamAmount != tile.getSteamAmount()) {
                icrafting.sendProgressBarUpdate(this, 0, tile.getField(2));
            }

            if(lastProgress != tile.getProgress())
                icrafting.sendProgressBarUpdate(this, 1, tile.getField(1));
        }
        this.lastSteamAmount = tile.getField(2);
        this.lastProgress = tile.getField(1);
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int slot, int par2) {
        tile.setField(slot, par2);
    }

   /* @Override
    public void addCraftingToCrafters(ICrafting iCrafting) {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, lastSteamAmount);
        iCrafting.sendProgressBarUpdate(this, 1, lastProgress);
    }*/

    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.tile);
    }
}
