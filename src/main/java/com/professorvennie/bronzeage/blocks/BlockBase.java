package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.BronzeAge;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by ProfessorVennie on 10/21/2014 at 5:20 PM.
 */
public class BlockBase extends Block {

    protected BlockBase(Material material, String name) {
        super(material);
        setBlockName(name);
        setCreativeTab(BronzeAge.tabMain);
    }

    @Override
    public Block setBlockName(String name) {
        //GameRegistry.registerBlock(this, name);
        return super.setBlockName(name);
    }
}
