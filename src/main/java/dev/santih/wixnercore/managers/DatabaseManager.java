package dev.santih.wixnercore.managers;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.mapping.DateStorage;
import dev.morphia.mapping.MapperOptions;
import dev.santih.wixnercore.config.Config;
import dev.santih.wixnercore.database.ProfileDAO;
import dev.santih.wixnercore.profile.IProfile;
import dev.santih.wixnercore.profile.Profile;
import dev.santih.wixnercore.profile.StaffProfile;
import org.bson.UuidRepresentation;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Optional;

public class DatabaseManager {

	private MongoClient client;

	private ProfileDAO profileDAO;

	private Datastore datastore;
	public void connect(final Class... entities) {
		final String mongoData = "mongodb://" +
				Config.MONGO_USERNAME + ":" +
				Config.MONGO_PASSWORD + "@" +
				Config.MONGO_HOSTNAME + ":" +
				Config.MONG0_PORT;

		Bukkit.getConsoleSender().sendMessage(mongoData);

		final ConnectionString connectionString = new ConnectionString(mongoData);

		MongoClientSettings settings = MongoClientSettings.builder()
				.uuidRepresentation(UuidRepresentation.STANDARD)
				.applyConnectionString(connectionString)
				.serverApi(ServerApi.builder().version(ServerApiVersion.V1).build())
				.build();

		client = MongoClients.create(settings);

		datastore = Morphia.createDatastore(client, "wixnercore",
				MapperOptions.builder()
						.discriminatorKey("")
						.dateStorage(DateStorage.SYSTEM_DEFAULT)
						.build());

		datastore.ensureIndexes();
		datastore.getMapper().map(entities);

		this.profileDAO = new ProfileDAO(datastore);

		Bukkit.getConsoleSender().sendMessage("Database loaded.");
	}

	public IProfile getProfileOrInput(final Player player) {
		Bukkit.getLogger().info("Attempting to retrieve or create profile for player: " + player.getName());

		IProfile playerProfile = profileDAO.get(player.getUniqueId());
		if(playerProfile == null) {
			if (player.hasPermission("wixner.staff")) {
				playerProfile = new StaffProfile();
			}else {
				playerProfile = new Profile();
			}
			player.sendMessage(playerProfile.toString());
			Bukkit.getLogger().info("New profile created for player: " + player.getName());

			saveProfile(playerProfile);
		}

		return playerProfile;
	}

	public Optional<IProfile> getProfile(final Player player) {
		return Optional.of(profileDAO.get(player.getUniqueId()));
	}

	public void saveProfile(final IProfile profile) {
		profileDAO.save(profile);
	}

}
