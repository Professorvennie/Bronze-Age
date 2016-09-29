package com.professorvennie.bronzeage.core.network;

import com.professorvennie.bronzeage.api.tiles.ISideConfigurable;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by ProfessorVennie on 1/9/2015 at 11:12 AM.
 */
public class MessageConfigUpdate extends MessageCoords implements IMessage {

    private EnumFacing direction;
    private boolean isTanks;

    public MessageConfigUpdate() {
    }

    public MessageConfigUpdate(int x, int y, int z, EnumFacing direction, boolean isTanks) {
        super(x, y, z);
        this.direction = direction;
        this.isTanks = isTanks;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        super.fromBytes(buf);

        direction = EnumFacing.getFront(buf.readInt());
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
            if (ctx.getServerHandler().playerEntity.worldObj.getTileEntity(new BlockPos(message.x, message.y, message.z)) instanceof ISideConfigurable) {
                ((ISideConfigurable) ctx.getServerHandler().playerEntity.worldObj.getTileEntity(new BlockPos(message.x, message.y, message.z))).changeMode(message.direction);
                if (message.isTanks)
                    ((ISideConfigurable) ctx.getServerHandler().playerEntity.worldObj.getTileEntity(new BlockPos(message.x, message.y, message.z))).changeTankMode(message.direction);
            }
            return null;
        }
    }
}