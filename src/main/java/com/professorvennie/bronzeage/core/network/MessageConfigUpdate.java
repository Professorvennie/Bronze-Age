package com.professorvennie.bronzeage.core.network;

import com.professorvennie.bronzeage.api.tiles.ISideConfigurable;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by ProfessorVennie on 1/9/2015 at 11:12 AM.
 */
public class MessageConfigUpdate extends MessageCoords implements IMessage {

    private ForgeDirection direction;
    private boolean isTanks;

    public MessageConfigUpdate() {
    }

    public MessageConfigUpdate(int x, int y, int z, ForgeDirection direction, boolean isTanks) {
        super(x, y, z);
        this.direction = direction;
        this.isTanks = isTanks;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        super.fromBytes(buf);

        direction = ForgeDirection.getOrientation(buf.readInt());
        isTanks = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        super.toBytes(buf);

        buf.writeInt(direction.ordinal());
        buf.writeBoolean(isTanks);
    }

    public static class Handler implements IMessageHandler<MessageConfigUpdate, IMessage> {

        @Override
        public IMessage onMessage(MessageConfigUpdate message, MessageContext ctx) {
            if (ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z) instanceof ISideConfigurable) {
                ((ISideConfigurable) ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z)).changeMode(message.direction);
                if (message.isTanks)
                    ((ISideConfigurable) ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z)).changeTankMode(message.direction);
            }
            return null;
        }
    }
}