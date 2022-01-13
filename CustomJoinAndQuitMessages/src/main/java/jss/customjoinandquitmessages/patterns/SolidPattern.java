package jss.customjoinandquitmessages.patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jss.customjoinandquitmessages.utils.IridiumColorAPI;

public class SolidPattern implements IPattern {

	Pattern pattern = Pattern.compile("<SOLID:([0-9A-Fa-f]{6})>");

	public String process(String string) {
		Matcher matcher = pattern.matcher(string);
		while (matcher.find()) {
			String color = matcher.group(1);
			string = string.replace(matcher.group(), IridiumColorAPI.getColor(color) + "");
		}
		return string;
	}

}
