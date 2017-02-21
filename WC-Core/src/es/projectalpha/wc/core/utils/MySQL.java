package es.projectalpha.wc.core.utils;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import es.projectalpha.wc.core.WCCore;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.UUID;

public class MySQL {

    protected Connection connection;

    private final String user, database, password, port, hostname;

    public MySQL(String hostname, String port, String database, String username, String password) {
        this.hostname = hostname;
        this.port = port;
        this.database = database;
        this.user = username;
        this.password = password;
    }

    public boolean checkConnection() throws SQLException {
        return connection != null && !connection.isClosed();
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean closeConnection() throws SQLException {
        if (connection == null) {
            return false;
        }
        connection.close();
        return true;
    }

    public Connection openConnection() throws SQLException, ClassNotFoundException {
        if (checkConnection()) {
            return connection;
        }

        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://"
                + this.hostname + ":" + this.port + "/" + this.database + "?autoReconnect=true", this.user, this.password);
        return connection;
    }

    // -----------------
    public void setupTable(Player p) {
        WCCore.getInstance().getServer().getScheduler().runTaskAsynchronously(WCCore.getInstance(), () -> {
            try {
                PreparedStatement statement = openConnection().prepareStatement("SELECT `id` FROM `wc_datos` WHERE `uuid` = ?");
                statement.setString(1, p.getUniqueId().toString());
                ResultSet rs = statement.executeQuery();
                if (!rs.next()) { //No hay filas encontradas, insertar nuevos datos

                    PreparedStatement inserDatos = openConnection().prepareStatement(
                            "INSERT INTO `wc_datos` (`uuid`, `name`, `grupo`) VALUES (?, ?, ?)");
                    inserDatos.setString(1, p.getUniqueId().toString());
                    inserDatos.setString(2, p.getName());
                    inserDatos.setInt(3, 0);
                    inserDatos.executeUpdate();

                }
            } catch (SQLException | ClassNotFoundException /*| IOException*/ ex) {
                ex.printStackTrace();
            }
        });
    }

    public void saveUser(WCUser u) {
        WCCore.getInstance().getServer().getScheduler().runTaskAsynchronously(WCCore.getInstance(), () -> {
            WCUser.UserData data = u.getUserData();
            try {

                PreparedStatement statementDatos = openConnection().prepareStatement("UPDATE `wc_datos` SET `grupo`=? WHERE `uuid`=?");
                statementDatos.setInt(1, data.getGrupo() != null ? data.getGrupo().getRank() : 0);
                statementDatos.setString(7, u.getUuid().toString());
                statementDatos.executeUpdate();

            } catch (Exception ex) {
                WCCore.getInstance().debugLog("Ha ocurrido un error guardando los datos de " + u.getName());
                ex.printStackTrace();
            }
        });
    }

    public WCUser.UserData loadUserData(UUID id) {
        WCUser.UserData data = new WCUser.UserData();
        try {

            //Datos
            PreparedStatement statementDatos = openConnection().prepareStatement("SELECT `grupo` FROM `wc_datos` WHERE `uuid` = ?");
            statementDatos.setString(1, id.toString());
            ResultSet rsDatos = statementDatos.executeQuery();

            if (rsDatos.next()) {
                int rank = rsDatos.getInt("grupo");
                data.setGrupo(WCCmd.Grupo.values()[rank] == null ? WCCmd.Grupo.Craftero : WCCmd.Grupo.values()[rank]);
            }
        } catch (CommunicationsException ex) {
            //Si (timeout) cerrar, abrir y volver a intentar
            WCCore.getInstance().debugLog(ex.toString());
            try {
                closeConnection();
                openConnection();
                return loadUserData(id);
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        } catch (Exception ex) {
            WCCore.getInstance().debugLog("Ha ocurrido un error cargando los datos de " + id);
            ex.printStackTrace();
        }
        return data;
    }
}
