package com.professorvennie.bronzeage.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

import java.util.List;

/**
 * Created by ProfessorVennie on 11/14/2014 at 7:50 PM.
 */
public class BlockOre extends BlockBase {

    public static final PropertyEnum ORES = PropertyEnum.create("variant", EnumOres.class);

    public BlockOre() {
        super(Material.ROCK, "ore");
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < EnumOres.values().length; i++) {
            EnumOres[] enumOreses = EnumOres.values();
            EnumOres ores = enumOreses[i];
            list.add(new ItemStack(item, 1, ores.getMeta()));
        }
    }

    public IBlockState getStateFromMeta(int meta){
        return this.getDefaultState().withProperty(ORES, EnumOres.getEnumFromMeta(meta));
    }

    public int getMetaFromState(IBlockState state){
        return ((EnumOres)state.getValue(ORES)).getMeta();
    }

    protected BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, ORES);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return ((EnumOres)state.getValue(ORES)).getMeta();
    }


    public static class ItemBlockOre extends ItemBlock {

        public ItemBlockOre(Block block) {
            super(block);
            setUnlocalizedName(block.getUnlocalizedName());
            setRegistryName(block.getUnlocalizedName());
        }

        @Override
        public int getMetadata(int damage) {
            return damage;
        }

        @Override
        public void getSubItems(Item item, CreativeTabs tab, List list) {
            for (int i = 0; i < EnumOres.values().length; i++)
                list.add(new ItemStack(item, 1, i));
        }

        @Override
        public String getUnlocalizedName(ItemStack itemStack) {
            return super.getUnlocalizedName() + "." + itemStack.getItemDamage();
        }
    }

    public static enum EnumOres implements IStringSerializable {

        COPPER(0, "copper"),
        TIN(1, "tin");

        private int meta;
        private String name;
        private static EnumOres[] ORES = new EnumOres[values().length];

        private EnumOres(int meta, String name){
            this.meta = meta;
            this.name = name;
        }

        public int getMeta() {
            return meta;
        }

        @Override
        public String getName() {
            return name;
        }

        public static EnumOres getEnumFromMeta(int meta){
            if (meta < 0 || meta >= ORES.length)
                meta = 0;

            return ORES[meta];
        }

        static{
            EnumOres[] var0 = values();
            int var1 = var0.length;

            for (int var2 = 0; var2 < var1; ++var2) {
                EnumOres var3 = var0[var2];
                ORES[var3.getMeta()] = var3;
            }
        }
    }
}