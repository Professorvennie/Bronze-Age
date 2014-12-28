package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.BronzeAge;
import com.professorvennie.bronzeage.api.manual.IManualEntry;
import com.professorvennie.bronzeage.api.wrench.IWrench;
import com.professorvennie.bronzeage.api.wrench.IWrenchable;
import com.professorvennie.bronzeage.lib.Reference;
import com.professorvennie.bronzeage.tileentitys.TileEntityBasicMachine;
import com.professorvennie.bronzeage.tileentitys.TileEntityMod;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;
import java.util.Random;

/**
 * Created by ProfessorVennie on 10/21/2014 at 7:28 PM.
 */
public abstract class BlockBasicMachine extends Block implements ITileEntityProvider, IWrenchable, IGuiHandler, IManualEntry {

    public boolean isActive;
    private String name;
    @SideOnly(Side.CLIENT)
    private IIcon frontIcon, sideIcon, topIcon;

    protected BlockBasicMachine(String name) {
        super(Material.iron);
        setCreativeTab(BronzeAge.tabMain);
        this.name = name;
        setBlockName(name);
        BronzeAge.guiHandler.registerHandler(getGuiId(), this);
    }

    public static void updateBlockState(boolean active, World worldObj, int xCoord, int yCoord, int zCoord, ItemStack blockActive, ItemStack blockIdle) {
        int i = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        TileEntity tileentity = worldObj.getTileEntity(xCoord, yCoord, zCoord);

        if (active) {
            worldObj.setBlock(xCoord, yCoord, zCoord, Block.getBlockFromItem(blockActive.getItem()));
        } else {
            worldObj.setBlock(xCoord, yCoord, zCoord, Block.getBlockFromItem(blockIdle.getItem()));
        }

        worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);

        if (tileentity != null) {
            tileentity.validate();
            worldObj.setTileEntity(xCoord, yCoord, zCoord, tileentity);
        }
    }

    private boolean shouldDisplayInCreative(ItemStack itemStack) {
        return itemStack.getItemDamage() == 0;
    }

    public abstract int getGuiId();

    @Override
    public void getSubBlocks(Item item, CreativeTabs tabs, List list) {
        list.add(new ItemStack(item, 1, 0));//Idle
        list.add(new ItemStack(item, 1, 1));//Active
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (getGuiId() != -1) {
            if (!world.isRemote) {
                if (player.getCurrentEquippedItem() == null)
                    player.openGui(BronzeAge.INSTANSE, getGuiId(), world, x, y, z);
                else if (!(player.getCurrentEquippedItem().getItem() instanceof IWrench))
                    player.openGui(BronzeAge.INSTANSE, getGuiId(), world, x, y, z);
            }
        }
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        sideIcon = iconRegister.registerIcon(Reference.MOD_ID + ":blockBronze");
        frontIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + name + (isActive ? "_Front_Active" : "_Front_Idle"));
        topIcon = iconRegister.registerIcon(Reference.MOD_ID + ":machines_Top");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (side == 1)
            return topIcon;
        else if (side == meta)
            return frontIcon;
        else if ((meta == 0 || meta == 1) && side == 3)
            return frontIcon;
        else
            return sideIcon;
    }

    private void setDefualtDirection(World world, int x, int y, int z) {
        if (!world.isRemote) {
            Block l = world.getBlock(x, y, z - 1);
            Block il = world.getBlock(x, y, z + 1);
            Block jl = world.getBlock(x - 1, y, z);
            Block kl = world.getBlock(x + 1, y, z - 1);
            byte b0 = 3;

            if (l.isNormalCube() && !il.isNormalCube()) {
                b0 = 3;
            }

            if (il.isNormalCube() && !l.isNormalCube()) {
                b0 = 2;
            }

            if (kl.isNormalCube() && !jl.isNormalCube()) {
                b0 = 5;
            }

            if (jl.isNormalCube() && !kl.isNormalCube()) {
                b0 = 4;
            }
            world.setBlockMetadataWithNotify(x, y, z, b0, 2);
        }
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        setDefualtDirection(world, x, y, z);
        super.onBlockAdded(world, x, y, z);
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemstack) {
        int l = MathHelper.floor_double((double) (entityLivingBase.rotationYaw * 4.0f / 360.0f) + 0.5D) & 3;

        if (l == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (l == 1) {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if (l == 2) {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (l == 3) {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }

        if (itemstack.hasDisplayName()) {
            ((TileEntityMod) world.getTileEntity(x, y, z)).setCustomName(itemstack.getDisplayName());
        }
    }

    @Override
    public abstract TileEntity createNewTileEntity(World world, int i);

    @Override
    public void dismantle(World world, EntityPlayer player, ItemStack wrench, int x, int y, int z) {
        if (wrench.getItem() instanceof IWrench) {
            ItemStack block = new ItemStack(world.getBlock(x, y, z), 1, world.getBlockMetadata(x, y, z));
            if (block.getTagCompound() == null)
                block.setTagCompound(new NBTTagCompound());

            if (world.getTileEntity(x, y, z) instanceof TileEntityBasicMachine) {
                TileEntityBasicMachine basicMachine = (TileEntityBasicMachine) world.getTileEntity(x, y, z);
                NBTTagList list = new NBTTagList();
                for (int i = 0; i < basicMachine.inventory.length; i++) {
                    if (basicMachine.inventory[i] != null) {
                        System.out.println(basicMachine.inventory[i]);
                        NBTTagCompound compound = new NBTTagCompound();
                        compound.setByte("slot", (byte) i);
                        basicMachine.inventory[i].writeToNBT(compound);
                        list.appendTag(compound);
                    }
                }
                block.getTagCompound().setTag("items", list);
            }

            EntityItem item = new EntityItem(world, (double) x, (double) y, (double) z, block);
            world.spawnEntityInWorld(item);
            world.setBlockToAir(x, y, z);
        }
    }

    @Override
    public boolean rotateBlock(World worldObj, int x, int y, int z, ForgeDirection axis) {
        int metadata = worldObj.getBlockMetadata(x, y, z) + 1;
        if (metadata > 5) metadata = 2;
        worldObj.setBlockMetadataWithNotify(x, y, z, metadata, 2);
        return true;
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        if (isActive && !world.isRemote) {

        }
    }

    public static class ItemBasicMachine extends ItemBlock {

        public ItemBasicMachine(Block block) {
            super(block);
        }

        @Override
        public boolean placeBlockAt(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
            boolean result = super.placeBlockAt(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
            if (result) {
                if (!world.isRemote) {
                    if (itemStack != null && itemStack.getTagCompound() != null) {
                        if (world.getTileEntity(x, y, z) instanceof TileEntityBasicMachine) {
                            TileEntityBasicMachine basicMachine = (TileEntityBasicMachine) world.getTileEntity(x, y, z);
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

        @Override
        public String getUnlocalizedName(ItemStack itemStack) {
            if (itemStack.getItemDamage() == 0)
                return super.getUnlocalizedName(itemStack) + "Idle";
            if (itemStack.getItemDamage() == 1)
                return super.getUnlocalizedName(itemStack) + "Active";
            else {
                itemStack.setItemDamage(0);
                return super.getUnlocalizedName(itemStack) + "Idle";
            }
        }
    }
}
