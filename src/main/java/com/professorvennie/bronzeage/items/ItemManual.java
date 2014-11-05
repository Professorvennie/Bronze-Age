package com.professorvennie.bronzeage.items;

import com.professorvennie.bronzeage.BronzeAge;
import com.professorvennie.bronzeage.lib.GuiIds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by ProfessorVennie on 10/23/2014 at 4:59 PM.
 */
public class ItemManual extends ItemBase {

    public ItemManual() {
        super("manual");
        setMaxStackSize(1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        player.openGui(BronzeAge.INSTANSE, GuiIds.MANUAL, world, 0, 0, 0);
        return super.onItemRightClick(itemStack, world, player);
    }
}
