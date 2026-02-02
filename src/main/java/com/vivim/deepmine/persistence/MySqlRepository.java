package com.vivim.deepmine.persistence;

import com.vivim.deepmine.player.PlayerProfile;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.*;
import java.util.Optional;
import java.util.UUID;

public class MySqlRepository implements ProfileRepository {
    private HikariDataSource dataSource;
    private final JavaPlugin plugin;

    public MySqlRepository(JavaPlugin pl) {
        this.plugin = pl;
    }

    @Override
    public void init() {
        this.dataSource = hikariConfigGetSource();
        String sql = "CREATE TABLE IF NOT EXISTS players (" +
                "uuid CHAR(36) PRIMARY KEY, " +
                "depth INTEGER DEFAULT 0, " +
                "money INTEGER DEFAULT 0, " +
                "thick INTEGER DEFAULT 0, " +
                "last_seen TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci";
        try (Connection conn = dataSource.getConnection();
             Statement st = conn.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) { System.out.print(ChatColor.RED + "[SQL] INIT TABLE FAIL! Error: " + e.getErrorCode());
            throw new RuntimeException(e); }
    }

    private HikariDataSource hikariConfigGetSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/minecraft_db");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setUsername(plugin.getConfig().getString("database.user"));
        config.setPassword(plugin.getConfig().getString("database.password"));

        config.setMaximumPoolSize(10);
        config.setConnectionTimeout(3000);
        config.setMinimumIdle(5);
        config.setIdleTimeout(60000);
        config.setMaxLifetime(1800000);

        return new HikariDataSource(config);
    }

    @Override
    public void save(PlayerProfile profile) {
        String sql = "INSERT INTO players (uuid, depth, money, thick, last_seen) VALUES (?, ?, ?, ?, NOW()) " +
                "ON DUPLICATE KEY UPDATE " +
                "depth = VALUES(depth), " +
                "money = VALUES(money), " +
                "thick = VALUES(thick), " +
                "last_seen = NOW()";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, profile.uuid.toString());
            stmt.setInt(2, profile.getDepth());
            stmt.setInt(3, profile.getMoney());
            stmt.setInt(4, profile.getCurrentThickness());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.print(ChatColor.RED + "[SQL] SAVE PLAYER DATA FAIL! Error: " + e.getErrorCode());
        }
    }

    @Override
    public Optional<PlayerProfile> load(UUID uuid) {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT depth, money, thick FROM players WHERE uuid = ?")) {

            stmt.setString(1, uuid.toString());

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    int depth = rs.getInt("depth");
                    int money = rs.getInt("money");
                    int thick = rs.getInt("thick");

                    return Optional.of(new PlayerProfile(uuid, depth, money, thick));
                } else {
                    return Optional.empty();
                }
            }

        } catch (SQLException e) {
            System.out.print(ChatColor.RED + "[SQL] LOAD PLAYER DATA FAIL! Error: " + e.getErrorCode());
            return Optional.empty();
        }
    }

    @Override
    public void close() {
        dataSource.close();
    }

    @Override
    public boolean playerJoined(UUID uuid) {
        try (Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT uuid FROM players WHERE uuid = ? LIMIT 1")) {

            stmt.setString(1, uuid.toString());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String uuid1 = rs.getString("uuid");
                    return (!uuid1.isEmpty());
                }
            }

        } catch (SQLException e) {
            System.out.print(ChatColor.RED + "[SQL] CHECK PLAYER JOINED FAIL! Error: " + e.getErrorCode());
            return false;
        }
        return false;
    }
}