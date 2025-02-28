package main

import "os"
import "os/signal"
import "syscall"
import "github.com/bwmarrin/discordgo"
import "github.com/joho/godotenv"
import "discordbot/events"

func main() {
	godotenv.Load()
	token := os.Getenv("TOKEN")

	discord, _ := discordgo.New("Bot " + token)

	registerEvents(discord)

	discord.Open()

	sc := make(chan os.Signal, 1)
	signal.Notify(sc, syscall.SIGINT, syscall.SIGTERM, os.Interrupt)
	<-sc
	discord.Close()
}

func registerEvents(discord *discordgo.Session) {
	discord.AddHandler(events.Ready)
	discord.AddHandler(events.MessageCreate)
}