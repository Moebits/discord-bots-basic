import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import structures.Command;
import structures.CommandInput;
import structures.Discord;

public class Register {
    public static void main(String[] args) throws InterruptedException {
        Dotenv dotenv = Dotenv.configure().load();
        String token = dotenv.get("TOKEN");

        JDA jda = JDABuilder.createDefault(token).build().awaitReady();
        Discord discord = new Discord(jda);
        
        registerCommands(jda, discord);
    }

    public static void registerCommands(JDA jda, Discord discord) {
        File commandFolder = new File("commands");
        File[] commandFiles = commandFolder.listFiles((_, name) -> name.endsWith(".java"));
        if (commandFiles == null) return;

        CommandListUpdateAction commandUpdate = jda.updateCommands();
        List<String> registeredCommands = new ArrayList<>();

        for (File file : commandFiles) {
            try {
                String className = "commands." + file.getName().replace(".java", "");
                Class<?> cls = Class.forName(className);
                Command command = (Command) cls.getDeclaredConstructor(Discord.class, CommandInput.class).newInstance(discord, null);
                
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