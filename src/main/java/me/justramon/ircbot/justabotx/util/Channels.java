package me.justramon.ircbot.justabotx.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.pircbotx.PircBotX;

public class Channels
{
	private static File chanfile = new File("channels.txt");
	
	/**
	 * Let's the bot join all the channels in the channel file.
	 * 
	 * @param bot
	 * @throws IOException
	 */
	public static void joinAll(PircBotX bot) throws IOException
	{
		//Creating the file if it doesn't exist
		if(!chanfile.exists())
			chanfile.createNewFile();
		
		//Enumerating through the file to join all the channels in there.
		List<String> channels = FileUtils.readLines(chanfile);
		for(String line : channels)
		{
			System.out.println(line);
			bot.sendIRC().joinChannel(line);
		}
	}
}
