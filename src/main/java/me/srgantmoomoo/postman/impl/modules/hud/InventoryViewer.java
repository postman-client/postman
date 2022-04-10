package me.srgantmoomoo.postman.impl.modules.hud;

import java.awt.Color;
import java.awt.Point; 

import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.Interface;
import com.lukflug.panelstudio.hud.HUDComponent;
import com.lukflug.panelstudio.theme.Theme;

import me.srgantmoomoo.Reference;
import me.srgantmoomoo.postman.backend.util.render.JColor;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.HudModule;
import me.srgantmoomoo.postman.framework.module.setting.settings.ColorSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.ModeSetting;
import me.srgantmoomoo.postman.impl.clickgui.back.ClickGui;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class InventoryViewer extends HudModule {
	public ColorSetting color = new ColorSetting("color", this, new JColor(Reference.POSTMAN_COLOR, 100)); 
	public ModeSetting size = new ModeSetting("size", this, "normal", "normal", "compact");
    
    public InventoryViewer() {
    	super("inventoryViewer","shows ur inventory on ur hud.", new Point(122, 2), Category.HUD);
    	this.addSettings(color, size);
    }
    
    @Override
    public void populate (Theme theme) {
    	this.component = new InventoryViewerComponent(theme);
    }

    private class InventoryViewerComponent extends HUDComponent {
		public InventoryViewerComponent (Theme theme) {
			super(getName(), theme.getPanelRenderer(), InventoryViewer.this.position);
		}
		
		@Override
		public void render (Context context) {
			super.render(context);

			Color bgcolor = new JColor(color.getValue());
			context.getInterface().fillRect(context.getRect(), bgcolor, bgcolor, bgcolor, bgcolor);
		
	        NonNullList<ItemStack> items = Minecraft.getMinecraft().player.inventory.mainInventory;

	        for (int itemsSize = items.size(), item = 9; item < itemsSize; ++item) {
	        	if(size.is("normal")) {
					int slotX = context.getPos().x + 1 + item % 9 * 18;
					int slotY = context.getPos().y + 1 + (item / 9 - 1) * 18;
					ClickGui.renderItem(items.get(item), new Point(slotX,slotY));
	        	}else {
	        		int slotX = context.getPos().x + 1 + item % 9 * 17;
		            int slotY = context.getPos().y + 1 + (item / 9 - 1) * 17;
					ClickGui.renderItem(items.get(item), new Point(slotX,slotY));
	        	}
	        }
		}

		@Override
		public int getWidth (Interface inter) {
			if(size.is("normal")) {
				return 162;
			}else {
				return 154;
			}
		}

		@Override
		public void getHeight (Context context) {
			if(size.is("normal")) {
				context.setHeight(54);
			}else {
				context.setHeight(52);
			}
		}
	}
}
