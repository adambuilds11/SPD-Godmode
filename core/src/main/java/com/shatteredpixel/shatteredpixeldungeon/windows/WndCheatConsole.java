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
 * This program is free software: it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Reflection;

public class WndCheatConsole extends Window {

    private static final int WIDTH_P = 120;
    private static final int WIDTH_L = 144;
    private static final int MARGIN = 2;
    private static final int BTN_HEIGHT = 16;

    // Items grouped by category
    private static final Category[] CATEGORIES = new Category[]{
        new Category("Weapons", new Class[]{
            com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Dagger.class,
            com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Gloves.class,
            com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Shortsword.class,
            com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Sword.class,
            com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Longsword.class,
            com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Greatsword.class,
            com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Spear.class,
            com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Quarterstaff.class,
            com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Mace.class,
            com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.BattleAxe.class,
            com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.WarHammer.class,
            com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Flail.class,
            com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Scimitar.class,
            com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Rapier.class,
            com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Sai.class,
            com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Crossbow.class,
            com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.RoundShield.class,
            com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Gauntlet.class,
            com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Greataxe.class,
        }),
        new Category("Armor", new Class[]{
            com.shatteredpixel.shatteredpixeldungeon.items.armor.ClothArmor.class,
            com.shatteredpixel.shatteredpixeldungeon.items.armor.LeatherArmor.class,
            com.shatteredpixel.shatteredpixeldungeon.items.armor.MailArmor.class,
            com.shatteredpixel.shatteredpixeldungeon.items.armor.ScaleArmor.class,
            com.shatteredpixel.shatteredpixeldungeon.items.armor.PlateArmor.class,
        }),
        new Category("Rings", new Class[]{
            com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfAccuracy.class,
            com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfArcana.class,
            com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfElements.class,
            com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfEnergy.class,
            com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfEvasion.class,
            com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfForce.class,
            com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfFuror.class,
            com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfHaste.class,
            com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfMight.class,
            com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfSharpshooting.class,
            com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfTenacity.class,
            com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfWealth.class,
        }),
        new Category("Potions", new Class[]{
            com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing.class,
            com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfStrength.class,
            com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfExperience.class,
            com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfInvisibility.class,
            com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfMindVision.class,
            com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHaste.class,
            com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfLevitation.class,
            com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfLiquidFlame.class,
            com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfToxicGas.class,
            com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfParalyticGas.class,
            com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfFrost.class,
            com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfPurity.class,
        }),
        new Category("Scrolls", new Class[]{
            com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfUpgrade.class,
            com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfIdentify.class,
            com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMagicMapping.class,
            com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation.class,
            com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRecharging.class,
            com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMirrorImage.class,
            com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRage.class,
            com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTerror.class,
            com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfLullaby.class,
            com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRetribution.class,
            com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRemoveCurse.class,
            com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTransmutation.class,
        }),
        new Category("Artifacts", new Class[]{
            com.shatteredpixel.shatteredpixeldungeon.items.artifacts.SandalsOfNature.class,
            com.shatteredpixel.shatteredpixeldungeon.items.artifacts.ChaliceOfBlood.class,
            com.shatteredpixel.shatteredpixeldungeon.items.artifacts.HornOfPlenty.class,
            com.shatteredpixel.shatteredpixeldungeon.items.artifacts.MasterThievesArmband.class,
            com.shatteredpixel.shatteredpixeldungeon.items.artifacts.TalismanOfForesight.class,
            com.shatteredpixel.shatteredpixeldungeon.items.artifacts.TimekeepersHourglass.class,
            com.shatteredpixel.shatteredpixeldungeon.items.artifacts.UnstableSpellbook.class,
            com.shatteredpixel.shatteredpixeldungeon.items.artifacts.EtherealChains.class,
            com.shatteredpixel.shatteredpixeldungeon.items.artifacts.DriedRose.class,
            com.shatteredpixel.shatteredpixeldungeon.items.artifacts.AlchemistsToolkit.class,
            com.shatteredpixel.shatteredpixeldungeon.items.artifacts.CloakOfShadows.class,
            com.shatteredpixel.shatteredpixeldungeon.items.artifacts.HolyTome.class,
        }),
        new Category("Misc", new Class[]{
            com.shatteredpixel.shatteredpixeldungeon.items.food.Food.class,
            com.shatteredpixel.shatteredpixeldungeon.items.bombs.Bomb.class,
        }),
    };

    private static final Class<? extends Mob>[] MOB_CLASSES = new Class[]{
            com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Rat.class,
            com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Crab.class,
            com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Snake.class,
            com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Gnoll.class,
            com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Skeleton.class,
            com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Bat.class,
            com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Golem.class,
            com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Warlock.class,
            com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Eye.class,
    };

