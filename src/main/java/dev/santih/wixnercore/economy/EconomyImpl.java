package dev.santih.wixnercore.economy;

import dev.santih.wixnercore.profile.IProfile;
import dev.santih.wixnercore.profile.Profile;
import dev.santih.wixnercore.profile.ProfileManager;
import dev.santih.wixnercore.profile.model.EconomyAccount;
import dev.santih.wixnercore.utils.NumberUtils;
import dev.santih.wixnercore.utils.UUIDFetcher;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class EconomyImpl implements Economy {
    private final ProfileManager profileManager;

    public EconomyImpl(final ProfileManager profileManager) {
        this.profileManager = profileManager;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return "UCore Economy";
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return -1;
    }

    @Override
    public String format(double v) {
        return NumberUtils.formatDouble(v);
    }

    @Override
    public String currencyNamePlural() {
        return "$";
    }

    @Override
    public String currencyNameSingular() {
        return "$";
    }

    @Override
    public boolean hasAccount(String playerName) {
        if (Bukkit.getPlayer(playerName) != null) {
            return profileManager.getProfile(Bukkit.getPlayer(playerName).getUniqueId()).getBankAccount() != null;
        }
        return false;
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        return true;
    }

    @Override
    public boolean hasAccount(String playerName, String worldName) {
        return hasAccount(playerName);
    }

    @Override
    public boolean hasAccount(OfflinePlayer player, String worldName) {
        return hasAccount(player);
    }

    @Override
    public double getBalance(String playerName) {
        //add support for offline players

        if (Bukkit.getPlayer(playerName) == null) {
            return 0;
        }

        if (profileManager.getProfile(Bukkit.getPlayer(playerName).getUniqueId()) == null) return 0;
        final IProfile profile = profileManager.getProfile(Bukkit.getPlayer(playerName).getUniqueId());

        if (profile.getBankAccount() == null) return 0;
        return profile.getBankAccount().getBalance();
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        return getBalance(offlinePlayer.getName());
    }

    @Override
    public double getBalance(String playerName, String world) {
        return getBalance(playerName);
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer, String world) {
        return getBalance(offlinePlayer);
    }

    @Override
    public boolean has(String playerName, double balance) {
        return getBalance(playerName) >= balance;
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, double v) {
        return has(offlinePlayer.getName(), v);
    }

    @Override
    public boolean has(String playerName, String worldName, double amount) {
        return has(Bukkit.getOfflinePlayer(playerName), amount);
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, String worldName, double amount) {
        return has(offlinePlayer.getName(), amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        if (playerName == null) {
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Player name cannot be null!");
        }
        if (amount < 0) {
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Cannot withdraw negative funds!");
        }

        if (Bukkit.getPlayer(playerName) == null) {
            final UUID playerUUID = UUIDFetcher.getUUID(playerName);
            //final FileConfiguration playerConfig = profileManager.getConfig(playerUUID);
            //int currentMoney = playerConfig.getInt("bankAccount.balance");
            //double newMoney = currentMoney - amount;

            //playerConfig.set("bankAccount.balance", newMoney);
            //try {
              //  playerConfig.save(profileManager.getFile(playerUUID).getFile());
            //} catch (IOException e) {
                //throw new RuntimeException(e);
            //}

            //return new EconomyResponse(amount, newMoney, EconomyResponse.ResponseType.SUCCESS, null);

        }

        final Player player = Bukkit.getPlayer(playerName);
        final UUID playerUUID = player.getUniqueId();

        if (profileManager.getProfile(playerUUID) == null) {
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Profile not loaded into our system - can not remove money.");
        }

        if (profileManager.getProfile(playerUUID).getBankAccount() == null) {
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "User does not have an account created.");
        }

        final EconomyAccount bankAccount = profileManager.getProfile(playerUUID).getBankAccount();

        //if(bankAccount.getBalance() < amount) {
        //    return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "User does not have enough money.");
        //}

        bankAccount.setBalance(bankAccount.getBalance() - amount);
        return new EconomyResponse(amount, bankAccount.getBalance() - amount, EconomyResponse.ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double amount) {
        return withdrawPlayer(offlinePlayer.getName(), amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        return withdrawPlayer(playerName, amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, double amount) {
        return withdrawPlayer(player, amount);
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, double amount) {
        if (playerName == null) {
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Player name can not be null.");
        }
        if (amount < 0) {
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Cannot deposit negative funds");
        }

        if (Bukkit.getPlayer(playerName) == null) {
            final UUID playerUUID = UUIDFetcher.getUUID(playerName);
            //final FileConfiguration playerConfig = profileManager.getConfig(playerUUID);
            //int currentMoney = playerConfig.getInt("bankAccount.balance");
            //double newMoney = currentMoney + amount;

            //playerConfig.set("bankAccount.balance", newMoney);

            //return new EconomyResponse(amount, newMoney, EconomyResponse.ResponseType.SUCCESS, null);
        }

        final Player player = Bukkit.getPlayer(playerName);
        final UUID playerUUID = player.getUniqueId();

        if (profileManager.getProfile(playerUUID) == null) {
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Profile not loaded into our system - can not remove money.");
        }

        if (profileManager.getProfile(playerUUID).getBankAccount() == null) {
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "User does not have an account created.");
        }

        final EconomyAccount bankAccount = profileManager.getProfile(playerUUID).getBankAccount();
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        return new EconomyResponse(amount, getBalance(playerName), EconomyResponse.ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double amount) {
        return depositPlayer(offlinePlayer.getName(), amount);
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        return depositPlayer(playerName, amount);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, String worldName, double amount) {
        return depositPlayer(player, amount);
    }

    @Override
    public boolean createPlayerAccount(String playerName) {
        if (this.hasAccount(playerName)) return false;
        if (Bukkit.getPlayer(playerName) == null) return false;

        final UUID playerUUID = Bukkit.getPlayer(playerName).getUniqueId();
        if (profileManager.getProfile(playerUUID) == null) return false;

        profileManager.getProfile(playerUUID).setBankAccount(new EconomyAccount());
        return true;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
        return createPlayerAccount(offlinePlayer.getName());
    }

    @Override
    public boolean createPlayerAccount(String playerName, String worldName) {
        return createPlayerAccount(playerName);
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
        return createPlayerAccount(player);
    }

    @Override
    public EconomyResponse createBank(String s, String s1) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "ShootMC Economy 2.0 does not support bank accounts!");
    }

    @Override
    public EconomyResponse createBank(String s, OfflinePlayer offlinePlayer) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "ShootMC Economy 2.0 does not support bank accounts!");
    }

    @Override
    public EconomyResponse deleteBank(String s) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "ShootMC Economy 2.0 does not support bank accounts!");
    }

    @Override
    public EconomyResponse bankBalance(String s) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "ShootMC Economy 2.0 does not support bank accounts!");
    }

    @Override
    public EconomyResponse bankHas(String s, double v) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "ShootMC Economy 2.0 does not support bank accounts!");
    }

    @Override
    public EconomyResponse bankWithdraw(String s, double v) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "ShootMC Economy 2.0 does not support bank accounts!");
    }

    @Override
    public EconomyResponse bankDeposit(String s, double v) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "ShootMC Economy 2.0 does not support bank accounts!");
    }

    @Override
    public EconomyResponse isBankOwner(String s, String s1) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "ShootMC Economy 2.0 does not support bank accounts!");
    }

    @Override
    public EconomyResponse isBankOwner(String s, OfflinePlayer offlinePlayer) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "ShootMC Economy 2.0 does not support bank accounts!");
    }

    @Override
    public EconomyResponse isBankMember(String s, String s1) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "ShootMC Economy 2.0 does not support bank accounts!");
    }

    @Override
    public EconomyResponse isBankMember(String s, OfflinePlayer offlinePlayer) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "ShootMC Economy 2.0 does not support bank accounts!");
    }

    @Override
    public List<String> getBanks() {
        return Collections.emptyList();
    }
}
