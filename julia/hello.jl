using Pkg
Pkg.activate(".")
Pkg.instantiate()

using DotEnv
DotEnv.load!()

using Discord

c = Client(ENV["TOKEN"])

function handler(c::Client, e::MessageCreate)
    println("Received message: $(e.message.content)")
end

add_handler!(c, MessageCreate, handler)
open(c)
wait(c)