package com.dustin.expansion.mmoplaceholders;

import java.util.List;
import java.util.Map;
import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.datatypes.database.PlayerStat;
import com.gmail.nossr50.datatypes.skills.PrimarySkillType;
import com.gmail.nossr50.mcMMO;
import org.bukkit.entity.Player;
import com.gmail.nossr50.util.player.UserManager;
import org.bukkit.OfflinePlayer;
import java.text.DecimalFormat;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class McMMOExpansion extends PlaceholderExpansion
{
    protected DecimalFormat percent;
    
    public McMMOExpansion() {
        this.percent = new DecimalFormat("##0.00%");
    }
    
    public String getAuthor() {
        return "DustinDinh";
    }
    
    public String getIdentifier() {
        return "mmoplus";
    }
    
    public String getVersion() {
        return "1.0.0";
    }
    
    public String onRequest(final OfflinePlayer player, final String identifier) {
        final McMMOPlayer mmoPlayer = UserManager.getOfflinePlayer(player);
        final Player p = (Player)player;
        
        if (identifier.contains("myrank")) {
            final String skill = identifier.substring(identifier.lastIndexOf("_") + 1);
            final Map<PrimarySkillType, Integer> skills = mcMMO.getDatabaseManager().readRank(p.getName());
            

            Integer rank = skills.get(PrimarySkillType.getSkill(skill));
            
            return (rank != null) ? rank.toString() : "Unranked";
        }
        
        if (identifier.contains("topname")) {
            final String skill = identifier.substring(identifier.indexOf("_") + 1, identifier.lastIndexOf("_"));
            final int position = Integer.parseInt(identifier.substring(identifier.lastIndexOf("_") + 1));
            final List<PlayerStat> topTenPlayer = (List<PlayerStat>)mcMMO.getDatabaseManager().readLeaderboard(PrimarySkillType.getSkill(skill), 1, 10);
            if (position > topTenPlayer.size() - 1) {
                return "EmptyName";
            }
            return topTenPlayer.get(position).name;
        }
        else if (identifier.contains("topval")) {
            final String skill = identifier.substring(identifier.indexOf("_") + 1, identifier.lastIndexOf("_"));
            final int position = Integer.parseInt(identifier.substring(identifier.lastIndexOf("_") + 1));
            final List<PlayerStat> topTenPlayer = (List<PlayerStat>)mcMMO.getDatabaseManager().readLeaderboard(PrimarySkillType.getSkill(skill), 1, 10);
            if (position > topTenPlayer.size() - 1) {
                return "EmptyValue";
            }
            return new StringBuilder(String.valueOf(topTenPlayer.get(position).statVal)).toString();
        }
        else {
            if (identifier.contains("checklevel")) {
                final String skill = identifier.substring(identifier.indexOf("_") + 1, identifier.lastIndexOf("_"));
                final int reqLevel = Integer.parseInt(identifier.substring(identifier.lastIndexOf("_") + 1));
                final int currentLevel = mmoPlayer.getSkillLevel(PrimarySkillType.getSkill(skill.toUpperCase()));
                return (currentLevel >= reqLevel) ? "§a✔" : "§c✘";
            }
            if (identifier.contains("mining")) {
                final String skill = identifier.replace("mining_", "");
                return new MiningSkills(p).getSkillInfo(skill);
            }
            if (identifier.contains("woodcutting")) {
                final String skill = identifier.replace("woodcutting_", "");
                return new WoodcuttingSkills(p).getSkillInfo(skill);
            }
            if (identifier.contains("excavation")) {
                final String skill = identifier.replace("excavation_", "");
                return new ExcavationSkills(p).getSkillInfo(skill);
            }
            if (identifier.contains("herbalism")) {
                final String skill = identifier.replace("herbalism_", "");
                return new HerbalismSkills(p).getSkillInfo(skill);
            }
            if (identifier.contains("fishing")) {
                final String skill = identifier.replace("fishing_", "");
                return new FishingSkills(p).getSkillInfo(skill);
            }
            if (identifier.contains("alchemy")) {
                final String skill = identifier.replace("alchemy_", "");
                return new AlchemySkills(p).getSkillInfo(skill);
            }
            if (identifier.contains("repair")) {
                final String skill = identifier.replace("repair_", "");
                return new RepairSkills(p).getSkillInfo(skill);
            }
            if (identifier.contains("salvage")) {
                final String skill = identifier.replace("salvage_", "");
                return new SalvageSkills(p).getSkillInfo(skill);
            }
            if (identifier.contains("smelting")) {
                final String skill = identifier.replace("smelting_", "");
                return new SmeltingSkill(p).getSkillInfo(skill);
            }
            if (identifier.contains("swords")) {
                final String skill = identifier.replace("swords_", "");
                return new SwordsSkills(p).getSkillInfo(skill);
            }
            if (identifier.contains("acrobatics")) {
                final String skill = identifier.replace("acrobatics_", "");
                return new AcrobaticsSkill(p).getSkillInfo(skill);
            }
            if (identifier.contains("axes")) {
                final String skill = identifier.replace("axes_", "");
                return new AxesSkills(p).getSkillInfo(skill);
            }
            if (identifier.contains("archery")) {
                final String skill = identifier.replace("archery_", "");
                return new ArcherySkills(p).getSkillInfo(skill);
            }
            if (identifier.contains("unarmed")) {
                final String skill = identifier.replace("unarmed_", "");
                return new UnarmedSkills(p).getSkillInfo(skill);
            }
            if (identifier.contains("taming")) {
                final String skill = identifier.replace("taming_", "");
                return new TamingSkills(p).getSkillInfo(skill);
            }
            return "Wrong Args";
        }
    }
}
