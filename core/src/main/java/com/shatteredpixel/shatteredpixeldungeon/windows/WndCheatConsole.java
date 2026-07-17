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
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Bat;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Crab;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.DM300;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Eye;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Gnoll;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Golem;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Goo;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Rat;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Skeleton;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Snake;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Tengu;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Warlock;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.YogDzewa;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClothArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.LeatherArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.MailArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ScaleArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.PlateArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.AlchemistsToolkit;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.ChaliceOfBlood;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.CloakOfShadows;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.DriedRose;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.EtherealChains;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.HolyTome;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.HornOfPlenty;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.MasterThievesArmband;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.SandalsOfNature;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.TalismanOfForesight;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.TimekeepersHourglass;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.UnstableSpellbook;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.Bomb;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Food;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfExperience;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfFrost;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHaste;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfInvisibility;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfLevitation;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfLiquidFlame;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfMindVision;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfParalyticGas;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfPurity;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfStrength;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfToxicGas;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfAccuracy;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfArcana;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfElements;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfEnergy;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfEvasion;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfForce;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfFuror;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfHaste;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfMight;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfSharpshooting;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfTenacity;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfWealth;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfLullaby;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMirrorImage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRemoveCurse;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRetribution;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTerror;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTransmutation;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.BattleAxe;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Crossbow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Dagger;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Flail;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Gauntlet;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Gloves;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Greataxe;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Greatsword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Longsword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Mace;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Quarterstaff;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Rapier;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.RoundShield;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Sai;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Scimitar;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Shortsword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Spear;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Sword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.WarHammer;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.WornShortsword;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.ScrollPane;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.ui.Component;
import com.watabou.utils.PathFinder;

public class WndCheatConsole extends Window {

    private static final int WIDTH_P = 140;
    private static final int WIDTH_L = 180;
    private static final int MARGIN = 2;
    private static final int BTN_HEIGHT = 16;

    // Registry of all spawnable items
    private static final SpawnEntry[] ITEMS = new SpawnEntry[]{
            new SpawnEntry("Dagger", Dagger.class),
            new SpawnEntry("Gloves", Gloves.class),
            new SpawnEntry("Shortsword", Shortsword.class),
            new SpawnEntry("Worn Shortsword", WornShortsword.class),
            new SpawnEntry("Sword", Sword.class),
            new SpawnEntry("Longsword", Longsword.class),
            new SpawnEntry("Greatsword", Greatsword.class),
            new SpawnEntry("Spear", Spear.class),
            new SpawnEntry("Quarterstaff", Quarterstaff.class),
            new SpawnEntry("Mace", Mace.class),
            new SpawnEntry("Battle Axe", BattleAxe.class),
            new SpawnEntry("War Hammer", WarHammer.class),
            new SpawnEntry("Flail", Flail.class),
            new SpawnEntry("Scimitar", Scimitar.class),
            new SpawnEntry("Rapier", Rapier.class),
            new SpawnEntry("Sai", Sai.class),
            new SpawnEntry("Crossbow", Crossbow.class),
            new SpawnEntry("Round Shield", RoundShield.class),
            new SpawnEntry("Gauntlet", Gauntlet.class),
            new SpawnEntry("Greataxe", Greataxe.class),
            new SpawnEntry("Cloth Armor", ClothArmor.class),
            new SpawnEntry("Leather Armor", LeatherArmor.class),
            new SpawnEntry("Mail Armor", MailArmor.class),
            new SpawnEntry("Scale Armor", ScaleArmor.class),
            new SpawnEntry("Plate Armor", PlateArmor.class),
            new SpawnEntry("Ring of Accuracy", RingOfAccuracy.class),
            new SpawnEntry("Ring of Arcana", RingOfArcana.class),
            new SpawnEntry("Ring of Elements", RingOfElements.class),
            new SpawnEntry("Ring of Energy", RingOfEnergy.class),
            new SpawnEntry("Ring of Evasion", RingOfEvasion.class),
            new SpawnEntry("Ring of Force", RingOfForce.class),
            new SpawnEntry("Ring of Furor", RingOfFuror.class),
            new SpawnEntry("Ring of Haste", RingOfHaste.class),
            new SpawnEntry("Ring of Might", RingOfMight.class),
            new SpawnEntry("Ring of Sharpshooting", RingOfSharpshooting.class),
            new SpawnEntry("Ring of Tenacity", RingOfTenacity.class),
            new SpawnEntry("Ring of Wealth", RingOfWealth.class),
            new SpawnEntry("Potion of Healing", PotionOfHealing.class),
            new SpawnEntry("Potion of Strength", PotionOfStrength.class),
            new SpawnEntry("Potion of Experience", PotionOfExperience.class),
            new SpawnEntry("Potion of Invisibility", PotionOfInvisibility.class),
            new SpawnEntry("Potion of Mind Vision", PotionOfMindVision.class),
            new SpawnEntry("Potion of Haste", PotionOfHaste.class),
            new SpawnEntry("Potion of Levitation", PotionOfLevitation.class),
            new SpawnEntry("Potion of Liquid Flame", PotionOfLiquidFlame.class),
            new SpawnEntry("Potion of Toxic Gas", PotionOfToxicGas.class),
            new SpawnEntry("Potion of Paralytic Gas", PotionOfParalyticGas.class),
            new SpawnEntry("Potion of Frost", PotionOfFrost.class),
            new SpawnEntry("Potion of Purity", PotionOfPurity.class),
            new SpawnEntry("Scroll of Upgrade", ScrollOfUpgrade.class),
            new SpawnEntry("Scroll of Identify", ScrollOfIdentify.class),
            new SpawnEntry("Scroll of Magic Mapping", ScrollOfMagicMapping.class),
            new SpawnEntry("Scroll of Teleportation", ScrollOfTeleportation.class),
            new SpawnEntry("Scroll of Recharging", ScrollOfRecharging.class),
            new SpawnEntry("Scroll of Mirror Image", ScrollOfMirrorImage.class),
            new SpawnEntry("Scroll of Rage", ScrollOfRage.class),
            new SpawnEntry("Scroll of Terror", ScrollOfTerror.class),
            new SpawnEntry("Scroll of Lullaby", ScrollOfLullaby.class),
            new SpawnEntry("Scroll of Retribution", ScrollOfRetribution.class),
            new SpawnEntry("Scroll of Remove Curse", ScrollOfRemoveCurse.class),
            new SpawnEntry("Scroll of Transmutation", ScrollOfTransmutation.class),
            new SpawnEntry("Sandals of Nature", SandalsOfNature.class),
            new SpawnEntry("Chalice of Blood", ChaliceOfBlood.class),
            new SpawnEntry("Horn of Plenty", HornOfPlenty.class),
            new SpawnEntry("Master Thieves Armband", MasterThievesArmband.class),
            new SpawnEntry("Talisman of Foresight", TalismanOfForesight.class),
            new SpawnEntry("Timekeeper's Hourglass", TimekeepersHourglass.class),
            new SpawnEntry("Unstable Spellbook", UnstableSpellbook.class),
            new SpawnEntry("Ethereal Chains", EtherealChains.class),
            new SpawnEntry("Dried Rose", DriedRose.class),
            new SpawnEntry("Alchemist's Toolkit", AlchemistsToolkit.class),
            new SpawnEntry("Cloak of Shadows", CloakOfShadows.class),
            new SpawnEntry("Holy Tome", HolyTome.class),
            new SpawnEntry("Food Ration", Food.class),
            new SpawnEntry("Bomb", Bomb.class),
    };

