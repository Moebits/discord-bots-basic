package events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import structures.Discord;
import structures.Command;
import structures.CommandInput;
import structures.MessageInput;

public class MessageReceived extends ListenerAdapter {
    protected Discord discord;

    public MessageReceived(Discord discord) {
        this.discord = discord;
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        var message = event.getMessage();
        var content = message.getContentRaw().trim();
        var prefix = "!";
        if (!content.startsWith(prefix)) return;
        if (content.equals(prefix)) return;

        var args = content.substring(prefix.length()).split("\\s+");
        var commandName = args[0].toLowerCase();

        try {
            var cls = discord.commands.get(commandName);
            if (cls != null) {
                var input = new MessageInput(message);
                var command = (Command) cls.getConstructor(Discord.class, CommandInput.class).newInstance(discord, input);
                command.run(args);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}