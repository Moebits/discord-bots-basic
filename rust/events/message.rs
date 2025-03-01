use serenity::async_trait;
use serenity::model::channel::Message;
use serenity::prelude::*;
use std::collections::HashMap;
use std::future::Future;
use std::sync::Arc;
use std::pin::Pin;
use crate::commands;

type CommandFnType = Box<dyn Fn(Arc<Context>, Arc<Message>) -> Pin<Box<dyn Future<Output = ()> + Send>> + Send + Sync>;

pub struct MessageEvent {
    pub commands: HashMap<&'static str, CommandFnType>,
}

impl MessageEvent {
    pub fn new() -> Self {
        Self {
            commands: Self::load_commands(),
        }
    }

    pub fn load_commands() -> HashMap<&'static str, CommandFnType> {
        let mut commands: HashMap<&'static str, CommandFnType> = HashMap::new();

        commands.insert("ping", Box::new(|ctx, msg| Box::pin(commands::ping::Ping::execute(ctx, msg))));

        return commands;
    }
}

#[async_trait]
impl EventHandler for MessageEvent {
    async fn message(&self, ctx: Context, msg: Message) {
        let prefix = "!";
        
        if !msg.content.trim().starts_with(prefix) {
            return;
        }
        if msg.content.trim() == prefix {
            return;
        }
        
        let args: Vec<&str> = msg.content.trim()[prefix.len()..].split_whitespace().collect();
        let command_name: &str = &args[0].to_lowercase();

        if let Some(command) = self.commands.get(&command_name) {
            command(Arc::new(ctx), Arc::new(msg)).await;
        }
    }
}