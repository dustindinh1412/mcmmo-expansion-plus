package com.dustin.expansion.mmoplaceholders;

import com.gmail.nossr50.util.random.RandomChanceUtil;
import com.gmail.nossr50.datatypes.skills.SubSkillType;
import com.gmail.nossr50.util.skills.SkillActivationType;
import org.bukkit.OfflinePlayer;
import com.gmail.nossr50.util.player.UserManager;
import org.bukkit.entity.Player;
import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.skills.smelting.SmeltingManager;

public class SmeltingSkill
{
    private SmeltingManager manager;
    private McMMOPlayer mmoPlayer;
    
    public SmeltingSkill(final Player p) {
        this.mmoPlayer = UserManager.getOfflinePlayer((OfflinePlayer)p);
        this.manager = this.mmoPlayer.getSmeltingManager();
    }
    
    public String getSkillInfo(final String skill) {
        switch (skill) {
            case "FEM": {
                return new StringBuilder(String.valueOf(this.manager.getFuelEfficiencyMultiplier())).toString();
            }
            case "SSC": {
                return RandomChanceUtil.calculateAbilityDisplayValues(SkillActivationType.RANDOM_LINEAR_100_SCALE_WITH_CAP, this.mmoPlayer.getPlayer(), SubSkillType.SMELTING_SECOND_SMELT)[0];
            }
            case "VXM": {
                return new StringBuilder(String.valueOf(this.manager.getVanillaXpMultiplier())).toString();
            }
            default:
                break;
        }
        return "Wrong skill";
    }
}
