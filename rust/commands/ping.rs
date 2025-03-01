use serenity::model::channel::Message;
use serenity::prelude::*;
use std::sync::Arc;

pub struct Ping;

impl Ping {
    pub async fn execute(ctx: Arc<Context>, msg: Arc<Message>) {
        if let Err(err) = msg.reply_ping(&ctx.http, "Pong").await {
            println!("{:?}", err);
        }
    }
}