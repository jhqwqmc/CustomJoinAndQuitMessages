package jss.customjoinandquitmessage.commands.subcommands;

import jss.customjoinandquitmessage.commands.utils.SubCommand;
import jss.customjoinandquitmessage.files.utils.Settings;
import jss.customjoinandquitmessage.utils.Utils;
import org.bukkit.command.CommandSender;

public class HelpCommand extends SubCommand {

    public String name() {
        return "help";
    }

    public String permission(){
        return "command.help";
    }

    public boolean requiresPermission() {
        return true;
    }

    public void onCommand(CommandSender sender, String[] args) {
        for (String s : Settings.lang_helpCommand) {
            Utils.sendColorMessage(sender, s);
        }
    }

    public boolean allowConsole() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public String disabledMessage() {
        return null;
    }
}
