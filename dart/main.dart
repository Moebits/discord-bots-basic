import "package:nyxx/nyxx.dart";
import "package:dotenv/dotenv.dart";

void main() async {
    var env = DotEnv(includePlatformEnvironment: true)..load();
    var token = env["TOKEN"];
    final client = await Nyxx.connectGateway(token!, GatewayIntents.all,
    options: GatewayClientOptions(plugins: [logging, cliIntegration]));

    client.onReady.listen((event) {
        print("Logged in as ${event.user.username}!");
    });
}