package com.dustin.expansion.mmoplaceholders;

import com.gmail.nossr50.util.random.RandomChanceUtil;
import com.gmail.nossr50.datatypes.skills.SubSkillType;
import com.gmail.nossr50.util.skills.SkillActivationType;
import org.bukkit.OfflinePlayer;
import com.gmail.nossr50.util.player.UserManager;
import org.bukkit.entity.Player;
import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.skills.mining.MiningManager;
import com.gmail.nossr50.commands.skills.MiningCommand;

public class MiningSkills extends MiningCommand
{
    private int skillLevel;
    private MiningManager miningManager;
    private McMMOPlayer mmoPlayer;
    
    public MiningSkills(final Player p) {
        this.mmoPlayer = UserManager.getOfflinePlayer((OfflinePlayer)p);
        this.miningManager = this.mmoPlayer.getMiningManager();
        this.skillLevel = this.miningManager.getSkillLevel();
    }
    
    public String getSkillInfo(final String skill) {
        switch (skill) {
            case "OB": {
                return this.percent.format(this.miningManager.getOreBonus() / 30.0);
            }
            case "BRI": {
                return new StringBuilder(String.valueOf(this.mmoPlayer.getMiningManager().getBlastRadiusModifier())).toString().replace(".0", "");
            }
            case "DDC": {
                return RandomChanceUtil.calculateAbilityDisplayValues(SkillActivationType.RANDOM_LINEAR_100_SCALE_WITH_CAP, this.mmoPlayer.getPlayer(), SubSkillType.MINING_DOUBLE_DROPS)[0].replace(".00", "");
            }
            case "SBL": {
                return this.getSkillLength();
            }
            case "DEDD": {
                return this.percent.format(this.miningManager.getBlastDamageModifier() / 100.0).replace(".00", "");
            }
            default:
                break;
        }
        return new StringBuilder(String.valueOf(this.miningManager.getDropMultiplier())).toString();
    }
    
    public String getSkillLength() {
        return this.calculateLengthDisplayValues(this.mmoPlayer.getPlayer(), (float)this.skillLevel)[0];
    }
}
