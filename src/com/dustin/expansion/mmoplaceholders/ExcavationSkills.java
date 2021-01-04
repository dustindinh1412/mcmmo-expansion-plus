package com.dustin.expansion.mmoplaceholders;

import org.bukkit.OfflinePlayer;
import com.gmail.nossr50.util.player.UserManager;
import org.bukkit.entity.Player;
import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.skills.excavation.ExcavationManager;
import com.gmail.nossr50.commands.skills.ExcavationCommand;

public class ExcavationSkills extends ExcavationCommand
{
    private int skillLevel;
    private ExcavationManager manager;
    private McMMOPlayer mmoPlayer;
    
    public ExcavationSkills(final Player p) {
        this.mmoPlayer = UserManager.getOfflinePlayer((OfflinePlayer)p);
        this.manager = this.mmoPlayer.getExcavationManager();
        this.skillLevel = this.manager.getSkillLevel();
    }
    
    public String getSkillInfo(final String skill) {
        switch (skill) {
            case "AEOA": {
                return new StringBuilder(String.valueOf(this.manager.getExperienceOrbsReward())).toString();
            }
            case "AEOC": {
                return new StringBuilder(String.valueOf(this.manager.getArchaelogyExperienceOrbChance())).toString();
            }
            case "GDBD": {
                return this.calculateLengthDisplayValues(this.mmoPlayer.getPlayer(), (float)this.skillLevel)[0];
            }
            default:
                break;
        }
        return "Wrong placeholder";
    }
}
