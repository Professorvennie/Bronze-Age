package com.professorvennie.bronzeage.tileentitys;

import com.professorvennie.bronzeage.api.recipes.GrinderRecipes;
import net.minecraft.item.ItemStack;

/**
 * Created by ProfessorVennie on 3/8/2015 at 4:12 PM.
 */
public class TileEntitySteamGrinder extends TileEntityBasicSteamMachine {

    public TileEntitySteamGrinder() {
        super("steamGrinder", 10000);
        setMachineSpeed(80);
    }

    @Override
    public void update() {
        super.update();
        if (canWork) {
            if(hasEnoughSteam() && (canGrind(1) || canGrind(2))){
                increaseProgressByOne();
                if(getProgress() == getMachineSpeed()){
                    grindItem();
                    resetProgress();
                    getSteamTank().drain(1000);
                }
            }
        }
    }

    private boolean canGrind(int slot) {
        if (slot == 1) {
            if (this.inventory[3] == null) {
                return false;
            } else {
                ItemStack itemstack = GrinderRecipes.getInstance().getResult(this.inventory[3]);
                if (itemstack == null) return false;
                if (this.inventory[4] == null) return true;
                if (!this.inventory[4].isItemEqual(itemstack)) return false;
                int result = inventory[4].stackSize + itemstack.stackSize;
                return result <= getInventoryStackLimit() && result <= this.inventory[4].getMaxStackSize();
            }
        }else if(slot == 2){
            if (this.inventory[3] == null) {
                return false;
            }else{
                ItemStack itemstack = GrinderRecipes.getInstance().getResult(this.inventory[3]);
                if (itemstack == null) return false;
                if (this.inventory[5] == null) return true;
                if (!this.inventory[5].isItemEqual(itemstack)) return false;
                int result = inventory[5].stackSize + itemstack.stackSize;
                return result <= getInventoryStackLimit() && result <= this.inventory[5].getMaxStackSize();
            }
        }
        return false;
    }

    public void grindItem(){
        if (this.canGrind(1)){
            ItemStack itemstack = GrinderRecipes.getInstance().getResult(this.inventory[3]);

            if (this.inventory[4] == null){
                this.inventory[4] = itemstack.copy();
            }
            else if (this.inventory[4].getItem() == itemstack.getItem()){
                this.inventory[4].stackSize += itemstack.stackSize;
            }

            this.inventory[3].stackSize--;

            if (this.inventory[3].stackSize <= 0){
                this.inventory[3] = null;
            }
        }else if (this.canGrind(2)){
            ItemStack itemstack = GrinderRecipes.getInstance().getResult(this.inventory[3]);

            if (this.inventory[5] == null){
                this.inventory[5] = itemstack.copy();
            }
            else if (this.inventory[5].getItem() == itemstack.getItem()){
                this.inventory[5].stackSize += itemstack.stackSize;
            }

            this.inventory[3].stackSize--;

            if (this.inventory[3].stackSize <= 0){
                this.inventory[3] = null;
            }
        }
    }

    private boolean hasEnoughSteam() {
        if (getSteamAmount() >= 1000)
            return true;
        return false;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        return false;
    }

    @Override
    public int getSizeInventory() {
        return 6;
    }

    @Override
    public int[] getInputSlots() {
        return new int[0];
    }

    @Override
    public int[] getExportSlots() {
        return new int[0];
    }
}
