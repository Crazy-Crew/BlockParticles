/*
 * Copyright 2016 inventivetalent. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ''AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and contributors and should not be interpreted as representing official policies,
 *  either expressed or implied, of anybody else.
 */

package org.inventivetalent.reflection.minecraft;

import org.inventivetalent.reflection.resolver.*;
import org.inventivetalent.reflection.resolver.minecraft.NMSClassResolver;

import javax.annotation.Nullable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;

public class DataWatcher {

	static ClassResolver    classResolver    = new ClassResolver();
	static NMSClassResolver nmsClassResolver = new NMSClassResolver();

	static Class<?> ItemStack        = nmsClassResolver.resolveSilent("ItemStack");
	static Class<?> ChunkCoordinates = nmsClassResolver.resolveSilent("ChunkCoordinates");
	static Class<?> BlockPosition    = nmsClassResolver.resolveSilent("BlockPosition");
	static Class<?> Vector3f         = nmsClassResolver.resolveSilent("Vector3f");
	static Class<?> DataWatcher      = nmsClassResolver.resolveSilent("DataWatcher");
	static Class<?> Entity           = nmsClassResolver.resolveSilent("Entity");
	static Class<?> TIntObjectMap    = classResolver.resolveSilent("gnu.trove.map.TIntObjectMap", "net.minecraft.util.gnu.trove.map.TIntObjectMap");

	static ConstructorResolver DataWacherConstructorResolver = new ConstructorResolver(DataWatcher);

	static FieldResolver DataWatcherFieldResolver = new FieldResolver(DataWatcher);

	static MethodResolver TIntObjectMapMethodResolver = new MethodResolver(TIntObjectMap);
	static MethodResolver DataWatcherMethodResolver   = new MethodResolver(DataWatcher);

	public static Object newDataWatcher(@Nullable Object entity) throws ReflectiveOperationException {
		return DataWacherConstructorResolver.resolve(new Class[] { Entity }).newInstance(entity);
	}

