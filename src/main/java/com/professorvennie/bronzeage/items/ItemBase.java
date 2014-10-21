package com.professorvennie.bronzeage.items;

import com.professorvennie.bronzeage.core.creativetab.CreativeTab;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Created by ProfessorVennie on 10/21/2014 at 5:29 PM.
 */
public class ItemBase extends Item {

    public ItemBase(String name){
        setUnlocalizedName(name);
        setCreativeTab(CreativeTab.tabMain);
    }

    @Override
    public Item setUnlocalizedName(String name) {
        GameRegistry.registerItem(this, this.getUnlocalizedName());
        return super.setUnlocalizedName(name);
    }
}
