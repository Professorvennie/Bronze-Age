package com.professorvennie.bronzeage.core.network;

import com.professorvennie.bronzeage.lib.Reference;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;


/**
 * Created by ProfessorVennie on 12/14/2014 at 7:41 PM.
 */
public class PacketHandler {

    public static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);

    public static void init() {
        INSTANCE.registerMessage(MessageButton.MessageButtonHandler.class, MessageButton.class, 0, Side.SERVER);
        INSTANCE.registerMessage(MessageConfigUpdate.Handler.class, MessageConfigUpdate.class, 1, Side.SERVER);
        INSTANCE.registerMessage(MessageUpdate.Handler.class, MessageUpdate.class, 2, Side.SERVER);
    }
}
