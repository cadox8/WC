package es.projectalpha.wc.survival.utils;

import java.util.ArrayList;

public class Info {

    private ArrayList<String> msgs;
    private int id;

    public Info(){
        msgs = new ArrayList<>();
        id = 0;
        init();
    }

    public void init(){
        msgs.add("&cConectate al servidor en un terminio de 30 dias o tu parcela sera borrada.");
        msgs.add("&aPVP activo en todos los mundos, menos en las parcelas.");
        msgs.add("&aActivate o desactivate el PVP con: &f/pvp");
        msgs.add("&aRecuerda visitar nuestro foro: &6worldcrafteros.net/foro");
        msgs.add("&aVota con: &6/vote &bpara ganar 70 tokens al dia.");
        msgs.add("&aUsa &e/exp2cash &bpara pasar la experiencia a dinero!");
        msgs.add("&aSolo esta permitido 1 parcela con cualquier rango.");
        msgs.add("&aRangos VIP en &f&ltienda.worldcrafteros.net");
        msgs.add("&aPara volver a tu parcela pon: &f/as tp num_parcela");
        msgs.add("&cProhibido usar bugs, puedes ser baneado del servidor.");
        msgs.add("&aNo aceptes TPA de nadie que no conozcas.");
        msgs.add("&aProhibido el uso de xray y hacks.");
        msgs.add("&aPara comprar y vender cosas ves a &f/warp shop");
        msgs.add("&cProhibido entrar con mas de 1 cuenta al servidor.");
        msgs.add("&aPara dar permisos a parcela, pon: &f/as addfriend <nombre>");
        msgs.add("&aNo des permisos a desconocidos en tu parcela.");
        msgs.add("&aSi quieres comerciar con un jugador usa &f/trade nombre");
        msgs.add("&cSi matas a alguien, ganaras el 10% de tu dinero. Si te matan, lo perderas.");
        msgs.add("&4Esta totalmente prohibido hacer circuitos de tolvas.");
        msgs.add("&aPuedes llevarte tu caballo a otro mundo, si te montas.");
        msgs.add("&aGana 2.000$ por hora, hasta un maximo de 48.000$ al dia por estar conectado.");
        msgs.add("&aCanjea 200 Tokens por 50.000$ en &f/tokens shop voto");
        msgs.add("&aCaptura animales con &6&lPoke&f&lEggs &a en /warp granja");
    }

    public String getInfoMsg(){
        String msg = msgs.get(id);
        id++;

        if (id == msgs.size()) id = 0;

        return msg;
    }
}
