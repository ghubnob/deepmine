package com.vivim.deepmine.persistence;

import com.vivim.deepmine.player.PlayerProfile;

import java.util.Optional;
import java.util.UUID;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class MongoRepository implements ProfileRepository {
    private MongoClient client;
    private MongoCollection<Document> players;
    @Override
    public void save(PlayerProfile profile) {
        players.updateOne(eq("_id",profile.uuid.toString()),
                new Document("$set", new Document()
                        .append("depth", profile.getDepth())
                        .append("money", profile.getMoney())
                        .append("thick", profile.getCurrentThickness())),
                new UpdateOptions().upsert(true));
    }

    @Override
    public Optional<PlayerProfile> load(UUID uuid) {
        Document doc = players.find(eq("_id",uuid.toString())).first();
        if (doc == null) return Optional.empty();
        return Optional.of(new PlayerProfile(uuid,doc.getInteger("depth"),doc.getInteger("money"),doc.getInteger("thick")));
    }

    @Override
    public boolean playerJoined(UUID uuid) {
        return players.find(eq("_id", uuid.toString())).first() != null;
    }

    @Override
    public void init() {
        try {
            client = MongoClients.create("mongodb://localhost:27017");
            MongoDatabase db = client.getDatabase("testdb");
            players = db.getCollection("players");
        } catch (Exception e) { System.out.print("MONGO INIT EXCEPTION! "); e.printStackTrace(); }
    }

    @Override
    public void close() { client.close(); }
}