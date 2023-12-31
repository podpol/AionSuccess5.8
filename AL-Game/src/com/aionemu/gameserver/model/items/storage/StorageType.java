/*
 * =====================================================================================*
 * This file is part of Aion-Unique (Aion-Unique Home Software Development)             *
 * Aion-Unique Development is a closed Aion Project that use Old Aion Project Base      *
 * Like Aion-Lightning, Aion-Engine, Aion-Core, Aion-Extreme, Aion-NextGen, ArchSoft,   *
 * Aion-Ger, U3J, Encom And other Aion project, All Credit Content                      *
 * That they make is belong to them/Copyright is belong to them. And All new Content    *
 * that Aion-Unique make the copyright is belong to Aion-Unique                         *
 * You may have agreement with Aion-Unique Development, before use this Engine/Source   *
 * You have agree with all of Term of Services agreement with Aion-Unique Development   *
 * =====================================================================================*
 */
package com.aionemu.gameserver.model.items.storage;

/****/
/** Author Rinzler (Encom)
/****/

public enum StorageType
{
	//Cube & Warehouse.
	CUBE(0, 27, 9, 162), //4.9
	REGULAR_WAREHOUSE(1, 112, 8),
	ACCOUNT_WAREHOUSE(2, 16, 8),
	LEGION_WAREHOUSE(3, 80, 8),
	
	//Pet's Bag.
	PET_BAG_6(32, 6, 6),
    PET_BAG_12(33, 12, 6),
    PET_BAG_18(34, 18, 6),
	PET_BAG_22(45, 22, 6), //5.8
    PET_BAG_24(35, 24, 6),
	PET_BAG_28(44, 28, 6), //5.1
	PET_BAG_30(40, 30, 6),
	
	//Cash Pet's Bag.
    CASH_PET_BAG_12(36, 12, 6),
    CASH_PET_BAG_18(37, 18, 6),
    CASH_PET_BAG_30(38, 30, 6),
    CASH_PET_BAG_24(39, 24, 6),
    CASH_PET_BAG_26(41, 26, 6),
    CASH_PET_BAG_32(42, 32, 6),
	CASH_PET_BAG_34(43, 34, 6),
	
	//Housing.
	HOUSE_STORAGE_01(60, 9, 9),
	HOUSE_STORAGE_02(61, 9, 9),
	HOUSE_STORAGE_03(62, 9, 9),
	HOUSE_STORAGE_04(63, 9, 9),
	HOUSE_STORAGE_05(64, 9, 9),
	HOUSE_STORAGE_06(65, 9, 9),
	HOUSE_STORAGE_07(66, 9, 9),
	HOUSE_STORAGE_08(67, 9, 9),
	HOUSE_STORAGE_09(68, 18, 9),
	HOUSE_STORAGE_10(69, 18, 9),
	HOUSE_STORAGE_11(70, 18, 9),
	HOUSE_STORAGE_12(71, 18, 9),
	HOUSE_STORAGE_14(73, 18, 9),
	HOUSE_STORAGE_16(75, 27, 9),
	HOUSE_STORAGE_18(77, 27, 9),
    HOUSE_STORAGE_20(79, 0, 0),
	
	//Other.
	BROKER(126),
	MAILBOX(127);
	
	//Pet's.
	public static final int PET_BAG_MIN = 32;
	public static final int PET_BAG_MAX = 45;
	
	//Housing.
	public static final int HOUSE_WH_MIN = 60;
	public static final int HOUSE_WH_MAX = 79;
	
	private int id;
	private int limit;
	private int length;
	private int specialLimit;
	
	private StorageType(int id, int limit, int length, int specialLimit) {
		this(id, limit, length);
		this.specialLimit = specialLimit;
	}
	
	private StorageType(int id, int limit, int length) {
		this(id);
		this.limit = limit;
		this.length = length;
	}
	
	private StorageType(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public int getLimit() {
		return limit;
	}
	
	public int getLength() {
		return length;
	}
	
	public int getSpecialLimit() {
		return specialLimit;
	}
	
	public static StorageType getStorageTypeById(int id) {
		for (StorageType st: values()) {
			if (st.id == id)
				return st;
		}
		return null;
	}
	
	public static int getStorageId(int limit, int length) {
		for (StorageType st: values()) {
			if (st.limit == limit && st.length == length)
				return st.id;
		}
		return -1;
	}
}