package com.professorvennie.bronzeage.items;

import com.professorvennie.bronzeage.api.BronzeAgeAPI;
import com.professorvennie.bronzeage.api.wrench.IWrench;
import com.professorvennie.bronzeage.api.wrench.IWrenchable;
import com.professorvennie.bronzeage.api.wrench.WrenchMaterial;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.input.Keyboard;

import java.util.List;

/**
 * Created by ProfessorVennie on 11/26/2014 at 11:50 PM.
 */
public class ItemWrench extends ItemBase implements IWrench {

    public ItemWrench() {
        super("Wrench");
        setHasSubtypes(true);
        setMaxStackSize(1);
        setMaxStackSize(6);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 6; i++)
            list.add(new ItemStack(item, 1, i));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return super.getUnlocalizedName(itemStack) + "." + itemStack.getItemDamage();
    }

    @Override
    public int getDurability(ItemStack itemStack) {
        return getWrenchMaterial(itemStack).getDurability();
    }

    @Override
    public float getNumOfUsesRemaining(ItemStack itemStack) {
        return getWrenchMaterial(itemStack).getNumOfUses();
    }

    @Override
    public void damage(ItemStack itemStack, boolean isDismantle) {

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean b) {
        IWrench wrench = (IWrench) itemStack.getItem();
        WrenchMaterial wrenchMaterial = ((IWrench) itemStack.getItem()).getWrenchMaterial(itemStack);

        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            list.add(EnumChatFormatting.GOLD + "Material Name: " + EnumChatFormatting.DARK_AQUA + wrenchMaterial.getName());
            list.add(EnumChatFormatting.GOLD + "Number of Uses Remaining: " + EnumChatFormatting.DARK_AQUA + ((IWrench) itemStack.getItem()).getWrenchMaterial(itemStack).getNumOfUses());
            list.add(EnumChatFormatting.GOLD + "Number of Uses Per Rotation: " + EnumChatFormatting.DARK_AQUA + wrenchMaterial.getUsesPerRotation());
            list.add(EnumChatFormatting.GOLD + "Number of Uses Per Dismantle: " + EnumChatFormatting.DARK_AQUA + wrenchMaterial.getUsesPerDismantle());
            list.add(EnumChatFormatting.GOLD + "Maximum Durability: " + EnumChatFormatting.DARK_AQUA + wrenchMaterial.getDurability());
            list.add(EnumChatFormatting.DARK_GRAY + "Right click on a Machine to rotate it.");
            list.add(EnumChatFormatting.DARK_GRAY + "Shift Right Click on a Machine to Dismantle it");
        } else {
            list.add(EnumChatFormatting.GOLD + "Number of Uses Remaining: " + EnumChatFormatting.DARK_AQUA + ((IWrench) itemStack.getItem()).getWrenchMaterial(itemStack).getNumOfUses());
            list.add(EnumChatFormatting.DARK_GRAY + "Hold " + EnumChatFormatting.DARK_AQUA + "Shift " + EnumChatFormatting.DARK_GRAY + "for more info");
        }
    }

    @Override
    public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        ItemWrench wrench = (ItemWrench) itemStack.getItem();
        if (!player.isSneaking()) {
            if (world.getBlock(x, y, z) instanceof IWrenchable) {
                Block block = world.getBlock(x, y, z);
                block.rotateBlock(world, x, y, z, ForgeDirection.getOrientation(side).getOpposite());

                float dismanlt = wrench.getWrenchMaterial(itemStack).getUsesPerRotation();
                float usesRemaining = wrench.getNumOfUsesRemaining(itemStack);
                if (usesRemaining - dismanlt >= 0 && !world.isRemote)
                    wrench.getWrenchMaterial(itemStack).subtractUse(dismanlt);
            }
        } else {
            if (world.getBlock(x, y, z) instanceof IWrenchable) {
                IWrenchable wrenchable = (IWrenchable) world.getBlock(x, y, z);
                if (!world.isRemote)
                    wrenchable.dismantle(world, player, itemStack, x, y, z);
                float dismanlt = wrench.getWrenchMaterial(itemStack).getUsesPerDismantle();
                float usesRemaining = wrench.getNumOfUsesRemaining(itemStack);
                if (usesRemaining - dismanlt >= 0 && !world.isRemote)
                    wrench.getWrenchMaterial(itemStack).subtractUse(dismanlt);

            }
        }
        return super.onItemUseFirst(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float p_77648_8_, float p_77648_9_, float p_77648_10_) {

        return super.onItemUse(itemStack, player, world, x, y, z, side, p_77648_8_, p_77648_9_, p_77648_10_);
    }

    @Override
    public void onWrenchUsed(World world, ItemStack wrench, int x, int y, int z) {

    }

    @Override
    public WrenchMaterial getWrenchMaterial(ItemStack itemStack) {
        switch (itemStack.getItemDamage()) {
            case 0:
                return BronzeAgeAPI.getInstance().stoneWrenchMaterial;
            case 1:
                return BronzeAgeAPI.getInstance().tinWrenchMaterial;
            case 2:
                return BronzeAgeAPI.getInstance().copperWrenchMaterial;
            case 3:
                return BronzeAgeAPI.getInstance().ironWrenchMaterial;
            case 4:
                return BronzeAgeAPI.getInstance().bronzeWrenchMaterial;
            case 5:
                return BronzeAgeAPI.getInstance().diamondWrenchMaterial;
        }
        return null;
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {

    }
}
