package com.professorvennie.bronzeage.items;

import com.professorvennie.bronzeage.api.BronzeAgeAPI;
import com.professorvennie.bronzeage.api.wrench.IWrench;
import com.professorvennie.bronzeage.api.wrench.IWrenchable;
import com.professorvennie.bronzeage.api.wrench.WrenchMaterial;
import com.professorvennie.bronzeage.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.List;

/**
 * Created by ProfessorVennie on 11/26/2014 at 11:50 PM.
 */
public class ItemWrench extends ItemBase implements IWrench {

    private final int numOfWrenches = 6;

    public ItemWrench() {
        super("wrench");
        setHasSubtypes(true);
        setMaxStackSize(1);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        if(itemStack.getTagCompound() != null && itemStack.getTagCompound().getBoolean("isBroken"))
            return super.getUnlocalizedName(itemStack) + "." + getWrenchMaterial(itemStack).getLocalizedName() + ".broken";
        return super.getUnlocalizedName(itemStack) + "." + getWrenchMaterial(itemStack).getLocalizedName();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < numOfWrenches; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return ((double)getWrenchMaterial(stack).getNumOfUses() - getNumOfUsesRemaining(stack)) / (double)getWrenchMaterial(stack).getNumOfUses();
    }

    @Override
    public int getDurability(ItemStack itemStack) {
        return getWrenchMaterial(itemStack).getDurability();
    }

    @Override
    public float getNumOfUsesRemaining(ItemStack itemStack) {
        initNBT(itemStack);
        return itemStack.getTagCompound().getFloat("NumOfUsesRemaining");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean b) {
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            list.add(TextFormatting.GOLD + translate("material") + ": " + TextFormatting.DARK_AQUA + getWrenchMaterial(itemStack).getLocalizedName());
            list.add(TextFormatting.GOLD + translate("numOfUses") + ": " + TextFormatting.DARK_AQUA + getNumOfUsesRemaining(itemStack));
            list.add(TextFormatting.GOLD + translate("rotation") + ": " + TextFormatting.DARK_AQUA + getWrenchMaterial(itemStack).getUsesPerRotation());
            list.add(TextFormatting.GOLD + translate("dismantle") + ": " + TextFormatting.DARK_AQUA + getWrenchMaterial(itemStack).getUsesPerDismantle());
            list.add(TextFormatting.GOLD + translate("durability") + ": " + TextFormatting.DARK_AQUA + getDurability(itemStack));

        } else {
            list.add(TextFormatting.GOLD + translate("numOfUses") + ": " + TextFormatting.DARK_AQUA + getNumOfUsesRemaining(itemStack));
            list.add(TextFormatting.DARK_GRAY + translate("hold") + " " + TextFormatting.DARK_AQUA + translate("shift") + " " + TextFormatting.DARK_GRAY + translate("moreInfo"));
            list.add(TextFormatting.DARK_GRAY + translate("hold") + " " + TextFormatting.DARK_AQUA + translate("shiftandh") + " " + TextFormatting.DARK_GRAY + translate("helpInfo"));
        }

