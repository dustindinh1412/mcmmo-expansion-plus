package com.dustin.expansion.mmoplaceholders;

import org.bukkit.Location;
import org.bukkit.permissions.Permissible;
import com.gmail.nossr50.util.Permissions;
import org.bukkit.entity.EntityType;
import com.gmail.nossr50.config.AdvancedConfig;
import com.gmail.nossr50.skills.fishing.Fishing;
import com.gmail.nossr50.util.skills.RankUtils;
import com.gmail.nossr50.datatypes.skills.SubSkillType;
import com.gmail.nossr50.util.random.RandomChanceUtil;
import com.gmail.nossr50.datatypes.skills.PrimarySkillType;
import com.gmail.nossr50.datatypes.treasure.Rarity;
import com.gmail.nossr50.config.treasure.FishingTreasureConfig;
import org.bukkit.OfflinePlayer;
import com.gmail.nossr50.util.player.UserManager;
import org.bukkit.entity.Player;
import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.skills.fishing.FishingManager;
import com.gmail.nossr50.commands.skills.FishingCommand;

public class FishingSkills extends FishingCommand
{
    private int lootTier;
    private FishingManager manager;
    private McMMOPlayer mmoPlayer;
    
    public FishingSkills(final Player p) {
        this.mmoPlayer = UserManager.getOfflinePlayer((OfflinePlayer)p);
        this.manager = this.mmoPlayer.getFishingManager();
        this.lootTier = this.mmoPlayer.getFishingManager().getLootTier();
    }
    
    public String getSkillInfo(final String skill) {
        switch (skill) {
            case "TH-Uncommon": {
                return new StringBuilder(String.valueOf(this.percent.format(FishingTreasureConfig.getInstance().getItemDropRate(this.lootTier, Rarity.UNCOMMON) / 100.0))).toString();
            }
            case "TH-Common": {
                return new StringBuilder(String.valueOf(this.percent.format(FishingTreasureConfig.getInstance().getItemDropRate(this.lootTier, Rarity.COMMON) / 100.0))).toString();
            }
            case "TH-Mythic": {
                return new StringBuilder(String.valueOf(this.percent.format(FishingTreasureConfig.getInstance().getItemDropRate(this.lootTier, Rarity.MYTHIC) / 100.0))).toString();
            }
            case "TH-Epic": {
                return new StringBuilder(String.valueOf(this.percent.format(FishingTreasureConfig.getInstance().getItemDropRate(this.lootTier, Rarity.EPIC) / 100.0))).toString();
            }
            case "TH-Rare": {
                return new StringBuilder(String.valueOf(this.percent.format(FishingTreasureConfig.getInstance().getItemDropRate(this.lootTier, Rarity.RARE) / 100.0))).toString();
            }
            case "SC": {
                return RandomChanceUtil.calculateAbilityDisplayValuesStatic(this.mmoPlayer.getPlayer(), PrimarySkillType.FISHING, this.manager.getShakeChance())[0];
            }
            case "FDR": {
                return new StringBuilder(String.valueOf(RankUtils.getRank(this.mmoPlayer.getPlayer(), SubSkillType.FISHING_FISHERMANS_DIET))).toString();
            }
            case "MAC": {
                double rawBiteChance = 1.0 / (this.mmoPlayer.getPlayer().getWorld().hasStorm() ? 300 : 500);
                Location location = this.manager.getHookLocation();
                if (location == null) {
                    location = this.mmoPlayer.getPlayer().getLocation();
                }
                if (Fishing.masterAnglerBiomes.contains(location.getBlock().getBiome())) {
                    rawBiteChance *= AdvancedConfig.getInstance().getMasterAnglerBiomeModifier();
                }
                if (this.mmoPlayer.getPlayer().isInsideVehicle() && this.mmoPlayer.getPlayer().getVehicle().getType() == EntityType.BOAT) {
                    rawBiteChance *= AdvancedConfig.getInstance().getMasterAnglerBoatModifier();
                }
                final double luckyModifier = Permissions.lucky((Permissible)this.mmoPlayer.getPlayer(), PrimarySkillType.FISHING) ? 1.333 : 1.0;
                return this.percent.format(rawBiteChance * 100.0 * luckyModifier);
            }
            case "MHC": {
                double totalEnchantChance = 0.0;
                Rarity[] values;
                for (int length = (values = Rarity.values()).length, i = 0; i < length; ++i) {
                    final Rarity rarity = values[i];
                    if (rarity != Rarity.MYTHIC) {
                        totalEnchantChance += FishingTreasureConfig.getInstance().getEnchantmentDropRate(this.lootTier, rarity);
                    }
                }
                if (totalEnchantChance >= 1.0) {
                    return this.percent.format(totalEnchantChance / 100.0);
                }
                return this.percent.format(0L);
            }
            case "TH-Legendary": {
                return new StringBuilder(String.valueOf(this.percent.format(FishingTreasureConfig.getInstance().getItemDropRate(this.lootTier, Rarity.LEGENDARY) / 100.0))).toString();
            }
            default:
                break;
        }
        return "Wrong placeholder";
    }
}
