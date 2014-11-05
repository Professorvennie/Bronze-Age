package com.professorvennie.bronzeage.items;

import net.minecraft.item.Item;

/**
 * Created by ProfessorVennie on 10/21/2014 at 5:20 PM.
 */
public class ModItems {

    public static Item manual;

    public static void init() {
        manual = new ItemManual();
    }
}
