package com.professorvennie.bronzeage.api.manual;

/**
 * Created by ProfessorVennie on 11/9/2014 at 6:52 PM.
 */

import net.minecraft.world.World;

/**
 * implement this interface on a block that is in the manual
 */
public interface IManualEntry {

    public IPage getPage(World world, int x, int y, int z);
}
