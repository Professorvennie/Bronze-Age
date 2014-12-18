package com.professorvennie.bronzeage.core.network;

import com.professorvennie.bronzeage.api.tiles.IButtonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by ProfessorVennie on 12/14/2014 at 7:41 PM.
 */
public class MessageButton extends MessageCoords implements IMessageHandler<MessageButton, IMessage>, IMessage {

    int id;

    public MessageButton() {
    }

    public MessageButton(int x, int y, int z, int id) {
        super(x, y, z);
        this.id = id;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        super.fromBytes(buf);
        id = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        super.toBytes(buf);
        buf.writeInt(id);
    }

    @Override
    public IMessage onMessage(MessageButton message, MessageContext ctx) {
        TileEntity tile = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);
        if (tile != null && tile instanceof IButtonHandler) {
            ((IButtonHandler) tile).handleClick(message.id);
        }
        return null;
    }
}
