package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.annotation.Optional;
import dev.santih.wixnercore.config.Messages;
import dev.santih.wixnercore.profile.IProfile;
import dev.santih.wixnercore.profile.Profile;
import dev.santih.wixnercore.profile.ProfileManager;
import dev.santih.strike.util.Common;
import org.bukkit.entity.Player;

@CommandAlias("home")
public class HomeCommand extends BaseCommand {

    @Dependency
    private ProfileManager profileManager;

    @Default
    public void onHome(Player player, @Optional String homeName) {
        IProfile profile = profileManager.getProfile(player.getUniqueId());
        if (profile == null) {
            Common.tell(player, Messages.PROFILE_NOT_LOADED);
            return;
        }

        if (homeName == null) {
            profile.getFirstHome().ifPresentOrElse(
                    home -> {
                        home.teleport(player);
                        Common.tell(player, Messages.HOME_TELEPORTED.replace("%home_name%", home.getName()));
                    },
                    () -> Common.tell(player, Messages.HOME_NO_HOMES)
            );
            return;
        }

        profile.getHomeByName(homeName).ifPresentOrElse(
                home -> {
                    home.teleport(player);
                    Common.tell(player, Messages.HOME_TELEPORTED.replace("%home_name%", home.getName()));
                },
                () -> Common.tell(player, Messages.HOME_NOT_FOUND)
        );
    }
}
