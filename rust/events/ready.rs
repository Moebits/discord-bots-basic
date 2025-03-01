use serenity::async_trait;
use serenity::model::gateway::Ready;
use serenity::prelude::*;

pub struct ReadyEvent;

#[async_trait]
impl EventHandler for ReadyEvent {
    async fn ready(&self, _: Context, ready: Ready) {
        println!("Logged in as {}!", ready.user.name);
    }
}
