package com.professorvennie.bronzeage.lib;

import com.professorvennie.bronzeage.api.BronzeAgeAPI;
import com.professorvennie.bronzeage.client.gui.pages.Page;
import com.professorvennie.bronzeage.client.gui.pages.PageCover;
import com.professorvennie.bronzeage.client.gui.pages.PageText;

/**
 * Created by ProfessorVennie on 10/25/2014 at 4:05 PM.
 */
public class BookData {

    public static PageCover pageCover = new PageCover(0, "bronzeAge.title", "bronzeAge.author");
    public static PageText basics = new PageText(1, "bronzeAge.book.basics");

    public static void initPages() {
        addPage(pageCover);
        addPage(basics);
    }

    private static void addPage(Page page) {
        BronzeAgeAPI.addPage(page);
    }
}
