require "dotenv/load"
require "discordrb"

bot = Discordrb::Commands::CommandBot.new token: ENV["TOKEN"], prefix: "!"

Dir.glob(File.join(__dir__, "events", "*.rb")).each do |file| 
  load file 
  bot_event bot
end

Dir.glob(File.join(__dir__, "commands", "*.rb")).each do |file|
  load file
  bot_command bot
end

bot.run