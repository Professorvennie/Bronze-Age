package com.professorvennie.bronzeage.items;

import com.professorvennie.bronzeage.BronzeAge;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

/**
 * Created by ProfessorVennie on 10/21/2014 at 5:29 PM.
 */
public abstract class ItemBase extends Item {

    public ItemBase(String name) {
        setUnlocalizedName(name);
        setCreativeTab(BronzeAge.tabMain);
    }

    @Override
    public Item setUnlocalizedName(String name) {
        return super.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public abstract void registerIcons(IIconRegister iconRegister);
}
