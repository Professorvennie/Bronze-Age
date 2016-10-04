package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.BronzeAge;
import com.professorvennie.bronzeage.api.enums.RedstoneMode;
import com.professorvennie.bronzeage.api.manual.IManualEntry;
import com.professorvennie.bronzeage.api.steam.ISteamBoiler;
import com.professorvennie.bronzeage.api.wrench.IWrench;
import com.professorvennie.bronzeage.api.wrench.IWrenchable;
import com.professorvennie.bronzeage.lib.Reference;
import com.professorvennie.bronzeage.tileentitys.TileEntityBasicMachine;
import com.professorvennie.bronzeage.tileentitys.TileEntityBasicSteamMachine;
import com.professorvennie.bronzeage.tileentitys.TileEntityMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

/**
 * Created by ProfessorVennie on 10/21/2014 at 7:28 PM.
 */
public abstract class BlockBasicMachine extends Block implements ITileEntityProvider, IWrenchable, IGuiHandler, IManualEntry {

    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final IProperty WORKING = PropertyBool.create("working");

    private String name;

    protected BlockBasicMachine(String name) {
        super(Material.IRON);
        setCreativeTab(BronzeAge.tabMain);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
        BronzeAge.guiHandler.registerHandler(getGuiId(), this);
    }

    public void registerItemModel(ItemBlock itemBlock) {
        BronzeAge.proxey.registerItemRenderer(itemBlock, 0, name);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        boolean working = false;
        if (tileEntity instanceof TileEntityBasicMachine){
            working = ((TileEntityBasicMachine)tileEntity).isWorking();
        }
        return state.withProperty(WORKING, working);
    }

    public abstract int getGuiId();

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (getGuiId() != -1) {
            if (!world.isRemote) {
                if (player.getHeldItem(hand) == null)
                    player.openGui(BronzeAge.INSTANSE, getGuiId(), world, pos.getX(), pos.getY(), pos.getZ());
                else if (!(player.getHeldItem(hand).getItem() instanceof IWrench)) {
                    player.openGui(BronzeAge.INSTANSE, getGuiId(), world, pos.getX(), pos.getY(), pos.getZ());
                    System.out.println("wrench");
                }
            }
        }
        return true;
    }

    @Override
    @Deprecated
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @Deprecated
    public boolean isFullCube(IBlockState state) {
        return false;
    }


    private void setDefualtDirection(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            IBlockState iblockstate = worldIn.getBlockState(pos.north());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock())
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock())
            {
                enumfacing = EnumFacing.EAST;
            }
            else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock())
            {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }


    public static void setState(boolean active, World worldIn, BlockPos pos){
        IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (active) {
            worldIn.setBlockState(pos, Blocks.LIT_FURNACE.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, Blocks.LIT_FURNACE.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        } else {
            worldIn.setBlockState(pos, Blocks.FURNACE.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, Blocks.FURNACE.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }

        if (tileentity != null) {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y){
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        setDefualtDirection(worldIn, pos, state);
        super.onBlockAdded(worldIn, pos, state);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

        if (stack.hasDisplayName()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (stack.hasDisplayName()) {
                ((TileEntityMod) worldIn.getTileEntity(pos)).setCustomName(stack.getDisplayName());
            }
        }
    }

    @Override
    public abstract TileEntity createNewTileEntity(World world, int i);

    @Override
    public void dismantle(World world, EntityPlayer player, ItemStack wrench, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        if (wrench.getItem() instanceof IWrench) {
            ItemStack block = new ItemStack(world.getBlockState(pos).getBlock());
            if (block.getTagCompound() == null)
                block.setTagCompound(new NBTTagCompound());
            if (world.getTileEntity(pos) instanceof ISteamBoiler) {
                ((ISteamBoiler) world.getTileEntity(pos)).getWaterTank().writeToNBT(block.getTagCompound());
                ((ISteamBoiler) world.getTileEntity(pos)).getSteamTank().writeToNBT(block.getTagCompound());
                block.getTagCompound().setInteger("temp", ((ISteamBoiler) world.getTileEntity(pos)).getTemperature());
            }

            if (world.getTileEntity(pos) instanceof TileEntityBasicSteamMachine) {
                TileEntityBasicSteamMachine basicMachine = (TileEntityBasicSteamMachine) world.getTileEntity(pos);
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
            world.setBlockToAir(pos);
        }
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    public BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, FACING, WORKING);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random random) {
        if (((TileEntityBasicMachine)world.getTileEntity(pos)).isActive && !world.isRemote) {
            System.out.println("ACTIVE");
            float x1 = (float) pos.getX() + 0.5f;
            float y1 = (float) pos.getY() + 1.0f;
            float z1 = (float) pos.getZ() + 0.5f;
            float f1 = random.nextFloat() * 0.6F - 0.3F;

            world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double) (x1 + f1), (double) y1, (double) (z1 + f1), 0.0D, 0.0D, 0.0D);
            world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double) (x1 - f1), (double) y1, (double) (z1 + f1), 0.0D, 0.0D, 0.0D);
            world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double) (x1 + f1), (double) y1, (double) (z1 - f1), 0.0D, 0.0D, 0.0D);
            world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double) (x1 - f1), (double) y1, (double) (z1 - f1), 0.0D, 0.0D, 0.0D);
            world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double) x1, (double) (y1 + f1), (double) z1, 0.0D, 0.0D, 0.0D);
            world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double) (x1 + f1), (double) (y1 + f1), (double) z1, 0.0D, 0.0D, 0.0D);
        }
    }
}
