package com.professorvennie.bronzeage.items;


import com.professorvennie.bronzeage.BronzeAge;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by ProfessorVennie on 12/6/2014 at 11:16 AM.
 */
public class ItemMaterials extends ItemBase {

    private final String[] NAMES = new String[]{"ingotCopper", "ingotTin", "ingotSilver", "ingotLead", "ingotBronze", "dirtyChunkCopper", "dirtyChunkTin", "dirtyChunkIron", "dirtyChunkGold", "dirtyChunkSilver", "dirtyChunkLead", "cleanChunkCopper", "cleanChunkTin", "cleanChunkIron", "cleanChunkGold", "cleanChunkSilver", "cleanChunkLead", "dustCopper", "dustTin", "dustIron", "dustGold", "dustSilver", "dustLead", "dustBronze"};
    public static final int COPPER = 0, TIN = 1, SILVER = 2, LEAD = 3, BRONZE = 4, DIRTY_CHUNK_COPPER = 5, DIRTY_CHUNK_TIN = 6, DIRTY_CHUNK_IRON = 7, DIRTY_CHUNK_GOLD = 8, DIRTY_CHUNK_SILVER = 9, DIRTY_CHUNK_LEAD = 10,
                                                                                   CLEAN_CHUNK_COPPER = 11, CLEAN_CHUNK_TIN = 12, CLEAN_CHUNK_IRON = 13, CLEAN_CHUNK_GOLD = 14, CLEAN_CHUNK_SILVER = 15, CLEAN_CHUNK_LEAD = 16,
                                                                                   DIRTY_DUST_COPPER = 17, DIRTY_DUST_TIN = 18, DIRTY_DUST_IRON = 19, DIRTY_DUST_GOLD = 20, DIRTY_DUST_SILVER = 21, DIRTY_DUST_LEAD = 22,
                                                                                   DUST_COPPER = 23, DUST_TIN = 24, DUST_IRON = 25, DUST_GOLD = 26, DUST_SILVER = 27, DUST_LEAD = 28;

    public ItemMaterials() {
        super("material");
        setHasSubtypes(true);
    }

    @Override
    public void registerItemModel() {
        BronzeAge.proxey.registerItemRenderer(this, getDamage(new ItemStack(this)), getUnlocalizedName(new ItemStack(this)));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        final List<ItemStack> items = Stream.of(EnumType.values()).map(enumType -> new ItemStack(item, 1, enumType.getMeta())).collect(Collectors.toList());
        list.addAll(items);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return super.getUnlocalizedName(itemStack) + "." + NAMES[itemStack.getItemDamage()];
    }

    public enum EnumType implements IStringSerializable{

        COPPER(0, "copper"),
        TIN(1, "tin"),
        SILVER(2, "silver"),
        LEAD(3, "lead"),
        BRONZE(4, "bronze");

        private static final EnumType[] META_LOOKUP = Stream.of(values()).sorted(Comparator.comparing(EnumType::getMeta)).toArray(EnumType[]::new);

        private final int meta;
        private final String name;

        EnumType(int meta, String name) {
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

        public static EnumType byMetadata(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length) {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }
    }
}