package jss.customjoinandquitmessages.manager;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.cryptomorin.xseries.messages.ActionBar;
import com.cryptomorin.xseries.messages.Titles;

import jss.customjoinandquitmessages.CustomJoinAndQuitMessages;
import jss.customjoinandquitmessages.json.Json;
import jss.customjoinandquitmessages.utils.Logger;
import jss.customjoinandquitmessages.utils.Settings;
import jss.customjoinandquitmessages.utils.Utils;

public class DisplayManager {

	private Player player;
	private final FileConfiguration config = CustomJoinAndQuitMessages.get().getConfigFile().getConfig();
	private final FileConfiguration groups = CustomJoinAndQuitMessages.get().getGroupsFile().getConfig();
	private String group;
	
	public DisplayManager(Player player) {
		this.player = player;
	}
	
	public void setGroup(String group) {
		this.group = group;
	}

	public void showAllMessage() {
		this.showFirstJoinMessage();
		this.showJoinMessage();
		this.showQuitMessage();
		this.showTitleMessage();
		this.showActionbar();
		this.showWelcomeMessage();
	}
	
	public void showFirstJoinMessage() {
		if(!Settings.firstjoin) Logger.warning("&e[showFirstJoinMessage] &b-> &7This feature is disabled and you will not be able to see the preview"); 
		
		String text;
		boolean isNormalType;
		boolean isModifyType;
		boolean isHover;
		boolean isClick;
		List<String> Hover_Text;
		String isClick_Mode;
		String Action_Command;
		String Action_Url;
		String Action_Suggest;
		
		if (Settings.is_Group_Display) {
			text = groups.getString(group + ".FirstJoin.Text");
			isNormalType = groups.getString(group + ".Type").equalsIgnoreCase("normal");
			isModifyType = groups.getString(group + ".Type").equalsIgnoreCase("modify");
			isHover = groups.getString(group + ".HoverEvent.Enabled").equals("true");
			isClick = groups.getString(group + ".ClickEvent.Enabled").equals("true");
			Hover_Text = groups.getStringList(group + ".HoverEvent.Hover");
			isClick_Mode = groups.getString(group + ".ClickEvent.Mode");
			Action_Command = groups.getString(group + ".ClickEvent.Actions.Command");
			Action_Url = groups.getString(group + ".ClickEvent.Actions.Url");
			Action_Suggest = groups.getString(group + ".ClickEvent.Actions.Suggest-Command");
		} else {
			text = Settings.join_message_first;
			isNormalType = Settings.join_type.equalsIgnoreCase("normal");
			isModifyType = Settings.join_type.equalsIgnoreCase("modify");
			
			isHover = config.getString("Join.HoverEvent.Enabled").equals("true");
			isClick = config.getString("Join.ClickEvent.Enabled").equals("true");
			Hover_Text = config.getStringList("Join.HoverEvent.Hover");
			isClick_Mode = config.getString("Join.ClickEvent.Mode");
			Action_Command = config.getString("Join.ClickEvent.Actions.Command");
			Action_Url = config.getString("Join.ClickEvent.Actions.Url");
			Action_Suggest = config.getString("Join.ClickEvent.Actions.Suggest-Command");
		}
		
		Json json = new Json(player, Utils.color(Utils.getVar(player, text)));
	
		if(isNormalType) {
			json.sendToAll();
			return;
		}else if(isModifyType) {			
			if (isHover) {
				if (isClick) {
					if (isClick_Mode.equalsIgnoreCase("command")) {
						json.setHover(Hover_Text).setExecuteCommand(Action_Command).sendToAll();
					} else if (isClick_Mode.equalsIgnoreCase("url")) {
						json.setHover(Hover_Text).setOpenURL(Action_Url).sendToAll();
					} else if (isClick_Mode.equalsIgnoreCase("suggest")) {
						json.setHover(Hover_Text).setSuggestCommand(Action_Suggest).sendToAll();
					}
				} else {
					json.setHover(Hover_Text).sendToAll();
				}
			} else {
				if (isClick) {
					if (isClick_Mode.equalsIgnoreCase("command")) {
						json.setExecuteCommand(Action_Command).sendToAll();
					} else if (isClick_Mode.equalsIgnoreCase("url")) {
						json.setOpenURL(Action_Url).sendToAll();
					} else if (isClick_Mode.equalsIgnoreCase("suggest")) {
						json.setSuggestCommand(Action_Suggest).sendToAll();
					}
				} else {
					json.sendToAll();
				}
			}
		}
	}
	
