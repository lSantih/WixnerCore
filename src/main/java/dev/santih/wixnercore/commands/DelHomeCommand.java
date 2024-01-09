package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import dev.santih.wixnercore.WixnerCore;
import dev.santih.wixnercore.config.Messages;
import dev.santih.wixnercore.profile.IProfile;
import dev.santih.wixnercore.profile.Profile;
import dev.santih.wixnercore.profile.ProfileManager;
import dev.santih.strike.util.Common;
import org.bukkit.entity.Player;

import co.aikar.commands.annotation.*;

@CommandAlias("delhome")
public class DelHomeCommand extends BaseCommand {

    @Dependency
    private WixnerCore plugin;

    @Dependency
    private ProfileManager profileManager;

    @Default
    @CommandPermission("unitycore.delhome")
    @Syntax("<name>")
    @Description("Delete a home location.")
    public void onDelHome(Player player, String homeName) {
        IProfile profile = profileManager.getProfile(player.getUniqueId());
        if (profile == null) {
            Common.tell(player, Messages.PROFILE_NOT_LOADED);
            return;
        }

        profile.getHomeByName(homeName).ifPresentOrElse(
                home -> {


                    profile.removeHome(home);
                    //profile.saveData();
                    Common.tell(player, Messages.HOME_DELETED.replaceAll("%home_name%", home.getName()));
                },
                () -> Common.tell(player, Messages.HOME_NOT_FOUND)
        );
    }
}