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
package com.aionemu.gameserver.model.stats.calc.functions;

import com.aionemu.gameserver.model.stats.calc.Stat2;
import com.aionemu.gameserver.model.stats.calc.StatOwner;
import com.aionemu.gameserver.model.stats.container.StatEnum;

/**
 * @author ATracer
 */
public class StatFunctionProxy implements IStatFunction, Comparable<IStatFunction> {

	private final StatOwner owner;
	private final IStatFunction proxiedFunction;
	private final StatEnum stat;

	public StatFunctionProxy(StatOwner owner, IStatFunction statFunction) {
		this.owner = owner;
		this.proxiedFunction = statFunction;
		this.stat = statFunction.getName();
	}

	public StatFunctionProxy(StatOwner owner, IStatFunction statFunction, StatEnum statEnum) {
		this.owner = owner;
		this.proxiedFunction = statFunction;
		this.stat = statEnum;
	}

	public IStatFunction getProxiedFunction() {
		return proxiedFunction;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatFunctionProxy other = (StatFunctionProxy) obj;
		if (owner == null) {
			if (other.owner != null)
				return false;
		}
		else if (!owner.equals(other.owner))
			return false;
		return true;
	}

	@Override
	public int compareTo(IStatFunction o) {
		return proxiedFunction.compareTo(o);
	}

	@Override
	public StatOwner getOwner() {
		return owner;
	}

	@Override
	public StatEnum getName() {
		return stat;
	}

	@Override
	public boolean isBonus() {
		return proxiedFunction.isBonus();
	}

	@Override
	public int getPriority() {
		return proxiedFunction.getPriority();
	}

	@Override
	public int getValue() {
		return proxiedFunction.getValue();
	}

	@Override
	public boolean validate(Stat2 stat, IStatFunction statFunction) {
		return proxiedFunction.validate(stat, statFunction);
	}

	@Override
	public void apply(Stat2 stat) {
		proxiedFunction.apply(stat);
	}
	
	@Override
	public boolean hasConditions() {
		return proxiedFunction.hasConditions();
	}

	@Override
	public String toString() {
		return "Proxy [name=" + proxiedFunction.getName() + ", bonus=" + isBonus() + ", value=" + getValue()
			+ ", priority=" + getPriority() + ", owner=" + owner + "]";
	}
}
