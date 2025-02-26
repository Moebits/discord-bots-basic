package commands;
import structures.Discord;
import structures.Command;
import structures.CommandInfo;
import structures.CommandInput;

public class Ping extends Command {
    public Ping(Discord discord, CommandInput input) {
        super(discord, input);
        this.info = new CommandInfo("ping", "Pong");
    }

    public void run(String[] args) {
        input.reply("Pong").queue();
    }
}