        if ((Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) && Keyboard.isKeyDown(Keyboard.KEY_H)) {
            list.add(TextFormatting.DARK_GRAY + translate("info.0"));
            list.add(TextFormatting.DARK_GRAY + translate("info.1"));
            list.add(TextFormatting.DARK_GRAY + translate("info.2"));
            list.add(TextFormatting.DARK_GRAY + translate("info.3"));
            list.add(TextFormatting.DARK_GRAY + translate("info.4"));
            list.add(TextFormatting.DARK_GRAY + translate("info.5"));
        }
    }

    private String translate(String name) {
        return I18n.translateToLocal("tooltip.wrench." + name);
    }

    @Override
    public EnumActionResult onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        float dismanlt = getWrenchMaterial(itemStack).getUsesPerDismantle();
        float rotate = getWrenchMaterial(itemStack).getUsesPerRotation();
        float usesRemaining = getNumOfUsesRemaining(itemStack);
        if (!player.isSneaking()) {
            if (world.getBlockState(pos).getBlock() instanceof IWrenchable) {
                if (usesRemaining - rotate >= 0 && !world.isRemote/* && world.getBlockState(pos).rotateBlock(world, x, y, z, ForgeDirection.getOrientation(side).getOpposite())*/) {
                    subtractUse(itemStack, rotate);
                    Block block = world.getBlockState(pos).getBlock();
                    block.rotateBlock(world, pos, EnumFacing.getFront(side.getIndex()).getOpposite());
                }else if(usesRemaining == 0){
                    initNBT(itemStack);
                    itemStack.getTagCompound().setBoolean("isBroken", true);
                }
            }
        } else {
            if (world.getBlockState(pos) instanceof IWrenchable) {
                IWrenchable wrenchable = (IWrenchable) world.getBlockState(pos);

                if (usesRemaining - dismanlt >= 0 && !world.isRemote) {
                    subtractUse(itemStack, dismanlt);
                    if (!world.isRemote) {
                        wrenchable.dismantle(world, player, itemStack, pos.getX(), pos.getY(), pos.getZ());
                    }
                }else if(usesRemaining == 0){
                    initNBT(itemStack);
                    itemStack.getTagCompound().setBoolean("isBroken", true);
                }
            }
        }

        if(!(usesRemaining - dismanlt >= 0 && usesRemaining - rotate >= 0)){
            initNBT(itemStack);
            itemStack.getTagCompound().setBoolean("isBroken", true);
        }

        return super.onItemUse(itemStack, player, world, pos, hand, side, hitX, hitY, hitZ);
    }

    @Override
    public EnumActionResult onItemUseFirst(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        float dismanlt = getWrenchMaterial(itemStack).getUsesPerDismantle();
        float rotate = getWrenchMaterial(itemStack).getUsesPerRotation();
        float usesRemaining = getNumOfUsesRemaining(itemStack);
        if (!player.isSneaking()) {
            if (world.getBlockState(pos).getBlock() instanceof IWrenchable) {
                if (usesRemaining - rotate >= 0 && !world.isRemote/* && world.getBlockState(pos).rotateBlock(world, x, y, z, ForgeDirection.getOrientation(side).getOpposite())*/) {
                    subtractUse(itemStack, rotate);
                    Block block = world.getBlockState(pos).getBlock();
                    block.rotateBlock(world, pos, EnumFacing.getFront(side.getIndex()).getOpposite());
                }else if(usesRemaining == 0){
                    initNBT(itemStack);
                    itemStack.getTagCompound().setBoolean("isBroken", true);
                }
            }
        } else {
            if (world.getBlockState(pos) instanceof IWrenchable) {
                IWrenchable wrenchable = (IWrenchable) world.getBlockState(pos);

                if (usesRemaining - dismanlt >= 0 && !world.isRemote) {
                    subtractUse(itemStack, dismanlt);
                    if (!world.isRemote) {
                        System.out.println("dismatle");
                        wrenchable.dismantle(world, player, itemStack, pos.getX(), pos.getY(), pos.getZ());
                    }
                }else if(usesRemaining == 0){
                    initNBT(itemStack);
                    itemStack.getTagCompound().setBoolean("isBroken", true);
                }
            }
        }

        if(!(usesRemaining - dismanlt >= 0 && usesRemaining - rotate >= 0)){
            initNBT(itemStack);
            itemStack.getTagCompound().setBoolean("isBroken", true);
        }
        return super.onItemUseFirst(itemStack, player, world, pos, side, hitX, hitY, hitZ, hand);
    }

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int i, boolean b) {
        initNBT(itemStack);
        if(getNumOfUsesRemaining(itemStack) == 0)
            itemStack.getTagCompound().setBoolean("isBroken", true);
        super.onUpdate(itemStack, world, entity, i, b);
    }

    @Override
    public WrenchMaterial getWrenchMaterial(ItemStack itemStack) {
        switch (itemStack.getItemDamage()) {
            case 0:
                return BronzeAgeAPI.stoneWrenchMaterial;
            case 1:
                return BronzeAgeAPI.tinWrenchMaterial;
            case 2:
                return BronzeAgeAPI.copperWrenchMaterial;
            case 3:
                return BronzeAgeAPI.ironWrenchMaterial;
            case 4:
                return BronzeAgeAPI.bronzeWrenchMaterial;
            case 5:
                return BronzeAgeAPI.diamondWrenchMaterial;
            default:
                return null;
        }
    }

    @Override
    public void addUse(ItemStack itemStack, float amountOfUses) {
        initNBT(itemStack);
        itemStack.getTagCompound().setFloat("NumOfUsesRemaining", itemStack.getTagCompound().getFloat("NumOfUsesRemaining") + amountOfUses);
    }

    @Override
    public void subtractUse(ItemStack itemStack, float amountOfUses) {
        initNBT(itemStack);
        itemStack.getTagCompound().setFloat("NumOfUsesRemaining", itemStack.getTagCompound().getFloat("NumOfUsesRemaining") - amountOfUses);
    }

    private void initNBT(ItemStack itemStack) {
        if (itemStack.getTagCompound() == null) {
            itemStack.setTagCompound(new NBTTagCompound());
            itemStack.getTagCompound().setFloat("NumOfUsesRemaining", getWrenchMaterial(itemStack).getNumOfUses());
        }
    }
}
