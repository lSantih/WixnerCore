package dev.santih.wixnercore.config;

import dev.santih.strike.module.config.ConfigDocument;
import dev.santih.strike.module.config.annotation.Path;

public class Config extends ConfigDocument {

	public Config() {
		setFileName("config.yml");
	}

	@Path("database.mongo.hostname")
	public static String MONGO_HOSTNAME = "cluster0.jmuwdke.mongodb.net";

	@Path("database.mongo.port")
	public static int MONG0_PORT = 27017;

	@Path("database.mongo.username")
	public static String MONGO_USERNAME = "wixner";

	@Path("database.mongo.password")
	public static String MONGO_PASSWORD = "2yqTLdh0PE8kgRhH";

	@Path("database.mongo.database")
	public static String MONGO_DATABASE = "wixner";


}
