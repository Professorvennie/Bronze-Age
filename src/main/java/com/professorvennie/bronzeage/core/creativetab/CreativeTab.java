package com.professorvennie.bronzeage.core.creativetab;

import com.professorvennie.bronzeage.lib.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * Created by ProfessorVennie on 10/21/2014 at 5:26 PM.
 */
public class CreativeTab {

    public static CreativeTabs tabMain = new CreativeTabs(Reference.MOD_ID) {
        @Override
        public Item getTabIconItem() {
            return Items.diamond;
        }
    };
}
