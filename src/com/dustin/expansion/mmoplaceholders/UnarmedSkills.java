package com.dustin.expansion.mmoplaceholders;

import com.gmail.nossr50.util.skills.CombatUtils;
import com.gmail.nossr50.util.random.RandomChanceUtil;
import com.gmail.nossr50.datatypes.skills.SubSkillType;
import com.gmail.nossr50.util.skills.SkillActivationType;
import org.bukkit.OfflinePlayer;
import com.gmail.nossr50.util.player.UserManager;
import org.bukkit.entity.Player;
import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.skills.unarmed.UnarmedManager;
import com.gmail.nossr50.commands.skills.UnarmedCommand;

public class UnarmedSkills extends UnarmedCommand
{
    private UnarmedManager manager;
    private McMMOPlayer mmoPlayer;
    
    public UnarmedSkills(final Player p) {
        this.mmoPlayer = UserManager.getOfflinePlayer((OfflinePlayer)p);
        this.manager = this.mmoPlayer.getUnarmedManager();
    }
    
    public String getSkillInfo(final String skill) {
        switch (skill) {
            case "BL": {
                return this.calculateLengthDisplayValues(this.mmoPlayer.getPlayer(), (float)this.manager.getSkillLevel())[0];
            }
            case "DC": {
                return RandomChanceUtil.calculateAbilityDisplayValues(SkillActivationType.RANDOM_LINEAR_100_SCALE_WITH_CAP, this.mmoPlayer.getPlayer(), SubSkillType.UNARMED_DISARM)[0];
            }
            case "ADC": {
                return RandomChanceUtil.calculateAbilityDisplayValues(SkillActivationType.RANDOM_LINEAR_100_SCALE_WITH_CAP, this.mmoPlayer.getPlayer(), SubSkillType.UNARMED_ARROW_DEFLECT)[0];
            }
            case "IGC": {
                return RandomChanceUtil.calculateAbilityDisplayValues(SkillActivationType.RANDOM_LINEAR_100_SCALE_WITH_CAP, this.mmoPlayer.getPlayer(), SubSkillType.UNARMED_IRON_GRIP)[0];
            }
            case "SAS": {
                return new StringBuilder(String.valueOf(this.manager.getSteelArmStyleDamage())).toString();
            }
            case "LBMD": {
                return new StringBuilder(String.valueOf(CombatUtils.getLimitBreakDamageAgainstQuality(this.mmoPlayer.getPlayer(), SubSkillType.UNARMED_UNARMED_LIMIT_BREAK, 1000))).toString();
            }
            default:
                break;
        }
        return "Wrong skill";
    }
}
