defmodule MyBot do
  use Nostrum.Consumer

  def start_link(_args) do
    Nostrum.Consumer.start_link(__MODULE__)
  end

  def handle_event({:READY, data}) do
    IO.puts("Bot is ready! Logged in as: #{data.user.username}")
    {:ok}
  end

  def handle_event(_event), do: {:ok}
end
