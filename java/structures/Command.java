package structures;

public abstract class Command {
    public Discord discord;
    public CommandInput input;
    public CommandInfo info;

    public Command(Discord discord, CommandInput input) {
        this.discord = discord;
        this.input = input;
    }

    public abstract void run(String[] args);
}