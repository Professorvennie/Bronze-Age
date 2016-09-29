package com.professorvennie.bronzeage.core.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * Created by ProfessorVennie on 12/14/2014 at 7:42 PM.
 */
public class MessageCoords implements IMessage {

    public int x;
    public int y;
    public int z;

    public MessageCoords() {
    }

    public MessageCoords(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }
}
