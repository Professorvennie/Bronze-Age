package com.professorvennie.bronzeage.lib;

import com.professorvennie.bronzeage.api.BronzeAgeAPI;
import com.professorvennie.bronzeage.blocks.ModBlocks;
import com.professorvennie.bronzeage.client.gui.GuiManual;
import com.professorvennie.bronzeage.client.gui.pages.*;
import com.professorvennie.bronzeage.items.ItemMaterials;
import com.professorvennie.bronzeage.items.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 * Created by ProfessorVennie on 10/25/2014 at 4:05 PM.
 */
public class BookData {

    public static PageCover pageCover = new PageCover(null, 0, "bronzeAge.book.title", "bronzeAge.book.author");
    public static PageText basics = new PageText(1, "bronzeAge.book.page.basics");
    public static PageSmelting copper = new PageSmelting(2, new ItemStack(ModBlocks.ore, 1, 0)),
                               tin = new PageSmelting(3, new ItemStack(ModBlocks.ore, 1, 1));
    public static PageCrafting bronze = new PageCrafting(4, new ItemStack(ModItems.materials, 1, ItemMaterials.BRONZE));
    public static PageText steamboiler = new PageText(5, "bronzeAge.book.page.steamBoiler");
    public static PageCrafting steamBoilerRecipe = new PageCrafting(6, new ItemStack(ModBlocks.steamBoilerIdle));
    public static PageText transportingSteam = new PageText(7, "bronzeAge.book.page.transporting");
    public static PageCrafting reciever = new PageCrafting(8, new ItemStack(ModBlocks.steamReceiver)),
                               transmitter = new PageCrafting(9, new ItemStack(ModBlocks.steamTransmitter));

    public static void initPages() {
        addPages(pageCover, basics, copper, tin, bronze, steamboiler, steamBoilerRecipe, transportingSteam, reciever, transmitter);
    }

    private static void addPages(Page... pages){
        for (Page p : pages){
            BronzeAgeAPI.addPage(p);
        }
    }
}