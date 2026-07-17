/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2026 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.debug.DebugMenu;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.CheckBox;
import com.shatteredpixel.shatteredpixeldungeon.ui.CurrencyIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;

public class WndDebugCheats extends Window {

	private static final int WIDTH_P = 120;
	private static final int WIDTH_L = 144;

	private static final int MARGIN = 2;
	private static final int BTN_HEIGHT = 16;

	public WndDebugCheats() {
		super();

		int width = PixelScene.landscape() ? WIDTH_L : WIDTH_P;

		RenderedTextBlock title = PixelScene.renderTextBlock("Cheat Menu", 9);
		title.hardlight(TITLE_COLOR);
		title.setPos(MARGIN, MARGIN);
		title.maxWidth(width - MARGIN * 2);
		add(title);

		float pos = title.bottom() + 2 * MARGIN;

		// God Mode
		CheckBox cbGodMode = new CheckBox("God Mode") {
			@Override
			protected void onClick() {
				super.onClick();
				DebugMenu.godMode = checked();
				if (DebugMenu.godMode && Dungeon.hero != null) {
					Dungeon.hero.HP = Dungeon.hero.HT;
				}
			}
		};
		cbGodMode.checked(DebugMenu.godMode);
		cbGodMode.setRect(0, pos, width, BTN_HEIGHT);
		add(cbGodMode);
		pos += BTN_HEIGHT + MARGIN;

		// One Hit Kill
		CheckBox cbOneHit = new CheckBox("One Hit Kill") {
			@Override
			protected void onClick() {
				super.onClick();
				DebugMenu.oneHitKill = checked();
			}
		};
		cbOneHit.checked(DebugMenu.oneHitKill);
		cbOneHit.setRect(0, pos, width, BTN_HEIGHT);
		add(cbOneHit);
		pos += BTN_HEIGHT + MARGIN;

		// Infinite Speed
		CheckBox cbInfiniteSpeed = new CheckBox("Infinite Speed") {
			@Override
			protected void onClick() {
				super.onClick();
				DebugMenu.infiniteSpeed = checked();
			}
		};
		cbInfiniteSpeed.checked(DebugMenu.infiniteSpeed);
		cbInfiniteSpeed.setRect(0, pos, width, BTN_HEIGHT);
		add(cbInfiniteSpeed);
		pos += BTN_HEIGHT + MARGIN;

		// Reveal Map
		CheckBox cbRevealMap = new CheckBox("Reveal Map") {
			@Override
			protected void onClick() {
				super.onClick();
				DebugMenu.revealMap = checked();
				if (DebugMenu.revealMap && Dungeon.level != null) {
					for (int i = 0; i < Dungeon.level.length(); i++) {
						Dungeon.level.mapped[i] = true;
						Dungeon.level.visited[i] = true;
					}
					Dungeon.observe();
					GameScene.updateFog();
				}
			}
		};
		cbRevealMap.checked(DebugMenu.revealMap);
		cbRevealMap.setRect(0, pos, width, BTN_HEIGHT);
		add(cbRevealMap);
		pos += BTN_HEIGHT + MARGIN;

		// Infinite Gold
		CheckBox cbInfiniteGold = new CheckBox("Infinite Gold") {
			@Override
			protected void onClick() {
				super.onClick();
				DebugMenu.infiniteGold = checked();
				if (DebugMenu.infiniteGold) {
					Dungeon.gold = 999999;
					CurrencyIndicator.showGold = true;
				}
			}
		};
		cbInfiniteGold.checked(DebugMenu.infiniteGold);
		cbInfiniteGold.setRect(0, pos, width, BTN_HEIGHT);
		add(cbInfiniteGold);
		pos += BTN_HEIGHT + MARGIN;

		// Knockback
		CheckBox cbKnockback = new CheckBox("Knockback Hit") {
			@Override
			protected void onClick() {
				super.onClick();
				DebugMenu.knockback = checked();
			}
		};
		cbKnockback.checked(DebugMenu.knockback);
		cbKnockback.setRect(0, pos, width, BTN_HEIGHT);
		add(cbKnockback);
		pos += BTN_HEIGHT + MARGIN;

		// Freeze Enemies
		CheckBox cbFreeze = new CheckBox("Freeze Enemies") {
			@Override
			protected void onClick() {
				super.onClick();
				DebugMenu.freezeEnemies = checked();
				if (DebugMenu.freezeEnemies) {
					freezeAllEnemies();
				} else {
					unfreezeAllEnemies();
				}
			}
		};
		cbFreeze.checked(DebugMenu.freezeEnemies);
		cbFreeze.setRect(0, pos, width, BTN_HEIGHT);
		add(cbFreeze);
		pos += BTN_HEIGHT + MARGIN;

		// Teleport to Cell
		RedButton btnTeleport = new RedButton("Teleport to Cell") {
			@Override
			protected void onClick() {
				hide();
				GLog.i("Tap a cell to teleport there");
				GameScene.selectCell(new TeleportListener());
			}
		};
		btnTeleport.setRect(0, pos, width, BTN_HEIGHT);
		add(btnTeleport);
		pos += BTN_HEIGHT + MARGIN;

		// Cheat Console button
		RedButton btnConsole = new RedButton("Cheat Console") {
			@Override
			protected void onClick() {
				GameScene.show(new WndCheatConsole());
			}
		};
		btnConsole.setRect(0, pos, width, BTN_HEIGHT);
		add(btnConsole);
		pos += BTN_HEIGHT + MARGIN;

		resize(width, (int) (pos));
	}

	private static class TeleportListener extends CellSelector.Listener {
		@Override
		public void onSelect(Integer cell) {
			if (cell == null || Dungeon.level == null) return;
			if (Dungeon.level.passable[cell] || Dungeon.level.avoid[cell]) {
				ScrollOfTeleportation.appear(Dungeon.hero, cell);
				Dungeon.observe();
				GameScene.updateFog();
				GLog.p("Teleported!");
			} else {
				GLog.n("Cannot teleport to that cell - not walkable");
			}
		}

		@Override
		public String prompt() {
			return "Choose teleport destination";
		}
	}

	private static void freezeAllEnemies() {
		if (Dungeon.level == null) return;
		for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
			if (mob.alignment == Char.Alignment.ENEMY) {
				Buff.affect(mob, Paralysis.class, Float.MAX_VALUE);
			}
		}
	}

	private static void unfreezeAllEnemies() {
		if (Dungeon.level == null) return;
		for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
			if (mob.alignment == Char.Alignment.ENEMY) {
				if (mob.buff(Paralysis.class) != null) {
					mob.buff(Paralysis.class).detach();
				}
			}
		}
	}
}