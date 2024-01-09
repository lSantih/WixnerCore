package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.WixnerCore;
import dev.santih.wixnercore.config.Messages;
import dev.santih.wixnercore.profile.IProfile;
import dev.santih.wixnercore.profile.Profile;
import dev.santih.wixnercore.profile.ProfileManager;
import dev.santih.strike.util.Common;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@CommandAlias("sethome")
public class SetHomeCommand extends BaseCommand {
    @Dependency
    private WixnerCore plugin;

    @Dependency
    private ProfileManager profileManager;


    @Default
    @Syntax("<home>")
    public void onSetHome(Player player, String homeName) {
        final IProfile profile = profileManager.getProfile(player.getUniqueId());
        if (profile == null) {
            Common.tell(player,Messages.PROFILE_NOT_LOADED);
            return;
        }

        final Location homeLocation = player.getLocation();

        if(profile.getHomeByName(homeName).isPresent()) {
            return;
        }
        profile.addHome(homeName, homeLocation);
        //profile.saveData();

        Common.tell(player, Messages.HOME_SET.replace("%home_name%", homeName));
    }
}