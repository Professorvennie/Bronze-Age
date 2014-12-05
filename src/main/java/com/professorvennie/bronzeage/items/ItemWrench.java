package com.professorvennie.bronzeage.items;

import com.professorvennie.bronzeage.api.wrench.IWrench;
import com.professorvennie.bronzeage.api.wrench.IWrenchable;
import com.professorvennie.bronzeage.api.wrench.WrenchMaterial;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.input.Keyboard;

import java.util.List;

/**
 * Created by ProfessorVennie on 11/26/2014 at 11:50 PM.
 */
public class ItemWrench extends ItemBase implements IWrench {

    private WrenchMaterial wrenchMaterial;

    public ItemWrench(WrenchMaterial wrenchMaterial) {
        super("wrench" + "." + wrenchMaterial.getName());
        this.wrenchMaterial = wrenchMaterial;
        setHasSubtypes(true);
        setMaxStackSize(1);
    }

    @Override
    public int getMaxDamage() {
        return super.getMaxDamage();
    }

    @Override
    public boolean isDamageable() {
        return super.isDamageable();
    }

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int tick, boolean b) {

    }

    @Override
    public int getDurability() {
        return getWrenchMaterial().getDurability();
    }

    @Override
    public float getNumOfUsesRemaining(ItemStack itemStack) {
        if (itemStack.getTagCompound() == null) {
            itemStack.setTagCompound(new NBTTagCompound());
            itemStack.getTagCompound().setFloat("NumOfUsesRemaining", wrenchMaterial.getNumOfUses());
        }
        return itemStack.getTagCompound().getFloat("NumOfUsesRemaining");
    }

    @Override
    public void damage(ItemStack itemStack, boolean isDismantle) {

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean b) {

        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            list.add(EnumChatFormatting.GOLD + "Material Name: " + EnumChatFormatting.DARK_AQUA + wrenchMaterial.getName());
            list.add(EnumChatFormatting.GOLD + "Number of Uses Remaining: " + EnumChatFormatting.DARK_AQUA + getNumOfUsesRemaining(itemStack));
            list.add(EnumChatFormatting.GOLD + "Number of Uses Per Rotation: " + EnumChatFormatting.DARK_AQUA + wrenchMaterial.getUsesPerRotation());
            list.add(EnumChatFormatting.GOLD + "Number of Uses Per Dismantle: " + EnumChatFormatting.DARK_AQUA + wrenchMaterial.getUsesPerDismantle());
            list.add(EnumChatFormatting.GOLD + "Max Durability: " + EnumChatFormatting.DARK_AQUA + getDurability());
            list.add(EnumChatFormatting.DARK_GRAY + "Right click on a Machine to rotate it.");
            list.add(EnumChatFormatting.DARK_GRAY + "Shift Right Click on a Machine to Dismantle it");
            list.add(EnumChatFormatting.DARK_GRAY + "The Number of Uses is Determined By");
            list.add(EnumChatFormatting.DARK_GRAY + "Dividing The Max Durability By 100");
        } else {
            list.add(EnumChatFormatting.GOLD + "Number of Uses Remaining: " + EnumChatFormatting.DARK_AQUA + getNumOfUsesRemaining(itemStack));
            list.add(EnumChatFormatting.DARK_GRAY + "Hold " + EnumChatFormatting.DARK_AQUA + "Shift " + EnumChatFormatting.DARK_GRAY + "for more info");
        }
    }

    @Override
    public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (!player.isSneaking()) {
            if (world.getBlock(x, y, z) instanceof IWrenchable) {

                float dismanlt = wrenchMaterial.getUsesPerRotation();
                float usesRemaining = getNumOfUsesRemaining(itemStack);
                if (usesRemaining - dismanlt >= 0 && !world.isRemote) {
                    subtractUse(itemStack, dismanlt);
                    Block block = world.getBlock(x, y, z);
                    block.rotateBlock(world, x, y, z, ForgeDirection.getOrientation(side).getOpposite());
                }
            }
        } else {
            if (world.getBlock(x, y, z) instanceof IWrenchable) {
                IWrenchable wrenchable = (IWrenchable) world.getBlock(x, y, z);

                float dismanlt = wrenchMaterial.getUsesPerDismantle();
                float usesRemaining = getNumOfUsesRemaining(itemStack);
                if (usesRemaining - dismanlt >= 0 && !world.isRemote) {
                    subtractUse(itemStack, dismanlt);
                    if (!world.isRemote)
                        wrenchable.dismantle(world, player, itemStack, x, y, z);
                }
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
    public WrenchMaterial getWrenchMaterial() {
        return wrenchMaterial;
    }

    @Override
    public void addUse(ItemStack itemStack, float amountOfUses) {
        if (itemStack.getTagCompound() == null) {
            itemStack.setTagCompound(new NBTTagCompound());
            itemStack.getTagCompound().setFloat("NumOfUsesRemaining", wrenchMaterial.getNumOfUses());
        }
        itemStack.getTagCompound().setFloat("NumOfUsesRemaining", itemStack.getTagCompound().getFloat("NumOfUsesRemaining") + amountOfUses);
    }

    @Override
    public void subtractUse(ItemStack itemStack, float amountOfUses) {
        if (itemStack.getTagCompound() == null) {
            itemStack.setTagCompound(new NBTTagCompound());
            itemStack.getTagCompound().setFloat("NumOfUsesRemaining", wrenchMaterial.getNumOfUses());
        }
        itemStack.getTagCompound().setFloat("NumOfUsesRemaining", itemStack.getTagCompound().getFloat("NumOfUsesRemaining") - amountOfUses);
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {

    }
}
