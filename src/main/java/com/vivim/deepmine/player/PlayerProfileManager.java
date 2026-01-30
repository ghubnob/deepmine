package com.vivim.deepmine.player;

import com.vivim.deepmine.persistence.ProfileRepository;
import com.vivim.deepmine.persistence.StorageExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class PlayerProfileManager {
    private final ConcurrentMap<UUID, PlayerProfile> profiles = new ConcurrentHashMap<>();
    private final PlayerProfileService service;
    private final JavaPlugin plugin;

    public PlayerProfileManager(ProfileRepository profRep, StorageExecutor executor, JavaPlugin plugin) {
        this.plugin = plugin;

        service = new PlayerProfileService(executor, profRep, plugin, profiles);
    }

    // PlayerJoin
    public PlayerProfile loadAsync(UUID uuid) {
        try {
            return service.loadAsync(uuid).get();
        } catch(InterruptedException | ExecutionException e) {
            plugin.getLogger().info("Ошибка загрузки данных: "+e.getMessage());
            return getOrCreateDefault(uuid);
        }
    }

    public PlayerProfile getIfLoaded(UUID uuid) {
        return profiles.get(uuid);
    }
    public PlayerProfile getOrCreateDefault(UUID uuid) {
        return profiles.computeIfAbsent(uuid, id -> new PlayerProfile(uuid,0,0, 0));
    }

    public CompletableFuture<Void> saveAsync(UUID uuid) {
        return service.saveAsync(uuid);
    }

    public CompletableFuture<Void> unloadAsync(UUID uuid) {
        return service.unloadAsync(uuid);
    }

    public void shutdown(long timeout, TimeUnit unit) {
        service.shutdown(timeout,unit);
    }
}