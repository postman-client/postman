package me.srgantmoomoo.postman.impl.clickgui.front;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.Reference;
import me.srgantmoomoo.postman.backend.util.render.JColor;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.ColorSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.NumberSetting;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class ClickGuiModule extends Module {
	public static ClickGuiModule INSTANCE;
	
	public ModeSetting theme = new ModeSetting("theme", this, "new", "new", "old");
	public BooleanSetting blur = new BooleanSetting("blur", this, true);
	public NumberSetting animationSpeed = new NumberSetting("animation", this, 150, 0, 1000, 50);
	public NumberSetting scrolls = new NumberSetting("scrollSpeed", this, 10, 0, 100, 1);
	public ModeSetting scrollMode = new ModeSetting("scroll", this, "container", "container", "screen");
	public ModeSetting description = new ModeSetting("description", this, "mouse", "mouse", "fixed");
	public ColorSetting enabledColor = new ColorSetting("enabledColor", this, new JColor(Reference.POSTMAN_COLOR, 255)); //(0, 157, 255, 255));
	public ColorSetting backgroundColor = new ColorSetting("bgColor", this, new JColor(0, 0, 0, 200)); //(0, 121, 194, 255));
	public ColorSetting settingBackgroundColor = new ColorSetting("settinBgColor", this, new JColor(0, 0, 0, 255));
	public ColorSetting outlineColor = new ColorSetting("settingsHighlight", this, new JColor(255, 255, 255, 255));
	public ColorSetting fontColor = new ColorSetting("categoryColor", this, new JColor(Reference.POSTMAN_COLOR, 255)); 
	public NumberSetting opacity = new NumberSetting("opacity", this, 255, 0, 255, 5);
	
	private static ResourceLocation shader = new ResourceLocation("minecraft", "shaders/post/blur" + ".json");
	private static ResourceLocation watermark = new ResourceLocation(Reference.MOD_ID, "textures/postman-logo-transparent.png");
	
	public ClickGuiModule() {
		super("clickGui", "click gui.", Keyboard.KEY_RSHIFT, Category.CLIENT);
		this.addSettings(blur, scrollMode, scrolls, description, animationSpeed, fontColor, enabledColor, backgroundColor, settingBackgroundColor, outlineColor);
		INSTANCE = this;
	}

	@Override
	public void onEnable() {
		Main.INSTANCE.clickGui.enterGUI();
		if(blur.isEnabled())
			mc.entityRenderer.loadShader(shader);
	}
	
	@Override
	public void onDisable() {
		if(mc.entityRenderer.getShaderGroup() != null)
			mc.entityRenderer.getShaderGroup().deleteShaderGroup();
	}

	@Override
	public void onUpdate() {
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			this.disable();
		}
		if(Main.INSTANCE.moduleManager.getModuleByName("hudEditor").isToggled()) {
			this.disable();
		}	
	}
	
	@Override
	public void onRender() {
		ScaledResolution sr = new ScaledResolution(mc);
		mc.renderEngine.bindTexture(watermark);
		Gui.drawScaledCustomSizeModalRect(0, sr.getScaledHeight() - 80, 0, 0, 80, 80, 80, 80, 80, 80);
	}
}
