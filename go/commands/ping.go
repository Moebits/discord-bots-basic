package commands

import "github.com/bwmarrin/discordgo"

func Ping(session *discordgo.Session, message *discordgo.MessageCreate) {
	session.ChannelMessageSendReply(message.ChannelID, "Pong", message.Reference())
}