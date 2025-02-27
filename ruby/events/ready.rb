def bot_event(bot)
  bot.ready do
    puts "Logged in as #{bot.profile.name}!"
  end
end