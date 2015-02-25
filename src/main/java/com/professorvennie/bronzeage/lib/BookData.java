package com.professorvennie.bronzeage.lib;

import com.professorvennie.bronzeage.api.BronzeAgeAPI;
import com.professorvennie.bronzeage.blocks.ModBlocks;
import com.professorvennie.bronzeage.client.gui.pages.Page;
import com.professorvennie.bronzeage.client.gui.pages.PageCover;
import com.professorvennie.bronzeage.client.gui.pages.PageCrafting;
import com.professorvennie.bronzeage.client.gui.pages.PageText;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 * Created by ProfessorVennie on 10/25/2014 at 4:05 PM.
 */
public class BookData {

    public static PageCover pageCover = new PageCover(0, "bronzeAge.book.title", "bronzeAge.book.author");
    public static PageText basics = new PageText(1, "bronzeAge.book.page.basics"), steamBoiler = new PageText(2, "bronzeAge.book.page.steamBoiler");
    public static PageCrafting steamBoilerRecipe = new PageCrafting(3, new ItemStack(ModBlocks.steamBoilerIdle));

    public static void initPages() {
        addPages(pageCover, basics, steamBoiler, steamBoilerRecipe);
    }

    private static void addPages(Page... pages){
        for (Page p : pages){
            BronzeAgeAPI.addPage(p);
        }
    }
}