    // Registry of all spawnable mobs
    private static final SpawnEntry[] MOBS = new SpawnEntry[]{
            new SpawnEntry("Rat", Rat.class),
            new SpawnEntry("Crab", Crab.class),
            new SpawnEntry("Snake", Snake.class),
            new SpawnEntry("Gnoll", Gnoll.class),
            new SpawnEntry("Skeleton", Skeleton.class),
            new SpawnEntry("Goo", Goo.class),
            new SpawnEntry("Tengu", Tengu.class),
            new SpawnEntry("DM-300", DM300.class),
            new SpawnEntry("Golem", Golem.class),
            new SpawnEntry("Warlock", Warlock.class),
            new SpawnEntry("Bat", Bat.class),
            new SpawnEntry("Eye", Eye.class),
            new SpawnEntry("Yog-Dzewa", YogDzewa.class),
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

        RenderedTextBlock output = PixelScene.renderTextBlock(6);
        output.maxWidth(width - MARGIN * 2);
        output.text("Use the buttons below to spawn items and mobs.", width - MARGIN * 2);
        output.setPos(MARGIN, pos);
        add(output);

        pos = output.bottom() + 2 * MARGIN;

        // Browse Items button
        RedButton btnItems = new RedButton("Browse Items") {
            @Override
            protected void onClick() {
                showItemPicker();
            }
        };
        btnItems.icon(Icons.get(Icons.BACKPACK));
        btnItems.setRect(0, pos, (width - MARGIN) / 2, BTN_HEIGHT);
        add(btnItems);

        // Browse Mobs button
        RedButton btnMobs = new RedButton("Browse Mobs") {
            @Override
            protected void onClick() {
                showMobPicker();
            }
        };
        btnMobs.icon(Icons.get(Icons.SNAKE));
        btnMobs.setRect(btnItems.right() + MARGIN, pos, (width - MARGIN) / 2, BTN_HEIGHT);
        add(btnMobs);
        pos += BTN_HEIGHT + MARGIN;

        resize(width, (int) pos);
    }

