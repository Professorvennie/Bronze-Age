package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.api.enums.RedstoneMode;
import com.professorvennie.bronzeage.api.steam.ISteamBoiler;
import com.professorvennie.bronzeage.tileentitys.TileEntityBasicSteamMachine;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import org.lwjgl.input.Keyboard;

import java.util.List;

/**
 * Created by snows on 9/18/2016.
 */
public class ItemBasicMachine extends ItemBlock {

    public ItemBasicMachine(Block block) {
        super(block);
        setUnlocalizedName(block.getUnlocalizedName());
        setRegistryName(block.getUnlocalizedName());
    }

    @Override
    public boolean placeBlockAt(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState state) {
        boolean result = super.placeBlockAt(itemStack, player, world, pos, side, hitX, hitY, hitZ, state);
        if (result) {
            if (!world.isRemote) {
                if (itemStack != null && itemStack.getTagCompound() != null) {
                    if (world.getTileEntity(pos) instanceof ISteamBoiler) {
                        ((ISteamBoiler) world.getTileEntity(pos)).getWaterTank().readFromNBT(itemStack.getTagCompound());
                        ((ISteamBoiler) world.getTileEntity(pos)).getSteamTank().readFromNBT(itemStack.getTagCompound());
                        ((ISteamBoiler) world.getTileEntity(pos)).setTemperature(itemStack.getTagCompound().getInteger("temp"));
                    }

                    if (world.getTileEntity(pos) instanceof TileEntityBasicSteamMachine) {
                        TileEntityBasicSteamMachine basicMachine = (TileEntityBasicSteamMachine) world.getTileEntity(pos);
                        basicMachine.getSteamTank().readFromNBT(itemStack.getTagCompound());
                        basicMachine.setRedstoneMode(RedstoneMode.values()[itemStack.getTagCompound().getInteger("RedStoneMode")]);
                        NBTTagList list = itemStack.getTagCompound().getTagList("items", Constants.NBT.TAG_COMPOUND);
                        basicMachine.inventory = new ItemStack[basicMachine.getSizeInventory()];

                        for (int i = 0; i < list.tagCount(); i++) {
                            NBTTagCompound compound = list.getCompoundTagAt(i);
                            int j = compound.getByte("slot") & 0xff;

                            if (j >= 0 && j < basicMachine.inventory.length) {
                                basicMachine.inventory[j] = ItemStack.loadItemStackFromNBT(compound);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    //// TODO: FIX THIS
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean b) {
        if (itemStack.getTagCompound() != null) {
            list.add("Pre Configured");
            if (!(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))) {
                list.add("Hold " + TextFormatting.AQUA + "Shift" + TextFormatting.GRAY + " for more information");
            } else {
                list.add("RedStone Mode: " + RedstoneMode.values()[itemStack.getTagCompound().getInteger("RedStoneMode")]);
                list.add("Steam Amount: " + itemStack.getTagCompound().getInteger("SteamAmount") + " mb");
                if (itemStack.getTagCompound().getInteger("Amount") != 0)
                    list.add("Water Amount: " + itemStack.getTagCompound().getInteger("Amount") + " mb");
                if (itemStack.getTagCompound().getInteger("temp") != 0)
                    list.add("Temperature: " + itemStack.getTagCompound().getInteger("temp") + " F");
                // }
            }
        }
    }
}
