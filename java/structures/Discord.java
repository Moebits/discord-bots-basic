package structures;

import net.dv8tion.jda.api.JDA;
import java.util.HashMap;

public class Discord {
    public final JDA jda;
    public final HashMap<String, Class<?>> commands;

    public Discord(JDA jda) {
        this.jda = jda;
        this.commands = new HashMap<>();
    }
}