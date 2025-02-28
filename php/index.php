<?php

require_once __DIR__ . "/vendor/autoload.php";

use Dotenv\Dotenv;
use Discord\Discord;
use Discord\WebSockets\Intents;
use Discord\WebSockets\Event;
use Discord\Parts\Channel\Message;

$dotenv = Dotenv::createImmutable(__DIR__);
$dotenv->load();
$token = $_ENV["TOKEN"];

$discord = new Discord([
    "token" => $token,
    "intents" => Intents::getDefaultIntents() | Intents::MESSAGE_CONTENT
]);

$commands = [];

$commandFiles = glob(__DIR__ . "/commands/*.php");

foreach ($commandFiles as $commandFile) {
    require_once $commandFile;
}

$discord->commands = $commands;

$eventFiles = glob(__DIR__ . "/events/*.php");

foreach ($eventFiles as $eventFile) {
    require_once $eventFile;
}

$discord->run();