package events;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import structures.Discord;
import structures.Command;
import structures.CommandInput;
import structures.SlashCommandInput;

public class SlashCommandInteraction extends ListenerAdapter {
    protected Discord discord;

    public SlashCommandInteraction(Discord discord) {
        this.discord = discord;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        var interaction = event.getInteraction();

        String[] args = interaction.getOptions().stream().map(option -> option.getAsString()).toArray(String[]::new);
        String commandName = interaction.getName().toLowerCase();

        try {
            Class<?> cls = discord.commands.get(commandName);
            if (cls != null) {
                SlashCommandInput input = new SlashCommandInput(event);
                Command command = (Command) cls.getConstructor(Discord.class, CommandInput.class).newInstance(discord, input);
                command.run(args);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}