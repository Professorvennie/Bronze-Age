package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.BronzeAge;
import com.professorvennie.bronzeage.lib.Reference;
import com.professorvennie.bronzeage.tileentitys.TileEntityMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by ProfessorVennie on 10/21/2014 at 7:28 PM.
 */
public class BlockBasicMachine extends Block implements ITileEntityProvider {

    private static boolean keepInventory;
    public boolean isActive;
    public int guiId = -1;
    private String name;
    @SideOnly(Side.CLIENT)
    private IIcon frontIcon, sideIcon, topIcon;

    protected BlockBasicMachine(Material material, String name, boolean isActive) {
        super(material);
        this.isActive = isActive;
        this.name = name;
        if (isActive)
            setBlockName(name + "Active");
        else {
            setBlockName(name + "Idle");
            setCreativeTab(BronzeAge.tabMain);
        }
    }

    public static void updateBlockState(boolean active, World worldObj, int xCoord, int yCoord, int zCoord, Block blockActive, Block blockIdle) {
        int i = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        TileEntity tileentity = worldObj.getTileEntity(xCoord, yCoord, zCoord);
        keepInventory = true;

        if (active) {
            worldObj.setBlock(xCoord, yCoord, zCoord, blockActive);
        } else {
            worldObj.setBlock(xCoord, yCoord, zCoord, blockIdle);
        }

        keepInventory = false;

        worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);

        if (tileentity != null) {
            tileentity.validate();
            worldObj.setTileEntity(xCoord, yCoord, zCoord, tileentity);
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (guiId != -1) {
            if (!world.isRemote) {
                player.openGui(BronzeAge.INSTANSE, guiId, world, x, y, z);
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
        else if (meta == 0 && side == 3)
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

    public void breakBlock(World world, int x, int y, int z, Block block, int side) {
        if (!keepInventory) {
            TileEntity tileEntity = world.getTileEntity(x, y, z);

            if (!(tileEntity instanceof ISidedInventory)) {
                return;
            }

            ISidedInventory inventory = (ISidedInventory) tileEntity;

            for (int i = 0; i < inventory.getSizeInventory(); i++) {

                ItemStack itemStack = inventory.getStackInSlot(i);

                if (itemStack != null && itemStack.stackSize > 0) {

                    Random rand = new Random();

                    float dX = rand.nextFloat() * 0.8F + 0.1F;
                    float dY = rand.nextFloat() * 0.8F + 0.1F;
                    float dZ = rand.nextFloat() * 0.8F + 0.1F;

                    EntityItem entityItem = new EntityItem(world, x + dX, y + dY, z + dZ, itemStack.copy());

                    if (itemStack.hasTagCompound()) {
                        entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
                    }

                    float factor = 0.05F;
                    entityItem.motionX = rand.nextGaussian() * factor;
                    entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                    entityItem.motionZ = rand.nextGaussian() * factor;
                    world.spawnEntityInWorld(entityItem);
                    itemStack.stackSize = 0;
                }
            }
        }
        super.breakBlock(world, x, y, z, block, side);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return null;
    }
}
