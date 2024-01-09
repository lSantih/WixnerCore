package dev.santih.wixnercore.config;

import dev.santih.strike.module.config.MessageDocument;

public class Messages extends MessageDocument {

	public Messages() {
		setFileName("messages_en.yml");
	}

	public static String BROADCAST_PREFIX = "&e[Broadcast] &f";
	public static String INVALID_WORLD = "&cInvalid world specified!";
	public static String MUST_SPECIFY_PLAYER = "&cYou must specify a player when using this command from the console!";
	public static String INVALID_PLAYER = "&cThe specified player is not online!";
	public static String INVENTORY_CLEAR = "&6Inventory of %player_name% cleared.";
	public static String INVENTORY_CLEAR_TARGET = "&6Your inventory has been cleared by %player_name%";

	public static String PROFILE_NOT_LOADED = "&cError: Your profile is not loaded. Please try again later.";
	public static String HOME_DELETED = "&aHome %home_name% deleted.";

	public static String HOME_NOT_FOUND = "&cHome not found.";

	public static String DISPOSAL_OPEN = "&6Opened a disposal inventory.";

	public static String BALANCE_ADD = "&aAdded %amount% to %player_name% account.";
	public static String BALANCE_REMOVE = "&aRemoved %amount% from %player_name% account.";
	public static String BALANCE_SET = "&aSet %player_name% account to %amount%.";

	public static String BALANCE_VIEW = "&6%player_name%'s Balance: %amount%";

	public static String MUST_HOLD_ITEM = "&c%player_name% must hold an item in their hand.";

	public static String INVALID_ENCHANTMENT = "&cInvalid enchantment name!";

	public static String ENCHANT = "&6Enchanted %material% of %player_name% with %enchantment% %level%";
	public static String ENCHANT_TARGET = "&6Your %material% has been enchanted with %enchantment% %level%";
	public static String ENDERCHEST_OPEN = "&6Opened your enderchest.";
	public static String ENDERCHEST_OPEN_OTHER = "&6Opened enderchest of %player_name%.";

	public static String EXP_ADD_SUCCESS = "&6Added %amount% %exp_type% to %player_name%!";
	public static String EXP_REMOVE_SUCCESS = "&6Removed %amount% %exp_type% from %player_name%!";
	public static String EXP_SET_SUCCESS = "&6Set %player_name%'s %exp_type% to %amount%!";
	public static String EXP_RESET_SUCCESS = "&6Reset %player_name%'s experience points to 0!";
	public static String EXP_VIEW = "&6%player_name% has %exp_amount% experience points and is level %level%!";
	public static String EXP_POINTS = "experience points";
	public static String EXP_LEVELS = "levels";

	public static String FEED_SELF = "&aYou've been fed!";
	public static String FEED_OTHER_PERMISSION = "&cYou don't have permission to feed other players!";
	public static String FEED_SUCCESS = "&aFed %target_name%!";
	public static String FEED_RECEIVED = "&aYou've been fed by %player_name%";

	public static String FLY_SELF_ENABLED = "&aFly mode enabled";
	public static String FLY_SELF_DISABLED = "&cFly mode disabled";
	public static String FLY_OTHER_PERMISSION = "&cYou don't have permission to toggle fly mode for other players!";
	public static String FLY_SUCCESS = "&aFly mode %action% for %target_name%";
	public static String FLY_RECEIVED = "&aFly mode %action% by %player_name%";
	public static String FORCE_CHAT_SUCCESS = "&aForced player %target_name% to write '%message%'";

	public static String FORCE_CMD_SUCCESS = "&aSuccessfully made %target_name% execute '%command%'";

	public static String GAMEMODE_PERMISSION = "&cYou don't have permission to use this command!";
	public static String GAMEMODE_NO_PLAYER = "&cYou must specify a player when using this command from the console!";
	public static String GAMEMODE_INVALID = "&cInvalid game mode specified!";
	public static String GAMEMODE_OTHER_PERMISSION = "&cYou don't have permission to change another player's game mode!";
	public static String GAMEMODE_SUCCESS = "&6Changed game mode of %target_name% to %game_mode%!";
	public static String GAMEMODE_RECEIVED = "&6Your game mode has been changed to %game_mode%!";

