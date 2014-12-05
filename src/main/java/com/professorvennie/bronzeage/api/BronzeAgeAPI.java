package com.professorvennie.bronzeage.api;

import com.professorvennie.bronzeage.api.manual.IPage;
import com.professorvennie.bronzeage.api.wrench.WrenchMaterial;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ProfessorVennie on 10/23/2014 at 5:14 PM.
 */
public class BronzeAgeAPI {
    public static WrenchMaterial stoneWrenchMaterial = new WrenchMaterial("stone", new ItemStack(Blocks.stone), 1000, 2, 3);
    public static WrenchMaterial tinWrenchMaterial = new WrenchMaterial("tin", null, 1500, 2, 2);
    public static WrenchMaterial copperWrenchMaterial = new WrenchMaterial("copper", null, 2500, 1, 2);
    public static WrenchMaterial ironWrenchMaterial = new WrenchMaterial("iron", new ItemStack(Items.iron_ingot), 3500, 1, 1);
    public static WrenchMaterial bronzeWrenchMaterial = new WrenchMaterial("bronze", null, 5000, 1, 1);
    public static WrenchMaterial diamondWrenchMaterial = new WrenchMaterial("diamond", new ItemStack(Items.diamond), 10000, 1, 1);
    private static List<IPage> pages = new ArrayList<IPage>();

    public static List<IPage> getPages() {
        return pages;
    }

    public static void addPage(IPage page) {
        pages.add(page);
    }

    public static void setPageToOpen(IPage page) {

    }
}
