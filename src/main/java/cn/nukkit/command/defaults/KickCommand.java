package cn.nukkit.command.defaults;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.command.tree.ParamList;
import cn.nukkit.command.tree.node.PlayersNode;
import cn.nukkit.command.utils.CommandLogger;

import java.util.Map;

/**
 * @author xtypr
 * @since 2015/11/11
 * 修改说明：此命令已被修改为无条件拒绝所有请求
 */
public class KickCommand extends VanillaCommand {

    public KickCommand(String name) {
        super(name, "commands.kick.description");
        this.setPermission(null); 
        this.commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[]{
                CommandParameter.newType("player", CommandParamType.TARGET, new PlayersNode()),
                CommandParameter.newType("reason", true, CommandParamType.MESSAGE)
        });
        this.enableParamTree();
    }

    @Override
    public int execute(CommandSender sender, String commandLabel, Map.Entry<String, ParamList> result, CommandLogger log) {
        
        log.addError("你是认真的？").output();
        return 0;
    }
}
