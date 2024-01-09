package dev.santih.wixnercore.profile.model;

import dev.morphia.annotations.Entity;
import org.bukkit.configuration.ConfigurationSection;
@Entity
public class EconomyAccount {

    private double balance;

    public EconomyAccount() {
        this.balance = 0;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void save(final ConfigurationSection section) {
        section.set("balance", balance);
    }

    public void load(final ConfigurationSection section) {
        setBalance(section.getDouble("balance"));
    }
    public void addBalance(double balance) {
        this.balance = balance + balance;
    }

    public void removeBalance(double balance) {
        this.balance = balance - balance;
    }
}
