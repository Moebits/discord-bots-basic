<?php

use Discord\Discord;
use Discord\Parts\Channel\Message;

global $commands;

$commands["ping"] = function (Message $message, Discord $discord) {
    $message->reply("Pong");
};