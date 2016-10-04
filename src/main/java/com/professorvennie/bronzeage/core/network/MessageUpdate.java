package com.professorvennie.bronzeage.core.network;

import com.professorvennie.bronzeage.tileentitys.TileEntitySteamBoiler;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by snows on 9/28/2016.
 */
public class MessageUpdate extends MessageCoords implements IMessage {

    private int water, steam, burnTime, temp;

    public MessageUpdate(){

    }

    public MessageUpdate(int x, int y, int z, int water, int steam, int burnTime, int temp){
        super(x, y, z);
        this.water = water;
        this.steam = steam;
        this.burnTime = burnTime;
        this.temp = temp;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        super.fromBytes(buf);

        water = buf.readInt();
        steam = buf.readInt();
        burnTime = buf.readInt();
        temp = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        super.toBytes(buf);

        buf.writeInt(water);
        buf.writeInt(steam);
        buf.writeInt(burnTime);
        buf.writeInt(temp);
    }

    public static class Handler implements IMessageHandler<MessageUpdate, IMessage>{

        @Override
        public IMessage onMessage(MessageUpdate message, MessageContext ctx) {
            if (ctx.getServerHandler().playerEntity.worldObj.getTileEntity(new BlockPos(message.x, message.y, message.z)) instanceof TileEntitySteamBoiler){
                TileEntitySteamBoiler boiler = (TileEntitySteamBoiler)ctx.getServerHandler().playerEntity.worldObj.getTileEntity(new BlockPos(message.x, message.y, message.z));
                System.out.println("Boiler Amount: " + boiler.getSteamAmount() + "  Boiler getFeild: " + boiler.getField(2) + "  MessageSteam: " + message.steam);
                //boiler.temp = message.temp;
                //boiler.burnTime = message.burnTime;
                boiler.setSteamAmount(message.steam);
            }
            return null;
        }
    }
}
