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
package com.aionemu.gameserver.model;

public enum TeleportAnimation
{
    NO_ANIMATION(0, 0),
    BEAM_ANIMATION(1, 3),
    JUMP_ANIMATION(3, 10),
    JUMP_ANIMATION_2(4, 10),
    FIRE_ANIMATION(4, 0x0B),//5.0
    JUMP_ANIMATION_3(8, 3),
	MAGE_ANIMATION(8, 10);
	
    private int startAnimation;
    private int endAnimation;
	
    private TeleportAnimation(int startAnimation, int endAnimation) {
	   this.startAnimation = startAnimation;
       this.endAnimation = endAnimation;
	}
	
    public int getStartAnimationId() {
        return startAnimation;
    }
	
    public int getEndAnimationId() {
        return endAnimation;
    }
	
    public boolean isNoAnimation() {
        return getStartAnimationId() == 0;
    }
}