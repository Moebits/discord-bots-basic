// swift-tools-version: 6.0
import PackageDescription

let package = Package(
    name: "DiscordBot",
    platforms: [.macOS(.v13)],
    dependencies: [
        .package(url: "https://github.com/DiscordBM/DiscordBM", from: "1.13.0"),
        .package(url: "https://github.com/thebarndog/swift-dotenv", from: "2.1.0")
    ],
    targets: [
        .executableTarget(
            name: "DiscordBot",
            dependencies: [
                .product(name: "DiscordBM", package: "DiscordBM"),
                .product(name: "SwiftDotenv", package: "swift-dotenv")
            ],
            path: ".")
    ]
)