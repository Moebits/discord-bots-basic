package structures;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.entities.User;

public final class MessageInput implements CommandInput {
    private final Message message;

    public MessageInput(Message message) {
        this.message = message;
    }

    @Override
    public RestAction<Message> reply(String content) {
        return message.reply(content);
    }

    @Override
    public User getUser() {
        return message.getAuthor();
    }
}