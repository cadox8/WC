package es.projectalpha.wc.clans;

import es.projectalpha.wc.core.api.WCUser;

import java.util.UUID;

public class ClanPlayer extends WCUser {

    public ClanPlayer(UUID uuid){
        super(uuid);
    }


    public void teleportBase(Clan clan){
        teleport(clan.getBase());
    }
}
