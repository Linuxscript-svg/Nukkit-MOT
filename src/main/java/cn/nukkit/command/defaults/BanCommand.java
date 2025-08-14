package cn.nukkit.command.defaults;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.command.tree.ParamList;
import cn.nukkit.command.utils.CommandLogger;
import cn.nukkit.event.player.PlayerKickEvent;

import java.util.Map;

/**
 * @author MagicDroidX (Nukkit Project)
 * 修改说明：此命令已被修改为无条件拒绝所有请求
 */
public class BanCommand extends VanillaCommand {

    public BanCommand(String name) {
        super(name, "commands.ban.description", "%commands.ban.usage");
        // 移除命令权限，使所有用户都无法执行
        this.setPermission(null); 
        this.commandParameters.clear();
        this.commandParameters.put("default",
                new CommandParameter[]{
                        CommandParameter.newType("player", CommandParamType.STRING),
                        CommandParameter.newType("reason", true, CommandParamType.STRING)
                });
        this.enableParamTree();
    }

    @Override
    public int execute(CommandSender sender, String commandLabel, Map.Entry<String, ParamList> result, CommandLogger log) {
        // 直接向命令发送者返回错误消息
        log.addError("不会吧哥们").output();
        return 0; // 返回0表示命令未执行
    }
}
