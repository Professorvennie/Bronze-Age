package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.core.creativetab.CreativeTab;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by ProfessorVennie on 10/21/2014 at 5:20 PM.
 */
public class BlockBase extends Block {

    protected BlockBase(Material material, String name) {
        super(material);
        setBlockName(name);
        setCreativeTab(CreativeTab.tabMain);
    }

    @Override
    public Block setBlockName(String name) {
        GameRegistry.registerBlock(this, this.getUnlocalizedName());
        return super.setBlockName(name);
    }
}
