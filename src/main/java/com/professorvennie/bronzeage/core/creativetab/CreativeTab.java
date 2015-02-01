package com.professorvennie.bronzeage.core.creativetab;

import com.professorvennie.bronzeage.blocks.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


/**
 * Created by ProfessorVennie on 10/21/2014 at 5:26 PM.
 */
public class CreativeTab extends CreativeTabs {

    public CreativeTab(String lable) {
        super(lable);
    }

    @Override
    public Item getTabIconItem() {
        return Item.getItemFromBlock(ModBlocks.steamBoilerIdle);
    }
}
