package com.dustin.expansion.mmoplaceholders;

import org.bukkit.OfflinePlayer;
import com.gmail.nossr50.util.player.UserManager;
import org.bukkit.entity.Player;
import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.skills.salvage.SalvageManager;

public class SalvageSkills
{
    private SalvageManager manager;
    private McMMOPlayer mmoPlayer;
    
    public SalvageSkills(final Player p) {
        this.mmoPlayer = UserManager.getOfflinePlayer((OfflinePlayer)p);
        this.manager = this.mmoPlayer.getSalvageManager();
    }
    
    public String getSkillInfo(final String skill) {
        switch (skill) {
            case "SC": {
                return new StringBuilder(String.valueOf(this.manager.getSalvageLimit())).toString();
            }
            case "ASR": {
                return new StringBuilder(String.valueOf(this.manager.getArcaneSalvageRank())).toString();
            }
            case "ASFEC": {
                return new StringBuilder(String.valueOf(this.manager.getExtractFullEnchantChance())).toString();
            }
            case "ASPEC": {
                return new StringBuilder(String.valueOf(this.manager.getExtractPartialEnchantChance())).toString();
            }
            default:
                break;
        }
        return "Wrong skill";
    }
}