	public void showJoinMessage() {
		if(!Settings.join) Logger.warning("&e[showJoinMessage] &b-> &7This feature is disabled and you will not be able to see the preview"); 

		String text;
		boolean isNormalType;
		boolean isModifyType;
		boolean isHover;
		boolean isClick;
		List<String> Hover_Text;
		String isClick_Mode;
		String Action_Command;
		String Action_Url;
		String Action_Suggest;	
		
		if (Settings.is_Group_Display) {
			text =  groups.getString(group + ".Join-Text");
			isNormalType = groups.getString(group + ".Type").equalsIgnoreCase("normal");
			isModifyType = groups.getString(group + ".Type").equalsIgnoreCase("modify");
			
			isHover = groups.getString(group + ".HoverEvent.Enabled").equals("true");
			isClick = groups.getString(group + ".ClickEvent.Enabled").equals("true");
			
			Hover_Text = groups.getStringList(group + "HoverEvent.Hover");

			isClick_Mode = groups.getString(group + ".ClickEvent.Mode");
			Action_Command = groups.getString(group + ".ClickEvent.Actions.Command");
			Action_Url = groups.getString(group + ".ClickEvent.Actions.Url");
			Action_Suggest = groups.getString(group + ".ClickEvent.Actions.Suggest-Command");
		} else {
			text = Settings.join_message;
			isNormalType = Settings.join_type.equalsIgnoreCase("normal");
			isModifyType = Settings.join_type.equalsIgnoreCase("modify");
			
			isHover = config.getString("Join.HoverEvent.Enabled").equals("true");
			isClick = config.getString("Join.ClickEvent.Enabled").equals("true");
			
			Hover_Text = config.getStringList("Join.HoverEvent.Hover");

			isClick_Mode = config.getString("Join.ClickEvent.Mode");
			Action_Command = config.getString("Join.ClickEvent.Actions.Command");
			Action_Url = config.getString("Join.ClickEvent.Actions.Url");
			Action_Suggest = config.getString("Join.ClickEvent.Actions.Suggest-Command");
		}
		
		Json json = new Json(player, Utils.color(Utils.getVar(player, text)));
			
		if(isNormalType) {
			json.sendToAll();
			return;
		}else if(isModifyType) {			
			if (isHover) {
				if (isClick) {
					if (isClick_Mode.equalsIgnoreCase("command")) {
						json.setHover(Hover_Text).setExecuteCommand(Action_Command).sendToAll();
					} else if (isClick_Mode.equalsIgnoreCase("url")) {
						json.setHover(Hover_Text).setOpenURL(Action_Url).sendToAll();
					} else if (isClick_Mode.equalsIgnoreCase("suggest")) {
						json.setHover(Hover_Text).setSuggestCommand(Action_Suggest).sendToAll();
					}
				} else {
					json.setHover(Hover_Text).sendToAll();
				}
			} else {
				if (isClick) {
					if (isClick_Mode.equalsIgnoreCase("command")) {
						json.setExecuteCommand(Action_Command).sendToAll();
					} else if (isClick_Mode.equalsIgnoreCase("url")) {
						json.setOpenURL(Action_Url).sendToAll();
					} else if (isClick_Mode.equalsIgnoreCase("suggest")) {
						json.setSuggestCommand(Action_Suggest).sendToAll();
					}
				} else {
					json.sendToAll();
				}
			}
		}
	}

