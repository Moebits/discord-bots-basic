local env = require("redotenv").load()
local discordia = require("discordia")
local client = discordia.Client()

client:on("ready", function()
	print(string.format("Logged in as %s!", client.user.username))
end)

local token = env["TOKEN"]

client:run(string.format("Bot %s", token))