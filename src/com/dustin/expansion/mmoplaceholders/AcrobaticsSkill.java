package com.dustin.expansion.mmoplaceholders;

import com.gmail.nossr50.util.random.RandomChanceUtil;
import com.gmail.nossr50.datatypes.skills.SubSkillType;
import com.gmail.nossr50.util.skills.SkillActivationType;
import org.bukkit.OfflinePlayer;
import com.gmail.nossr50.util.player.UserManager;
import org.bukkit.entity.Player;
import com.gmail.nossr50.datatypes.player.McMMOPlayer;

public class AcrobaticsSkill
{
    private McMMOPlayer mmoPlayer;
    
    public AcrobaticsSkill(final Player p) {
        this.mmoPlayer = UserManager.getOfflinePlayer((OfflinePlayer)p);
    }
    
    public String getSkillInfo(final String skill) {
        switch (skill) {
            case "DC": {
                return RandomChanceUtil.calculateAbilityDisplayValues(SkillActivationType.RANDOM_LINEAR_100_SCALE_WITH_CAP, this.mmoPlayer.getPlayer(), SubSkillType.ACROBATICS_DODGE)[0];
            }
            case "RC": {
                return RandomChanceUtil.calculateAbilityDisplayValues(SkillActivationType.RANDOM_LINEAR_100_SCALE_WITH_CAP, this.mmoPlayer.getPlayer(), SubSkillType.ACROBATICS_ROLL)[0];
            }
            default:
                break;
        }
        return "Wrong skill";
    }
}
