package com.dustin.expansion.mmoplaceholders;

import org.bukkit.OfflinePlayer;
import com.gmail.nossr50.util.player.UserManager;
import org.bukkit.entity.Player;
import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.skills.alchemy.AlchemyManager;
import com.gmail.nossr50.commands.skills.AlchemyCommand;

public class AlchemySkills extends AlchemyCommand
{
    private AlchemyManager manager;
    private McMMOPlayer mmoPlayer;
    
    public AlchemySkills(final Player p) {
        this.mmoPlayer = UserManager.getOfflinePlayer((OfflinePlayer)p);
        this.manager = this.mmoPlayer.getAlchemyManager();
    }
    
    public String getSkillInfo(final String skill) {
        switch (skill) {
            case "BS": {
                return this.calculateAbilityDisplayValues(this.mmoPlayer.getPlayer())[0];
            }
            case "CR": {
                return new StringBuilder(String.valueOf(this.manager.getTier())).toString();
            }
            case "IL": {
                return this.manager.getIngredientList();
            }
            default:
                break;
        }
        return "Wrong skill";
    }
}
