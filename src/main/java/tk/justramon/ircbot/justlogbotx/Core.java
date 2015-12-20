package tk.justramon.ircbot.justlogbotx;

import java.io.File;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import tk.justramon.ircbot.justlogbotx.NotImportant.Passwords;
import tk.justramon.ircbot.justlogbotx.cmds.CommandSwitch;
import tk.justramon.ircbot.justlogbotx.cmds.QuitAndUpdate;

public class Core extends ListenerAdapter<PircBotX>
{
	public static PircBotX bot;
	public void onMessage(MessageEvent<PircBotX> event) throws Exception
	{
		String[] args = event.getMessage().split(" ");

		if(args[0].startsWith("?"))
			CommandSwitch.exe(event, args);

		Log.exe(event, args);
	}
	
	public static void main(String[] args) throws Exception
	{
		File oldJar = new File("JustLogBotX" + QuitAndUpdate.getJarInt(true) + ".jar");
		Thread.sleep(3000);
		oldJar.delete();
		if(args.length > 0 && args[0].equals("-wip"))
		{
			Configuration<PircBotX> configuration = new Configuration.Builder<PircBotX>()
					.setName(args.length > 1 ? args[1] : "JustLogBotDev")
					.setLogin("JustLogBotX")
					.setRealName("Just LogBot X.")
					.setAutoReconnect(true)
					.setServerHostname("irc.esper.net")
					.addAutoJoinChannel("#bl4ckb0tTest")
					.setAutoNickChange(true)
					.setCapEnabled(true)
					.addListener(new Core())
					.buildConfiguration();
			bot = new PircBotX(configuration);
			bot.startBot();
		}
		else
		{
			
		//Configure what we want our bot to do
		Configuration<PircBotX> configuration = new Configuration.Builder<PircBotX>()
				.setName("JustLogBotX")
				.setNickservPassword(Passwords.NICKSERV.getPassword())
				.setLogin("JustLogBotX")
				.setRealName("Just LogBot X.")
				.setAutoReconnect(true)
				.setServerHostname("irc.esper.net")
				.addAutoJoinChannel("#JustRamon")
				.addAutoJoinChannel("#bl4ckscor3")
				.addAutoJoinChannel("#shadowchild")
				.setAutoNickChange(true)
				.setCapEnabled(true)
				.addListener(new Core())
				.buildConfiguration();
		bot = new PircBotX(configuration);
		bot.startBot();
		}
	}
}