	public static String GIVE_INVALID_ITEM = "&cInvalid item specified!";
	public static String GIVE_SUCCESS = "&6Gave &a%amount% &6of &a%item% &6to &a%target_name%.";
	public static String GIVE_INVENTORY_FULL = "&cCould not give items to &a%target_name%. Their inventory is full.";

	public static String GOD_MODE_ENABLED = "&aGod mode enabled";
	public static String GOD_MODE_DISABLED = "&cGod mode disabled";
	public static String GOD_MODE_ENABLED_OTHER = "&aGod mode enabled for %player_name%";
	public static String GOD_MODE_DISABLED_OTHER = "&cGod mode disabled for %player_name%";
	public static String GOD_MODE_ENABLED_BY = "&aGod mode enabled by %player_name%";
	public static String GOD_MODE_DISABLED_BY = "&cGod mode disabled by %player_name%";

	public static String HAT_SUCCESS = "&6Enjoy your new hat!";

	public static String HEAL_SUCCESS_SELF = "&aHealed.";
	public static String HEAL_SUCCESS_OTHER = "&aHealed %player_name%!";
	public static String HEAL_SUCCESS_TARGET = "&aYou've been healed by %healer_name%!";

	public static String HOME_TELEPORTED = "&aTeleported to home %home_name%!";
	public static String HOME_NO_HOMES = "&cYou don't have any homes set!";

	public static String HOME_LIST_NO_HOMES = "&cYou don't have any homes set!";
	public static String HOME_LIST_HEADER = "&aYour homes:";
	public static String HOME_LIST_ITEM = "\n&f- %home_name%";

	public static String INVSEE_OPENED = "&aInventory of %player_name% opened";

	public static String KICKALL_REASON = "&aKick all executed by %player_name%";
	public static String KICKALL_SUCCESS = "&aSuccessfully kicked %kicked_players% players.";

	public static String KILL_KILLED_BY = "&6You have been killed by %killer_name%.";
	public static String KILL_KILLED = "&6Killed %target_name%.";
	public static String KILL_KILLED_SELF = "&6Killed yourself.";

	public static String LORE_NO_LORE = "&cThe item does not have any lore line.";
	public static String LORE_LINE_ADDED = "&aLine added: %lore% at position %position%";
	public static String LORE_INVALID_LINE_RANGE = "&cThe line is not valid, try a range between 1 and %max%";
	public static String LORE_LINE_REMOVED = "&aLine removed at position %position%";

	public static String LORE_LINE_SET = "&aSet lore line %line% to: %text%.";
	public static String  LORE_RESET = "&aLore reset.";

	public static String MSG_RECIPIENT_MESSAGES_DISABLED = "&cError: %player% has private messages disabled.";

	public static String NEAR_NO_PLAYERS_FOUND = "&cNo players found within %radius% blocks.";
	public static String NEAR_HEADER = "&6Nearby players within %radius% blocks:";
	public static String NEAR_PLAYER_FORMAT = "&e%player%&7 (%distance% blocks)";

	public static String PING_YOUR_PING = "&aYour ping is %ping%ms";
	public static String PING_TARGET_PING = "&a%player_name% ping is %ping%ms";
	public static String PING_SPECIFY_PLAYER = "&cYou must specify a player name!";

	public static String PTIME_INVALID_WORLD = "&cInvalid world specified!";
	public static String PTIME_SET_DAY = "&6Set time to &eday&6 in world %world%.";
	public static String PTIME_SET_NIGHT = "&6Set time to &8night&6 in world %world%.";
	public static String PTIME_SET_AFTERNOON = "&6Set time to &eafternoon&6 in world %world%.";
	public static String PTIME_SET_MIDNIGHT = "&6Set time to &dmidnight&6 in world %world%.";
	public static String PTIME_SET_CUSTOM = "&6Set time to &e%ticks% ticks&6 in world %world%.";
	public static String PTIME_RESET = "&6Set time to &7reset &6in world %world%.";
	public static String PTIME_INVALID_TIME = "&cInvalid time specified!";

