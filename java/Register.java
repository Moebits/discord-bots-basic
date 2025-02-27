import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import java.io.File;
import java.util.ArrayList;
import structures.Command;
import structures.CommandInput;
import structures.Discord;

public class Register {
    public static void main(String[] args) throws InterruptedException {
        var dotenv = Dotenv.configure().load();
        var token = dotenv.get("TOKEN");

        var jda = JDABuilder.createDefault(token).build().awaitReady();
        var discord = new Discord(jda);
        
        registerCommands(jda, discord);
    }

    public static void registerCommands(JDA jda, Discord discord) {
        var commandFolder = new File("commands");
        var commandFiles = commandFolder.listFiles((_, name) -> name.endsWith(".java"));
        if (commandFiles == null) return;

        var commandUpdate = jda.updateCommands();
        var registeredCommands = new ArrayList<String>();

        for (File file : commandFiles) {
            try {
                var className = "commands." + file.getName().replace(".java", "");
                var cls = Class.forName(className);
                var command = (Command) cls.getConstructor(Discord.class, CommandInput.class).newInstance(discord, null);
                
                commandUpdate.addCommands(Commands.slash(command.info.name, command.info.description));
                registeredCommands.add(command.info.name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        commandUpdate.queue();
        System.out.println(String.format("Registered %d application (/) commands", registeredCommands.size()));
    }
}