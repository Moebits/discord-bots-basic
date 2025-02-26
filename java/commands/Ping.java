package commands;

import net.dv8tion.jda.api.entities.Message;
import structures.Discord;

public class Ping {
    Discord discord;
    Message message;

    public Ping(Discord discord, Message message) {
        this.discord = discord;
        this.message = message;
    }

    public void run(String[] args) {
        message.reply("Pong!").queue();
    }
}