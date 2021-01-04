package com.dustin.expansion.mmoplaceholders;

import com.gmail.nossr50.util.skills.CombatUtils;
import com.gmail.nossr50.datatypes.skills.SubSkillType;
import com.gmail.nossr50.util.skills.SkillActivationType;
import org.bukkit.OfflinePlayer;
import com.gmail.nossr50.util.player.UserManager;
import org.bukkit.entity.Player;
import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.skills.swords.SwordsManager;
import com.gmail.nossr50.commands.skills.SwordsCommand;

public class SwordsSkills extends SwordsCommand
{
    private int skillLevel;
    private SwordsManager manager;
    private McMMOPlayer mmoPlayer;
    
    public SwordsSkills(final Player p) {
        this.mmoPlayer = UserManager.getOfflinePlayer((OfflinePlayer)p);
        this.manager = this.mmoPlayer.getSwordsManager();
        this.skillLevel = this.manager.getSkillLevel();
    }
    
    public String getSkillInfo(final String skill) {
        switch (skill) {
            case "RC": {
                return this.getAbilityDisplayValues(SkillActivationType.RANDOM_LINEAR_100_SCALE_WITH_CAP, this.mmoPlayer.getPlayer(), SubSkillType.SWORDS_RUPTURE)[0];
            }
            case "RL": {
                return new StringBuilder(String.valueOf(this.manager.getRuptureBleedTicks())).toString();
            }
            case "SD": {
                return new StringBuilder(String.valueOf(this.manager.getStabDamage())).toString();
            }
            case "CAC": {
                return this.getAbilityDisplayValues(SkillActivationType.RANDOM_LINEAR_100_SCALE_WITH_CAP, this.mmoPlayer.getPlayer(), SubSkillType.SWORDS_COUNTER_ATTACK)[0];
            }
            case "SSL": {
                return this.calculateLengthDisplayValues(this.mmoPlayer.getPlayer(), (float)this.skillLevel)[0];
            }
            case "LBMD": {
                return new StringBuilder(String.valueOf(CombatUtils.getLimitBreakDamageAgainstQuality(this.mmoPlayer.getPlayer(), SubSkillType.SWORDS_SWORDS_LIMIT_BREAK, 1000))).toString();
            }
            default:
                break;
        }
        return "Wrong skill";
    }
}
