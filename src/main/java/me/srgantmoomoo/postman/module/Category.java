package me.srgantmoomoo.postman.module;

import com.lukflug.panelstudio.setting.ICategory;
import com.lukflug.panelstudio.setting.IClient;
import com.lukflug.panelstudio.setting.IModule;
import me.srgantmoomoo.postman.Main;

import java.util.Arrays;
import java.util.stream.Stream;

public enum Category implements ICategory {
    PLAYER("player"), RENDER("render"), PVP("pvp"), EXPLOITS("exploits"), MOVEMENT("movement"), HUD("hud"), CLIENT("client"), BOT("bot");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getDisplayName() {
        return name;
    }

    @Override
    public Stream<IModule> getModules() {
        return Main.INSTANCE.moduleManager.getModules().stream().map(module->module);
    }

    public static IClient getClient() {
        return new IClient() {
            @Override
            public Stream<ICategory> getCategories() {
                return Arrays.stream(Category.values());
            }
        };
    }

}
