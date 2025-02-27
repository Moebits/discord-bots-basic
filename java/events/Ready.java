package events;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import structures.Discord;

public class Ready extends ListenerAdapter {
    protected Discord discord;

    public Ready(Discord discord) {
        this.discord = discord;
    }

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        var discord = event.getJDA().getSelfUser();
        System.out.printf("Logged in as %s!\n", discord.getName());
    }   
}
