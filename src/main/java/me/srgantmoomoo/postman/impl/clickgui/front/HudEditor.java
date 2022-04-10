package me.srgantmoomoo.postman.impl.clickgui.front;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.BooleanSetting;
import net.minecraft.util.ResourceLocation;

public class HudEditor extends Module {
	public BooleanSetting exitToClickGui = new BooleanSetting("exitToClickGui", this, true);
	
	private static final ResourceLocation shader = new ResourceLocation("minecraft", "shaders/post/blur" + ".json");
	
	public HudEditor() {
		super("hudEditor", "descrp", Keyboard.KEY_NONE, Category.HUD);
		this.addSettings(exitToClickGui);
	}
	
	@Override
	public void onEnable() {
		Main.INSTANCE.clickGui.enterHUDEditor();
		if(ClickGuiModule.INSTANCE.blur.isEnabled()) 
			mc.entityRenderer.loadShader(shader);
	}
	
	@Override
	public void onUpdate() {
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			if(exitToClickGui.isEnabled()) {
				this.disable(); 
				Main.INSTANCE.clickGui.enterGUI();
			} else {
				this.disable();
			}
		}
	}
	
	@Override
	public void onDisable() {
		mc.entityRenderer.getShaderGroup().deleteShaderGroup();
	}
}
