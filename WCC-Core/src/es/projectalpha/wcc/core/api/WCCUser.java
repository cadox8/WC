package es.projectalpha.wcc.core.api;

import es.projectalpha.wcc.core.WCCCore;
import es.projectalpha.wcc.core.utils.ReflectionAPI;
import es.projectalpha.wcc.core.utils.Utils;
import lombok.Getter;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

public class WCCUser {

    private WCCCore plugin = WCCCore.getInstance();

    @Getter private UUID uuid;

    public WCCUser(OfflinePlayer p) {
        this(p.getUniqueId());
    }

    public WCCUser(UUID id) {
        uuid = id;
    }

    public OfflinePlayer getOfflinePlayer() {
        return plugin.getServer().getOfflinePlayer(uuid);
    }

    public Player getPlayer() {
        return plugin.getServer().getPlayer(uuid);
    }

    public String getName() {
        return getOfflinePlayer().getName();
    }

    public boolean isOnline() {
        return getOfflinePlayer().isOnline();
    }

    public boolean hasPermission(String permission){
        return getPlayer().hasPermission(permission);
    }

    public void sendMessage(String str) {
        getPlayer().sendMessage(Utils.colorize(str));
    }

    public void sendMessagePrefix(String str) {
        getPlayer().sendMessage(WCCCore.getPrefix() + Utils.colorize(str));
    }

    public void toggleAdminChat() {
        if (!WCCServer.getAdminChatMode().contains(this)) {
            sendMessagePrefix("&2AdminChat Activado");
            WCCServer.getAdminChatMode().add(this);
        } else {
            sendMessagePrefix("&2AdminChat Desactivado");
            WCCServer.getAdminChatMode().remove(this);
        }
    }

    /*
     * Reflection
     */
    public void sendActionBar(Player p, String msg) {
        try {
            Constructor<?> constructor = ReflectionAPI.getNmsClass("PacketPlayOutChat").getConstructor(ReflectionAPI.getNmsClass("IChatBaseComponent"), byte.class);
            Object icbc = ReflectionAPI.getNmsClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + Utils.colorize(msg) + "\"}");
            Object packet = constructor.newInstance(icbc, (byte) 2);

            ReflectionAPI.sendPacket(p, packet);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sendHeaderAndFooter(Player p, String headerText, String footerText) {
        try {
            Class chatSerializer = ReflectionAPI.getNmsClass("IChatBaseComponent$ChatSerializer");

            Object tabHeader = chatSerializer.getMethod("a", String.class).invoke(chatSerializer, "{'text': '" + Utils.colorize(headerText) + "'}");
            Object tabFooter = chatSerializer.getMethod("a", String.class).invoke(chatSerializer, "{'text': '" + Utils.colorize(footerText) + "'}");
            Object tab = ReflectionAPI.getNmsClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(new Class[]{ReflectionAPI.getNmsClass("IChatBaseComponent")}).newInstance(new Object[]{tabHeader});

            Field f = tab.getClass().getDeclaredField("b");
            f.setAccessible(true);
            f.set(tab, tabFooter);

            ReflectionAPI.sendPacket(p, tab);
        } catch (IllegalAccessException | InstantiationException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public int getPing() {
        try {
            Method getHandleMethod = getPlayer().getClass().getDeclaredMethod("getHandle");
            getHandleMethod.setAccessible(true);

            Object entityPlayer = getHandleMethod.invoke(getPlayer());

            Field pingField = entityPlayer.getClass().getDeclaredField("ping");
            pingField.setAccessible(true);

            return pingField.getInt(entityPlayer);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException e) {}
        return -1;
    }

}
