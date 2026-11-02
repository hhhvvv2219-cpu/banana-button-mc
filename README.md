# Banana Button — Minecraft 1.20.1 Fabric

Adds a **BANANA button**. Press it and bananas fly out.

## Features

- Press **B** in game to open the banana screen
- Click **БАНАН** — 10 bananas fly out in a spread cone
- `/banana` command does the same
- Bananas use snowball physics, burst into particles on impact
- Rendering is pure geometry: no textures, no models

## Requirements

- Minecraft **1.20.1**
- Fabric Loader **0.15.0+**
- Fabric API **0.92.x** for 1.20.1
- Java **17**

## Build

The Gradle wrapper JAR is not committed (binary), so generate it once:

```bash
gradle wrapper --gradle-version 8.5
./gradlew build
```

Or open the folder in IntelliJ IDEA, let Gradle sync, then run `build`.

The mod JAR appears in `build/libs/banana-button-1.0.0.jar`.

Dev run: `./gradlew runClient`

## Install

1. Put the built JAR (not `-sources`) into `.minecraft/mods/`
2. Install Fabric Loader 1.20.1 and Fabric API
3. Press **B** in game

## How it works

1. Client presses the button in `BananaScreen`
2. `ClientPlayNetworking` sends packet `bananamod:throw_banana`
3. Server receiver calls `BananaEntity.volley(player, 1.5f)`
4. 10 entities spawn with random spread
5. On collision the banana discards itself and emits particles

All spawning happens server-side, so the mod works in singleplayer and on a dedicated server.

## Structure

```
src/main/java/dev/bananamod/
  BananaMod.java             common entrypoint, entity registration
  BananaNetworking.java      packet channel
  entity/BananaEntity.java   projectile + volley logic
  command/BananaCommand.java /banana command
  client/BananaModClient.java  renderer + keybind
  client/BananaScreen.java     the button screen
  client/BananaEntityRenderer.java  geometry renderer
```

## License

MIT
