package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * Created by ProfessorVennie on 11/14/2014 at 7:50 PM.
 */
public class BlockOre extends BlockBase {

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockOre() {
        super(Material.rock, "ore");
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < icons.length; i++)
            list.add(new ItemStack(item, 1, i));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        icons = new IIcon[2];
        String[] names = new String[]{"oreCopper", "oreTin"};

        for (int i = 0; i < icons.length; i++)
            icons[i] = iconRegister.registerIcon(Reference.MOD_ID + ":" + names[i]);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (meta == 0)
            return icons[0];
        else
            return icons[1];
    }

    public static class ItemBlockOre extends ItemBlock {

        public ItemBlockOre(Block block) {
            super(block);
        }

        @Override
        public void getSubItems(Item item, CreativeTabs tab, List list) {
            for (int i = 0; i < 2; i++)
                list.add(new ItemStack(item, 1, i));
        }

        @Override
        public String getUnlocalizedName(ItemStack itemStack) {
            return super.getUnlocalizedName() + "." + itemStack.getItemDamage();
        }
    }
}
