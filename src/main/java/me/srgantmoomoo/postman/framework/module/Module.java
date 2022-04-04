package me.srgantmoomoo.postman.framework.module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.lukflug.panelstudio.settings.Toggleable;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.backend.event.events.RenderEvent;
import me.srgantmoomoo.postman.framework.module.setting.Setting;
import me.srgantmoomoo.postman.framework.module.setting.settings.KeybindSetting;
import net.minecraft.client.Minecraft;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 */

public abstract class Module implements Toggleable {
	protected static final Minecraft mc = Minecraft.getMinecraft();
	
	public final String name, description;
	public KeybindSetting keyCode = new KeybindSetting(0);
	public Category category;
	public boolean toggled = false, expanded;
	public int index;
	public List<Setting> settings = new ArrayList<Setting>();
	
	public Module(String name, String description, int key, Category category) {
		this.name = name;
		this.description = description;
		this.keyCode.code = key;
		this.addSettings(keyCode);
		this.category = category;
		this.toggled = false;
	}
	
	public void addSettings(Setting... settings) {
		this.settings.addAll(Arrays.asList(settings));
		this.settings.sort(Comparator.comparingInt(setting -> setting == this.keyCode ? 1 : 0));
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public int getKey() {
		return this.keyCode.code;
	}
	
	public void setKey(int key) {
		this.keyCode.code = key;
		
		if(Main.INSTANCE.saveLoad != null) {
			Main.INSTANCE.saveLoad.save();
		}
	} 
	
	public String getName() {
		return this.name;
	}
	
	public Category getCategory() {
		return this.category;
	}
	
	public final boolean isOn() {
		return this.toggled;
	}
	
	public void toggle() {
		toggled = !toggled;
		if(toggled) {
			enable();
		} else {
			disable();
		}
		
		if(Main.INSTANCE.saveLoad != null) {
			Main.INSTANCE.saveLoad.save();
		}
	}
	
	public boolean isToggled() {
		return toggled;
	}
	
	public void setToggled(boolean toggled) {
		this.toggled = toggled;
		
		if(toggled) {
			Main.EVENT_BUS.subscribe(this);
		} else {
			Main.EVENT_BUS.unsubscribe(this);
		}
		
		if(Main.INSTANCE.saveLoad != null) {
			Main.INSTANCE.saveLoad.save();
		}
	}
	
	protected void enable() {
		this.onEnable();
		this.setToggled(true);
	}
	
	protected void disable() {
		this.onDisable();
		this.setToggled(false);
	}
	
	protected void onEnable() {}
	
	protected void onDisable() {}
	
	public void onWorldRender(RenderEvent event) {}
	
	public void onUpdate() {}
	
	public void onRender() {}
}