    private void showItemPicker() {
        final int width = PixelScene.landscape() ? WIDTH_L : WIDTH_P;
        Component listContent = new Component();
        float listPos = MARGIN;
        for (final SpawnEntry entry : ITEMS) {
            RedButton btn = new RedButton(entry.name) {
                @Override
                protected void onClick() {
                    spawnItem(entry);
                    hide();
                }
            };
            btn.setRect(0, listPos, width, BTN_HEIGHT);
            btn.multiline = true;
            listContent.add(btn);
            listPos += BTN_HEIGHT + MARGIN;
        }
        listContent.setSize(width, listPos);

        // Use a fixed-height scroll window (since we can't easily make a scrollable outside Window)
        final int fixedHeight = Math.min((int)listPos, PixelScene.uiCamera.height - 20);
        ScrollPane scrollPane = new ScrollPane(listContent);
        scrollPane.setRect(0, 0, width, fixedHeight);

        Window subWindow = new Window() {
            private float p = MARGIN;
            {
                RenderedTextBlock title = PixelScene.renderTextBlock("Select Item to Spawn", 9);
                title.hardlight(TITLE_COLOR);
                title.setPos(MARGIN, MARGIN);
                title.maxWidth(width - MARGIN * 2);
                add(title);
                p = title.bottom() + 2 * MARGIN;

                add(scrollPane);
                scrollPane.setRect(0, p, width, fixedHeight);
                resize(width, (int) (p + fixedHeight));
            }
        };
        GameScene.show(subWindow);
    }

    private void showMobPicker() {
        final int width = PixelScene.landscape() ? WIDTH_L : WIDTH_P;
        Component listContent = new Component();
        float listPos = MARGIN;
        for (final SpawnEntry entry : MOBS) {
            RedButton btnFoe = new RedButton(entry.name + " [Foe]") {
                @Override
                protected void onClick() {
                    spawnMob(entry, Char.Alignment.ENEMY);
                    hide();
                }
            };
            btnFoe.setRect(0, listPos, (width - MARGIN) / 2, BTN_HEIGHT);
            listContent.add(btnFoe);

            RedButton btnAlly = new RedButton(entry.name + " [Ally]") {
                @Override
                protected void onClick() {
                    spawnMob(entry, Char.Alignment.ALLY);
                    hide();
                }
            };
            btnAlly.setRect(btnFoe.right() + MARGIN, listPos, (width - MARGIN) / 2, BTN_HEIGHT);
            listContent.add(btnAlly);
            listPos += BTN_HEIGHT + MARGIN;
        }
        listContent.setSize(width, listPos);

        final int fixedHeight = Math.min((int)listPos, PixelScene.uiCamera.height - 20);
        ScrollPane scrollPane = new ScrollPane(listContent);
        scrollPane.setRect(0, 0, width, fixedHeight);

        Window subWindow = new Window() {
            private float p = MARGIN;
            {
                RenderedTextBlock title = PixelScene.renderTextBlock("Select Mob to Spawn", 9);
                title.hardlight(TITLE_COLOR);
                title.setPos(MARGIN, MARGIN);
                title.maxWidth(width - MARGIN * 2);
                add(title);
                p = title.bottom() + 2 * MARGIN;

                add(scrollPane);
                scrollPane.setRect(0, p, width, fixedHeight);
                resize(width, (int) (p + fixedHeight));
            }
        };
        GameScene.show(subWindow);
    }

    private void spawnItem(SpawnEntry entry) {
        try {
            Item item = (Item) entry.itemClass.newInstance();
            item.identify();
            if (Dungeon.hero != null && Dungeon.hero.isAlive()) {
                if (item.doPickUp(Dungeon.hero)) {
                    GLog.p("Spawned " + entry.name + " - added to inventory");
                } else {
                    Dungeon.level.drop(item, Dungeon.hero.pos).sprite.drop();
                    GLog.i("Spawned " + entry.name + " at your feet");
                }
            } else {
                Dungeon.level.drop(item, Dungeon.hero.pos).sprite.drop();
                GLog.i("Spawned " + entry.name + " at your feet");
            }
        } catch (Exception e) {
            GLog.n("Failed to spawn " + entry.name);
        }
    }

    @SuppressWarnings("unchecked")
    private void spawnMob(SpawnEntry entry, Char.Alignment alignment) {
        try {
            Mob mob = (Mob) entry.itemClass.newInstance();
            int pos = Dungeon.hero.pos;
            int spawnPos = pos;
            for (int i : PathFinder.NEIGHBOURS8) {
                int cell = pos + i;
                if (cell >= 0 && cell < Dungeon.level.length()
                        && Dungeon.level.passable[cell]
                        && Actor.findChar(cell) == null) {
                    spawnPos = cell;
                    break;
                }
            }
            mob.pos = spawnPos;
            mob.alignment = alignment;
            if (alignment == Char.Alignment.ALLY) {
                // Set ally - mob will automatically wander/be passive
                mob.clearEnemy();
            } else {
                // Set foe - this makes it aggressive toward the hero
                mob.aggro(Dungeon.hero);
            }
            Dungeon.level.mobs.add(mob);
            GameScene.addSprite(mob);
            Actor.addDelayed(mob, 0);
            GLog.i("Spawned " + (alignment == Char.Alignment.ALLY ? "ally " : "foe ") + entry.name);
        } catch (Exception e) {
            GLog.n("Failed to spawn " + entry.name);
        }
    }

    private static class SpawnEntry {
        String name;
        Class<?> itemClass;

        SpawnEntry(String name, Class<?> itemClass) {
            this.name = name;
            this.itemClass = itemClass;
        }
    }
}