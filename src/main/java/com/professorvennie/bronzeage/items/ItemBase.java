package com.professorvennie.bronzeage.items;

import com.professorvennie.bronzeage.BronzeAge;
import net.minecraft.item.Item;

/**
 * Created by ProfessorVennie on 10/21/2014 at 5:29 PM.
 */
public abstract class ItemBase extends Item {

    String name;

    public ItemBase(String name) {
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(BronzeAge.tabMain);
        BronzeAge.proxey.registerItemRenderer(this, 0, name);
    }

    public void registerItemModel() {
        BronzeAge.proxey.registerItemRenderer(this, 0, name);
    }
}
