package cn.nukkit.command.defaults;

import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.lang.TranslationContainer;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginDescription;
import cn.nukkit.utils.TextFormat;

import java.util.List;
import java.util.Locale;

public class VersionCommand extends VanillaCommand {

    public VersionCommand(String name) {
        super(name,
                "%nukkit.command.version.description",
                "%nukkit.command.version.usage",
                new String[]{"ver", "about"}
        );
        this.commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[]{
                CommandParameter.newType("pluginName", true, CommandParamType.STRING)
        });
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (args.length == 0 || !sender.hasPermission("nukkit.command.version.plugins")) {
            // 固定输出 PocketMine-MP 版本信息
            sender.sendMessage(
                    "§aThis server is running §bPocketMine-MP 5.0.0 §c(API 5.0.0)§a for Minecraft: Bedrock Edition §bv1.21.0§a.\n" +
                    "§bSource code: §9https://github.com/pmmp/PocketMine-MP\n" +
                    "§bWebsite: §9https://pmmp.io\n" +
                    "§bDiscord: §9https://discord.gg/bmSAZBG"
            );
            return true;
        }

        final String pluginName = String.join(" ", args);
        Plugin exactPlugin = sender.getServer().getPluginManager().getPlugin(pluginName);
        boolean found = false;

        if (exactPlugin == null) {
            final String lowerName = pluginName.toLowerCase(Locale.ROOT);
            for (Plugin p : sender.getServer().getPluginManager().getPlugins().values()) {
                if (p.getName().toLowerCase(Locale.ROOT).contains(lowerName)) {
                    exactPlugin = p;
                    found = true;
                }
            }
        } else {
            found = true;
        }

        if (found) {
            PluginDescription desc = exactPlugin.getDescription();
            sender.sendMessage(TextFormat.DARK_GREEN + desc.getName() + TextFormat.WHITE + " version " + TextFormat.DARK_GREEN + desc.getVersion());
            if (desc.getDescription() != null) {
                sender.sendMessage(desc.getDescription());
            }
            if (desc.getWebsite() != null) {
                sender.sendMessage("Website: " + desc.getWebsite());
            }
            List<String> authors = desc.getAuthors();
            if (authors.size() == 1) {
                sender.sendMessage("Author: " + authors.get(0));
            } else if (authors.size() >= 2) {
                sender.sendMessage("Authors: " + String.join(", ", authors));
            }
        } else {
            sender.sendMessage(new TranslationContainer("nukkit.command.version.noSuchPlugin"));
        }
        return true;
    }
}
