package events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import structures.Discord;
import structures.Command;
import structures.CommandInput;
import structures.MessageInput;

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

        String commandName = args[0].toLowerCase();

        try {
            Class<?> commandClass = discord.commands.get(commandName);
            if (commandClass != null) {
                MessageInput input = new MessageInput(message);
                Command command = (Command) commandClass.getDeclaredConstructor(Discord.class, CommandInput.class).newInstance(discord, input);
                command.run(args);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}