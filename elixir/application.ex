defmodule MyApp.Application do
  use Application

  def start(_type, _args) do
    children = [
      MyBot
    ]

    opts = [strategy: :one_for_one, name: MyApp.Supervisor]
    Supervisor.start_link(children, opts)
  end
end
