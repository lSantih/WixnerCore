    package dev.santih.wixnercore.utils;

    import dev.santih.strike.util.Common;
	import org.bukkit.command.CommandSender;
    import org.bukkit.entity.Player;

	public class MessageUtils {

        public static void sendCoreMessage(final Player player, final String message) {
            player.sendMessage(Common.colorize(message));
        }

        public static void sendCoreMessage(final CommandSender player, final String message) {
            player.sendMessage(Common.colorize(message));
        }

        public static void sendPrivateMessage(Player sender, Player recipient, String message) {
            String formattedMessage = Common.colorize("&7[&d" + sender.getName() + " &7-> &dme&7] " + message);
            recipient.sendMessage(formattedMessage);

            formattedMessage = Common.colorize("&7[&dme &7-> &d" + recipient.getName() + "&7] " + message);
            sender.sendMessage(formattedMessage);
        }

    }

