package jss.customjoinandquitmessages.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import jss.customjoinandquitmessages.CustomJoinAndQuitMessages;
import jss.customjoinandquitmessages.gui.DisplayGui;
import jss.customjoinandquitmessages.manager.DisplayManager;
import jss.customjoinandquitmessages.utils.EventUtils;
import jss.customjoinandquitmessages.utils.Logger;
import jss.customjoinandquitmessages.utils.Utils;

public class CustomJoinAndQuitCmd implements CommandExecutor, TabCompleter{
	
	private CustomJoinAndQuitMessages plugin;
	private EventUtils EventsUtils = new EventUtils(plugin);
		
	public CustomJoinAndQuitCmd(CustomJoinAndQuitMessages plugin) {
		this.plugin = plugin;
		plugin.getCommand("CustomJoinAndQuitMessages").setExecutor(this);
		plugin.getCommand("CustomJoinAndQuitMessages").setTabCompleter(this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			if(args.length >= 1) {
				if(args[0].equalsIgnoreCase("help")) {
					List<String> list = plugin.Locale().help_1;
					Utils.sendColorMessage(EventsUtils.getConsoleSender(), "&5-=-=-=-=-=-=-=-=-=-=-=&6[&d"+plugin.name+"&6]&5=-=-=-=-=-=-=-=-=-=-=-");
					for(String text : list) {
						Utils.sendColorMessage(EventsUtils.getConsoleSender(), text);
					}
					Utils.sendColorMessage(EventsUtils.getConsoleSender(), "&5-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
				}else if(args[0].equalsIgnoreCase("reload")) {
					plugin.getPreConfigLoader().loadConfig();
					plugin.getPreConfigLoader().loadLangs();
					plugin.getConfigFile().reloadConfig();
					Utils.sendColorMessage(EventsUtils.getConsoleSender(), Utils.getPrefix() + " " + plugin.Locale().reload);
				}else if(args[0].equalsIgnoreCase("info")) {
					Utils.sendColorMessage(EventsUtils.getConsoleSender(), "&5 <||=-=-=-=-="+Utils.getPrefix()+"&5=-=-=-=-=-=-");
					Utils.sendColorMessage(EventsUtils.getConsoleSender(), "&5 <|| &c* &bName: &3"+plugin.name);
					Utils.sendColorMessage(EventsUtils.getConsoleSender(), "&5 <|| &c* &bAuthor: &3jonagamerpro1234");
					Utils.sendColorMessage(EventsUtils.getConsoleSender(), "&5 <|| &c* &bVersion: &a" + plugin.version);
					Utils.sendColorMessage(EventsUtils.getConsoleSender(), "&5 <|| &c* &bUpdate: &e" + plugin.useLatestversion);
					Utils.sendColorMessage(EventsUtils.getConsoleSender(), "&5 <||=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
				}else {
					Utils.sendColorMessage(EventsUtils.getConsoleSender(), Utils.getPrefix() + " " + plugin.Locale().Error_Cmd);
				}
				return true;
			}
			Utils.sendColorMessage(EventsUtils.getConsoleSender(), Utils.getPrefix() + " " + plugin.Locale().Help_cmd);
			return false;
		}
		Player j = (Player) sender;
		if(args.length >= 1) {
			if(args[0].equalsIgnoreCase("help")) {
				if((j.isOp()) ||  (j.hasPermission("Cjm.Help"))) {
					List<String> list = plugin.Locale().help_1;
					Utils.sendColorMessage(j, "&5-=-=-=-=-=-=-=-=-=-=-=&6[&d"+plugin.name+"&6]&5=-=-=-=-=-=-=-=-=-=-=-");
					for(String text : list) {
						Utils.sendColorMessage(EventsUtils.getConsoleSender(), text);
					}
					Utils.sendColorMessage(j, "&5-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
				}else {
					Utils.sendTextComponent116Hover(j, "TEXT", plugin.Locale().No_Permission, plugin.Locale().No_Permission_Label);
				}
				return true;
			}
			
			if(args[0].equalsIgnoreCase("display")) {
				if((j.isOp()) || (j.hasPermission("Cjm.Display"))){
					
					DisplayGui displayGui = new DisplayGui(j, plugin);
					DisplayManager displayManager = new DisplayManager(j);
					
					if(args.length >= 2) {
						
						if(args[1].equalsIgnoreCase("open")) {
							
							if(args.length >= 3) {
								
								String group = args[2];
								
								if(group == null) {
									Logger.error(group);
									return true;
								}
								
								displayManager.setGroup(group);
								
								displayGui.open();
								return true;
							}
							
							displayGui.open();
							return true;
						}
						
						if(args[1].equalsIgnoreCase("join")) {
							displayManager.showJoinMessage();
							return true;
						}
						
						if(args[1].equalsIgnoreCase("quit")) {
							displayManager.showQuitMessage();
							return true;
						}
						
						if(args[1].equalsIgnoreCase("title")) {
							displayManager.showTitleMessage();
							return true;
						}
						
						if(args[1].equalsIgnoreCase("actionbar")) {
							displayManager.showActionbar();
							return true;
						}
						
						if(args[1].equalsIgnoreCase("welcome")) {
							displayManager.showWelcomeMessage();
							return true;
						}
						
						if(args[1].equalsIgnoreCase("sound")) {
							
							
							if(args.length <= 2) {
								
								if(args[2].equalsIgnoreCase("join")) {
									displayManager.showJoinSound();
									return true;
								}
								
								if(args[2].equalsIgnoreCase("quit")) {
									displayManager.showQuitSound();
									return true;
								}
								
								Utils.sendColorMessage(j, Utils.getPrefixPlayer() + " " + plugin.Locale().Error_Cmd);
								return true;
							}
							
							Utils.sendColorMessage(j, "");
							return true;
						}
						
						if(args[1].equalsIgnoreCase("firstjoin")) {
							displayManager.showFirstJoinMessage();
							return true;
						}
						
						if(args[1].equalsIgnoreCase("all")) {
							displayManager.showAllMessage();
							return true;
						}
						
						Utils.sendColorMessage(j, Utils.getPrefixPlayer() + " " + plugin.Locale().Error_Cmd);
						return true;
					}
					Utils.sendColorMessage(j, "");
				}else {
					Utils.sendTextComponent116Hover(j, "TEXT", plugin.Locale().No_Permission, plugin.Locale().No_Permission_Label);
				}
				return true;
			}
			
			if(args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
				if((j.isOp()) || (j.hasPermission("Cjm.Reload"))) {
					plugin.getPreConfigLoader().loadConfig();
					plugin.getPreConfigLoader().loadLangs();
					plugin.getConfigFile().reloadConfig();
					Utils.sendColorMessage(j, Utils.getPrefixPlayer() + " " + plugin.Locale().reload);
				}else {
					Utils.sendTextComponent116Hover(j, "TEXT", plugin.Locale().No_Permission, plugin.Locale().No_Permission_Label);
				}
				return true;
			}
			if(args[0].equalsIgnoreCase("info")) {
				Utils.sendColorMessage(j, "&5-=-=-=-=-=[&b"+plugin.name+"&5]=-=-=-=-=-=-");
				Utils.sendColorMessage(j, "&5> &3Name: &b"+plugin.name);
				Utils.sendColorMessage(j, "&5> &3Author: &6jonagamerpro1234");
				Utils.sendColorMessage(j, "&5> &3Version: &6"+plugin.version);
				Utils.sendColorMessage(j, "&5> &3Update: &a" + plugin.getUpdateVersion());
				Utils.sendColorMessage(j, "&5-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
				return true;
			}
			Utils.sendColorMessage(j, Utils.getPrefixPlayer() + " " + plugin.Locale().Error_Cmd);
			return true;
		}
		Utils.sendColorMessage(j, Utils.getPrefixPlayer() + " " + plugin.Locale().Help_cmd);
		return true;
	}
	
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> options = new ArrayList<>();
		String lastArgs = args.length != 0 ? args[(args.length - 1)] : "";
		if(!(sender instanceof Player)) {
			switch (args.length) {
			case 0:
			case 1:
				options.add("help");
				options.add("reload");
				options.add("info");
				break;
			}
			return Utils.TabLimit(options, lastArgs);
		}

		Player j = (Player) sender;
		if((j.isOp()) || (j.hasPermission("Cjm.Tab"))) {
			switch (args.length) {
			case 0:
			case 1:
				options.add("help");
				options.add("reload");
				options.add("info");
				options.add("display");
				break;
			case 2:
				if(args[0].equalsIgnoreCase("display")) {
					options.add("open");
					options.add("firstjoin");
					options.add("join");
					options.add("quit");
					options.add("title");
					options.add("actionbar");
					options.add("sound");
					options.add("welcome");
				}
				break;
			case 3:
				if(args[0].equalsIgnoreCase("display")) {
					for(String groups : plugin.getGroupsFile().getConfig().getKeys(false)) {
						options.add(groups);
					}
				}
				if(args[0].equalsIgnoreCase("sound")) {
					options.add("join");
					options.add("quit");
				}
				break;
			case 4:
				if(args[0].equalsIgnoreCase("sound")) {
					for(String groups : plugin.getGroupsFile().getConfig().getKeys(false)) {
						options.add(groups);
					}
				}
				break;
			}
		}
		return Utils.TabLimit(options, lastArgs);
	}
}
