package com.dustin.expansion.mmoplaceholders;

import com.gmail.nossr50.util.skills.SkillUtils;
import com.gmail.nossr50.datatypes.skills.PrimarySkillType;
import com.gmail.nossr50.util.skills.RankUtils;
import com.gmail.nossr50.util.random.RandomChanceUtil;
import com.gmail.nossr50.datatypes.skills.SubSkillType;
import com.gmail.nossr50.util.skills.SkillActivationType;
import org.bukkit.OfflinePlayer;
import com.gmail.nossr50.util.player.UserManager;
import org.bukkit.entity.Player;
import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.skills.herbalism.HerbalismManager;
import com.gmail.nossr50.commands.skills.HerbalismCommand;

public class HerbalismSkills extends HerbalismCommand
{
    private int skillLevel;
    private HerbalismManager manager;
    private McMMOPlayer mmoPlayer;
    
    public HerbalismSkills(final Player p) {
        this.mmoPlayer = UserManager.getOfflinePlayer((OfflinePlayer)p);
        this.manager = this.mmoPlayer.getHerbalismManager();
        this.skillLevel = this.manager.getSkillLevel();
    }
    
    public String getSkillInfo(final String skill) {
        switch (skill) {
            case "DDC": {
                return RandomChanceUtil.calculateAbilityDisplayValues(SkillActivationType.RANDOM_LINEAR_100_SCALE_WITH_CAP, this.mmoPlayer.getPlayer(), SubSkillType.HERBALISM_DOUBLE_DROPS)[0].replace(".00", "");
            }
            case "FDR": {
                return new StringBuilder(String.valueOf(RankUtils.getRank(this.mmoPlayer.getPlayer(), SubSkillType.HERBALISM_FARMERS_DIET))).toString();
            }
            case "GTC": {
                return RandomChanceUtil.calculateAbilityDisplayValues(SkillActivationType.RANDOM_LINEAR_100_SCALE_WITH_CAP, this.mmoPlayer.getPlayer(), SubSkillType.HERBALISM_GREEN_THUMB)[0].replace(".00", "");
            }
            case "GTD": {
                return SkillUtils.calculateLengthDisplayValues(this.mmoPlayer.getPlayer(), (float)this.skillLevel, PrimarySkillType.HERBALISM)[0];
            }
            case "GTS": {
                return new StringBuilder(String.valueOf(RankUtils.getRank(this.mmoPlayer.getPlayer(), SubSkillType.HERBALISM_GREEN_THUMB))).toString();
            }
            case "HLC": {
                return RandomChanceUtil.calculateAbilityDisplayValues(SkillActivationType.RANDOM_LINEAR_100_SCALE_WITH_CAP, this.mmoPlayer.getPlayer(), SubSkillType.HERBALISM_HYLIAN_LUCK)[0].replace(".00", "");
            }
            case "STC": {
                return RandomChanceUtil.calculateAbilityDisplayValues(SkillActivationType.RANDOM_LINEAR_100_SCALE_WITH_CAP, this.mmoPlayer.getPlayer(), SubSkillType.HERBALISM_SHROOM_THUMB)[0].replace(".00", "");
            }
            default:
                break;
        }
        return "Wrong placeholder";
    }
}
