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

import com.aionemu.gameserver.geoEngine.bounding.BoundingVolume;
import com.aionemu.gameserver.geoEngine.collision.Collidable;
import com.aionemu.gameserver.geoEngine.collision.CollisionResults;
import com.aionemu.gameserver.geoEngine.math.Matrix3f;
import com.aionemu.gameserver.geoEngine.math.Matrix4f;
import com.aionemu.gameserver.geoEngine.math.Ray;
import com.aionemu.gameserver.geoEngine.math.Vector3f;

public class Geometry extends Spatial {

	/**
	 * The mesh contained herein
	 */
	protected Mesh mesh;
	protected Matrix4f cachedWorldMat = new Matrix4f();

	/**
	 * Do not use this constructor. Serialization purposes only.
	 */
	public Geometry() {
	}

	/**
	 * Create a geometry node without any mesh data.
	 *
	 * @param name
	 *            The name of this geometry
	 */
	public Geometry(String name) {
		super(name);
	}

	/**
	 * Create a geometry node with mesh data.
	 *
	 * @param name
	 *            The name of this geometry
	 * @param mesh
	 *            The mesh data for this geometry
	 */
	public Geometry(String name, Mesh mesh) {
		this(name);
		if (mesh == null) {
			throw new NullPointerException();
		}

		this.mesh = mesh;
	}

	@Override
	public int getVertexCount() {
		return mesh.getVertexCount();
	}

	@Override
	public int getTriangleCount() {
		return mesh.getTriangleCount();
	}

	public void setMesh(Mesh mesh) {

		this.mesh = mesh;
	}

	public Mesh getMesh() {
		return mesh;
	}

	/**
	 * @return The bounding volume of the mesh, in model space.
	 */
	public BoundingVolume getModelBound() {
		return mesh.getBound();
	}

	/**
	 * Updates the bounding volume of the mesh. Should be called when the mesh has been modified.
	 */
	@Override
	public void updateModelBound() {
		mesh.updateBound();
		worldBound = getModelBound().transform(cachedWorldMat, worldBound);
	}

	public Matrix4f getWorldMatrix() {
		return cachedWorldMat;
	}

	@Override
	public void setModelBound(BoundingVolume modelBound) {
		mesh.setBound(modelBound);
	}

	@Override
	public int collideWith(Collidable other, CollisionResults results) {
		if (other instanceof Ray) {
			if (!worldBound.intersects(((Ray) other))) {
				return 0;
			}
		}
		// NOTE: BIHTree in mesh already checks collision with the
		// mesh's bound
		int prevSize = results.size();
		int added = mesh.collideWith(other, cachedWorldMat, worldBound, results);
		int newSize = results.size();
		for (int i = prevSize; i < newSize; i++) {
			results.getCollisionDirect(i).setGeometry(this);
		}
		return added;
	}

	@Override
	public void setTransform(Matrix3f rotation, Vector3f loc, float scale) {
		cachedWorldMat.loadIdentity();
		cachedWorldMat.setRotationMatrix(rotation);
		cachedWorldMat.scale(scale);
		cachedWorldMat.setTranslation(loc);
	}

	@Override
	public short getCollisionFlags() {
		return mesh.getCollisionFlags();
	}

	@Override
	public void setCollisionFlags(short flags) {
		mesh.setCollisionFlags(flags);
	}
}
