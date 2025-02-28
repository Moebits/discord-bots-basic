<?php

use Discord\Discord;

$discord->on("init", function (Discord $discord) {
    echo "Logged in as {$discord->user->username}!\n";
});