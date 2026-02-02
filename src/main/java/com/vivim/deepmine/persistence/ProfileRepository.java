package com.vivim.deepmine.persistence;

import com.vivim.deepmine.player.PlayerProfile;
import com.vivim.deepmine.player.PlayerProfileManager;

import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public interface ProfileRepository {
    Optional<PlayerProfile> load(UUID uuid);
    void save(PlayerProfile profile);
    void init();
    void close();
    boolean exists(UUID uuid);
}
