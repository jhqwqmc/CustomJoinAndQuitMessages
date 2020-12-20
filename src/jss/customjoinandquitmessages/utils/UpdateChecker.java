package jss.customjoinandquitmessages.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import jss.customjoinandquitmessages.CustomJoinAndQuitMessages;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class UpdateChecker implements UpdateHelper{

	private CustomJoinAndQuitMessages plugin;
	
	public UpdateChecker(CustomJoinAndQuitMessages plugin) {
		this.plugin = plugin;
	}
	
	public void Update(CommandSender sender) {
		FileConfiguration config = plugin.getConfig();
		String path = "Config.Update.Enabled";
		String lang = config.getString("Config.Update.Lang");
		
		if(config.getString(path).equals("true")) {
			try {
				URL url = new URL("https://api.spigotmc.org/legacy/update.php?resource=57006");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				int Time_Out = 1250;
				conn.setConnectTimeout(Time_Out);
				conn.setReadTimeout(Time_Out);
				plugin.latestversion = new BufferedReader(new InputStreamReader(conn.getInputStream())).readLine();
				if(plugin.latestversion.length() <= 9) {
					if(!plugin.version.equals(plugin.latestversion)) {
						sendMessage(sender, lang);
					}
				}
			}catch(Exception ex) {
				sendError(sender, lang);
			}
			
		}
		
	}
	
	public void Update(Player player) {
		FileConfiguration config = plugin.getConfig();
		String path = "Config.Update.Enabled";
		String lang = config.getString("Config.Update.Lang");
		
		if(config.getString(path).equals("true")) {
			try {
				URL url = new URL("https://api.spigotmc.org/legacy/update.php?resource=57006");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				int Time_Out = 1250;
				conn.setConnectTimeout(Time_Out);
				conn.setReadTimeout(Time_Out);
				plugin.latestversion = new BufferedReader(new InputStreamReader(conn.getInputStream())).readLine();
				if(plugin.latestversion.length() <= 9) {
					if(!plugin.version.equals(plugin.latestversion)) {
						sendMessage(player, lang);
					}
				}
			}catch(Exception ex) {
				sendError(player, lang);
			}
			
		}
	}

	public void sendMessage(CommandSender sender, String lang) {
		
		String temp = lang;
		
		if(temp.equalsIgnoreCase("es")) {
			Utils.sendColorMessage(sender, Utils.getPrefix() + " &5<| ");
			Utils.sendColorMessage(sender, Utils.getPrefix() + " &5<| " + Utils.getLine());
			Utils.sendColorMessage(sender, Utils.getPrefix() + " &5<| &bVersion Actual &3[&a"+plugin.version+"&3] &9-->&b Version Nueva &3[&d"+plugin.latestversion+"&3]" );
			Utils.sendColorMessage(sender, Utils.getPrefix() + " &5<| ");
			Utils.sendColorMessage(sender, Utils.getPrefix() + " &5<| &eHay una nueva version disponible, copia el enlace de abajo para dirigirte a la pag del plugin");
			Utils.sendColorMessage(sender, Utils.getPrefix() + " &5<| &ehttps://www.spigotmc.org/resources/custom-join-and-quit-message-1-7-x-1-16-x.57006/");
			Utils.sendColorMessage(sender, Utils.getPrefix() + " &5<| " + Utils.getLine());
			return;
		}
		
		if(temp.equalsIgnoreCase("en")) {
			Utils.sendColorMessage(sender, Utils.getPrefix() + " &5<| ");
			Utils.sendColorMessage(sender, Utils.getPrefix() + " &5<| " + Utils.getLine());
			Utils.sendColorMessage(sender, Utils.getPrefix() + " &5<| &bCurrent version &3[&a"+plugin.version+"&3] &9-->&b New version &3[&d"+plugin.latestversion+"&3]" );
			Utils.sendColorMessage(sender, Utils.getPrefix() + " &5<| ");
			Utils.sendColorMessage(sender, Utils.getPrefix() + " &5<| &eThere is a new version available, copy the link below to go to the plugin page");
			Utils.sendColorMessage(sender, Utils.getPrefix() + " &5<| &ehttps://www.spigotmc.org/resources/custom-join-and-quit-message-1-7-x-1-16-x.57006/");
			Utils.sendColorMessage(sender, Utils.getPrefix() + " &5<| " + Utils.getLine());
			return;
		}
	}
	
	public void sendMessage(Player player, String lang) {
		
		String temp = lang;
		
		if(temp.equalsIgnoreCase("es")) {
			
			TextComponent msg = new TextComponent();
			msg.setText(Utils.color(Utils.getPrefixPlayer() + " &eClick en este mensaje para copiar el enlace"));
			msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/custom-join-and-quit-message-1-7-x-1-16-x.57006/"));
			
			Utils.sendColorMessage(player, Utils.getPrefixPlayer() + "&5 " + Utils.getLine());
			Utils.sendColorMessage(player, Utils.getPrefixPlayer() + " &bVersion Actual &3[&a"+plugin.version+"&3] &9-->&b Version Nueva &3[&d"+plugin.latestversion+"&3]" );
			Utils.sendColorMessage(player, Utils.getPrefixPlayer() + " ");
			Utils.sendColorMessage(player, Utils.getPrefixPlayer() + " &eHay una nueva version disponible");
			//Utils.sendColorMessage(player, Utils.getPrefixPlayer() + " &eClick en este mensaje para copiar el enlace");
			player.spigot().sendMessage(msg);
			Utils.sendColorMessage(player, Utils.getPrefixPlayer() + "&5 " + Utils.getLine());	
			return;
		}
		
		if(temp.equalsIgnoreCase("en")) {
			
			TextComponent msg = new TextComponent();
			msg.setText(Utils.color(Utils.getPrefixPlayer() + " &eClick on this message to copy the link"));
			msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/custom-join-and-quit-message-1-7-x-1-16-x.57006/"));

			
			Utils.sendColorMessage(player, Utils.getPrefixPlayer() + "&5 " + Utils.getLine());
			Utils.sendColorMessage(player, Utils.getPrefixPlayer() + " &bCurrent version &3[&a"+plugin.version+"&3] &9-->&b New version &3[&d"+plugin.latestversion+"&3]" );
			Utils.sendColorMessage(player, Utils.getPrefixPlayer() + " ");
			Utils.sendColorMessage(player, Utils.getPrefixPlayer() + " &eThere is a new version available");
			//Utils.sendColorMessage(player, Utils.getPrefixPlayer() + " &eClick on this message to copy the link");
			player.spigot().sendMessage(msg);
			Utils.sendColorMessage(player, Utils.getPrefixPlayer() + "&5 " + Utils.getLine());
			return;
		}
	}
	
	public  void sendError(CommandSender sender, String lang) {
		String temp = lang;
		
		if(temp.equalsIgnoreCase("es")) {
			Utils.sendColorMessage(sender, Utils.getPrefix() + " &5<| ");
			Utils.sendColorMessage(sender, Utils.getPrefix() + " &5<| " + Utils.getLine());
			Utils.sendColorMessage(sender, Utils.getPrefix() + " &5<| &bVersion Actual &3[&a"+plugin.version+"&3] &9-->&b Version Nueva &3[&c"+plugin.latestversion+"&3]");
			Utils.sendColorMessage(sender, Utils.getPrefix() + " &5<| ");
			Utils.sendColorMessage(sender, Utils.getPrefix() + " &5<| &cNo se ha podido encontrar ninguna version nueva o ya tienes la version mas reciente");
			Utils.sendColorMessage(sender, Utils.getPrefix() + " &5<| &c!Verifica si estas conectado a internet!");
			Utils.sendColorMessage(sender, Utils.getPrefix() + " &5<| " + Utils.getLine());
			return;
		}
		
		if(temp.equalsIgnoreCase("en")) {
			Utils.sendColorMessage(sender, Utils.getPrefix() + " &5<| ");
			Utils.sendColorMessage(sender, Utils.getPrefix() + " &5<| " + Utils.getLine());
			Utils.sendColorMessage(sender, Utils.getPrefix() + " &5<| &bCurrent version &3[&a"+plugin.version+"&3] &9-->&b New version &3[&c"+plugin.latestversion+"&3]");
			Utils.sendColorMessage(sender, Utils.getPrefix() + " &5<| ");
			Utils.sendColorMessage(sender, Utils.getPrefix() + " &5<| &cNo new version could be found or you already have the most recent version");
			Utils.sendColorMessage(sender, Utils.getPrefix() + " &5<| &c!Check if you are connected to the internet!");
			Utils.sendColorMessage(sender, Utils.getPrefix() + " &5<| " + Utils.getLine());
			return;
		}
	}
	
	public  void sendError(Player player, String lang) {
		String temp = lang;
		
		if(temp.equalsIgnoreCase("es")) {
			Utils.sendColorMessage(player, Utils.getPrefixPlayer() + "&5 " + Utils.getLine());
			Utils.sendColorMessage(player, Utils.getPrefixPlayer() + " &bVersion Actual &3[&a"+plugin.version+"&3] &9-->&b Version Nueva &3[&c"+plugin.latestversion+"&3]" );
			Utils.sendColorMessage(player, Utils.getPrefixPlayer() + " ");
			Utils.sendColorMessage(player, Utils.getPrefixPlayer() + " &cNo se ha podido encontrar ninguna version nueva o ya tienes la version mas reciente");
			Utils.sendColorMessage(player, Utils.getPrefixPlayer() + " &c!Verifica si estas conectado a internet!");
			Utils.sendColorMessage(player, Utils.getPrefixPlayer() + "&5 " + Utils.getLine());
			return;
		}
		
		if(temp.equalsIgnoreCase("en")) {
			Utils.sendColorMessage(player, Utils.getPrefixPlayer() + "&5 " + Utils.getLine());
			Utils.sendColorMessage(player, Utils.getPrefixPlayer() + " &bCurrent version &3[&a"+plugin.version+"&3] &9-->&b New version &3[&c"+plugin.latestversion+"&3]" );
			Utils.sendColorMessage(player, Utils.getPrefixPlayer() + " ");
			Utils.sendColorMessage(player, Utils.getPrefixPlayer() + " &cNo new version could be found or you already have the most recent version");
			Utils.sendColorMessage(player, Utils.getPrefixPlayer() + " &c!Check if you are connected to the internet!");
			Utils.sendColorMessage(player, Utils.getPrefixPlayer() + "&5 " + Utils.getLine());
			return;
		}
	}
}
