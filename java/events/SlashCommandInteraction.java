package events;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import structures.Discord;
import structures.Command;
import structures.CommandInput;
import structures.SlashCommandInput;

public class SlashCommandInteraction extends ListenerAdapter {
    Discord discord;

    public SlashCommandInteraction(Discord discord) {
        this.discord = discord;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        var interaction = event.getInteraction();

        String commandName = interaction.getName().toLowerCase();

        String[] args = interaction.getOptions().stream().map(option -> option.getAsString()).toArray(String[]::new);

        try {
            Class<?> commandClass = discord.commands.get(commandName);
            if (commandClass != null) {
                SlashCommandInput input = new SlashCommandInput(event);
                Command command = (Command) commandClass.getDeclaredConstructor(Discord.class, CommandInput.class).newInstance(discord, input);
                command.run(args);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}