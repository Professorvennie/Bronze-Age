package com.professorvennie.bronzeage.core.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by ProfessorVennie on 1/9/2015 at 11:12 AM.
 */
public class MessageConfigUpdate extends MessageCoords implements IMessageHandler<MessageConfigUpdate, IMessage>, IMessage {

    private ForgeDirection direction;

    public MessageConfigUpdate() {
    }

    public MessageConfigUpdate(int x, int y, int z, ForgeDirection direction) {
        super(x, y, z);
        this.direction = direction;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        super.fromBytes(buf);

        direction = ForgeDirection.getOrientation(buf.readInt());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        super.toBytes(buf);

        buf.writeInt(direction.ordinal());
    }

    @Override
    public IMessage onMessage(MessageConfigUpdate message, MessageContext ctx) {
        /*if(Minecraft.getMinecraft().theWorld.getTileEntity(message.x, message.y, message.z) instanceof ISideConfigurable){
            ((ISideConfigurable) Minecraft.getMinecraft().theWorld.getTileEntity(message.x, message.y, message.z)).changeMode(direction);
        }*/
        System.out.println(direction);
        return null;
    }
}