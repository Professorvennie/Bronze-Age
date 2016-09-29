package com.professorvennie.bronzeage.core.network;

import com.professorvennie.bronzeage.api.tiles.IButtonHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by ProfessorVennie on 12/14/2014 at 7:41 PM.
 */
public class MessageButton extends MessageCoords implements IMessage {

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

    public static class MessageButtonHandler implements IMessageHandler<MessageButton, IMessage> {

        @Override
        public IMessage onMessage(MessageButton message, MessageContext ctx) {
            TileEntity tile = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(new BlockPos(message.x, message.y, message.z));
            if (tile != null && tile instanceof IButtonHandler) {
                ((IButtonHandler) tile).handleClick(message.id);
            }
            return null;
        }
    }
}
