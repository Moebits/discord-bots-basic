package structures;

import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

public final class SlashCommandInput implements CommandInput {
    public final CommandInteraction interaction;

    public SlashCommandInput(CommandInteraction interaction) {
        this.interaction = interaction;
    }

    @Override
    public RestAction<Message> reply(String content) {
        return interaction.reply(content).complete().retrieveOriginal();
    }

    @Override
    public User getUser() {
        return interaction.getUser();
    }
}