package events;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.entities.User;
import structures.Discord;

public class Ready extends ListenerAdapter {
    Discord discord;

    public Ready(Discord discord) {
        this.discord = discord;
    }

    @Override
    public void onReady(ReadyEvent event) {
        User discord = event.getJDA().getSelfUser();
        System.out.printf("Logged in as %s!\n", discord.getName());
    }   
}