	public static String PROFILE_ALREADY_LOADED = "&cError: Profile for player %player% is already loaded.";
	public static String PROFILE_LOAD_SUCCESS = "&aSuccessfully loaded profile for player %player%.";
	public static String PROFILE_UNLOAD_SUCCESS = "&aSuccessfully unloaded profile for player %player%.";
	public static String PROFILE_LOADED_STATUS = "&aProfile for player %player% is loaded.";
	public static String PROFILE_NOT_LOADED_STATUS = "&cProfile for player %player% is not loaded.";

	public static String RENAME_SUCCESS = "&aItem renamed to %name%";

	public static String REPAIR_ALL_SUCCESS_SELF = "&aRepaired %count% items in your inventory.";
	public static String REPAIR_ALL_SUCCESS_OTHER = "&aRepaired %count% items in %target%'s inventory!";
	public static String REPAIR_ALL_SUCCESS_TARGET = "&a%count% items in your inventory have been repaired by %player%!";

	public static String REPAIR_SUCCESS_SELF = "&aItem successfully repaired";
	public static String REPAIR_SUCCESS_OTHER = "&aRepaired %target%'s item!";
	public static String REPAIR_SUCCESS_TARGET = "&aYour item has been repaired by %player%!";
	public static String REPAIR_FAILED = "&cItem cannot be repaired!";

	public static String NO_ONE_TO_REPLY = "&cError: You have no one to reply to.";
	public static String RECIPIENT_MESSAGES_DISABLED = "&cError: %recipient% has private messages disabled.";

	public static String RECIPIENT_NOT_ONLINE = "&cError: The last player who messaged you is not online.";

	public static String MUST_HOLD_LEATHER_ARMOR = "&cYou must be holding a leather armor item to use this command!";
	public static String INVALID_HEX_COLOR = "&cInvalid hex color!";
	public static String LEATHER_ARMOR_COLOR_SET = "&aLeather armor color set to %hexColor%!";

	public static String NO_HOME_SPECIFIED = "&cYou must specify a home.";

	public static String HOME_SET = "&aHome set with name %home_name%";

	public static String SIGN_UPDATED = "&aSign line %line_number% updated.";
	public static String INVALID_LINE = "&cInvalid line number. Please use a number between 1 and 4.";
	public static String NOT_LOOKING_AT_SIGN = "&cYou are not looking at a sign.";

	public static String SKULL_RECEIVED = "&aYou have been given the skull of %playerName%.";
	public static String SKULL_RECEIVED_SELF = "&aYou have been given your own skull.";

	public static String SPAWNER_INVALID_ENTITY_TYPE = "&cInvalid entity type!";
	public static String SPAWNER_NOT_LOOKING_AT_SPAWNER = "&c%s must be looking at a spawner to perform this action!";
	public static String SPAWNER_CHANGED_TYPE = "&6Changed spawner type to %type% for %player_name%!";
	public static String SPAWNER_GIVEN = "&6Gave a %type% spawner to %player_name%!";
	public static String SPAWNER_CHANGED_SPEED = "&6Changed spawner speed to %speed% seconds for %player_name%!";
	public static String SPAWNER_CHANGED_MAX_NEARBY_ENTITIES = "&6Changed spawner maximum nearby entities to %entities% for %player_name%!";
	public static String SPAWNER_CHANGED_REQUIRED_PLAYER_RANGE = "&6Changed spawner required player range to %range% blocks for %player_name%!";
	public static String SPAWNER_CHANGED_SPAWN_RANGE = "&6Changed spawner spawn range to %range% blocks for %player_name%!";
	public static String SPAWNER_CHANGED_MAX_DELAY = "&6Changed spawner maximum delay to %delay% ticks for %player_name%!";
	public static String SPAWNER_CHANGED_MIN_DELAY = "&6Changed spawner minimum delay to %delay% ticks for %player_name%!";
	public static String INVALID_NUMBER = "&cYou must enter a valid number.";

