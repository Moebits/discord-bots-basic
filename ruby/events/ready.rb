# typed: true
extend T::Sig

sig {params(bot: Discordrb::Commands::CommandBot).void}
def bot_event(bot)
  bot.ready do
    puts "Logged in as #{bot.profile.name}!"
  end
end