    public WndCheatConsole() {
        super();

        int width = PixelScene.landscape() ? WIDTH_L : WIDTH_P;

        RenderedTextBlock title = PixelScene.renderTextBlock("Cheat Console", 9);
        title.hardlight(TITLE_COLOR);
        title.setPos(MARGIN, MARGIN);
        title.maxWidth(width - MARGIN * 2);
        add(title);

        float pos = title.bottom() + 2 * MARGIN;

        // Spawn Item by category
        for (final Category cat : CATEGORIES) {
            RedButton btn = new RedButton(cat.name) {
                @Override
                protected void onClick() {
                    showItemCategory(cat);
                }
            };
            btn.icon(Icons.get(Icons.BACKPACK));
            btn.setRect(0, pos, width, BTN_HEIGHT);
            add(btn);
            pos += BTN_HEIGHT + MARGIN;
        }

        // Spawn Mob buttons
        RedButton btnMobFoe = new RedButton("Mob (Foe)") {
            @Override
            protected void onClick() {
                showMobPicker(Char.Alignment.ENEMY);
            }
        };
        btnMobFoe.icon(Icons.get(Icons.SKULL));
        btnMobFoe.setRect(0, pos, (width - MARGIN) / 2, BTN_HEIGHT);
        add(btnMobFoe);

        RedButton btnMobAlly = new RedButton("Mob (Ally)") {
            @Override
            protected void onClick() {
                showMobPicker(Char.Alignment.ALLY);
            }
        };
        btnMobAlly.icon(Icons.get(Icons.SNAKE));
        btnMobAlly.setRect(btnMobFoe.right() + MARGIN, pos, (width - MARGIN) / 2, BTN_HEIGHT);
        add(btnMobAlly);
        pos += BTN_HEIGHT + MARGIN;

        resize(width, (int) pos);
    }

    // Shows a WndOptions with the items in this category (max ~19 items, fits screen)
    private void showItemCategory(final Category cat) {
        final String[] names = new String[cat.classes.length];
        for (int i = 0; i < cat.classes.length; i++) {
            try {
                Item item = Reflection.newInstance(cat.classes[i]);
                names[i] = item.name();
            } catch (Exception e) {
                names[i] = cat.classes[i].getSimpleName();
            }
        }

        GameScene.show(new WndOptions("Select " + cat.name, "Click to spawn:", names) {
            @Override
            protected void onSelect(int index) {
                spawnItem(cat.classes[index]);
            }
        });
    }

    private void showMobPicker(final Char.Alignment alignment) {
        final String[] names = new String[MOB_CLASSES.length];
        for (int i = 0; i < MOB_CLASSES.length; i++) {
            try {
                Mob mob = Reflection.newInstance(MOB_CLASSES[i]);
                names[i] = mob.name();
            } catch (Exception e) {
                names[i] = MOB_CLASSES[i].getSimpleName();
            }
        }

        GameScene.show(new WndOptions("Select Mob (" + (alignment == Char.Alignment.ENEMY ? "Foe" : "Ally") + ")", "Click to spawn:", names) {
            @Override
            protected void onSelect(int index) {
                spawnMob(MOB_CLASSES[index], alignment);
            }
        });
    }

    private void spawnItem(Class<? extends Item> cls) {
        try {
            Item item = Reflection.newInstance(cls);
            item.identify();
            if (Dungeon.hero != null && Dungeon.hero.isAlive()) {
                if (item.doPickUp(Dungeon.hero)) {
                    GLog.p("Spawned " + item.name() + " - added to inventory");
                } else if (Dungeon.level != null) {
                    Dungeon.level.drop(item, Dungeon.hero.pos).sprite.drop();
                    GLog.i("Spawned " + item.name() + " at your feet");
                }
            }
        } catch (Exception e) {
            GLog.n("Failed to spawn item!");
        }
    }

    private void spawnMob(Class<? extends Mob> cls, Char.Alignment alignment) {
        try {
            Mob mob = Reflection.newInstance(cls);
            int heroPos = Dungeon.hero.pos;
            int spawnPos = heroPos;
            for (int i : PathFinder.NEIGHBOURS8) {
                int cell = heroPos + i;
                if (Dungeon.level != null && cell >= 0 && cell < Dungeon.level.length()
                        && Dungeon.level.passable[cell]
                        && Actor.findChar(cell) == null) {
                    spawnPos = cell;
                    break;
                }
            }
            mob.pos = spawnPos;
            mob.alignment = alignment;
            if (alignment == Char.Alignment.ALLY) {
                mob.clearEnemy();
            } else {
                mob.aggro(Dungeon.hero);
            }
            if (Dungeon.level != null) {
                Dungeon.level.mobs.add(mob);
                GameScene.addSprite(mob);
                Actor.addDelayed(mob, 0);
            }
            GLog.i("Spawned " + (alignment == Char.Alignment.ALLY ? "ally " : "foe ") + mob.name());
        } catch (Exception e) {
            GLog.n("Failed to spawn mob!");
        }
    }

    private static class Category {
        String name;
        Class<? extends Item>[] classes;

        @SuppressWarnings("unchecked")
        Category(String name, Class<?>... classes) {
            this.name = name;
            this.classes = (Class<? extends Item>[]) classes;
        }
    }
}