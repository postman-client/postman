<img src="https://user-images.githubusercontent.com/69589624/132962368-25885f65-740e-4955-9b31-4a1cb899b660.png" alt="postman logo" width="175"/>

# postman reborn public

[![GitHub all releases](https://img.shields.io/github/downloads/moomooooo/postman/total?color=79C1FF&style=flat-square)](https://github.com/moomooooo/postman/releases)
[![Lines of code](https://img.shields.io/tokei/lines/github/flufferyautism/postman-reborn?color=79C1FF&style=flat-square)](https://github.com//postman-reborn/tree/master/src/main/java/me/srgantmoomoo)
[![Discord](https://img.shields.io/discord/760964236779716648?color=79C1FF&label=discord&style=flat-square)](https://discord.gg/Jd8EmEuhb5)
[![GitHub](https://img.shields.io/github/license/moomooooo/postman?color=79C1FF&style=flat-square)](https://github.com/moomooooo/postman/blob/master/LICENSE)

Im sorry to everyone, but ill very rarely will update this repo in the future

**A continuation of where SrgantMooMoo left this client**
By the Postman Community

Time for shit coding, Code updates are far apart, but work is still being done, I have a life and cannot code 24/7, maybe over the summer 

***if you are not apart of the discord and are not aware, the main postman client is currently out of development. this is due to some personal things i have to take care of in life. i really do plan on returning if i find interest in it again, there are so many things i left unfinished in this client. but for now i just want to thank everyone who supported me, everyone who has helped me out directly and indirectly. i met so many awesome ppl with this project. thank u, i love you all :))***

postman is a client that runs off Minecraft Forge 1.12.2, it is intended for use on servers which allow client-side modification. postman takes inpiration from many previous clients like osiris, kami, gamesense, and others. this client uses ZeroMemes's Alpine for an event system, lukflugs PanelStudio for the clickGui library, and Forge's 1.12.2 mod api. i intend for this project to be community driven and as open as possible, open access, open development, and open sourced. 
<br>
<br>

This is where Postman Reborn steps in, This client is a fork of the oringal postman, mainly intended to add features, bug fixes and more to the client
Thanks for keeping an eye

this client is on Minecraft 1.12.2, Forge version 1.12.2-14.23.5.2768, Alpine 1.5, PanelStudio 0.1.8, and is coded in 100% java.

check out the offical postman website - https://techale.github.io/postman-website/. <br />
*postman archive* - this basically just holds all the old versions of postman, https://github.com/moomooooo/postman-archive. <br />

thank you,

-SrgantMooMoo

![image](https://user-images.githubusercontent.com/69589624/129431288-d6a1c2db-7a68-488d-b885-901b86ca02f7.png)

# instructions
**download**
1. postman uses forge in order to run so, if you havent already, go to http://files.minecraftforge.net/maven/net/minecraftforge/forge/index_1.12.2.html and download the latest or recommended version of forge 1.12.2. <br />
2. at the top of this readme, click on the downloads button and select the latest version, or your preferred version. <br />
3. click assets at the bottom of the release, then download the .jar file. <br />
4. if you havent ran forge yet, do so, if you have already ran forge at least once, type %appdata% in ur windows search, enter, and navigate to .minecraft -> mods (if there is no mods folder than you can add it yourself) -> 1.12.2, "1.12.2" may not exist either, so you can create it yourself if needed. this process may vary depending on your device, so you may need to look it up. <br />
5. drag the postman .jar file that you downloaded earlier into the 1.12.2 folder. <br />
- if you want to take advantage of postman's baritone integration, you can download the [baritone api](https://github.com/cabaletta/baritone/releases/download/v1.2.14/baritone-api-forge-1.2.14.jar) and drag it into your mods folder along with postman.

**use**
- prefix is "," (comma), this is customizable too.
- the inital keybind for the clickgui is rcontrol.
- once your clickgui is opened you will see a bunch of categories, you can drag these around and right click them to open them.
- each category has different modules you may enable by left clicking and right clicking to open up their settings.
- the settings consist of booleans (enable or disable), numbers (sliders to change the value of something), and modes (switches modes). 
- you can rebind each module in the settings by clicking keybind (at the bottom of each modules settings), and clicking the button you want to bind the module to.
- esc to close the clickgui 0_0.

**building**
1. gradlew setupDecompWorkspace <br />
2. gradlew eclipse (for eclipse) or gradlew genIntellijRuns (for intellij) <br />
(./gradlew for linux and mac users) <br />
3. gradlew build <br />

# credits
**really helpful people**
- lukflug, being cool person. making panelstudio. helping me with panelstudio.
- techale, adding multithreading to the main class. making postman website. helping me a lot :).
- RECIOR, for postman bot, and being an extremly based individual. <br>
- cattyngmd, making pr's for a variety of fixes and modules. <br>
- SrgantMooMoo, for making the oringal client <br>

**contributors** <br>
- mwahaha9, fixing the spelling in an onDisable method in autoGap.
- jacobtread, giving proper credits to the MinnDevelopment java discord rpc.

**base stuff**
- mod api - *Forge* http://files.minecraftforge.net.
- event system - *ZeroMemes, Alpine* https://github.com/ZeroMemes/Alpine.
- discord rpc - *club.minnced, java discord rpc* https://github.com/MinnDevelopment/java-discord-rpc.
- clickGui library - *lukflug, PanelStudio* https://github.com/lukflug/PanelStudio/tree/main.
- mixins - *SpongeForge* https://www.spongepowered.org/downloads/spongeforge/stable/1.12.2.
- font renderer - *086, KAMI* some stuff was rewritten, so it's not exact, https://github.com/zeroeightysix/KAMI.

**bassically everything that has been skidded, hopefully one day this is pointless :D. whenever i have the time, i try to work on unskidding**
- outline esp utils - *superblaubeere27* https://www.youtube.com/channel/UCtRhisaTkICo72ZI8Z2yWNg.
- surround & holeTp - *Hoosier's Gamesense* https://github.com/IUDevman/gamesense-client. 0_0
- 2dEsp - most of the code is from *finz0's Osiris* https://github.com/epicfacethe3rd/Osiris.

... for more depth, the necessary modules are credited. if anyone has an issue with crediting please lmk. Fluffery#6215 or ping on the main postman discord
