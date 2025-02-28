<?php

use Discord\Discord;
use Discord\WebSockets\Event;
use Discord\Parts\Channel\Message;

global $commands;

$discord->on(Event::MESSAGE_CREATE, function (Message $message, Discord $discord) use ($commands) {
    $prefix = "!";

    if (strpos($message->content, $prefix) === 0) {
        $args = explode(" ", substr($message->content, strlen($prefix)));
        $commandName = strtolower($args[0]);

        if (isset($commands[$commandName])) {
            $commands[$commandName]($message, $discord);
        }
    }
});