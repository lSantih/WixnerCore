package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Dependency;
import dev.santih.wixnercore.WixnerCore;
import dev.santih.wixnercore.config.Messages;
import dev.santih.wixnercore.profile.IProfile;
import dev.santih.wixnercore.profile.Profile;
import dev.santih.wixnercore.profile.ProfileManager;
import dev.santih.strike.util.Common;
import org.bukkit.entity.Player;

@CommandAlias("homelist")
public class HomeListCommand extends BaseCommand {

    @Dependency
    private WixnerCore plugin;

    @Dependency
    private ProfileManager profileManager;

    @Default
    public void onHomeList(Player player) {
        IProfile profile = profileManager.getProfile(player.getUniqueId());
        if (profile == null) {
            Common.tell(player, Messages.PROFILE_NOT_LOADED);
            return;
        }

        if (profile.getHomesCount() == 0) {
            Common.tell(player, Messages.HOME_LIST_NO_HOMES);
            return;
        }

        StringBuilder message = new StringBuilder(Messages.HOME_LIST_HEADER);
        for (String homeName : profile.getHomeNames()) {
            message.append(Messages.HOME_LIST_ITEM.replace("%home_name%", homeName));
        }
        Common.tell(player, message.toString());
    }
}
