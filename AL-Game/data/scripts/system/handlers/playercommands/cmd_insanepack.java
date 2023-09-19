/*
 * =====================================================================================*
 * This file is part of Aion-Unique (Aion-Unique Home Software Development)             *
 * Aion-Unique Development is a closed Aion Project that use Old Aion Project Base      *
 * Like Aion-Lightning, Aion-Engine, Aion-Core, Aion-Extreme, Aion-NextGen,      	    *
 * Aion-Ger, U3J, Encom And other Aion project, All Credit Content                      *
 * That they make is belong to them/Copyright is belong to them. And All new Content    *
 * that Aion-Unique make the copyright is belong to Aion-Unique                         *
 * You may have agreement with Aion-Unique Development, before use this Engine/Source   *
 * You have agree with all of Term of Services agreement with Aion-Unique Development   *
 * =====================================================================================*
 */
package playercommands;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.chathandlers.PlayerCommand;
import com.aionemu.gameserver.services.item.ItemService;

/***************************/
/* Created by ElApotsainer */
/***************************/
public class cmd_insanepack extends PlayerCommand {

public cmd_insanepack() {
super("insanepack");
}
@Override
public void execute(final Player player, String...param){
if(param.length < 1){
PacketSendUtility.sendMessage(player, "Syntax:' .insanepack info '");
return;
}
if(param[0].equals("scrolls")){
ItemService.addItem(player,164002111, 500); //<Greater Running Scroll (1 hour)>
ItemService.addItem(player,164002113, 500); //<Greater Awakening Scroll (1 hour)>
ItemService.addItem(player,164002112, 500); //<Greater Courage Scroll (1 hour)>
ItemService.addItem(player,164002115, 500); //<Major Crit Spell Scroll (1 hour)>
ItemService.addItem(player,164002114, 500); //<Major Crit Strike Scroll (1 hour)>
ItemService.addItem(player,164000259, 500); //<Premium Anti-Shock Scroll>
ItemService.addItem(player,164000260, 500); //<Premium Magic Suppression Scroll>
ItemService.addItem(player,164000021, 500); //<Boost Elemental Defense>
ItemService.addItem(player,164000114, 500); //<Fine Fireproof Scroll>
ItemService.addItem(player,164000115, 500); //<Fine Earthproof Scroll>
ItemService.addItem(player,164000116, 500); //<Fine Waterproof Scroll>
ItemService.addItem(player,164000117, 500); //<Fine Windproof Scroll>
ItemService.addItem(player,164000257, 500); //<Fine Strike Resist Scroll>
ItemService.addItem(player,162000083, 500); //<Leader's Recovery Scroll>
PacketSendUtility.sendMessage(player, "\uE022 Scrolls has been added to your Inventory. \uE022");  
}
if(param[0].equals("godstone")){
ItemService.addItem(player,168000162, 2); //<Illusion Godstone: Ereshkigal's Honor>
ItemService.addItem(player,168000164, 2); //<Illusion Godstone: Beritra's Plot>
ItemService.addItem(player,168000165, 2); //<Illusion Godstone: Fregion's Trick>
ItemService.addItem(player,168000166, 2); //<Illusion Godstone: Lumiel's Intervention>
ItemService.addItem(player,168000167, 2); //<Illusion Godstone: Kaisinel's Fantasy>
ItemService.addItem(player,168000161, 2); //<Illusion Godstone: Meslamtaeda's Greed>
ItemService.addItem(player,168000176, 2); //<Illusion Godstone: Vaizel's Vow>
ItemService.addItem(player,168000174, 2); //<Illusion Godstone: Tiamat's Fury>
PacketSendUtility.sendMessage(player, "\uE022 Congratulations, You Just Added All The Famous Godstones! \uE022");
}
if(param[0].equals("potions")){
ItemService.addItem(player,162000178, 500); //<Refined Recovery Potion>
ItemService.addItem(player,162000181, 500); //<Vital Recovery Serum>
ItemService.addItem(player,162000197, 500); //<Upgraded Divine Life Serum>
ItemService.addItem(player,162000141, 500); //<Sublime Wind Serum>
ItemService.addItem(player,162000107, 500); //<Saam King's Herbs>
PacketSendUtility.sendMessage(player, "\uE022 Potions has been added to your Inventory. \uE022"); 
}
if(param[0].equals("goodies")){
ItemService.addItem(player,160009017, 5); //<Vinna Juice>
ItemService.addItem(player,160010337, 5); //<Event Heartfelt Chocolate>
ItemService.addItem(player,160010333, 5); //<Event Scintillating Chocolate>
ItemService.addItem(player,160010334, 5); //<Event Constitutional Chocolate>
ItemService.addItem(player,164000266, 50); //<Enhanced Seed of Detection>
ItemService.addItem(player,160003587, 50); //<Pepento Jelly>
ItemService.addItem(player,169300007, 10000); //<Master Odella Powder>
ItemService.addItem(player,160005052, 500); //<Spicy Banquet Meal for Victory Wishes>
ItemService.addItem(player,160002433, 500); //<Spicy Plumbo with Cheese>
ItemService.addItem(player,166050129, 10); //<Blazing Warlord's Idian: Physical/Magical Attack>
ItemService.addItem(player,169610146, 1); //<Glorious Number One (30 days)>
PacketSendUtility.sendMessage(player, "\uE022 Goodies has been added to your Inventory. \uE022");
}
if(param[0].equals("gladiator")){
ItemService.addItem(player,110601900, 1); //<Prime Guardian Royal Breastplate>
ItemService.addItem(player,113601847, 1); //<Prime Guardian Royal Greaves>
ItemService.addItem(player,114601853, 1); //<Prime Guardian Royal Sabatons>
ItemService.addItem(player,111601870, 1); //<Prime Guardian Royal Gauntlets>
ItemService.addItem(player,112601851, 1); //<Prime Guardian Royal Shoulderplates>
ItemService.addItem(player,125005038, 1); //<Prime Guardian Royal Helm>
ItemService.addItem(player,123001587, 1); //<Prime Guardian Royal Combat Waistband>
ItemService.addItem(player,120015055, 2); //<Prime Guardian Royal Combat Earrings>
ItemService.addItem(player,121001553, 1); //<Prime Guardian Royal Combat Necklace>
ItemService.addItem(player,122001838, 2); //<Prime Guardian Royal Combat Ring>
ItemService.addItem(player,101301540, 1); //<Prime Guardian Royal Polearm>
ItemService.addItem(player,101301416, 1); //<Polearm of Provenance>
ItemService.addItem(player,110601902, 1); //<Prime Archon Royal Breastplate>
ItemService.addItem(player,113601849, 1); //<Prime Archon Royal Greaves>
ItemService.addItem(player,114601855, 1); //<Prime Archon Royal Sabatons>
ItemService.addItem(player,111601872, 1); //<Prime Archon Royal Gauntlets>
ItemService.addItem(player,112601853, 1); //<Prime Archon Royal Shoulderplates>
ItemService.addItem(player,125005049, 1); //<Prime Archon Royal Helm>
ItemService.addItem(player,123001589, 1); //<Prime Archon Royal Combat Waistband>
ItemService.addItem(player,120015057, 2); //<Prime Archon Royal Combat Earrings>
ItemService.addItem(player,121001555, 1); //<Prime Archon Royal Battle Necklace>
ItemService.addItem(player,122001840, 2); //<Prime Archon Royal Combat Ring>
ItemService.addItem(player,100002170, 1); //<Prime Archon Royal Long Sword>
ItemService.addItem(player,101301541, 1); //<Prime Archon Royal Polearm>
ItemService.addItem(player,167010259, 50); //<Manastone: Attack +6 / Crit Strike +9>
ItemService.addItem(player,167010264, 50); //<Manastone: Attack +6 / Accuracy +14>
ItemService.addItem(player,190100225, 1); //<Tamed Lion (1 day)>
ItemService.addItem(player,187000227, 1); //<Prime Guardian Royal Combat Wings>
ItemService.addItem(player,187000228, 1); //<Prime Archon Royal Combat Wings>
PacketSendUtility.sendMessage(player, "\uE022 Gladiator Gears has been added to your Inventory. \uE022");
}
if(param[0].equals("templar")){
ItemService.addItem(player,110601901, 1); //<Prime Guardian Royal Magic Breastplate>
ItemService.addItem(player,113601848, 1); //<Prime Guardian Royal Magic Greaves>
ItemService.addItem(player,114601854, 1); //<Prime Guardian Royal Magic Sabatons>
ItemService.addItem(player,111601871, 1); //<Prime Guardian Royal Magic Gauntlets>
ItemService.addItem(player,112601852, 1); //<Prime Guardian Royal Magic Shoulderplates>
ItemService.addItem(player,125005039, 1); //<Prime Guardian Royal Magic Helm>
ItemService.addItem(player,123001587, 1); //<Prime Guardian Royal Combat Waistband>
ItemService.addItem(player,120015055, 2); //<Prime Guardian Royal Combat Earrings>
ItemService.addItem(player,121001553, 1); //<Prime Guardian Royal Combat Necklace>
ItemService.addItem(player,122001838, 2); //<Prime Guardian Royal Combat Ring>
ItemService.addItem(player,100901659, 1); //<Prime Guardian Royal Two-handed Sword>
ItemService.addItem(player,100901532, 1); //<Provenance Greatsword>
ItemService.addItem(player,100002168, 1); //<Prime Guardian Royal Long Sword>
ItemService.addItem(player,100002015, 1); //<Provenance Sword>
ItemService.addItem(player,115002155, 1); //<Prime Guardian Royal Battle Shield>
ItemService.addItem(player,110601903, 1); //<Prime Archon Royal Plate Breast Protector>
ItemService.addItem(player,113601850, 1); //<Prime Archon Royal Magic Greaves>
ItemService.addItem(player,114601856, 1); //<Prime Archon Royal Magic Sabatons>
ItemService.addItem(player,111601873, 1); //<Prime Archon Royal Magic Gauntlets>
ItemService.addItem(player,112601854, 1); //<Prime Archon Royal Magic Shoulderplates>
ItemService.addItem(player,125005050, 1); //<Prime Archon Royal Magic Helm>
ItemService.addItem(player,123001589, 1); //<Prime Archon Royal Combat Waistband>
ItemService.addItem(player,120015057, 2); //<Prime Archon Royal Combat Earrings>
ItemService.addItem(player,121001555, 1); //<Prime Archon Royal Battle Necklace>
ItemService.addItem(player,122001840, 2); //<Prime Archon Royal Combat Ring>
ItemService.addItem(player,100901659, 1); //<Prime Archon Royal Two-handed Sword>
ItemService.addItem(player,100002171, 1); //<Prime Archon Royal Long Sword>
ItemService.addItem(player,115002157, 1); //<Prime Archon Royal Battle Shield>
ItemService.addItem(player,167010259, 50); //<Manastone: Attack +6 / Crit Strike +9>
ItemService.addItem(player,167010261, 50); //<Manastone: Attack +6 / Magical Accuracy +8>
ItemService.addItem(player,190100225, 1); //<Tamed Lion (1 day)>
ItemService.addItem(player,187000227, 1); //<Prime Guardian Royal Combat Wings>
ItemService.addItem(player,187000228, 1); //<Prime Archon Royal Combat Wings>
PacketSendUtility.sendMessage(player, "\uE022 Templar Gears has been added to your Inventory. \uE022");
}
if(param[0].equals("cleric")){
ItemService.addItem(player,110551493, 1); //<Prime Guardian Royal Magic Hauberk>
ItemService.addItem(player,113502072, 1); //<Prime Guardian Royal Magic Chausses>
ItemService.addItem(player,114502080, 1); //<Prime Guardian Royal Magic Brogans
ItemService.addItem(player,111502062, 1); //<Prime Guardian Royal Magic Handguards>
ItemService.addItem(player,112501998, 1); //<Prime Guardian Royal Magic Spaulders>
ItemService.addItem(player,125005035, 1); //<Prime Guardian Royal Magic Chain Helm>
ItemService.addItem(player,123001588, 1); //<Prime Guardian Royal Magic Waistband>
ItemService.addItem(player,120015056, 2); //<Prime Guardian Royal Magic Earrings>
ItemService.addItem(player,121001554, 1); //<Prime Guardian Royal Magic Necklace>
ItemService.addItem(player,122001839, 2); //<Prime Guardian Royal Magic Ring>
ItemService.addItem(player,101501638, 2); //<Prime Guardian Royal Cane>
ItemService.addItem(player,100101621, 1); //<Prime Guardian Royal War Hammer>
ItemService.addItem(player,115002156, 1); //<Prime Guardian Royal Scale Shield>
ItemService.addItem(player,110551496, 1); //<Prime Archon Royal Magic Hauberk>
ItemService.addItem(player,113502075, 1); //<Prime Archon Royal Magic Chausses>
ItemService.addItem(player,114502083, 1); //<Prime Archon Royal Magic Brogans
ItemService.addItem(player,111502065, 1); //<Prime Archon Royal Magic Handguards>
ItemService.addItem(player,112502001, 1); //<Prime Archon Royal Magic Spaulders>
ItemService.addItem(player,125005046, 1); //<Prime Archon Royal Magic Chain Helm>
ItemService.addItem(player,123001590, 1); //<Prime Archon Royal Magic Waistband>
ItemService.addItem(player,120015058, 2); //<Prime Archon Royal Magic Earrings>
ItemService.addItem(player,121001556, 1); //<Prime Archon Royal Magic Necklace>
ItemService.addItem(player,122001841, 2); //<Prime Archon Royal Magic Ring>
ItemService.addItem(player,101501640, 2); //<Prime Archon Royal Cane>
ItemService.addItem(player,100101623, 1); //<Prime Archon Royal War Hammer>
ItemService.addItem(player,115002158, 1); //<Prime Archon Royal Scale Shield>  
ItemService.addItem(player,167010258, 50); //<Manastone: Magic Boost +29 / Magical Accuracy +8>
ItemService.addItem(player,190100225, 1); //<Tamed Lion (1 day)>
ItemService.addItem(player,187000227, 1); //<Prime Guardian Royal Combat Wings>
ItemService.addItem(player,187000228, 1); //<Prime Archon Royal Combat Wings>
PacketSendUtility.sendMessage(player, "\uE022 Cleric Gears has been added to your Inventory. \uE022");
}

if(param[0].equals("chanter")){
ItemService.addItem(player,110551494, 1); //<Prime Guardian Royal Hauberk>
ItemService.addItem(player,113502073, 1); //<Prime Guardian Royal Chausses>
ItemService.addItem(player,114502081, 1); //<Prime Guardian Royal Brogans>
ItemService.addItem(player,111502063, 1); //<Prime Guardian Royal Handguards>
ItemService.addItem(player,112501999, 1); //<Prime Guardian Royal Spaulders>
ItemService.addItem(player,125005036, 1); //<Prime Guardian Royal Chain Helm>
ItemService.addItem(player,123001587, 1); //<Prime Guardian Royal Combat Waistband>
ItemService.addItem(player,120015055, 2); //<Prime Guardian Royal Combat Earrings>
ItemService.addItem(player,121001553, 1); //<Prime Guardian Royal Combat Necklace>
ItemService.addItem(player,122001838, 2); //<Prime Guardian Royal Combat Ring>
ItemService.addItem(player,101501639, 1); //<Prime Guardian Royal Staff>
ItemService.addItem(player,101501518, 1); //<Provenance Staff>
ItemService.addItem(player,100101622, 1); //<Prime Guardian Royal Mace>
ItemService.addItem(player,115002155, 1); //<Prime Guardian Royal Battle Shield>
ItemService.addItem(player,110551497, 1); //<Prime Archon Royal Hauberk>
ItemService.addItem(player,113502076, 1); //<Prime Archon Royal Chausses>
ItemService.addItem(player,114502084, 1); //<Prime Archon Royal Brogans>
ItemService.addItem(player,111502066, 1); //<Prime Archon Royal Handguards>
ItemService.addItem(player,112502002, 1); //<Prime Archon Royal Chain Pauldron>
ItemService.addItem(player,125005047, 1); //<Prime Archon Royal Chain Helm>
ItemService.addItem(player,123001589, 1); //<Prime Archon Royal Combat Waistband>
ItemService.addItem(player,120015057, 2); //<Prime Archon Royal Combat Earrings>
ItemService.addItem(player,121001555, 1); //<Prime Archon Royal Battle Necklace>
ItemService.addItem(player,122001840, 2); //<Prime Archon Royal Combat Ring>
ItemService.addItem(player,101501641, 1); //<Prime Archon Royal Staff>
ItemService.addItem(player,100101624, 1); //<Prime Archon Royal Mace>
ItemService.addItem(player,115002157, 1); //<Prime Archon Royal Battle Shield>
ItemService.addItem(player,167010259, 50); //<Manastone: Attack +6 / Crit Strike +9>
ItemService.addItem(player,167010261, 50); //<Manastone: Attack +6 / Magical Accuracy +8>
ItemService.addItem(player,190100225, 1); //<Tamed Lion (1 day)>
ItemService.addItem(player,187000227, 1); //<Prime Guardian Royal Combat Wings>
ItemService.addItem(player,187000228, 1); //<Prime Archon Royal Combat Wings>
PacketSendUtility.sendMessage(player, "\uE022 Chanter Gears has been added to your Inventory. \uE022");
}

if(param[0].equals("assassin")){
ItemService.addItem(player,110302124, 1); //<Prime Guardian Royal Leather Jacket>
ItemService.addItem(player,113302094, 1); //<Prime Guardian Royal Breeches>
ItemService.addItem(player,114302131, 1); //<Prime Guardian Royal Leather Combat Boots>
ItemService.addItem(player,111302069, 1); //<Prime Guardian Royal Protective Gloves>
ItemService.addItem(player,112302006, 1); //<Prime Guardian Royal Shoulderguards>
ItemService.addItem(player,125005032, 1); //<Prime Guardian Royal Leather Combat Hat>
ItemService.addItem(player,123001587, 1); //<Prime Guardian Royal Combat Waistband>
ItemService.addItem(player,120015055, 2); //<Prime Guardian Royal Combat Earrings>
ItemService.addItem(player,121001553, 1); //<Prime Guardian Royal Combat Necklace>
ItemService.addItem(player,122001838, 2); //<Prime Guardian Royal Combat Ring>
ItemService.addItem(player,100002169, 1); //<Prime Guardian Royal One-Handed Sword>
ItemService.addItem(player,100201811, 1); //<Prime Guardian Royal Dagger>
ItemService.addItem(player,101701650, 2); //<Immortal Oath bow>
ItemService.addItem(player,110302127, 1); //<Prime Archon Royal Leather Jacket>
ItemService.addItem(player,113302097, 1); //<Prime Archon Royal Breeches>
ItemService.addItem(player,114302134, 1); //<Prime Archon Royal Leather Combat Boots>
ItemService.addItem(player,111302072, 1); //<Prime Archon Royal Protective Gloves>
ItemService.addItem(player,112302009, 1); //<Prime Archon Royal Shoulderguards>
ItemService.addItem(player,125005043, 1); //<Prime Archon Royal Leather Combat Hat>
ItemService.addItem(player,123001589, 1); //<Prime Archon Royal Combat Waistband>
ItemService.addItem(player,120015057, 2); //<Prime Archon Royal Combat Earrings>
ItemService.addItem(player,121001555, 1); //<Prime Archon Royal Battle Necklace>
ItemService.addItem(player,122001840, 2); //<Prime Archon Royal Combat Ring>
ItemService.addItem(player,100002172, 1); //<Prime Archon Royal One-handed Sword>
ItemService.addItem(player,100201812, 1); //<Prime Archon Royal Dagger>
ItemService.addItem(player,101701650, 2); //<Immortal Oath bow>
ItemService.addItem(player,167010259, 50); //<Manastone: Attack +6 / Crit Strike +9>
ItemService.addItem(player,167010261, 50); //<Manastone: Attack +6 / Magical Accuracy +8>
ItemService.addItem(player,190100225, 1); //<Tamed Lion (1 day)>
ItemService.addItem(player,187000227, 1); //<Prime Guardian Royal Combat Wings>
ItemService.addItem(player,187000228, 1); //<Prime Archon Royal Combat Wings>
PacketSendUtility.sendMessage(player, "\uE022 Assassin Gears has been added to your Inventory. \uE022");
}

if(param[0].equals("ranger")){
ItemService.addItem(player,110302125, 1); //<Prime Guardian Royal Jerkin>
ItemService.addItem(player,113302095, 1); //<Prime Guardian Royal Breeches>
ItemService.addItem(player,114302132, 1); //<Prime Guardian Royal Boots>
ItemService.addItem(player,111302070, 1); //<Prime Guardian Royal Vambrace>
ItemService.addItem(player,112302007, 1); //<Prime Guardian Royal Shoulderguards>
ItemService.addItem(player,125005033, 1); //<Prime Guardian Royal Hat>
ItemService.addItem(player,123001587, 1); //<Prime Guardian Royal Combat Waistband>
ItemService.addItem(player,120015055, 2); //<Prime Guardian Royal Combat Earrings>
ItemService.addItem(player,121001553, 1); //<Prime Guardian Royal Combat Necklace>
ItemService.addItem(player,122001838, 2); //<Prime Guardian Royal Combat Ring>
ItemService.addItem(player,100002168, 1); //<Prime Guardian Royal Sword>
ItemService.addItem(player,100201811, 1); //<Prime Guardian Royal Dagger>
ItemService.addItem(player,101701642, 2); //<Prime Guardian Royal Bow>
ItemService.addItem(player,110302128, 1); //<Prime Archon Royal Jerkin>
ItemService.addItem(player,113302098, 1); //<Prime Archon Royal Breeches>
ItemService.addItem(player,114302135, 1); //<Prime Archon Royal Boots>
ItemService.addItem(player,111302073, 1); //<Prime Archon Royal Vambrace>
ItemService.addItem(player,112302010, 1); //<Prime Archon Royal Shoulderguards>
ItemService.addItem(player,125005044, 1); //<Prime Archon Royal Hat>
ItemService.addItem(player,123001589, 1); //<Prime Archon Royal Combat Waistband>
ItemService.addItem(player,120015057, 2); //<Prime Archon Royal Combat Earrings>
ItemService.addItem(player,121001555, 1); //<Prime Archon Royal Battle Necklace>
ItemService.addItem(player,122001840, 2); //<Prime Archon Royal Combat Ring>
ItemService.addItem(player,100002171, 1); //<Prime Archon Royal Sword>
ItemService.addItem(player,100201812, 1); //<Prime Archon Royal Dagger>
ItemService.addItem(player,101701643, 2); //<Prime Archon Royal Bow>
ItemService.addItem(player,167010259, 50); //<Manastone: Attack +6 / Crit Strike +9>
ItemService.addItem(player,167010261, 50); //<Manastone: Attack +6 / Magical Accuracy +8>
ItemService.addItem(player,190100225, 1); //<Tamed Lion (1 day)>
ItemService.addItem(player,187000227, 1); //<Prime Guardian Royal Combat Wings>
ItemService.addItem(player,187000228, 1); //<Prime Archon Royal Combat Wings>
PacketSendUtility.sendMessage(player, "\uE022 Ranger Gears has been added to your Inventory. \uE022");
}

if(param[0].equals("sorcerer")){
ItemService.addItem(player,110102137, 1); //<Prime Guardian Royal Tunic>
ItemService.addItem(player,113101942, 1); //<Prime Guardian Royal Leggings>
ItemService.addItem(player,114101976, 1); //<Prime Guardian Royal Shoes>
ItemService.addItem(player,111101935, 1); //<Prime Guardian Royal Gloves>
ItemService.addItem(player,112101879, 1); //<Prime Guardian Royal Pauldrons>
ItemService.addItem(player,125005029, 1); //<Prime Guardian Royal Bandana>
ItemService.addItem(player,123001588, 1); //<Prime Guardian Royal Magic Waistband>
ItemService.addItem(player,120015056, 2); //<Prime Guardian Royal Magic Earrings>
ItemService.addItem(player,121001554, 1); //<Prime Guardian Royal Magic Necklace>
ItemService.addItem(player,122001839, 2); //<Prime Guardian Royal Magic Ring>
ItemService.addItem(player,100501585, 2); //<Prime Guardian Royal Orb>
ItemService.addItem(player,100601691, 2); //<Prime Guardian Royal Spellbook>
ItemService.addItem(player,110102140, 1); //<Prime Archon Royal Tunic>
ItemService.addItem(player,113101945, 1); //<Prime Archon Royal Leggings>
ItemService.addItem(player,114101979, 1); //<Prime Archon Royal Shoes>
ItemService.addItem(player,111101938, 1); //<Prime Archon Royal Gloves>
ItemService.addItem(player,112101882, 1); //<Prime Archon Royal Pauldrons>
ItemService.addItem(player,125005040, 1); //<Prime Archon Royal Bandana>
ItemService.addItem(player,123001590, 1); //<Prime Archon Royal Magic Waistband>
ItemService.addItem(player,120015058, 2); //<Prime Archon Royal Magic Earrings>
ItemService.addItem(player,121001556, 1); //<Prime Archon Royal Magic Necklace>
ItemService.addItem(player,122001841, 2); //<Prime Archon Royal Magic Ring>
ItemService.addItem(player,100501587, 2); //<Prime Archon Royal Orb>
ItemService.addItem(player,100601693, 2); //<Prime Archon Royal Spellbook>
ItemService.addItem(player,167010258, 50); //<Manastone: Magic Boost +29 / Magical Accuracy +8>
ItemService.addItem(player,167010250, 50); //<Manastone: Magic Boost: +29 / HP +50>
ItemService.addItem(player,190100225, 1); //<Tamed Lion (1 day)>
ItemService.addItem(player,187000227, 1); //<Prime Guardian Royal Combat Wings>
ItemService.addItem(player,187000228, 1); //<Prime Archon Royal Combat Wings>
PacketSendUtility.sendMessage(player, "\uE022 Sorcerer Gears has been added to your Inventory. \uE022");
}

if(param[0].equals("spiritmaster")){
ItemService.addItem(player,110102138, 1); //<Prime Guardian Royal Magic Tunic>
ItemService.addItem(player,113101943, 1); //<Prime Guardian Royal Magic Leggings>
ItemService.addItem(player,114101977, 1); //<Prime Guardian Royal Magic Shoes>
ItemService.addItem(player,111101936, 1); //<Prime Guardian Royal Magic Gloves>
ItemService.addItem(player,112101880, 1); //<Prime Guardian Royal Magic Pauldrons>
ItemService.addItem(player,125005030, 1); //<Prime Guardian Royal Magic Bandana>
ItemService.addItem(player,123001588, 1); //<Prime Guardian Royal Magic Waistband>
ItemService.addItem(player,120015056, 2); //<Prime Guardian Royal Magic Earrings>
ItemService.addItem(player,121001554, 1); //<Prime Guardian Royal Magic Necklace>
ItemService.addItem(player,122001839, 2); //<Prime Guardian Royal Magic Ring>
ItemService.addItem(player,100501586, 2); //<Prime Guardian Royal Orb>
ItemService.addItem(player,100601692, 2); //<Prime Guardian Royal Spellbook>
ItemService.addItem(player,110102141, 1); //<Prime Archon Royal Magic Tunic>
ItemService.addItem(player,113101946, 1); //<Prime Archon Royal Magic Leggings>
ItemService.addItem(player,114101980, 1); //<Prime Archon Royal Magic Shoes>
ItemService.addItem(player,111101939, 1); //<Prime Archon Royal Magic Gloves>
ItemService.addItem(player,112101883, 1); //<Prime Archon Royal Magic Pauldrons>
ItemService.addItem(player,125005041, 1); //<Prime Archon Royal Magic Bandana>
ItemService.addItem(player,123001590, 1); //<Prime Archon Royal Magic Waistband>
ItemService.addItem(player,120015058, 2); //<Prime Archon Royal Magic Earrings>
ItemService.addItem(player,121001556, 1); //<Prime Archon Royal Magic Necklace>
ItemService.addItem(player,122001841, 2); //<Prime Archon Royal Magic Ring>
ItemService.addItem(player,100601694, 2); //<Prime Archon Royal Magic Book>
ItemService.addItem(player,100501588, 2); //<Prime Archon Royal Jewel>
ItemService.addItem(player,167010258, 50); //<Manastone: Magic Boost +29 / Magical Accuracy +8>
ItemService.addItem(player,167010250, 50); //<Manastone: Magic Boost: +29 / HP +50>
ItemService.addItem(player,190100225, 1); //<Tamed Lion (1 day)>
ItemService.addItem(player,187000227, 1); //<Prime Guardian Royal Combat Wings>
ItemService.addItem(player,187000228, 1); //<Prime Archon Royal Combat Wings>
PacketSendUtility.sendMessage(player, "\uE022 Spiritmaster Gears has been added to your Inventory. \uE022");
}

if(param[0].equals("gunner")){
ItemService.addItem(player,110302126, 1); //<Prime Guardian Royal Magic Jerkin>
ItemService.addItem(player,113302096, 1); //<Prime Guardian Royal Magic Breeches>
ItemService.addItem(player,114302133, 1); //<Prime Guardian Royal Magic Boots>
ItemService.addItem(player,111302071, 1); //<Prime Guardian Royal Magic Vambrace>
ItemService.addItem(player,112302008, 1); //<Prime Guardian Royal Magic Shoulderguards>
ItemService.addItem(player,125005034, 1); //<Prime Guardian Royal Magic Hat>
ItemService.addItem(player,123001588, 1); //<Prime Guardian Royal Magic Waistband>
ItemService.addItem(player,120015056, 2); //<Prime Guardian Royal Magic Earrings>
ItemService.addItem(player,121001554, 1); //<Prime Guardian Royal Magic Necklace>
ItemService.addItem(player,122001839, 2); //<Prime Guardian Royal Magic Ring>
ItemService.addItem(player,101801457, 2); //<Prime Guardian Royal Pistol>
ItemService.addItem(player,101901355, 2); //<Prime Guardian Royal Aethercannon>
ItemService.addItem(player,110302129, 1); //<Prime Archon Royal Magic Jerkin>
ItemService.addItem(player,113302099, 1); //<Prime Archon Royal Magic Breeches>
ItemService.addItem(player,114302136, 1); //<Prime Archon Royal Magic Boots>
ItemService.addItem(player,111302074, 1); //<Prime Archon Royal Magic Vambrace>
ItemService.addItem(player,112302011, 1); //<Prime Archon Royal Magic Shoulderguards>
ItemService.addItem(player,125005045, 1); //<Prime Archon Royal Magic Hat>
ItemService.addItem(player,123001590, 1); //<Prime Archon Royal Magic Waistband>
ItemService.addItem(player,120015058, 2); //<Prime Archon Royal Magic Earrings>
ItemService.addItem(player,121001556, 1); //<Prime Archon Royal Magic Necklace>
ItemService.addItem(player,122001841, 2); //<Prime Archon Royal Magic Ring>
ItemService.addItem(player,101801458, 2); //<Prime Archon Royal Pistol>
ItemService.addItem(player,101901356, 2); //<Prime Archon Royal Aethercannon>
ItemService.addItem(player,167010258, 50); //<Manastone: Magic Boost +29 / Magical Accuracy +8>
ItemService.addItem(player,167010250, 50); //<Manastone: Magic Boost: +29 / HP +50>
ItemService.addItem(player,190100225, 1); //<Tamed Lion (1 day)>
ItemService.addItem(player,187000227, 1); //<Prime Guardian Royal Combat Wings>
ItemService.addItem(player,187000228, 1); //<Prime Archon Royal Combat Wings>
PacketSendUtility.sendMessage(player, "\uE022 Gunner Gears has been added to your Inventory. \uE022");
}

if(param[0].equals("aethertech")){
ItemService.addItem(player,110551495, 1); //<Prime Guardian Royal Hauberk>
ItemService.addItem(player,113502074, 1); //<Prime Guardian Chausses>
ItemService.addItem(player,114502082, 1); //<Prime Guardian Royal Brogans>
ItemService.addItem(player,111502064, 1); //<Prime Guardian Royal Chain Protective Gloves>
ItemService.addItem(player,112502000, 1); //<Prime Guardian Royal Spaulders>
ItemService.addItem(player,125005037, 1); //<Prime Guardian Royal Chain Helm>
ItemService.addItem(player,123001588, 1); //<Prime Guardian Royal Magic Waistband>
ItemService.addItem(player,120015056, 2); //<Prime Guardian Royal Magic Earrings>
ItemService.addItem(player,121001554, 1); //<Prime Guardian Royal Magic Necklace>
ItemService.addItem(player,122001839, 2); //<Prime Guardian Royal Magic Ring>
ItemService.addItem(player,102101294, 2); //<Prime Guardian Royal Cipher-Blade>
ItemService.addItem(player,110551498, 1); //<Prime Archon Royal Hauberk>
ItemService.addItem(player,113502077, 1); //<Prime Archon Chausses>
ItemService.addItem(player,114502085, 1); //<Prime Archon Royal Brogans>
ItemService.addItem(player,111502067, 1); //<Prime Archon Royal Chain Protective Gloves>
ItemService.addItem(player,112502003, 1); //<Prime Archon Royal Spaulders>
ItemService.addItem(player,125005048, 1); //<Prime Archon Royal Chain Helm>
ItemService.addItem(player,123001590, 1); //<Prime Archon Royal Magic Waistband>
ItemService.addItem(player,120015058, 2); //<Prime Archon Royal Magic Earrings>
ItemService.addItem(player,121001556, 1); //<Prime Archon Royal Magic Necklace>
ItemService.addItem(player,122001841, 2); //<Prime Archon Royal Magic Ring>
ItemService.addItem(player,102101295, 2); //<Prime Archon Royal Cipher-Blade>
ItemService.addItem(player,167010258, 50); //<Manastone: Magic Boost +29 / Magical Accuracy +8>
ItemService.addItem(player,167010250, 50); //<Manastone: Magic Boost: +29 / HP +50>
ItemService.addItem(player,190100225, 1); //<Tamed Lion (1 day)>
ItemService.addItem(player,187000227, 1); //<Prime Guardian Royal Combat Wings>
ItemService.addItem(player,187000228, 1); //<Prime Archon Royal Combat Wings>
PacketSendUtility.sendMessage(player, "\uE022 Aethertech Gears has been added to your Inventory. \uE022");
}

if(param[0].equals("songweaver")){
ItemService.addItem(player,110102139, 1); //<Prime Guardian Royal Cloth Breast Guard>
ItemService.addItem(player,113101944, 1); //<Prime Guardian Royal Cloth Leg Protectors>
ItemService.addItem(player,114101978, 1); //<Prime Guardian Royal Cloth Combat Boots>
ItemService.addItem(player,111101937, 1); //<Prime Guardian Royal Cloth Protective Gloves>
ItemService.addItem(player,112101881, 1); //<Prime Guardian Royal Cloth Shoulder Protectors>
ItemService.addItem(player,125005031, 1); //<Prime Guardian Royal Cloth Combat Bandana>
ItemService.addItem(player,123001588, 1); //<Prime Guardian Royal Magic Waistband>
ItemService.addItem(player,120015056, 2); //<Prime Guardian Royal Magic Earrings>
ItemService.addItem(player,121001554, 1); //<Prime Guardian Royal Magic Necklace>
ItemService.addItem(player,122001839, 2); //<Prime Guardian Royal Magic Ring>
ItemService.addItem(player,102001479, 2); //<Prime Guardian Royal Harp>
ItemService.addItem(player,110102142, 1); //<Prime Archon Royal Cloth Breast Guard>
ItemService.addItem(player,113101947, 1); //<Prime Archon Royal Cloth Leg Protectors>
ItemService.addItem(player,114101981, 1); //<Prime Archon Royal Cloth Combat Boots>
ItemService.addItem(player,111101940, 1); //<Prime Archon Royal Cloth Protective Gloves>
ItemService.addItem(player,112101884, 1); //<Prime Archon Royal Cloth Shoulder Protectors>
ItemService.addItem(player,125005042, 1); //<Prime Archon Royal Cloth Combat Bandana>
ItemService.addItem(player,123001590, 1); //<Prime Archon Royal Magic Waistband>
ItemService.addItem(player,120015058, 2); //<Prime Archon Royal Magic Earrings>
ItemService.addItem(player,121001556, 1); //<Prime Archon Royal Magic Necklace>
ItemService.addItem(player,122001841, 2); //<Prime Archon Royal Magic Ring>
ItemService.addItem(player,102001480, 2); //<Prime Archon Royal Harp>
ItemService.addItem(player,167010258, 50); //<Manastone: Magic Boost +29 / Magical Accuracy +8>
ItemService.addItem(player,167010250, 50); //<Manastone: Magic Boost: +29 / HP +50>
ItemService.addItem(player,190100225, 1); //<Tamed Lion (1 day)>
ItemService.addItem(player,187000227, 1); //<Prime Guardian Royal Combat Wings>
ItemService.addItem(player,187000228, 1); //<Prime Archon Royal Combat Wings>
PacketSendUtility.sendMessage(player, "\uE022 Songweaver Gears has been added to your Inventory. \uE022");
}


if(param[0].equals("info")){
PacketSendUtility.sendMessage(player, "----- \uE079 Insane's Pack Information \uE079 -----\n" +
"STUFF:\n" +
"For Scrolls Type: ' .insanepack scrolls '\n" +
"For Potions Type: ' .insanepack potions '\n" +
"For Godstones Type: ' .insanepack godstone '\n" +
"For 'goodies' Shards , Titles , Idians , ETC Type: ' .insanepack goodies '\n" +
"--------------------------------------------------------------------------\n" +
"GEAR:\n" +
"For \uE079 GLADIATOR \uE079 Type: ' .insanepack gladiator '\n" +
"For \uE079 TEMPLAR \uE079 Type: ' .insanepack templar '\n" +
"For \uE079 CLERIC \uE079 Type: ' .insanepack cleric '\n" +
"For \uE079 CHANTER \uE079 Type: ' .insanepack chanter '\n" +
"For \uE079 ASSASSIN \uE079 Type: ' .insanepack assassin '\n" +
"For \uE079 RANGER \uE079 Type: ' .insanepack ranger '\n" +
"For \uE079 SORCERER \uE079 Type: ' .insanepack sorcerer '\n" +
"For \uE079 SPIRITMASTER \uE079 Type: ' .insanepack spiritmaster '\n" +
"For \uE079 GUNNER \uE079 Type: ' .insanepack gunner '\n" +
"For \uE079 AETHERTECH \uE079 Type: ' .insanepack aethertech '\n" +
"For \uE079 SONGWEAVER \uE079 Type: ' .insanepack songweaver '\n" +
"\uE020 Have fun kickin ass! \uE020");
}

}
@Override
public void onFail(Player player, String message) {
PacketSendUtility.sendMessage(player, "Syntax:' .insanepack info '");
}
}