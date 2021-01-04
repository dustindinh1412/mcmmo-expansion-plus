package com.dustin.expansion.mmoplaceholders;

import com.gmail.nossr50.util.skills.CombatUtils;
import com.gmail.nossr50.util.random.RandomChanceUtil;
import com.gmail.nossr50.datatypes.skills.SubSkillType;
import com.gmail.nossr50.util.skills.SkillActivationType;
import com.gmail.nossr50.skills.axes.Axes;
import org.bukkit.OfflinePlayer;
import com.gmail.nossr50.util.player.UserManager;
import org.bukkit.entity.Player;
import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.skills.axes.AxesManager;
import com.gmail.nossr50.commands.skills.AxesCommand;

public class AxesSkills extends AxesCommand
{
    private int skillLevel;
    private AxesManager manager;
    private McMMOPlayer mmoPlayer;
    
    public AxesSkills(final Player p) {
        this.mmoPlayer = UserManager.getOfflinePlayer((OfflinePlayer)p);
        this.manager = this.mmoPlayer.getAxesManager();
        this.skillLevel = this.manager.getSkillLevel();
    }
    
    public String getSkillInfo(final String skill) {
        switch (skill) {
            case "AI": {
                return new StringBuilder(String.valueOf(this.manager.getImpactDurabilityDamage())).toString();
            }
            case "AM": {
                return new StringBuilder(String.valueOf(Axes.getAxeMasteryBonusDamage(this.mmoPlayer.getPlayer()))).toString();
            }
            case "GI": {
                return new StringBuilder(String.valueOf(Axes.greaterImpactBonusDamage)).toString();
            }
            case "CSC": {
                return RandomChanceUtil.calculateAbilityDisplayValues(SkillActivationType.RANDOM_LINEAR_100_SCALE_WITH_CAP, this.mmoPlayer.getPlayer(), SubSkillType.AXES_CRITICAL_STRIKES)[0];
            }
            case "SSD": {
                return super.calculateLengthDisplayValues(this.mmoPlayer.getPlayer(), (float)this.skillLevel)[0];
            }
            case "LBMD": {
                return new StringBuilder(String.valueOf(CombatUtils.getLimitBreakDamageAgainstQuality(this.mmoPlayer.getPlayer(), SubSkillType.AXES_AXES_LIMIT_BREAK, 1000))).toString();
            }
            default:
                break;
        }
        return "Wrong skill";
    }
}
