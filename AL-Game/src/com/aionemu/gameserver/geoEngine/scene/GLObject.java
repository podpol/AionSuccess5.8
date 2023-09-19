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
package com.aionemu.gameserver.geoEngine.scene;

/**
 * Describes a GL object. An encapsulation of a certain object on the native side of the graphics library. This class is used to track
 */
public abstract class GLObject implements Cloneable {

	/**
	 * The ID of the object, usually depends on its type. Typically returned from calls like glGenTextures, glGenBuffers, etc.
	 */
	protected int id = -1;
	/**
	 * A reference to a "handle". By hard referencing a certain object, it's possible to find when a certain GLObject is no longer used, and to delete its instance from the graphics library.
	 */
	protected Object handleRef = null;
	/**
	 * True if the data represented by this GLObject has been changed and needs to be updated before used.
	 */
	protected boolean updateNeeded = true;
	/**
	 * The type of the GLObject, usually specified by a subclass.
	 */
	protected final Type type;

	public static enum Type {

		/**
		 * Vertex buffers are used to describe geometry data and it's attributes.
		 */
		VertexBuffer,
		/**
		 * ShaderSource is a shader source code that controls the output of a certain rendering pipeline, like vertex position or fragment color.
		 */
		ShaderSource,
		/**
		 * A Shader is an aggregation of ShaderSources, collectively they cooperate to control the vertex and fragment processor.
		 */
		Shader,
	}

	public GLObject(Type type) {
		this.type = type;
		this.handleRef = new Object();
	}

	/**
	 * Protected constructor that doesn't allocate handle ref. This is used in subclasses for the createDestructableClone().
	 */
	protected GLObject(Type type, int id) {
		this.type = type;
		this.id = id;
	}

	/**
	 * Sets the ID of the GLObject. This method is used in Renderer and must not be called by the user.
	 *
	 * @param id
	 *            The ID to set
	 */
	public void setId(int id) {
		if (this.id != -1) {
			throw new IllegalStateException("ID has already been set for this GL object.");
		}

		this.id = id;
	}

	/**
	 * @return The ID of the object. Should not be used by user code in most cases.
	 */
	public int getId() {
		return id;
	}

	public void setUpdateNeeded() {
		updateNeeded = true;
	}

	/**
	 *
	 */
	public void clearUpdateNeeded() {
		updateNeeded = false;
	}

	public boolean isUpdateNeeded() {
		return updateNeeded;
	}

	@Override
	public String toString() {
		return type.name() + " " + Integer.toHexString(hashCode());
	}

	/**
	 * This should create a deep clone. For a shallow clone, use createDestructableClone().
	 *
	 * @return
	 */
	@Override
	protected GLObject clone() {
		try {
			GLObject obj = (GLObject) super.clone();
			obj.handleRef = new Object();
			obj.id = -1;
			obj.updateNeeded = true;
			return obj;
		}
		catch (CloneNotSupportedException ex) {
			throw new AssertionError();
		}
	}

	// @Override
	// public boolean equals(Object other){
	// if (this == other)
	// return true;
	// if (!(other instanceof GLObject))
	// return false;
	//
	// }
	// Specialized calls to be used by object manager only.

	/**
	 * Called when the GL context is restarted to reset all IDs. Prevents "white textures" on display restart.
	 */
	public abstract void resetObject();

	/**
	 * Creates a shallow clone of this GL Object. The deleteObject method should be functional for this object.
	 */
	public abstract GLObject createDestructableClone();
}
