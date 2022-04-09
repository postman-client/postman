package me.srgantmoomoo.postman.impl.modules.pvp;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import me.srgantmoomoo.Main;
import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.framework.module.setting.settings.NumberSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;

public class KillAura extends Module {
	public NumberSetting range = new NumberSetting("range", this, 4, 1, 6, 0.5);
	public BooleanSetting targetFriends = new BooleanSetting("targetFriends", this, false);
	public BooleanSetting switchA = new BooleanSetting("switch", this, false);
	public BooleanSetting swordOnly = new BooleanSetting("swordOnly", this, false);
	public BooleanSetting players = new BooleanSetting("players", this, true);
	public BooleanSetting passives = new BooleanSetting("passives", this, false);
	public BooleanSetting hostiles = new BooleanSetting("hostiles", this, false);
	
	public KillAura() {
		super ("killAura", "automatically hits anything near u.", Keyboard.KEY_NONE, Category.PVP);
		this.addSettings(range, targetFriends, switchA, swordOnly, players, passives, hostiles);
	}

	@Override
	public void onUpdate() {
		if (mc.player == null || mc.player.isDead) return;
		List<Entity> targets = mc.world.loadedEntityList.stream()
				.filter(entity -> entity != mc.player)
				.filter(entity -> mc.player.getDistance(entity) <= range.getValue())
				.filter(entity -> !entity.isDead)
				.filter(entity -> attackCheck(entity))
				.sorted(Comparator.comparing(s -> mc.player.getDistance(s)))
				.collect(Collectors.toList());

		targets.forEach(target -> {
			attack(target);
		});
	}

	public void attack(Entity e) {
		if (mc.player.getCooledAttackStrength(0) >= 1){
			mc.playerController.attackEntity(mc.player, e);
			mc.player.swingArm(EnumHand.MAIN_HAND);
		}
	}
	
	private boolean attackCheck(Entity entity) {
		if (players.isEnabled() && entity instanceof EntityPlayer) {
			if(!targetFriends.isEnabled() && !Main.INSTANCE.friendManager.isFriend(entity.getName())) {
				if (((EntityPlayer) entity).getHealth() > 0) { 
					return true;
				}
			}
		}

		if (passives.isEnabled() && entity instanceof EntityAnimal)
			return !(entity instanceof EntityTameable);
		return hostiles.isEnabled() && entity instanceof EntityMob;
	}
}