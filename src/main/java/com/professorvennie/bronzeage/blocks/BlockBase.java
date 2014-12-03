package com.professorvennie.bronzeage.blocks;

import com.professorvennie.bronzeage.BronzeAge;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

/**
 * Created by ProfessorVennie on 10/21/2014 at 5:20 PM.
 */
public abstract class BlockBase extends Block {

    protected BlockBase(Material material, String name) {
        super(material);
        setBlockName(name);
        setCreativeTab(BronzeAge.tabMain);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public abstract void registerBlockIcons(IIconRegister iconRegister);

    @Override
    public Block setBlockName(String name) {
        return super.setBlockName(name);
    }
}
