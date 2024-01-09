package dev.santih.wixnercore;

import co.aikar.commands.BukkitCommandCompletionContext;
import co.aikar.commands.CommandCompletions;
import co.aikar.commands.PaperCommandManager;
import dev.santih.wixnercore.commands.*;
import dev.santih.wixnercore.commands.staff.StaffModeCommand;
import dev.santih.wixnercore.config.Config;
import dev.santih.wixnercore.config.Messages;
import dev.santih.wixnercore.economy.EconomyImpl;
import dev.santih.wixnercore.features.staff.managers.ItemManager;
import dev.santih.wixnercore.features.staff.managers.ModeManager;
import dev.santih.wixnercore.features.staff.managers.VanishManager;
import dev.santih.wixnercore.listeners.PlayerListener;
import dev.santih.wixnercore.listeners.ProfileListener;
import dev.santih.wixnercore.listeners.SpawnerListener;
import dev.santih.wixnercore.managers.DatabaseManager;
import dev.santih.wixnercore.managers.EconomyManager;
import dev.santih.wixnercore.managers.TpaManager;
import dev.santih.wixnercore.profile.IProfile;
import dev.santih.wixnercore.profile.Profile;
import dev.santih.wixnercore.profile.ProfileManager;
import dev.santih.wixnercore.utils.SimpleLocation;
import dev.santih.strike.StrikePlugin;
import dev.santih.strike.module.config.ConfigDocument;
import dev.santih.strike.module.config.FilesModule;
import dev.santih.strike.module.config.MessageDocument;
import dev.santih.strike.module.conversation.ConversationModule;
import dev.santih.strike.module.gui.MenuModule;
import lombok.Getter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class WixnerCore extends StrikePlugin {

    private static final Map<String, Enchantment> ENCHANTMENTS = new HashMap<>();
    @Getter
    private final ProfileManager profileManager = new ProfileManager();
    private final TpaManager tpaManager = new TpaManager(profileManager);

    private final DatabaseManager databaseManager = new DatabaseManager();

    @Getter
    private final VanishManager vanishManager = new VanishManager(profileManager, this);

    private final ItemManager itemManager = new ItemManager(vanishManager);
    private final ModeManager modeManager = new ModeManager(itemManager, profileManager);

    private EconomyManager economyManager;

    private MessageDocument enMessages;
    private ConfigDocument config;

    private EconomyImpl economy;
    static {
        for (Enchantment enchantment : Enchantment.values()) {
            ENCHANTMENTS.put(enchantment.getKey().getKey().replace(' ', '_'), enchantment);
        }
    }

    public WixnerCore(){
        setInstance(this);

        setEnabledModules(
                Arrays.asList(
                        MenuModule.class,
                        ConversationModule.class,
                        FilesModule.class
                )
        );
        //Register Plugin Messages

        this.enMessages = new Messages();
        registerAutoMessager(enMessages);

        //Register Plugin Config

        this.config = new Config();
        registerAutoConfig(config);

    }

    @Override
    public void onPluginLoad() {
        PaperCommandManager manager = new PaperCommandManager(this);

        CommandCompletions<BukkitCommandCompletionContext> completions = manager.getCommandCompletions();

        // Register game mode completion with lowercase game mode names
        manager.getCommandCompletions().registerAsyncCompletion("gamemodes", c -> Arrays.stream(GameMode.values()).map(GameMode::name).map(String::toLowerCase).collect(Collectors.toList()));

        manager.getCommandCompletions().registerCompletion("enchantments", c -> ENCHANTMENTS.keySet());

        // Register entity types completion
        manager.getCommandCompletions().registerAsyncCompletion("entitytypes", c -> Arrays.stream(EntityType.values())
                .filter(EntityType::isSpawnable)
                .map(Enum::name)
                .collect(Collectors.toList()));

        manager.getCommandCompletions().registerAsyncCompletion("homes", c -> {
            final IProfile profile = profileManager.getProfile(c.getPlayer().getUniqueId());
            if(profile == null) {
                return Collections.emptyList();
            }

            return profile.getHomeNames();
        });
        manager.getCommandCompletions().registerAsyncCompletion("worlds", c -> Bukkit.getWorlds().stream().map(World::getName).collect(Collectors.toList()));
        manager.getCommandCompletions().registerCompletion("time", c -> Arrays.asList("day", "night","noon", "midnight"));
        manager.getCommandCompletions().registerCompletion("itemstack", c -> {
            return Arrays.stream(Material.values())
                    .filter(Material::isItem)
                    .map(Material::name)
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());
        });

        ServicesManager servicesManager = getServer().getServicesManager();

        this.economy = new EconomyImpl(profileManager);
        servicesManager.register(Economy.class,  economy, this, ServicePriority.Normal);

        economyManager = new EconomyManager();
        databaseManager.connect(
                Profile.class,
                SimpleLocation.class
        );

        manager.registerDependency(ProfileManager.class, profileManager);
        manager.registerDependency(EconomyManager.class, economyManager);
        manager.registerDependency(TpaManager.class, tpaManager);
        manager.registerDependency(Messages.class, enMessages);
        manager.registerDependency(Config.class, config);
        manager.registerDependency(ModeManager.class, modeManager);

        manager.registerCommand(new RenameCommand());
        manager.registerCommand(new WorldTpCommand());
        manager.registerCommand(new FeedCommand());
        manager.registerCommand(new HealCommand());
        manager.registerCommand(new FlyCommand());
        manager.registerCommand(new InvseeCommand());
        manager.registerCommand(new LoreCommand());
        manager.registerCommand(new GodCommand());
        manager.registerCommand(new ForceChatCommand());
        manager.registerCommand(new ForceCmdCommand());
        manager.registerCommand(new PingCommand());
        manager.registerCommand(new KickAllCommand());
        manager.registerCommand(new RepairCommand());
        manager.registerCommand(new RepairAllCommand());
        manager.registerCommand(new TopCommand());
        manager.registerCommand(new NearCommand());
        manager.registerCommand(new TpposCommand());
        manager.registerCommand(new GameModeCommand());
        manager.registerCommand(new ExpCommand());
        manager.registerCommand(new EnchantCommand());
        manager.registerCommand(new SpeedCommand());
        manager.registerCommand(new SpawnerCommand());
        manager.registerCommand(new SkullCommand());
        manager.registerCommand(new TimeCommand());
        manager.registerCommand(new WeatherCommand());
        manager.registerCommand(new ClearCommand());
        manager.registerCommand(new EnderChestCommand());
        manager.registerCommand(new DisposalCommand());
        manager.registerCommand(new BroadcastCommand());
        manager.registerCommand(new BroadcastWorldCommand());
        manager.registerCommand(new GCCommand());
        manager.registerCommand(new GiveCommand());
        manager.registerCommand(new KillCommand());
        manager.registerCommand(new TPAllCommand());
        manager.registerCommand(new HatCommand());
        manager.registerCommand(new ProfileCommand());
        manager.registerCommand(new TpaCommand());
        manager.registerCommand(new TpdenyCommand());
        manager.registerCommand(new TpacceptCommand());
        manager.registerCommand(new MsgCommand());
        manager.registerCommand(new ReplyCommand());
        manager.registerCommand(new SettingsCommand());
        manager.registerCommand(new WorkbenchCommand());
        manager.registerCommand(new HomeListCommand());
        manager.registerCommand(new SetHomeCommand());
        manager.registerCommand(new DelHomeCommand());
        manager.registerCommand(new HomeCommand());
        manager.registerCommand(new ChatFormatCommand());
        manager.registerCommand(new SetArmorHexCommand());
        manager.registerCommand(new CartographyCommand());
        manager.registerCommand(new GrindstoneCommand());
        manager.registerCommand(new LoomCommand());
        manager.registerCommand(new SmithingCommand());
        manager.registerCommand(new StonecutterCommand());
        manager.registerCommand(new AnvilCommand());
        manager.registerCommand(new ItemInfoCommand());
        manager.registerCommand(new TPHereCommand());
        manager.registerCommand(new SignLineCommand());
        manager.registerCommand(new PTimeCommand());
        manager.registerCommand(new PWeatherCommand());
        manager.registerCommand(new TeleportCommand());
        manager.registerCommand(new UnityCommand());

        //Staff Mode

        manager.registerCommand(new StaffModeCommand());

        //Load not loaded profiles (reload support)

        profileManager.cacheAllOnline();

        //Register economy

        manager.registerCommand(new EcoCommand());

        registerEvents();
    }


    @Override
    public void onPluginStop() {
        ServicesManager servicesManager = getServer().getServicesManager();

        if(economy != null) servicesManager.unregister(Economy.class, economy);
    }

    private void registerEvents() {
        Arrays.asList(
                        new PlayerListener(this),
                        new SpawnerListener(),
                        new ProfileListener(databaseManager, profileManager))
                .forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}
