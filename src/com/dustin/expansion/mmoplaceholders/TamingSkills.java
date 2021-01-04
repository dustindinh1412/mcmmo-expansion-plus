package com.dustin.expansion.mmoplaceholders;

import com.gmail.nossr50.skills.taming.Taming;
import com.gmail.nossr50.util.random.RandomChanceUtil;
import com.gmail.nossr50.datatypes.skills.SubSkillType;
import com.gmail.nossr50.util.skills.SkillActivationType;
import org.bukkit.OfflinePlayer;
import com.gmail.nossr50.util.player.UserManager;
import org.bukkit.entity.Player;
import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.commands.skills.TamingCommand;

public class TamingSkills extends TamingCommand
{
    private McMMOPlayer mmoPlayer;
    
    public TamingSkills(final Player p) {
        this.mmoPlayer = UserManager.getOfflinePlayer((OfflinePlayer)p);
    }
    
    public String getSkillInfo(final String skill) {
        switch (skill) {
            case "GC": {
                return RandomChanceUtil.calculateAbilityDisplayValues(SkillActivationType.RANDOM_LINEAR_100_SCALE_WITH_CAP, this.mmoPlayer.getPlayer(), SubSkillType.TAMING_GORE)[0];
            }
            case "PC": {
                return RandomChanceUtil.calculateAbilityDisplayValues(SkillActivationType.RANDOM_LINEAR_100_SCALE_WITH_CAP, this.mmoPlayer.getPlayer(), SubSkillType.TAMING_PUMMEL)[0];
            }
            case "SC": {
                return new StringBuilder(String.valueOf(Taming.sharpenedClawsBonusDamage)).toString();
            }
            case "SP": {
                return new StringBuilder(String.valueOf(Taming.shockProofModifier)).toString();
            }
            case "TF": {
                return new StringBuilder(String.valueOf(Taming.thickFurModifier)).toString();
            }
            case "FFS": {
                return this.percent.format(Taming.fastFoodServiceActivationChance / 100.0);
            }
            default:
                break;
        }
        return "Wrong skill";
    }
}
