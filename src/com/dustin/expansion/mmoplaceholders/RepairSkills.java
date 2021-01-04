package com.dustin.expansion.mmoplaceholders;

import com.gmail.nossr50.util.random.RandomChanceUtil;
import com.gmail.nossr50.datatypes.skills.SubSkillType;
import com.gmail.nossr50.util.skills.SkillActivationType;
import com.gmail.nossr50.skills.repair.Repair;
import org.bukkit.OfflinePlayer;
import com.gmail.nossr50.util.player.UserManager;
import org.bukkit.entity.Player;
import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.skills.repair.RepairManager;
import java.text.DecimalFormat;

public class RepairSkills
{
    protected DecimalFormat percent;
    private int skillLevel;
    private RepairManager manager;
    private McMMOPlayer mmoPlayer;
    
    public RepairSkills(final Player p) {
        this.percent = new DecimalFormat("##0.00%");
        this.mmoPlayer = UserManager.getOfflinePlayer((OfflinePlayer)p);
        this.manager = this.mmoPlayer.getRepairManager();
        this.skillLevel = this.manager.getSkillLevel();
    }
    
    public String getSkillInfo(final String skill) {
        switch (skill) {
            case "RM": {
                return this.percent.format(Math.min(Repair.repairMasteryMaxBonus / Repair.repairMasteryMaxBonusLevel * this.skillLevel, Repair.repairMasteryMaxBonus) / 100.0);
            }
            case "AFR": {
                return new StringBuilder(String.valueOf(this.manager.getArcaneForgingRank())).toString();
            }
            case "SRC": {
                return RandomChanceUtil.calculateAbilityDisplayValues(SkillActivationType.RANDOM_LINEAR_100_SCALE_WITH_CAP, this.mmoPlayer.getPlayer(), SubSkillType.REPAIR_SUPER_REPAIR)[0];
            }
            case "AFOF": {
                return new StringBuilder(String.valueOf(this.manager.getDowngradeEnchantChance())).toString();
            }
            case "AFOS": {
                return new StringBuilder(String.valueOf(this.manager.getKeepEnchantChance())).toString();
            }
            default:
                break;
        }
        return "Wrong skill";
    }
}
