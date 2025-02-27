def bot_command(bot)
  bot.command(:ping) do |event|
    event.message.reply! "Pong", mention_user: true
  end
end