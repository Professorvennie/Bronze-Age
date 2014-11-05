package com.professorvennie.bronzeage.lib;

import com.professorvennie.bronzeage.api.BronzeAgeAPI;
import com.professorvennie.bronzeage.client.gui.pages.Page;
import com.professorvennie.bronzeage.client.gui.pages.PageCover;
import com.professorvennie.bronzeage.client.gui.pages.PageSmelting;
import com.professorvennie.bronzeage.client.gui.pages.PageText;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 * Created by ProfessorVennie on 10/25/2014 at 4:05 PM.
 */
public class BookData {

    public static void initPages() {
        addPage(new PageCover(0, "bronzeAge.title", "bronzeAge.author"));
        addPage(new PageText(1, "bronzeAge.book.basics"));
        addPage(new PageSmelting(2, new ItemStack(Blocks.iron_ore)));
    }

    private static void addPage(Page page) {
        BronzeAgeAPI.addPage(page);
    }

    private static Page getPage(int pageNum) {
        return BronzeAgeAPI.getPages().get(pageNum);
    }
}
