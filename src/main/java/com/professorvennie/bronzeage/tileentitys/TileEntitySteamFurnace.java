package com.professorvennie.bronzeage.tileentitys;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

/**
 * Created by ProfessorVennie on 1/7/2015 at 7:02 PM.
 */
public class TileEntitySteamFurnace extends TileEntityBasicSteamMachine {

    public TileEntitySteamFurnace() {
        super("steamFurnace", 10000);
        setMachineSpeed(85);
    }

    @Override
    public void updateEntity() {
    super.updateEntity();
        if (canWork) {
            if(hasEnoughSteam() && (canSmelt(1) || canSmelt(2))){
                increaseProgressByOne();
                isActive = true;
                if(getProgress() == getMachineSpeed()){
                    smeltItem();
                    resetProgress();
                    getSteamTank().drain(1000);
                }
            }else
                isActive = false;
        }
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        if (slot == 2 && FurnaceRecipes.smelting().getSmeltingResult(itemStack) != null)
            return true;
        return false;
    }

    private boolean hasEnoughSteam() {
        if (getSteamAmount() >= 1000)
            return true;
        return false;
    }

    private boolean canSmelt(int slot) {
        if (slot == 1) {
            if (this.inventory[2] == null) {
                return false;
            } else {
                ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[2]);
                if (itemstack == null) return false;
                if (this.inventory[3] == null) return true;
                if (!this.inventory[3].isItemEqual(itemstack)) return false;
                int result = inventory[3].stackSize + itemstack.stackSize;
                return result <= getInventoryStackLimit() && result <= this.inventory[3].getMaxStackSize();
            }
        }else if(slot == 2){
            if (this.inventory[2] == null) {
                return false;
            }else{
                ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[2]);
                if (itemstack == null) return false;
                if (this.inventory[4] == null) return true;
                if (!this.inventory[4].isItemEqual(itemstack)) return false;
                int result = inventory[4].stackSize + itemstack.stackSize;
                return result <= getInventoryStackLimit() && result <= this.inventory[4].getMaxStackSize();
            }
        }
        return false;
    }

    public void smeltItem(){
        if (this.canSmelt(1)){
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[2]);

            if (this.inventory[3] == null){
                this.inventory[3] = itemstack.copy();
            }
            else if (this.inventory[3].getItem() == itemstack.getItem()){
                this.inventory[3].stackSize += itemstack.stackSize;
            }

            this.inventory[2].stackSize--;

            if (this.inventory[2].stackSize <= 0){
                this.inventory[2] = null;
            }
        }else if (this.canSmelt(2)){
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[2]);

            if (this.inventory[4] == null){
                this.inventory[4] = itemstack.copy();
            }
            else if (this.inventory[4].getItem() == itemstack.getItem()){
                this.inventory[4].stackSize += itemstack.stackSize;
            }

            this.inventory[2].stackSize--;

            if (this.inventory[2].stackSize <= 0){
                this.inventory[2] = null;
            }
        }
    }

    @Override
    public int getSizeInventory() {
        return 5;
    }

    @Override
    public int[] getInputSlots() {
        return new int[]{0, 2};
    }

    @Override
    public int[] getExportSlots() {
        return new int[]{1, 3, 4};
    }
}