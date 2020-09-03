package com.willfp.ecoenchants.enchantments.ecoenchants.normal;

import com.willfp.ecoenchants.enchantments.EcoEnchant;
import com.willfp.ecoenchants.enchantments.EcoEnchantBuilder;
import com.willfp.ecoenchants.enchantments.EcoEnchants;
import com.willfp.ecoenchants.enchantments.util.checks.EnchantChecks;
import com.willfp.ecoenchants.events.naturalexpgainevent.NaturalExpGainEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
public class Wisdom extends EcoEnchant {
    public Wisdom() {
        super(
                new EcoEnchantBuilder("wisdom", EnchantmentType.NORMAL, 5.0)
        );
    }

    // START OF LISTENERS

    @EventHandler
    public void onExpChange(NaturalExpGainEvent event) {
        Player player = event.getExpChangeEvent().getPlayer();

        if(event.getExpChangeEvent().getAmount() < 0) return;

        if (!EnchantChecks.mainhand(player, this)) return;

        int level = EnchantChecks.getMainhandLevel(player, this);

        event.getExpChangeEvent().setAmount((int) Math.ceil(event.getExpChangeEvent().getAmount() * (1 + (level * this.getConfig().getDouble(EcoEnchants.CONFIG_LOCATION + "bonus-per-point")))));
    }
}
