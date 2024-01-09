package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.WixnerCore;
import dev.santih.wixnercore.config.Messages;
import dev.santih.wixnercore.managers.EconomyManager;
import dev.santih.wixnercore.utils.MessageUtils;
import dev.santih.strike.util.Common;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.OfflinePlayer;

@CommandAlias("eco")
@Description("Economy command")
public class EcoCommand extends BaseCommand {

    @Dependency
    private EconomyManager economyManager;

    @Dependency
    private WixnerCore wixnerCore;

    private void executeEconomyAction(
            Player sender, OfflinePlayer target, double amount, EconomyAction action) {
        final Economy economy = economyManager.getEconomy();
        if (economy == null) {
            MessageUtils.sendCoreMessage(sender, "&cEconomy not found!");
            return;
        }

        switch (action) {
            case ADD:
                //TEMP CODE
                wixnerCore.getProfileManager().getProfile(sender.getUniqueId()).setBalance((int) amount);
                economy.depositPlayer(target, amount);
                Common.tell(sender, Messages.BALANCE_ADD.replaceAll("%player_name%", target.getName()).replaceAll("%amount%", economy.format(amount)));
                break;
            case REMOVE:
                economy.withdrawPlayer(target, amount);
                Common.tell(sender, Messages.BALANCE_REMOVE.replaceAll("%player_name%", target.getName()).replaceAll("%amount%", economy.format(amount)));
                break;
            case SET:
                economy.withdrawPlayer(target, economy.getBalance(target));
                economy.depositPlayer(target, amount);

                Common.tell(sender, Messages.BALANCE_SET.replaceAll("%player_name%", target.getName()).replaceAll("%amount%", economy.format(amount)));
                break;
            case VIEW:
                //TEMP CODE
                final int balance = wixnerCore.getProfileManager().getProfile(sender.getUniqueId()).getBalance();
                //final double balance = economy.getBalance(target);
                Common.tell(sender, Messages.BALANCE_VIEW.replaceAll("%player_name%", target.getName()).replaceAll("%amount%", economy.format(balance)));
                break;
        }
    }

    @Subcommand("add")
    @CommandCompletion("@players @range(0,1000000)") // Assuming a reasonable range for the amount
    @Syntax("<target> <amount>")
    public void onAdd(Player sender, OfflinePlayer target, double amount) {
        executeEconomyAction(sender, target, amount, EconomyAction.ADD);
    }

    @Subcommand("remove")
    @CommandCompletion("@players @range(0,1000000)")
    @Syntax("<target> <amount>")
    public void onRemove(Player sender, OfflinePlayer target, double amount) {
        executeEconomyAction(sender, target, amount, EconomyAction.REMOVE);
    }

    @Subcommand("set")
    @CommandCompletion("@players @range(0,1000000)")
    @Syntax("<target> <amount>")
    public void onSet(Player sender, OfflinePlayer target, double amount) {
        executeEconomyAction(sender, target, amount, EconomyAction.SET);
    }

    @Subcommand("view")
    @CommandCompletion("@players")
    @Syntax("<target>")
    public void onView(Player sender, OfflinePlayer target) {
        executeEconomyAction(sender, target, 0, EconomyAction.VIEW);
    }

    private enum EconomyAction {
        ADD,
        REMOVE,
        SET,
        VIEW
    }
}
