package com.dustin.expansion.mmoplaceholders;

import com.gmail.nossr50.util.random.RandomChanceUtil;
import com.gmail.nossr50.datatypes.skills.SubSkillType;
import com.gmail.nossr50.util.skills.SkillActivationType;
import org.bukkit.OfflinePlayer;
import com.gmail.nossr50.util.player.UserManager;
import org.bukkit.entity.Player;
import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.skills.woodcutting.WoodcuttingManager;
import com.gmail.nossr50.commands.skills.WoodcuttingCommand;

public class WoodcuttingSkills extends WoodcuttingCommand
{
    private int skillLevel;
    private WoodcuttingManager manager;
    private McMMOPlayer mmoPlayer;
    
    public WoodcuttingSkills(final Player p) {
        this.mmoPlayer = UserManager.getOfflinePlayer((OfflinePlayer)p);
        this.manager = this.mmoPlayer.getWoodcuttingManager();
        this.skillLevel = this.manager.getSkillLevel();
    }
    
    public String getSkillInfo(final String skill) {
        switch (skill) {
            case "DDC": {
                return RandomChanceUtil.calculateAbilityDisplayValues(SkillActivationType.RANDOM_LINEAR_100_SCALE_WITH_CAP, this.mmoPlayer.getPlayer(), SubSkillType.WOODCUTTING_HARVEST_LUMBER)[0].replace(".00", "");
            }
            case "TFL": {
                return this.calculateLengthDisplayValues(this.mmoPlayer.getPlayer(), (float)this.skillLevel)[0];
            }
            default:
                break;
        }
        return "Wrong placeholder";
    }
}
