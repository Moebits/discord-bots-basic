package structures;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.entities.User;

public sealed interface CommandInput permits MessageInput, SlashCommandInput {
    RestAction<Message> reply(String message);
    User getUser();
}