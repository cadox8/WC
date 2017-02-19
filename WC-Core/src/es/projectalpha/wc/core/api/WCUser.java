package es.projectalpha.wc.core.api;

import es.projectalpha.wc.core.WCCore;
import es.projectalpha.wc.core.utils.ReflectionAPI;
import es.projectalpha.wc.core.utils.Utils;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

public class WCUser {

    private WCCore plugin = WCCore.getInstance();

    @Getter private UUID uuid;

    @Getter @Setter private UserData userData;

    public WCUser(OfflinePlayer p) {
        this(p.getUniqueId());
    }

    public WCUser(UUID id) {
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

    public void sendDiv(){
        getPlayer().sendMessage(Utils.colorize("&e====================="));
    }

    public void sendMessage(String str) {
        getPlayer().sendMessage(Utils.colorize(str));
    }

    public void sendMessagePrefix(String str) {
        getPlayer().sendMessage(WCCore.getPrefix() + Utils.colorize(str));
    }

    public void sendSound(Sound sound){
        getPlayer().playSound(getPlayer().getLocation(), sound, 1, 1);
    }

    public void toggleAdminChat() {
        if (!WCServer.getAdminChatMode().contains(this)) {
            sendMessagePrefix("&2AdminChat Activado");
            WCServer.getAdminChatMode().add(this);
        } else {
            sendMessagePrefix("&2AdminChat Desactivado");
            WCServer.getAdminChatMode().remove(this);
        }
    }

    public void toggleFly(){
        if (getPlayer().isFlying()){
            sendMessagePrefix("&cVuelo desactivado");
            getPlayer().setAllowFlight(false);
        } else {
            sendMessagePrefix("&2Vuelo activado");
            getPlayer().setAllowFlight(true);
        }
    }

    /*
     * Reflection
     */
    public void sendActionBar(String msg) {
        try {
            Constructor<?> constructor = ReflectionAPI.getNmsClass("PacketPlayOutChat").getConstructor(ReflectionAPI.getNmsClass("IChatBaseComponent"), byte.class);
            Object icbc = ReflectionAPI.getNmsClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + Utils.colorize(msg) + "\"}");
            Object packet = constructor.newInstance(icbc, (byte) 2);

            ReflectionAPI.sendPacket(getPlayer(), packet);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sendHeaderAndFooter(String headerText, String footerText) {
        try {
            Class chatSerializer = ReflectionAPI.getNmsClass("IChatBaseComponent$ChatSerializer");

            Object tabHeader = chatSerializer.getMethod("a", String.class).invoke(chatSerializer, "{'text': '" + Utils.colorize(headerText) + "'}");
            Object tabFooter = chatSerializer.getMethod("a", String.class).invoke(chatSerializer, "{'text': '" + Utils.colorize(footerText) + "'}");
            Object tab = ReflectionAPI.getNmsClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(new Class[]{ReflectionAPI.getNmsClass("IChatBaseComponent")}).newInstance(new Object[]{tabHeader});

            Field f = tab.getClass().getDeclaredField("b");
            f.setAccessible(true);
            f.set(tab, tabFooter);

            ReflectionAPI.sendPacket(getPlayer(), tab);
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

    /*
    * JSON
    */
    public void jsonURL(String text, String hover, String url){
        TextComponent message = new TextComponent(text);
        message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hover).create()));
        getPlayer().spigot().sendMessage(message);
    }

    public  void jsonMessages(String text, String hover, String command){
        TextComponent message = new TextComponent(text);
        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hover).create()));
        getPlayer().spigot().sendMessage(message);
    }

    @Data
    public static class UserData {
        Location lastLocation = null;
    }
}
