package com.vivim.deepmine.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vivim.deepmine.player.PlayerProfile;
import com.vivim.deepmine.player.PlayerProfileManager;

import java.io.*;
import java.util.Optional;
import java.util.UUID;

public class JsonProfileRepository implements ProfileRepository {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final File folder;

    public JsonProfileRepository(File dataFolder) {
        this.folder = new File(dataFolder, "players");
        if (!folder.exists()) folder.mkdirs();
    }

    @Override
    public Optional<PlayerProfile> load(UUID uuid) {
        File file = new File(folder, uuid.toString()+".json");
        if (!file.exists()) return Optional.empty();

        try(Reader reader = new FileReader(file)) {
            return Optional.of(gson.fromJson(reader,PlayerProfile.class));
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void save(PlayerProfile profile) {
        File file = new File(folder,profile.uuid.toString()+".json");

        try(Writer writer = new FileWriter(file)) {
            gson.toJson(profile,writer);
        } catch(IOException e) {e.printStackTrace();}
    }

    @Override
    public void init() {}

    @Override
    public void close() {}

    @Deprecated
    @Override
    public boolean isPlayerJoined(UUID uuid) {
        return false;
    }
}
