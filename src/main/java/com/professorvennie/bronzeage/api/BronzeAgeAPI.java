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

    public static WrenchMaterial stoneWrenchMaterial = new WrenchMaterial("stone", new ItemStack(Blocks.stone), 1000, 2f, 3f);
    public static WrenchMaterial tinWrenchMaterial = new WrenchMaterial("tin", null, 1500, 2f, 2f);
    public static WrenchMaterial copperWrenchMaterial = new WrenchMaterial("copper", null, 2500, 1f, 2f);
    public static WrenchMaterial ironWrenchMaterial = new WrenchMaterial("iron", new ItemStack(Items.iron_ingot, 0, 1), 3500, 1f, 1f);
    public static WrenchMaterial bronzeWrenchMaterial = new WrenchMaterial("bronze", null, 5000, 1f, 0.5f);
    public static WrenchMaterial diamondWrenchMaterial = new WrenchMaterial("diamond", new ItemStack(Items.diamond), 10000, 0.5f, 0.5f);

    private static List<IPage> pages = new ArrayList<IPage>();

    public static List<IPage> getPages() {
        return pages;
    }

    public static void addPage(IPage page) {
        pages.add(page);
    }
}
