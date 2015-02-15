package com.professorvennie.bronzeage.items;

import com.professorvennie.bronzeage.api.BronzeAgeAPI;
import com.professorvennie.bronzeage.api.ISteamUsingItem;
import com.professorvennie.bronzeage.api.wrench.IWrench;
import com.professorvennie.bronzeage.api.wrench.WrenchMaterial;
import com.professorvennie.bronzeage.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by ProfessorVennie on 2/14/2015 at 7:17 PM.
 */
public class ItemSteamWrench extends ItemBase implements IWrench, ISteamUsingItem {

    private int capacity = 100000, steamAmount;
    private final String TAG_STEAMAMOUNT = "steamAmount", TAG_CAPACITY = "capacity";

    public ItemSteamWrench() {
        super("steamWrench");
    }

    @Override
    public int getSteamCapacity() {
        return capacity;
    }

    @Override
    public void receiveSteam(ItemStack itemStack, int amount) {

    }

    @Override
    public void extractSteam(ItemStack itemStack, int amount) {

    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean b) {

    }

    @Override
    public boolean canExtract() {
        return false;
    }

    @Override
    public boolean canReceive() {
        return false;
    }

    @Override
    public int getDurability(ItemStack itemStack) {
        return 0;
    }

    @Override
    public float getNumOfUsesRemaining(ItemStack itemStack) {
        return (float)itemStack.getTagCompound().getInteger(TAG_STEAMAMOUNT) / 100;
    }

    @Override
    public WrenchMaterial getWrenchMaterial(ItemStack itemStack) {
        return BronzeAgeAPI.steamWrenchMaterial;
    }

    @Override
    public void addUse(ItemStack itemStack, float amountOfUses) {

    }

    @Override
    public void subtractUse(ItemStack itemStack, float amountOfUses) {

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon(Reference.MOD_ID + "steamWrench");
    }
}
