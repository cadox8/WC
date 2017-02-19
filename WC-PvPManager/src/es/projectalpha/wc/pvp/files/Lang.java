package es.projectalpha.wc.pvp.files;

import es.projectalpha.wc.pvp.files.Files;

public class Lang {
	
	public void setupLang() {
		
		Files.clang.set("lang.noperm", "&4No tienes permisos para hacer eso.");
		Files.clang.set("lang.help", "&2Para usar este plugin, escribe /pvp help.");
		Files.clang.set("lang.pvp_on", "&4Has activado tu pvp.");
		Files.clang.set("lang.pvp_off", "&2 Has desactivado tu pvp.");
		Files.clang.set("lang.leave_in_pvp", "&7 se ha desconectado en combate!");
		Files.clang.set("lang.pvp_on_error", "&4Ya tienes el pvp activado.");
		Files.clang.set("lang.pvp_off_error", "&4Ya tienes el pvp desactivado.");
		Files.clang.set("lang.player_not_exist", "&4 Jugador no encontrado.");
		Files.clang.set("lang.prefix", "&6[&4PvPM&6]&r");
	}
}
