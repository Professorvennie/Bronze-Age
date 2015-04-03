package com.professorvennie.bronzeage.api.steam;

import com.professorvennie.bronzeage.tileentitys.TileEntitySteamReceiver;
import com.professorvennie.bronzeage.tileentitys.TileEntitySteamTransmitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ProfessorVennie on 3/20/2015 at 4:25 PM.
 */
public class SteamNetwork {

    private static Map<String, List<TileEntitySteamTransmitter>> quantumLinks = new HashMap<String, List<TileEntitySteamTransmitter>>();

    public static List<TileEntitySteamTransmitter> getLinks(String owner){
        if (owner == null){
            return new ArrayList<TileEntitySteamTransmitter>();
        }
        List<TileEntitySteamTransmitter> providers = quantumLinks.get(owner);
        if (providers == null){
            providers = new ArrayList<TileEntitySteamTransmitter>();
        }
        return providers;
    }

    public static void registerLink(TileEntitySteamTransmitter item){
        if (item.getOwner() != null){
            if (!quantumLinks.containsKey(item.getOwner())){
                quantumLinks.put(item.getOwner(), new ArrayList<TileEntitySteamTransmitter>());
            }

            if (!quantumLinks.get(item.getOwner()).contains(item)){
                quantumLinks.get(item.getOwner()).add(item);
            }
        }
    }

    public static void removeLink(TileEntitySteamTransmitter item)    {
        if (item.getOwner() != null){
            quantumLinks.get(item.getOwner()).remove(item);
        }
    }

    public static void purge(){
        quantumLinks.clear();
    }

    public static int requestEnergy(int value, boolean simulate, String owner){

        int tosend = 0;

        // Since there are multiple items on the network that can send energy
        // we will loop over all of them until we have the amount requested
        // or there is no more left
        for (TileEntitySteamTransmitter link : getLinks(owner)){
            /*if (link.canSend()){
                tosend += link.requestEnergy(value - tosend, simulate);

                if (tosend == value){
                    break;
                }
            }*/
        }

        return tosend;
    }
}