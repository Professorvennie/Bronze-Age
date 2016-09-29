package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.BronzeAge;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

/**
 * Created by ProfessorVennie on 10/21/2014 at 5:20 PM.
 */
public abstract class BlockBase extends Block {

    String name;

    protected BlockBase(Material material, String name) {
        super(material);
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(BronzeAge.tabMain);
    }

    public void registerItemModel(ItemBlock itemBlock) {
        BronzeAge.proxey.registerItemRenderer(itemBlock, 0, name);
    }

    @Override
    public Block setUnlocalizedName(String name) {
        return super.setUnlocalizedName(name);
    }
}
