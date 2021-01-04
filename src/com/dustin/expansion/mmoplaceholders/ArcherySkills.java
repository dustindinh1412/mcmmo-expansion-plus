package com.dustin.expansion.mmoplaceholders;

import com.gmail.nossr50.skills.archery.Archery;
import com.gmail.nossr50.util.skills.CombatUtils;
import com.gmail.nossr50.util.random.RandomChanceUtil;
import com.gmail.nossr50.datatypes.skills.SubSkillType;
import com.gmail.nossr50.util.skills.SkillActivationType;
import org.bukkit.OfflinePlayer;
import com.gmail.nossr50.util.player.UserManager;
import org.bukkit.entity.Player;
import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.commands.skills.ArcheryCommand;

public class ArcherySkills extends ArcheryCommand
{
    private McMMOPlayer mmoPlayer;
    
    public ArcherySkills(final Player p) {
        this.mmoPlayer = UserManager.getOfflinePlayer((OfflinePlayer)p);
    }
    
    public String getSkillInfo(final String skill) {
        switch (skill) {
            case "DC": {
                return RandomChanceUtil.calculateAbilityDisplayValues(SkillActivationType.RANDOM_LINEAR_100_SCALE_WITH_CAP, this.mmoPlayer.getPlayer(), SubSkillType.ARCHERY_DAZE)[0];
            }
            case "ARC": {
                return RandomChanceUtil.calculateAbilityDisplayValues(SkillActivationType.RANDOM_LINEAR_100_SCALE_WITH_CAP, this.mmoPlayer.getPlayer(), SubSkillType.ARCHERY_ARROW_RETRIEVAL)[0];
            }
            case "LBMD": {
                return new StringBuilder(String.valueOf(CombatUtils.getLimitBreakDamageAgainstQuality(this.mmoPlayer.getPlayer(), SubSkillType.ARCHERY_ARCHERY_LIMIT_BREAK, 1000))).toString();
            }
            case "SSBD": {
                return this.percent.format(Archery.getDamageBonusPercent(this.mmoPlayer.getPlayer()));
            }
            default:
                break;
        }
        return "Wrong skill";
    }
}
