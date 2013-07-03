package com.khushnish.cricketlive.utils;

public class Utils {
	public static String getPlayerShortName ( String playerName ) {
		String name = "";
		try {
			if ( playerName != null && !playerName.equalsIgnoreCase("") ) {
				name = playerName.substring(0, 1) + playerName.substring(playerName.indexOf(" "));
			} else {
				name = playerName;
			}
		} catch (Exception e) {
			name = playerName;
			e.printStackTrace();
		}
		return name;
	}
}
