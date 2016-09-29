package com.professorvennie.bronzeage.items;

import com.professorvennie.bronzeage.BronzeAge;
import com.professorvennie.bronzeage.api.manual.IManualEntry;
import com.professorvennie.bronzeage.lib.GuiIds;
import com.professorvennie.bronzeage.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by ProfessorVennie on 10/23/2014 at 4:59 PM.
 */
public class ItemManual extends ItemBase {

    public ItemManual() {
        super("manual");
        setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        playerIn.openGui(BronzeAge.INSTANSE, GuiIds.MANUAL, worldIn, 0, 0, 0);
        return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        Block block = worldIn.getBlockState(pos).getBlock();
        if (block != null && block instanceof IManualEntry){
            IManualEntry entry = (IManualEntry) block;
            BronzeAge.proxey.setPageToOpen(entry.getPage(worldIn, pos.getX(), pos.getY(), pos.getZ()));
        }
        return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean b) {
        if(itemStack.getTagCompound() != null){
            if (itemStack.getTagCompound().getInteger("CurrentPage") == 0)
                list.add(I18n.translateToLocal("tooltip.manual.cover"));
            else
                 list.add(I18n.translateToLocal("tooltip.manual.currentPage") + " " + itemStack.getTagCompound().getInteger("CurrentPage"));
        }
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public EnumRarity getRarity(ItemStack itemStack) {
        return EnumRarity.RARE;
    }
}
