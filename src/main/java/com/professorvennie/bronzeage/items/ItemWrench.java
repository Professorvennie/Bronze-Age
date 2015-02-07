package com.professorvennie.bronzeage.items;

import com.professorvennie.bronzeage.api.BronzeAgeAPI;
import com.professorvennie.bronzeage.api.wrench.IWrench;
import com.professorvennie.bronzeage.api.wrench.IWrenchable;
import com.professorvennie.bronzeage.api.wrench.WrenchMaterial;
import com.professorvennie.bronzeage.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.input.Keyboard;

import java.util.List;

/**
 * Created by ProfessorVennie on 11/26/2014 at 11:50 PM.
 */
public class ItemWrench extends ItemBase implements IWrench {

    private final int numOfWrenches = 6;
    private IIcon[] icons;

    public ItemWrench() {
        super("wrench");
        setHasSubtypes(true);
        setMaxStackSize(1);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
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
            list.add(EnumChatFormatting.GOLD + translate("material") + ": " + EnumChatFormatting.DARK_AQUA + getWrenchMaterial(itemStack).getLocalizedName());
            list.add(EnumChatFormatting.GOLD + translate("numOfUses") + ": " + EnumChatFormatting.DARK_AQUA + getNumOfUsesRemaining(itemStack));
            list.add(EnumChatFormatting.GOLD + translate("rotation") + ": " + EnumChatFormatting.DARK_AQUA + getWrenchMaterial(itemStack).getUsesPerRotation());
            list.add(EnumChatFormatting.GOLD + translate("dismantle") + ": " + EnumChatFormatting.DARK_AQUA + getWrenchMaterial(itemStack).getUsesPerDismantle());
            list.add(EnumChatFormatting.GOLD + translate("durability") + ": " + EnumChatFormatting.DARK_AQUA + getDurability(itemStack));

        } else {
            list.add(EnumChatFormatting.GOLD + translate("numOfUses") + ": " + EnumChatFormatting.DARK_AQUA + getNumOfUsesRemaining(itemStack));
            list.add(EnumChatFormatting.DARK_GRAY + translate("hold") + " " + EnumChatFormatting.DARK_AQUA + translate("shift") + " " + EnumChatFormatting.DARK_GRAY + translate("moreInfo"));
            list.add(EnumChatFormatting.DARK_GRAY + translate("hold") + " " + EnumChatFormatting.DARK_AQUA + translate("shiftandh") + " " + EnumChatFormatting.DARK_GRAY + translate("helpInfo"));
        }

        if ((Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) && Keyboard.isKeyDown(Keyboard.KEY_H)) {
            list.add(EnumChatFormatting.DARK_GRAY + translate("info.0"));
            list.add(EnumChatFormatting.DARK_GRAY + translate("info.1"));
            list.add(EnumChatFormatting.DARK_GRAY + translate("info.2"));
            list.add(EnumChatFormatting.DARK_GRAY + translate("info.3"));
            list.add(EnumChatFormatting.DARK_GRAY + translate("info.4"));
            list.add(EnumChatFormatting.DARK_GRAY + translate("info.5"));
        }
    }

    private String translate(String name) {
        return StatCollector.translateToLocal("tooltip.wrench." + name);
    }

    @Override
    public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        float dismanlt = getWrenchMaterial(itemStack).getUsesPerDismantle();
        float rotate = getWrenchMaterial(itemStack).getUsesPerRotation();
        float usesRemaining = getNumOfUsesRemaining(itemStack);
        if (!player.isSneaking()) {
            if (world.getBlock(x, y, z) instanceof IWrenchable) {
                if (usesRemaining - rotate >= 0 && !world.isRemote && world.getBlock(x, y, z).rotateBlock(world, x, y, z, ForgeDirection.getOrientation(side).getOpposite())) {
                    subtractUse(itemStack, rotate);
                    Block block = world.getBlock(x, y, z);
                    block.rotateBlock(world, x, y, z, ForgeDirection.getOrientation(side).getOpposite());
                    player.swingItem();
                }
            }
        } else {
            if (world.getBlock(x, y, z) instanceof IWrenchable) {
                IWrenchable wrenchable = (IWrenchable) world.getBlock(x, y, z);

                if (usesRemaining - dismanlt >= 0 && !world.isRemote) {
                    subtractUse(itemStack, dismanlt);
                    if (!world.isRemote) {
                        player.swingItem();
                        wrenchable.dismantle(world, player, itemStack, x, y, z);
                    }
                }
            }
        }
        return super.onItemUseFirst(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
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

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        icons = new IIcon[numOfWrenches];

        for (int i = 0; i < icons.length; i++)
            icons[i] = iconRegister.registerIcon(Reference.MOD_ID + ":" + getWrenchMaterial(new ItemStack(this, 1, i)).getLocalizedName() + "Wrench");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int i) {
        return i < icons.length ? icons[i] : icons[0];
    }
}
