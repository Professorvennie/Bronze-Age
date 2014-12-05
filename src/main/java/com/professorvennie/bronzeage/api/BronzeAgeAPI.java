package com.professorvennie.bronzeage.api;

import com.professorvennie.bronzeage.api.manual.IPage;
import com.professorvennie.bronzeage.api.wrench.WrenchMaterial;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ProfessorVennie on 10/23/2014 at 5:14 PM.
 */
public class BronzeAgeAPI {

    private static BronzeAgeAPI INSTANCE = new BronzeAgeAPI();
    public WrenchMaterial stoneWrenchMaterial = new WrenchMaterial("stone", 1000, 2, 3);
    public WrenchMaterial tinWrenchMaterial = new WrenchMaterial("tin", 1500, 2, 2);
    public WrenchMaterial copperWrenchMaterial = new WrenchMaterial("copper", 2500, 1, 2);
    public WrenchMaterial ironWrenchMaterial = new WrenchMaterial("iron", 3500, 1, 1);
    public WrenchMaterial bronzeWrenchMaterial = new WrenchMaterial("bronze", 5000, 1, 1);
    public WrenchMaterial diamondWrenchMaterial = new WrenchMaterial("diamond", 10000, 1, 1);
    private List<IPage> pages = new ArrayList<IPage>();

    public static BronzeAgeAPI getInstance() {
        return INSTANCE;
    }

    public List<IPage> getPages() {
        return pages;
    }

    public void addPage(IPage page) {
        pages.add(page);
    }

    public void setPageToOpen(IPage page) {

    }
}
