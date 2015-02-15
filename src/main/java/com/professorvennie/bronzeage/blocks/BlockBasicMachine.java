package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.BronzeAge;
import com.professorvennie.bronzeage.api.enums.RedstoneMode;
import com.professorvennie.bronzeage.api.manual.IManualEntry;
import com.professorvennie.bronzeage.api.tiles.ISteamBoiler;
import com.professorvennie.bronzeage.api.wrench.IWrench;
import com.professorvennie.bronzeage.api.wrench.IWrenchable;
import com.professorvennie.bronzeage.lib.Reference;
import com.professorvennie.bronzeage.tileentitys.TileEntityBasicMachine;
import com.professorvennie.bronzeage.tileentitys.TileEntityBasicSteamMachine;
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
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.input.Keyboard;

import java.util.List;
import java.util.Random;

/**
 * Created by ProfessorVennie on 10/21/2014 at 7:28 PM.
 */
public abstract class BlockBasicMachine extends Block implements ITileEntityProvider, IWrenchable, IGuiHandler, IManualEntry {

    public boolean isActive;
    @SideOnly(Side.CLIENT)
    public IIcon frontIconIdle, frontIconActive, sideIcon, topIcon;
    private String name;

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

    public abstract int getGuiId();

    /*@Override
    public void getSubBlocks(Item item, CreativeTabs tabs, List list) {
        list.add(new ItemStack(item, 1, 0));//Idle
        list.add(new ItemStack(item, 1, 1));//Active
    }*/

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (getGuiId() != -1) {
            if (!world.isRemote) {
                if (world.getTileEntity(x, y, z) instanceof ISteamBoiler) {
                    ((ISteamBoiler) world.getTileEntity(x, y, z)).fill(null, 1000);
                }
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
        topIcon = iconRegister.registerIcon(Reference.MOD_ID + ":machines_Top");
        frontIconIdle = iconRegister.registerIcon(Reference.MOD_ID + ":" + name + "_Front_Idle");
        frontIconActive = iconRegister.registerIcon(Reference.MOD_ID + ":" + name + "_Front_Active");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (side == 1)
            return topIcon;
        else if (side == meta)
            return frontIconIdle;
        else if (meta == 0 && side == 3)
            return frontIconIdle;
        else
            return sideIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess block, int x, int y, int z, int side) {
        int meta = block.getBlockMetadata(x, y, z);
        TileEntityBasicMachine tile = (TileEntityBasicMachine)block.getTileEntity(x, y, z);
        if(side == meta && tile.isActive)
            return frontIconActive;
        return getIcon(side, meta);
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
            if (world.getTileEntity(x, y, z) instanceof ISteamBoiler) {
                ((ISteamBoiler) world.getTileEntity(x, y, z)).getWaterTank().writeToNBT(block.getTagCompound());
                ((ISteamBoiler) world.getTileEntity(x, y, z)).getSteamTank().writeToNBT(block.getTagCompound());
            }

            if (world.getTileEntity(x, y, z) instanceof TileEntityBasicSteamMachine) {
                TileEntityBasicSteamMachine basicMachine = (TileEntityBasicSteamMachine) world.getTileEntity(x, y, z);
                basicMachine.getSteamTank().writeToNBT(block.getTagCompound());
                block.getTagCompound().setInteger("RedStoneMode", basicMachine.getRedStoneMode().ordinal());
                NBTTagList list = new NBTTagList();
                for (int i = 0; i < basicMachine.inventory.length; i++) {
                    if (basicMachine.inventory[i] != null) {
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
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        if (((TileEntityBasicMachine)world.getTileEntity(x, y, z)).isActive && !world.isRemote) {
            System.out.println("ACTIVE");
            float x1 = (float) x + 0.5f;
            float y1 = (float) y + 1.0f;
            float z1 = (float) z + 0.5f;
            float f1 = random.nextFloat() * 0.6F - 0.3F;

            world.spawnParticle("smoke", (double) (x1 + f1), (double) y1, (double) (z1 + f1), 0.0D, 0.0D, 0.0D);
            world.spawnParticle("smoke", (double) (x1 - f1), (double) y1, (double) (z1 + f1), 0.0D, 0.0D, 0.0D);
            world.spawnParticle("smoke", (double) (x1 + f1), (double) y1, (double) (z1 - f1), 0.0D, 0.0D, 0.0D);
            world.spawnParticle("smoke", (double) (x1 - f1), (double) y1, (double) (z1 - f1), 0.0D, 0.0D, 0.0D);
            world.spawnParticle("smoke", (double) x1, (double) (y1 + f1), (double) z1, 0.0D, 0.0D, 0.0D);
            world.spawnParticle("smoke", (double) (x1 + f1), (double) (y1 + f1), (double) z1, 0.0D, 0.0D, 0.0D);
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
                        if (world.getTileEntity(x, y, z) instanceof ISteamBoiler) {
                            ((ISteamBoiler) world.getTileEntity(x, y, z)).getWaterTank().readFromNBT(itemStack.getTagCompound());
                            ((ISteamBoiler) world.getTileEntity(x, y, z)).getSteamTank().readFromNBT(itemStack.getTagCompound());
                        }

                        if (world.getTileEntity(x, y, z) instanceof TileEntityBasicSteamMachine) {
                            TileEntityBasicSteamMachine basicMachine = (TileEntityBasicSteamMachine) world.getTileEntity(x, y, z);
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

        @Override
        public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean b) {
            if (itemStack.getTagCompound() != null) {
                list.add("Pre Configured");
                if (!(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))) {
                    list.add("Hold " + EnumChatFormatting.AQUA + "Shift" + EnumChatFormatting.GRAY + " for more information");
                } else {
                    list.add("RedStone Mode: " + RedstoneMode.values()[itemStack.getTagCompound().getInteger("RedStoneMode")]);
                    list.add("Steam Amount: " + itemStack.getTagCompound().getInteger("SteamAmount") + " mb");
                    if (itemStack.getTagCompound().getInteger("Amount") != 0)
                        list.add("Water Amount: " + itemStack.getTagCompound().getInteger("Amount") + " mb");
                }
            }
        }

        @Override
        public String getUnlocalizedName(ItemStack itemStack) {
            /*switch (itemStack.getItemDamage()) {
                case 0:
                    return super.getUnlocalizedName(itemStack) + "Idle";
                case 1:
                    return super.getUnlocalizedName(itemStack) + "Active";
                default:
                    itemStack.setItemDamage(0);*/
                    return super.getUnlocalizedName(itemStack) /*+ "Idle"*/;
            //}
        }
    }
}
