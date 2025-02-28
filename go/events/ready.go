package events

import "fmt"
import "github.com/bwmarrin/discordgo"

func Ready(session *discordgo.Session, ready *discordgo.Ready) {
	fmt.Printf("Logged in as %s!\n", session.State.User.Username)
}
