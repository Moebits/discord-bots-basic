package events

import "strings"
import "github.com/bwmarrin/discordgo"
import "discordbot/commands"

type CommandHandler func(session *discordgo.Session, message *discordgo.MessageCreate)

var Commands = map[string]CommandHandler{
	"ping": commands.Ping,
}

func MessageCreate(session *discordgo.Session, message *discordgo.MessageCreate) {
	prefix := "!"

	if !strings.HasPrefix(message.Content, prefix) || message.Content == prefix {
		return
	}

	args := strings.Fields(strings.TrimPrefix(message.Content, prefix))
	commandName := strings.ToLower(args[0])

	command, exists := Commands[commandName]
	
	if exists {
		command(session, message)
	}
}