use std::env;
use dotenv::dotenv;
use serenity::async_trait;
use serenity::model::channel::Message;
use serenity::model::gateway::Ready;
use serenity::prelude::*;

mod commands;
mod events;

struct Handler;

#[async_trait]
impl EventHandler for Handler {
    async fn message(&self, ctx: Context, msg: Message) {
        let event = events::message::MessageEvent::new();
        event.message(ctx.clone(), msg.clone()).await;
    }

    async fn ready(&self, ctx: Context, ready: Ready) {
        let event = events::ready::ReadyEvent;
        event.ready(ctx.clone(), ready.clone()).await;
    }
}

#[tokio::main]
async fn main() {
    dotenv().ok();
    let token = env::var("TOKEN").expect("No token");
    let intents = GatewayIntents::all();

    let mut client = Client::builder(&token, intents)
        .event_handler(Handler).await.expect("Client create error");

    client.start().await.expect("Client start error");
}