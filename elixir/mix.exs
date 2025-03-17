defmodule MyApp.MixProject do
  use Mix.Project

  def project do [
      app: :my_app,
      version: "0.0.1",
      elixir: "~> 1.18",
      start_permanent: Mix.env() == :prod,
      deps: deps()
  ]
  end

  defp deps do
    [{:nostrum, "~> 0.10.4"}]
  end
end
