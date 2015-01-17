package com.professorvennie.bronzeage.items;

import com.professorvennie.bronzeage.BronzeAge;
import com.professorvennie.bronzeage.api.manual.IManualEntry;
import com.professorvennie.bronzeage.lib.GuiIds;
import com.professorvennie.bronzeage.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by ProfessorVennie on 10/23/2014 at 4:59 PM.
 */
public class ItemManual extends ItemBase {

    public ItemManual() {
        super("manual");
        setMaxStackSize(1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":manual");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        player.openGui(BronzeAge.INSTANSE, GuiIds.MANUAL, world, 0, 0, 0);
        return super.onItemRightClick(itemStack, world, player);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        Block block = world.getBlock(x, y, z);
        if (block != null && block instanceof IManualEntry) {
            IManualEntry entry = (IManualEntry) block;
            BronzeAge.proxey.setPageToOpen(entry.getPage(world, x, y, z));
        }
        return super.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return true;
    }

    @Override
    public EnumRarity getRarity(ItemStack itemStack) {
        return EnumRarity.rare;
    }
}
