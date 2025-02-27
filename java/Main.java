import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import structures.Discord;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        var dotenv = Dotenv.configure().load();
        var token = dotenv.get("TOKEN");

        var jda = JDABuilder.createDefault(token)
            .enableIntents(GatewayIntent.MESSAGE_CONTENT)
            .build();

            var discord = new Discord(jda); 

        loadCommands(discord);

        for (ListenerAdapter listener : loadEvents(discord)) {
            jda.addEventListener(listener);
        }

        jda.awaitReady();
    }

    public static void loadCommands(Discord discord) {
        var commandFolder = new File("commands");
        var commandFiles = commandFolder.listFiles((_, name) -> name.endsWith(".java"));
        if (commandFiles == null) return;

        for (File file : commandFiles) {
            try {
                var className = "commands." + file.getName().replace(".java", "");
                var cls = Class.forName(className);
                var commandName = file.getName().replace(".java", "").toLowerCase();

                discord.commands.put(commandName, cls);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static List<ListenerAdapter> loadEvents(Discord discord) {
        var eventListeners = new ArrayList<ListenerAdapter>();
        var eventFolder = new File("events");
        var eventFiles = eventFolder.listFiles((_, name) -> name.endsWith(".java"));
        if (eventFiles == null) return eventListeners;

        for (File file : eventFiles) {
            try {
                var className = "events." + file.getName().replace(".java", "");
                var cls = Class.forName(className);
                if (ListenerAdapter.class.isAssignableFrom(cls)) {
                    eventListeners.add((ListenerAdapter) cls.getConstructor(Discord.class).newInstance(discord));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return eventListeners;
    }
}