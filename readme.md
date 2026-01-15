# Hi :)

This is MyWaifu Kotlin Multi Platform project.

The aim of this project is to hunt for waifus.

That's all.

Apart from getting big in size (caching), this project will not get
big at anything else, at all.

The code is aimed to be reusable.

I hope that this project is useful for anyone out there. Amen.

I am sorry for the lack of comments inside the code.

If you wish me to support specific waifu (or other fun) APIs, you
can contact me for a request.

I cannot guarantee that I will support them, but I will seriously
consider your suggestion.

Thank you, and stay healthy.

# Building the project

## Requirements

1. Basic knowledge of using terminal / command line processor. Ideally,
   you should have known how to navigate and change the directory 
   location (`cd` command).
2. Java Development Kit (JDK) version 17 or newer. I am using
   [Adoptium's distribution, Temurin](
   https://adoptium.net/temurin/releases?version=17&os=any&arch=any
   ). Ideally, when you type `java -version` from the terminal / command
   line processor, it should display something like this:
   ```
   openjdk 17.0.17 2025-10-21
   OpenJDK Runtime Environment Temurin-17.0.17+10 (build 17.0.17+10)
   OpenJDK 64-Bit Server VM Temurin-17.0.17+10 (build 17.0.17+10, mixed mode, sharing)
   ```
3. Some mobile data (around 2 GBs) or WiFi. This is for initial download
   only. Or, if you just want to try it, visit the GitHub page link at the
   right side of this page.

## Steps

1. Navigate to the project root directory where this `readme.md`
   file is approachable from the command line.
2. Build according to your platform target:
   - Windows executable (EXE) file:
     - From the terminal/command line processor:
       ```shell
       # Powershell
       ./gradlew :composeApp:createDistributable
       # or command prompt (CMD)
       gradlew :composeApp:createDistributable
       ```
     - Final executable file could be found in:
       `<root_folder>composeApp\build\compose\binaries\main\app\heaven.from.mywaifump\heaven.from.mywaifump.exe`
   - Android application package (APK) file:
     - From the terminal/command line processor:
       ```shell
       # Powershell
       ./gradlew :composeApp:assembleRelease
       # or command prompt (CMD)
       gradlew :composeApp:assembleRelease
     - Final APK file could be found in:
       `<root_folder>\composeApp\build\outputs\apk\release\composeApp-release.apk`
   - Web assembly JS runnable HTML distribution file:
     - From the terminal/command line processor:
       ```shell
       # Powershell
       ./gradlew :composeApp:wasmJsBrowserDistribution
       # or command prompt (CMD)
       gradlew :composeApp:wasmJsBrowserDistribution
     - Final runnable HTML file could be found in:
       `<root_folder>\composeApp\build\dist\wasmJs\productionExecutable\index.html`
     - The runnable HTML file *cannot* directly executed inside a browser
       due to cross-origin limitation (web browser fundamental safety).
       Instead, you can provide this file from your HTTP server, e.g.:
       - Navigate to the HTML produced before in terminal/command line
         processor. Then, execute this command:
         ```shell
         python -m http.server 8000
         ```
       - Then goto http://localhost:8000 in your web browser.
3. Enjoy your waifu. :)