	public static String SPEED_INVALID_RANGE = "&cSpeed must be between 1 and 10";
	public static String SPEED_SET_FLY_SPEED = "&6Set fly speed of %player% to %speed%!";
	public static String SPEED_SET_WALK_SPEED = "&6Set walk speed of %player% to %speed%!";
	public static String SPEED_VIEW = "&6%player% has a walk speed of %walk_speed% and a fly speed of %fly_speed%!";
	public static String SPEED_CONSOLE_TARGET = "&cYou must specify a player when running this command from the console!";

	public static String TELEPORT_CANNOT_TELEPORT_TO_SELF = "&cYou cannot teleport to yourself!";
	public static String TELEPORT_SUCCESS = "&aTeleported to %target%.";

	public static String TIME_INVALID_WORLD = "&cInvalid world specified!";
	public static String TIME_SPECIFY_WORLD_CONSOLE = "&cYou must specify a world when using this command from the console!";
	public static String TIME_SET_DAY = "&6Set time to &eday&6 in world %world%.";
	public static String TIME_SET_NIGHT = "&6Set time to &8night&6 in world %world%.";
	public static String TIME_SET_AFTERNOON = "&6Set time to &eafternoon&6 in world %world%.";
	public static String TIME_SET_MIDNIGHT = "&6Set time to &5midnight&6 in world %world%.";
	public static String TIME_SET_TICKS = "&6Set time to &e%ticks% ticks&6 in world %world%.";
	public static String TIME_INVALID_TIME = "&cInvalid time specified!";

	public static String TOP_ALREADY_AT_HIGHEST_BLOCK = "&cYou are already at the highest block in your position!";
	public static String TOP_TELEPORTED_TO_HIGHEST_BLOCK = "&aTeleported to the highest block above your position!";

	public static String TPACCEPT_NO_PENDING_REQUESTS = "&cError: You have no pending teleport requests.";
	public static String TPACCEPT_REQUEST_ACCEPTED = "&aTeleport request accepted.";

	public static String TPA_TELEPORT_REQUEST_DISABLED = "&cError: %s has teleport requests disabled.";
	public static String TPA_ALREADY_SENT_REQUEST = "&cError: You have already sent a teleport request to %s.";
	public static String TPA_REQUEST_SENT = "&aTeleport request sent to %s.";
	public static String TPA_REQUEST_RECEIVED = "&a%s has requested to teleport to you. Type /tpaccept to accept or /tpdeny to deny.";

	public static String TPALL_NO_PLAYERS = "&cNo players to teleport!";
	public static String TPALL_TELEPORTED_TO_YOU = "&6Teleported %d players to you.";
	public static String TPALL_PLAYER_TELEPORTED = "&6Teleported to %s.";

	public static String TPDENY_NO_PENDING_REQUESTS = "&cError: You have no pending teleport requests.";
	public static String TPDENY_REQUEST_DENIED = "&cTeleport request denied.";

	public static String TPHERE_TELEPORTED_TARGET = "&6Teleported %s to your location.";
	public static String TPHERE_TELEPORTED_SELF = "&6You have been teleported to %s's location.";

	public static String TELEPORT_POS_SUCCESS = "&6Teleported to %x%, %y%, %z% in world %world%!";

	public static String TARGET_PROFILE_NOT_LOADED = "&cThe profile for the target player is not loaded.";
	public static String WEATHER_CLEAR = "&6Changed weather to sun in %world%.";
	public static String WEATHER_STORM = "&6Changed weather to storm in %world%.";
	public static String WEATHER_THUNDER = "&6Changed weather to thunder in %world%.";
	public static String CONSOLE_WORLD_REQUIRED = "&cYou must specify a world when running this command from the console!";

	public static String WORLD_TELEPORTED = "&aTeleported to world %world_name%";
}