	public void showQuitMessage() {
		if(!Settings.quit) Logger.warning("&e[showQuitMessage] &b-> &7This feature is disabled and you will not be able to see the preview"); 
			
		String text;
		boolean isNormalType;
		boolean isModifyType;
		boolean isHover;
		boolean isClick;
		List<String> Hover_Text;
		String isClick_Mode;
		String Action_Command;
		String Action_Url;
		String Action_Suggest;	
		
		if (Settings.is_Group_Display) {
			text =  groups.getString(group + ".Quit-Text");
			isNormalType = groups.getString(group + ".Type").equalsIgnoreCase("normal");
			isModifyType = groups.getString(group + ".Type").equalsIgnoreCase("modify");
			
			isHover = groups.getString(group + ".HoverEvent.Enabled").equals("true");
			isClick = groups.getString(group + ".ClickEvent.Enabled").equals("true");
			
			Hover_Text = groups.getStringList(group + "HoverEvent.Hover");

			isClick_Mode = groups.getString(group + ".ClickEvent.Mode");
			Action_Command = groups.getString(group + ".ClickEvent.Actions.Command");
			Action_Url = groups.getString(group + ".ClickEvent.Actions.Url");
			Action_Suggest = groups.getString(group + ".ClickEvent.Actions.Suggest-Command");
		} else {
			text = Settings.quit_message;
			isNormalType = Settings.quit_type.equalsIgnoreCase("normal");
			isModifyType = Settings.quit_type.equalsIgnoreCase("modify");
			
			isHover = config.getString("Quit.HoverEvent.Enabled").equals("true");
			isClick = config.getString("Quit.ClickEvent.Enabled").equals("true");
			
			Hover_Text = config.getStringList("Quit.HoverEvent.Hover");

			isClick_Mode = config.getString("Quit.ClickEvent.Mode");
			Action_Command = config.getString("Quit.ClickEvent.Actions.Command");
			Action_Url = config.getString("Quit.ClickEvent.Actions.Url");
			Action_Suggest = config.getString("Quit.ClickEvent.Actions.Suggest-Command");
		}
		
		Json json = new Json(player, Utils.color(Utils.getVar(player, text)));
		
		if(isNormalType) {
			json.sendToAll();
			return;
		}else if(isModifyType) {			
			if (isHover) {
				if (isClick) {
					if (isClick_Mode.equalsIgnoreCase("command")) {
						json.setHover(Hover_Text).setExecuteCommand(Action_Command).sendToAll();
					} else if (isClick_Mode.equalsIgnoreCase("url")) {
						json.setHover(Hover_Text).setOpenURL(Action_Url).sendToAll();
					} else if (isClick_Mode.equalsIgnoreCase("suggest")) {
						json.setHover(Hover_Text).setSuggestCommand(Action_Suggest).sendToAll();
					}
				} else {
					json.setHover(Hover_Text).sendToAll();
				}
			} else {
				if (isClick) {
					if (isClick_Mode.equalsIgnoreCase("command")) {
						json.setExecuteCommand(Action_Command).sendToAll();
					} else if (isClick_Mode.equalsIgnoreCase("url")) {
						json.setOpenURL(Action_Url).sendToAll();
					} else if (isClick_Mode.equalsIgnoreCase("suggest")) {
						json.setSuggestCommand(Action_Suggest).sendToAll();
					}
				} else {
					json.sendToAll();
				}
			}
		}
	}
	
	public void showWelcomeMessage() {
		if(!Settings.welcome) Logger.warning("&e[showWelcomeMessage] &b-> &7This feature is disabled and you will not be able to see the preview"); 
		
		Settings.list_welcome.forEach( (text) -> {
			Utils.sendColorMessage(player, Utils.getVar(player, text));
		});
	}
	
	public void showTitleMessage() {
		if(!Settings.join_title) Logger.warning("&e[showTitleMessage] &b-> &7This feature is disabled and you will not be able to see the preview"); 
			
		Titles.sendTitle(player, Settings.join_title_fadein, Settings.join_title_stay, Settings.join_title_fadeout, Settings.join_message_title_title, Settings.join_message_title_subtitle);
	}
	
	public void showActionbar() {
		if(!Settings.join_actionbar) Logger.warning("&e[showActionbar] &b-> &7This feature is disabled and you will not be able to see the preview"); 
		ActionBar.sendActionBar(player, Settings.join_message_actionbar_text);
	}
	
	public void showJoinSound() {
		Utils.sendColorMessage(player, "&cTest join sound");
	}
	
	public void showQuitSound() {
		Utils.sendColorMessage(player, "&cTest quit sound");
	}
	
}
