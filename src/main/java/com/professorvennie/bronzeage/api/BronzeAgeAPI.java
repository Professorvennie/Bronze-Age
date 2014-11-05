package com.professorvennie.bronzeage.api;

import com.professorvennie.bronzeage.client.gui.pages.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ProfessorVennie on 10/23/2014 at 5:14 PM.
 */
public class BronzeAgeAPI {

    private static List<Page> pages = new ArrayList<Page>();

    public static List<Page> getPages() {
        return pages;
    }

    public static void addPage(Page page) {
        pages.add(page);
    }
}
