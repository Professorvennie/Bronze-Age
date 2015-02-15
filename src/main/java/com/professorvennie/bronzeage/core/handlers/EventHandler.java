package com.professorvennie.bronzeage.core.handlers;

import com.professorvennie.bronzeage.items.ModItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * Created by ProfessorVennie on 2/14/2015 at 10:33 PM.
 */
public class EventHandler {

    public static final String TAG_RECEIVED_BOOK = "Received_Book";

    @SubscribeEvent
    public void onWorldLoad(PlayerEvent.PlayerLoggedInEvent event){
        EntityPlayer player = event.player;
        if(!player.worldObj.isRemote){
            if(!player.getEntityData().hasKey(TAG_RECEIVED_BOOK) && !player.getEntityData().getBoolean(TAG_RECEIVED_BOOK)){
                player.getEntityData().setBoolean(TAG_RECEIVED_BOOK, true);
                player.inventory.addItemStackToInventory(new ItemStack(ModItems.manual));
            }
        }
    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerLoggedInEvent event){
        event.player.getEntityData().setBoolean(TAG_RECEIVED_BOOK, true);
    }
}
