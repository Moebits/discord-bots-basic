use serenity::async_trait;
use serenity::model::channel::Message;
use serenity::prelude::*;
use std::collections::HashMap;
use std::future::Future;
use std::sync::Arc;
use std::pin::Pin;
use crate::commands;

pub struct MessageEvent {
    pub commands: HashMap<String, Box<dyn Fn(Arc<Context>, Arc<Message>) -> Pin<Box<dyn Future<Output = ()> + Send + 'static>> + Send + Sync>>,
}

impl MessageEvent {
    pub fn new() -> Self {
        Self {
            commands: Self::load_commands(),
        }
    }

    pub fn load_commands() -> HashMap<String, Box<dyn Fn(Arc<Context>, Arc<Message>) -> Pin<Box<dyn Future<Output = ()> + Send + 'static>> + Send + Sync>> {
        let mut commands: HashMap<String, Box<dyn Fn(Arc<Context>, Arc<Message>) -> Pin<Box<dyn Future<Output = ()> + Send + 'static>> + Send + Sync>> = HashMap::new();

        commands.insert("ping".to_string(), Box::new(|ctx, msg| Box::pin(commands::ping::Ping::execute(ctx, msg))));

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
        let command_name = args[0].to_lowercase();

        if let Some(command) = self.commands.get(&command_name) {
            command(Arc::new(ctx), Arc::new(msg)).await;
        }
    }
}
