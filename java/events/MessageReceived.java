package events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.lang.reflect.Method;
import structures.Discord;

public class MessageReceived extends ListenerAdapter {
    Discord discord;

    public MessageReceived(Discord discord) {
        this.discord = discord;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        String content = message.getContentRaw().trim();
        String prefix = "!";
        if (!content.startsWith(prefix)) return;
        if (content.equals(prefix)) return;

        String[] args = content.substring(prefix.length()).split("\\s+");

        try {
            Class<?> commandClass = discord.commands.get(args[0].toLowerCase());
            if (commandClass != null) {
                Object commandInstance = commandClass.getDeclaredConstructor(Discord.class, Message.class).newInstance(discord, message);
                Method runMethod = commandClass.getMethod("run", String[].class);
                runMethod.invoke(commandInstance, (Object) args);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}