	public static Object setValue(Object dataWatcher, int index, Object dataWatcherObject/*1.9*/, Object value) throws ReflectiveOperationException {
		if (Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1)) {
			return V1_8.setValue(dataWatcher, index, value);
		} else {
			return V1_9.setItem(dataWatcher, index, dataWatcherObject, value);
		}
	}

	public static Object setValue(Object dataWatcher, int index, V1_9.ValueType type, Object value) throws ReflectiveOperationException {
		return setValue(dataWatcher, index, type.getType(), value);
	}

	public static Object setValue(Object dataWatcher, int index, Object value, FieldResolver dataWatcherObjectFieldResolver/*1.9*/, String... dataWatcherObjectFieldNames/*1.9*/) throws ReflectiveOperationException {
		if (Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1)) {
			return V1_8.setValue(dataWatcher, index, value);
		} else {
			Object dataWatcherObject = dataWatcherObjectFieldResolver.resolve(dataWatcherObjectFieldNames).get(null/*Should be a static field*/);
			return V1_9.setItem(dataWatcher, index, dataWatcherObject, value);
		}
	}

	public static Object getValue(DataWatcher dataWatcher, int index) throws ReflectiveOperationException {
		if (Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1)) {
			return V1_8.getValue(dataWatcher, index);
		} else {
			return V1_9.getValue(dataWatcher, index);
		}
	}

	//TODO: update type-ids to 1.9
	public static int getValueType(Object value) {
		int type = 0;
		if (value instanceof Number) {
			if (value instanceof Byte) {
				type = 0;
			} else if (value instanceof Short) {
				type = 1;
			} else if (value instanceof Integer) {
				type = 2;
			} else if (value instanceof Float) {
				type = 3;
			}
		} else if (value instanceof String) {
			type = 4;
		} else if (value != null && value.getClass().equals(ItemStack)) {
			type = 5;
		} else if (value != null && (value.getClass().equals(ChunkCoordinates) || value.getClass().equals(BlockPosition))) {
			type = 6;
		} else if (value != null && value.getClass().equals(Vector3f)) {
			type = 7;
		}

		return type;
	}

	/**
	 * Helper class for versions newer than 1.9
	 */
	public static class V1_9 {

		static Class<?> DataWatcherItem   = nmsClassResolver.resolveSilent("DataWatcher$Item");//>= 1.9 only
		static Class<?> DataWatcherObject = nmsClassResolver.resolveSilent("DataWatcherObject");//>= 1.9 only

		static ConstructorResolver DataWatcherItemConstructorResolver;//>=1.9 only

		static FieldResolver DataWatcherItemFieldResolver;//>=1.9 only
		static FieldResolver DataWatcherObjectFieldResolver;//>=1.9 only

		public static Object newDataWatcherItem(Object dataWatcherObject, Object value) throws ReflectiveOperationException {
			if (DataWatcherItemConstructorResolver == null) { DataWatcherItemConstructorResolver = new ConstructorResolver(DataWatcherItem); }
			return DataWatcherItemConstructorResolver.resolveFirstConstructor().newInstance(dataWatcherObject, value);
		}

		public static Object setItem(Object dataWatcher, int index, Object dataWatcherObject, Object value) throws ReflectiveOperationException {
			return setItem(dataWatcher, index, newDataWatcherItem(dataWatcherObject, value));
		}

		public static Object setItem(Object dataWatcher, int index, Object dataWatcherItem) throws ReflectiveOperationException {
			Map<Integer, Object> map = (Map<Integer, Object>) DataWatcherFieldResolver.resolve("c").get(dataWatcher);
			map.put(index, dataWatcherItem);
			return dataWatcher;
		}

		//		public static Object getValue(Object dataWatcher, int index) throws ReflectiveOperationException {
		//			Map<Integer, Object> map = (Map<Integer, Object>) DataWatcherFieldResolver.resolve("c").get(dataWatcher);
		//			return map.get(index);
		//		}

		public static Object getItem(Object dataWatcher, Object dataWatcherObject) throws ReflectiveOperationException {
			return DataWatcherMethodResolver.resolve(new ResolverQuery("c", DataWatcherObject)).invoke(dataWatcher, dataWatcherObject);
		}

		public static Object getValue(Object dataWatcher, Object dataWatcherObject) throws ReflectiveOperationException {
			return DataWatcherMethodResolver.resolve("get").invoke(dataWatcher, dataWatcherObject);
		}

		public static Object getValue(Object dataWatcher, ValueType type) throws ReflectiveOperationException {
			return getValue(dataWatcher, type.getType());
		}

		public static Object getItemObject(Object item) throws ReflectiveOperationException {
			if (DataWatcherItemFieldResolver == null) { DataWatcherItemFieldResolver = new FieldResolver(DataWatcherItem); }
			return DataWatcherItemFieldResolver.resolve("a").get(item);
		}

		public static int getItemIndex(Object dataWatcher, Object item) throws ReflectiveOperationException {
			int index = -1;//Return -1 if the item is not in the DataWatcher
			Map<Integer, Object> map = (Map<Integer, Object>) DataWatcherFieldResolver.resolve("c").get(dataWatcher);
			for (Map.Entry<Integer, Object> entry : map.entrySet()) {
				if (entry.getValue().equals(item)) {
					index = entry.getKey();
					break;
				}
			}
			return index;
		}

		public static Type getItemType(Object item) throws ReflectiveOperationException {
			if (DataWatcherObjectFieldResolver == null) { DataWatcherObjectFieldResolver = new FieldResolver(DataWatcherObject); }
			Object object = getItemObject(item);
			Object serializer = DataWatcherObjectFieldResolver.resolve("b").get(object);
			Type[] genericInterfaces = serializer.getClass().getGenericInterfaces();
			if (genericInterfaces.length > 0) {
				Type type = genericInterfaces[0];
				if (type instanceof ParameterizedType) {
					Type[] actualTypes = ((ParameterizedType) type).getActualTypeArguments();
					if (actualTypes.length > 0) {
						return actualTypes[0];
					}
				}
			}
			return null;
		}

		public static Object getItemValue(Object item) throws ReflectiveOperationException {
			if (DataWatcherItemFieldResolver == null) { DataWatcherItemFieldResolver = new FieldResolver(DataWatcherItem); }
			return DataWatcherItemFieldResolver.resolve("b").get(item);
		}

		public static void setItemValue(Object item, Object value) throws ReflectiveOperationException {
			DataWatcherItemFieldResolver.resolve("b").set(item, value);
		}

		public enum ValueType {

			/**
			 * Byte
			 */
			ENTITY_FLAG("Entity", 57 /*"ax", "ay"*/),
			/**
			 * Integer
			 */
			ENTITY_AIR_TICKS("Entity", 58/*"ay", "az"*/),
			/**
			 * String
			 */
			ENTITY_NAME("Entity", 59/*"az", "aA"*/),
			/**
			 * Byte &lt; 1.9 Boolean &gt; 1.9
			 */
			ENTITY_NAME_VISIBLE("Entity", 60/*"aA", "aB"*/),
			/**
			 * Boolean
			 */
			ENTITY_SILENT("Entity", 61/*"aB", "aC"*/),

			//////////

			//TODO: Add EntityLiving#as (Byte)
			ENTITY_as("EntityLiving", 2/* "as", "at"*/),

			/**
			 * Float
			 */
			ENTITY_LIVING_HEALTH("EntityLiving", "HEALTH"),

			//TODO: Add EntityLiving#f (Integer) - Maybe active potions?
			ENTITY_LIVING_f("EntityLiving", 2/*"f"*/),

			//TODO: Add EntityLiving#g (Boolean) - Maybe ambient potions?
			ENTITY_LIVING_g("EntityLiving", 3/*"g"*/),

			//TODO: Add EntityLiving#h (Integer)
			ENTITY_LIVING_h("EntityLiving", 4/*"h"*/),

			//////////

			/**
			 * Byte
			 */
			ENTITY_INSENTIENT_FLAG("EntityInsentient", 0/* "a"*/),

			///////////

			/**
			 * Integer
			 */
			ENTITY_SLIME_SIZE("EntitySlime", 0/* "bt", "bu"*/),

			/////////////

			//TODO: Add EntityWither#a (Integer)
			ENTITY_WITHER_a("EntityWither", 0/*"a"*/),

			//TODO:  Add EntityWither#b (Integer)
			ENTITY_WIHER_b("EntityWither", 1/*"b"*/),

			//TODO: Add EntityWither#c (Integer)
			ENTITY_WITHER_c("EntityWither", 2/*"c"*/),

			//TODO: Add EntityWither#bv (Integer) - (DataWatcherObject<Integer>[] bv, seems to be an array of {a, b, c})
			ENTITY_WITHER_bv("EntityWither", 3/*"bv", "bw"*/),

			//TODO: Add EntityWither#bw (Integer)
			ENTITY_WITHER_bw("EntityWither", 4/*"bw", "bx"*/),

			///////////

			/**
			 * Float
			 */
			ENTITY_HUMAN_ABSORPTION_HEARTS("EntityHuman", 0 /*"a"*/),

			/**
			 * Integer
			 */
			ENTITY_HUMAN_SCORE("EntityHuman", 1 /*"b"*/),

			/**
			 * Byte
			 */
			ENTITY_HUMAN_SKIN_LAYERS("EntityHuman", 2 /*"bp", "bq"*/),

			/**
			 * Byte (0 = left, 1 = right)
			 */
			ENTITY_HUMAN_MAIN_HAND("EntityHuman", 3/*"bq", "br"*/);

			private Object type;

			ValueType(String className, String... fieldNames) {
				try {
					this.type = new FieldResolver(nmsClassResolver.resolve(className)).resolve(fieldNames).get(null);
				} catch (Exception e) {
					if (Minecraft.VERSION.newerThan(Minecraft.Version.v1_9_R1)) {
						System.err.println("[ReflectionHelper] Failed to find DataWatcherObject for " + className + " " + Arrays.toString(fieldNames));
					}
				}
			}

			ValueType(String className, int index) {
				try {
					this.type = new FieldResolver(nmsClassResolver.resolve(className)).resolveIndex(index).get(null);
				} catch (Exception e) {
					if (Minecraft.VERSION.newerThan(Minecraft.Version.v1_9_R1)) {
						System.err.println("[ReflectionHelper] Failed to find DataWatcherObject for " + className + " #" + index);
					}
				}
			}

			public boolean hasType() {
				return getType() != null;
			}

			public Object getType() {
				return type;
			}
		}
	}

	/**
	 * Helper class for versions older than 1.8
	 */
	public static class V1_8 {

		static Class<?> WatchableObject = nmsClassResolver.resolveSilent("WatchableObject", "DataWatcher$WatchableObject");//<=1.8 only

		static ConstructorResolver WatchableObjectConstructorResolver;//<=1.8 only

		static FieldResolver WatchableObjectFieldResolver;//<=1.8 only

		public static Object newWatchableObject(int index, Object value) throws ReflectiveOperationException {
			return newWatchableObject(getValueType(value), index, value);
		}

		public static Object newWatchableObject(int type, int index, Object value) throws ReflectiveOperationException {
			if (WatchableObjectConstructorResolver == null) { WatchableObjectConstructorResolver = new ConstructorResolver(WatchableObject); }
			return WatchableObjectConstructorResolver.resolve(new Class[] {
					int.class,
					int.class,
					Object.class }).newInstance(type, index, value);
		}

		public static Object setValue(Object dataWatcher, int index, Object value) throws ReflectiveOperationException {
			int type = getValueType(value);

			Object map = DataWatcherFieldResolver.resolve("dataValues").get(dataWatcher);
			TIntObjectMapMethodResolver.resolve(new ResolverQuery("put", int.class, Object.class)).invoke(map, index, newWatchableObject(type, index, value));

			return dataWatcher;
		}

		public static Object getValue(Object dataWatcher, int index) throws ReflectiveOperationException {
			Object map = DataWatcherFieldResolver.resolve("dataValues").get(dataWatcher);

			return TIntObjectMapMethodResolver.resolve(new ResolverQuery("get", int.class)).invoke(map, index);
		}

		public static int getWatchableObjectIndex(Object object) throws ReflectiveOperationException {
			if (WatchableObjectFieldResolver == null) { WatchableObjectFieldResolver = new FieldResolver(WatchableObject); }
			return WatchableObjectFieldResolver.resolve("b").getInt(object);
		}

		public static int getWatchableObjectType(Object object) throws ReflectiveOperationException {
			if (WatchableObjectFieldResolver == null) { WatchableObjectFieldResolver = new FieldResolver(WatchableObject); }
			return WatchableObjectFieldResolver.resolve("a").getInt(object);
		}

		public static Object getWatchableObjectValue(Object object) throws ReflectiveOperationException {
			if (WatchableObjectFieldResolver == null) { WatchableObjectFieldResolver = new FieldResolver(WatchableObject); }
			return WatchableObjectFieldResolver.resolve("c").get(object);
		}

	}

	private DataWatcher() {
	}
}
