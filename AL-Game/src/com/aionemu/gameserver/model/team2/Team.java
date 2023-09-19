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
package com.aionemu.gameserver.model.team2;

import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.network.aion.AionServerPacket;
import com.google.common.base.Predicate;

import java.util.Collection;

/**
 * @author ATracer
 */
public interface Team<M, TM extends TeamMember<M>> {

    Integer getTeamId();

    TM getMember(Integer objectId);

    boolean hasMember(Integer objectId);

    void addMember(TM member);

    void removeMember(TM member);

    void removeMember(Integer objectId);

    Collection<M> getMembers();

    Collection<M> getOnlineMembers();

    void onEvent(TeamEvent event);

    Collection<TM> filter(Predicate<TM> predicate);

    Collection<M> filterMembers(Predicate<M> predicate);

    void sendPacket(AionServerPacket packet);

    void sendPacket(AionServerPacket packet, Predicate<M> predicate);

    int onlineMembers();

    Race getRace();

    int size();

    boolean isFull();
}
