package com.vivim.deepmine.player;

import com.vivim.deepmine.persistence.ProfileRepository;
import com.vivim.deepmine.persistence.StorageExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class PlayerProfileService {
    private final StorageExecutor executor;
    private final ProfileRepository repository;
    private final JavaPlugin plugin;
    private final ConcurrentMap<UUID, PlayerProfile> profiles;
    public PlayerProfileService(StorageExecutor exec, ProfileRepository repo, JavaPlugin plugin, ConcurrentMap<UUID, PlayerProfile> profiles) {
        this.executor = exec;
        this.repository = repo;
        this.plugin = plugin;
        this.profiles = profiles;
    }

    public CompletableFuture<Void> saveAsync(UUID uuid) {
        PlayerProfile profile = profiles.get(uuid);
        if (profile == null) return CompletableFuture.completedFuture(null);

        return executor.supplyAsync(() -> {
            try {
                repository.save(profile);
            } catch (Exception e) {
                plugin.getLogger().log(Level.SEVERE, "Ошибка сохранения профиля " + uuid, e);
            }
            return null;
        });
    }

    public CompletableFuture<Void> unloadAsync(UUID uuid) {
        PlayerProfile profile = profiles.get(uuid);
        if (profile == null) return CompletableFuture.completedFuture(null);

        return saveAsync(uuid).thenRun(() -> profiles.remove(uuid));
    }

    public void shutdown(long timeout, TimeUnit unit) {
        // собрать futures
        List<CompletableFuture<Void>> futures = profiles.keySet().stream()
                .map(this::saveAsync)
                .collect(Collectors.toList());

        // ждать завершения всех сохранений
        CompletableFuture<Void> all = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        try {
            all.get(timeout, unit);
        } catch (Exception e) {
            plugin.getLogger().log(Level.WARNING, "Не все сохранения завершились вовремя", e);
        }

        profiles.clear();

        try {
            executor.shutdownAndAwait(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public CompletableFuture<PlayerProfile> loadAsync(UUID uuid) {
        PlayerProfile existing = profiles.get(uuid);
        if (existing != null) {
            return CompletableFuture.completedFuture(existing);
        }

        return executor.supplyAsync(() -> {
            try {
                Optional<PlayerProfile> opt = repository.load(uuid);
                PlayerProfile profile = opt.orElseGet(() -> new PlayerProfile(uuid, 0, 0, 0));
                profiles.put(uuid, profile);
                return profile;
            } catch (Exception e) {
                plugin.getLogger().log(Level.SEVERE, "Ошибка загрузки профиля " + uuid, e);
                return new PlayerProfile(uuid, 0, 0, 0);
            }
        });
    }
}
