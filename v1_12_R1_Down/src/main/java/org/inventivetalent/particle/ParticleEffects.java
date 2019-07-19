package org.inventivetalent.particle;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.inventivetalent.reflection.minecraft.Minecraft;
import org.inventivetalent.reflection.minecraft.Minecraft.Version;
import org.inventivetalent.reflection.resolver.ConstructorResolver;
import org.inventivetalent.reflection.resolver.FieldResolver;
import org.inventivetalent.reflection.resolver.MethodResolver;
import org.inventivetalent.reflection.resolver.minecraft.NMSClassResolver;

import java.util.ArrayList;
import java.util.Collection;

public enum ParticleEffects {
	
	EXPLOSION_NORMAL("explode"),
	EXPLOSION_LARGE("largeexplode"),
	EXPLOSION_HUGE("hugeexplosion"),
	FIREWORKS_SPARK("fireworksSpark"),
	WATER_BUBBLE("bubble"),
	WATER_SPLASH("splash"),
	WATER_WAKE("wake"),
	SUSPENDED("suspended"),
	SUSPENDED_DEPTH("depthsuspend"),
	CRIT("crit"),
	CRIT_MAGIC("magicCrit"),
	SMOKE_NORMAL("smoke"),
	SMOKE_LARGE("largesmoke"),
	SPELL("spell"),
	SPELL_INSTANT("instantSpell"),
	SPELL_MOB("mobSpell", Version.v1_7_R1, Feature.COLOR),
	SPELL_MOB_AMBIENT("mobSpellAmbient"),
	SPELL_WITCH("witchMagic"),
	DRIP_WATER("dripWater"),
	DRIP_LAVA("dripLava"),
	VILLAGER_ANGRY("angryVillager"),
	VILLAGER_HAPPY("happyVillager"),
	TOWN_AURA("townaura"),
	NOTE("note", Version.v1_7_R1, Feature.COLOR),
	PORTAL("portal"),
	ENCHANTMENT_TABLE("enchantmenttable"),
	FLAME("flame"),
	LAVA("lava"),
	FOOTSTEP("footstep"),
	CLOUD("cloud"),
	REDSTONE("reddust", Version.v1_7_R1, Feature.COLOR),
	SNOWBALL("snowballpoof"),
	SNOW_SHOVEL("snowshovel"),
	SLIME("slime"),
	HEART("heart"),
	BARRIER("barrier", Version.v1_8_R1),
	ITEM_CRACK("iconcrack_", Version.v1_7_R1, Feature.DATA) {
		//Unsupported methods
		@Override
		public void send(Collection<? extends Player> receivers, Location location, double offsetX, double offsetY, double offsetZ, double speed, int count) {
			throw new ParticleException("Cannot use default send() for ITEM_CRACK");
		}
		
		@Override
		public void send(Collection<? extends Player> receivers, Location location, double offsetX, double offsetY, double offsetZ, double speed, int count, double range) {
			throw new ParticleException("Cannot use default send() for ITEM_CRACK");
		}
		
		@Override
		public void send(Collection<? extends Player> receivers, double x, double y, double z, double offsetX, double offsetY, double offsetZ, double speed, int count, double range) {
			throw new ParticleException("Cannot use default send() for ITEM_CRACK");
		}
		
		@Override
		public void send(Collection<? extends Player> receivers, double x, double y, double z, double offsetX, double offsetY, double offsetZ, double speed, int count) {
			throw new ParticleException("Cannot use default send() for ITEM_CRACK");
		}
	},
	BLOCK_CRACK("blockcrack_", Version.v1_7_R1, Feature.DATA) {
		//Unsupported methods
		@Override
		public void send(Collection<? extends Player> receivers, Location location, double offsetX, double offsetY, double offsetZ, double speed, int count) {
			throw new ParticleException("Cannot use default send() for BLOCK_CRACK");
		}
		
		@Override
		public void send(Collection<? extends Player> receivers, Location location, double offsetX, double offsetY, double offsetZ, double speed, int count, double range) {
			throw new ParticleException("Cannot use default send() for BLOCK_CRACK");
		}
		
		@Override
		public void send(Collection<? extends Player> receivers, double x, double y, double z, double offsetX, double offsetY, double offsetZ, double speed, int count, double range) {
			throw new ParticleException("Cannot use default send() for BLOCK_CRACK");
		}
		
		@Override
		public void send(Collection<? extends Player> receivers, double x, double y, double z, double offsetX, double offsetY, double offsetZ, double speed, int count) {
			throw new ParticleException("Cannot use default send() for BLOCK_CRACK");
		}
	},
	BLOCK_DUST("blockdust_", Version.v1_7_R1, Feature.DATA) {
		@Override
		public void sendData(Collection<? extends Player> receivers, double x, double y, double z, double offsetX, double offsetY, double offsetZ, double speed, int count, int itemId, byte data) {
			data = -1;
			super.sendData(receivers, x, y, z, offsetX, offsetY, offsetZ, speed, count, itemId, data);
		}
		
		//Unsupported methods
		@Override
		public void send(Collection<? extends Player> receivers, Location location, double offsetX, double offsetY, double offsetZ, double speed, int count) {
			throw new ParticleException("Cannot use default send() for BLOCK_DUST");
		}
		
		@Override
		public void send(Collection<? extends Player> receivers, Location location, double offsetX, double offsetY, double offsetZ, double speed, int count, double range) {
			throw new ParticleException("Cannot use default send() for BLOCK_DUST");
		}
		
		@Override
		public void send(Collection<? extends Player> receivers, double x, double y, double z, double offsetX, double offsetY, double offsetZ, double speed, int count, double range) {
			throw new ParticleException("Cannot use default send() for BLOCK_DUST");
		}
		
		@Override
		public void send(Collection<? extends Player> receivers, double x, double y, double z, double offsetX, double offsetY, double offsetZ, double speed, int count) {
			throw new ParticleException("Cannot use default send() for BLOCK_DUST");
		}
	},
	WATER_DROP("droplet", Version.v1_8_R1),
	ITEM_TAKE("take", Version.v1_8_R1),
	MOB_APPEARANCE("mobappearance", Version.v1_8_R1),
	DRAGON_BREATH("dragonbreath", Version.v1_9_R1),
	END_ROD("endRod", Version.v1_9_R1),
	DAMAGE_INDICATOR("damageIndicator", Version.v1_9_R1),
	SWEEP_ATTACK("sweepAttack", Version.v1_9_R1),
	FALLING_DUST("fallingDust", Version.v1_10_R1),
	SPIT("spit", Version.v1_11_R1),
	TOTEM("totem", Version.v1_11_R1);
	
	protected Particle particle;
	private String name;
	private Version minVersion;
	private Feature feature;
	
	ParticleEffects(String name, Version minVersion, Feature feature) {
		this.name = name;
		this.minVersion = minVersion;
		this.feature = feature;
		if(feature != null) {
			this.particle = feature.particle(this);
		}else {
			this.particle = new Particle(this);
		}
	}
	
	ParticleEffects(String name, Version minVersion) {
		this(name, minVersion, null);
	}
	
	ParticleEffects(String name) {
		this(name, Version.v1_7_R1);
	}
	
	static Enum<?> getEnum(String enumFullName) {
		String[] x = enumFullName.split("\\.(?=[^.]+$)");
		if(x.length == 2) {
			String enumClassName = x[0];
			String enumName = x[1];
			try {
				Class<Enum> cl = (Class<Enum>) Class.forName(enumClassName);
				return Enum.valueOf(cl, enumName);
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	protected static void sendPacket(Object packet, Player p) throws IllegalArgumentException, ClassNotFoundException {
		if(Reflection.EntityPlayerFieldResolver == null) {
			Reflection.EntityPlayerFieldResolver = new FieldResolver(Reflection.NMS_CLASS_RESOLVER.resolve("EntityPlayer"));
		}
		if(Reflection.PlayerConnectionMethodResolver == null) {
			Reflection.PlayerConnectionMethodResolver = new MethodResolver(Reflection.NMS_CLASS_RESOLVER.resolve("PlayerConnection"));
		}
		
		try {
			Object handle = Minecraft.getHandle(p);
			final Object connection = Reflection.EntityPlayerFieldResolver.resolve("playerConnection").get(handle);
			Reflection.PlayerConnectionMethodResolver.resolve("sendPacket").invoke(connection, packet);
		}catch(ReflectiveOperationException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * @return the minimum {@link Version} required for this particle
	 */
	public Version getMinVersion() {
		return minVersion;
	}
	
	/**
	 * @return <code>true</code> if this particle is compatible with the server version
	 */
	public boolean isCompatible() {
		return Minecraft.VERSION.newerThan(getMinVersion());
	}
	
	//*** Public send methods
	
	//General
	
	/**
	 * Check if this particle has special {@link ParticleEffects.Feature}s - Particles with features cannot use the default send() methods - Particles without features cannot use special sendColor or sendData methods
	 *
	 * @param feature {@link ParticleEffects.Feature} to check
	 * @return <code>true</code> if this particle has the feature
	 * @see #hasNoFeatures()
	 */
	public boolean hasFeature(Feature feature) {
		return this.feature == feature;
	}
	
	/**
	 * Check if this particle has no {@link ParticleEffects.Feature}s - Particles without features cannot use special sendColor or sendData methods - Particles with features cannot use the default send() methods
	 *
	 * @return <code>true</code> if this particle has no special features
	 * @see #hasFeature(Feature)
	 */
	public boolean hasNoFeatures() {
		return this.feature == null;
	}
	
	/**
	 * Send this particle
	 *
	 * @param receivers Collection of receivers
	 * @param x         X-Location
	 * @param y         Y-Location
	 * @param z         Z-Location
	 * @param offsetX   X-Offset
	 * @param offsetY   Y-Offset
	 * @param offsetZ   Z-Offset
	 * @param speed     Particle speed
	 * @param count     Particle count
	 * @param range     Maximum visibility range
	 */
	public void send(Collection<? extends Player> receivers, double x, double y, double z, double offsetX, double offsetY, double offsetZ, double speed, int count, double range) {
		double squareRange = range * range;
		Collection<Player> inRange = new ArrayList<>();
		for(Player receiver : receivers) {
			if(receiver.getLocation().distanceSquared(new Location(receiver.getWorld(), x, y, z)) <= squareRange) {
				inRange.add(receiver);
			}
		}
		this.send(inRange, x, y, z, offsetX, offsetY, offsetZ, speed, count);
	}
	
	/**
	 * Send this particle
	 *
	 * @param receivers Collection of receivers
	 * @param location  {@link Location}
	 * @param offsetX   X-Offset
	 * @param offsetY   Y-Offset
	 * @param offsetZ   Z-Offset
	 * @param speed     Particle speed
	 * @param count     Particle count
	 * @param range     Maximum visibility range
	 */
	public void send(Collection<? extends Player> receivers, Location location, double offsetX, double offsetY, double offsetZ, double speed, int count, double range) {
		receivers = new ArrayList<>(receivers);
		receivers.removeIf(player -> !player.getWorld().getName().equals(location.getWorld().getName()));
		send(receivers, location.getX(), location.getY(), location.getZ(), offsetX, offsetY, offsetZ, speed, count, range);
	}
	
	//Color
	
	/**
	 * Send this particle
	 *
	 * @param receivers Collection of receivers
	 * @param x         X-Location
	 * @param y         Y-Location
	 * @param z         Z-Location
	 * @param offsetX   X-Offset
	 * @param offsetY   Y-Offset
	 * @param offsetZ   Z-Offset
	 * @param speed     Particle speed
	 * @param count     Particle count
	 */
	public void send(Collection<? extends Player> receivers, double x, double y, double z, double offsetX, double offsetY, double offsetZ, double speed, int count) {
		this.particle.send(receivers, x, y, z, offsetX, offsetY, offsetZ, speed, count);
	}
	
	/**
	 * Send this particle
	 *
	 * @param receivers Collection of receivers
	 * @param location  {@link Location}
	 * @param offsetX   X-Offset
	 * @param offsetY   Y-Offset
	 * @param offsetZ   Z-Offset
	 * @param speed     Particle speed
	 * @param count     Particle count
	 */
	public void send(Collection<? extends Player> receivers, Location location, double offsetX, double offsetY, double offsetZ, double speed, int count) {
		receivers = new ArrayList<>(receivers);
		receivers.removeIf(player -> !player.getWorld().getName().equals(location.getWorld().getName()));
		this.particle.send(receivers, location.getX(), location.getY(), location.getZ(), offsetX, offsetY, offsetZ, speed, count);
	}
	
	/**
	 * Send this particle with a color
	 *
	 * @param receivers Collection of receivers
	 * @param x         X-Location
	 * @param y         Y-Location
	 * @param z         Z-Location
	 * @param color     {@link Color} of the particle
	 * @throws ParticleException if this particle cannot have a color
	 */
	public void sendColor(Collection<? extends Player> receivers, double x, double y, double z, Color color) {
		if(!hasFeature(Feature.COLOR)) {
			throw new ParticleException("This particle cannot be colored");
		}
		((ColoredParticle) this.particle).send(receivers, x, y, z, color);
	}
	
	/**
	 * Send this particle with a color
	 *
	 * @param receivers Collection of receivers
	 * @param x         X-Location
	 * @param y         Y-Location
	 * @param z         Z-Location
	 * @param color     {@link  java.awt.Color} of the particle
	 * @throws ParticleException if this particle cannot have a color
	 */
	public void sendColor(Collection<? extends Player> receivers, double x, double y, double z, java.awt.Color color) {
		if(!hasFeature(Feature.COLOR)) {
			throw new ParticleException("This particle cannot be colored");
		}
		((ColoredParticle) this.particle).send(receivers, x, y, z, color);
	}
	
	//Data
	
	public void sendColor(Collection<? extends Player> receivers, Location location, Color color) {
		receivers = new ArrayList<>(receivers);
		receivers.removeIf(player -> !player.getWorld().getName().equals(location.getWorld().getName()));
		sendColor(receivers, location.getX(), location.getY(), location.getZ(), color);
	}
	
	public void sendColor(Collection<? extends Player> receivers, Location location, java.awt.Color color) {
		receivers = new ArrayList<>(receivers);
		receivers.removeIf(player -> !player.getWorld().getName().equals(location.getWorld().getName()));
		sendColor(receivers, location.getX(), location.getY(), location.getZ(), color);
	}
	
	/**
	 * Send this particle with block or item data
	 *
	 * @param receivers Collection of receivers
	 * @param x         X-Location
	 * @param y         Y-Location
	 * @param z         Z-Location
	 * @param offsetX   X-Offset
	 * @param offsetY   Y-Offset
	 * @param offsetZ   Z-Offset
	 * @param speed     Particle speed
	 * @param count     Particle count
	 * @param itemId    ID of the item/block
	 * @param data      Data of the item/block
	 * @throws ParticleException if this particle cannot have block or item data
	 */
	public void sendData(Collection<? extends Player> receivers, double x, double y, double z, double offsetX, double offsetY, double offsetZ, double speed, int count, int itemId, byte data) {
		if(!hasFeature(Feature.DATA)) {
			throw new ParticleException("This particle cannot have block/item data");
		}
		((DataParticle) this.particle).send(receivers, x, y, z, offsetX, offsetY, offsetZ, speed, count, itemId, data);
	}
	
	/**
	 * Send this particle with block or item data
	 *
	 * @param receivers Collection of receivers
	 * @param x         X-Location
	 * @param y         Y-Location
	 * @param z         Z-Location
	 * @param offsetX   X-Offset
	 * @param offsetY   Y-Offset
	 * @param offsetZ   Z-Offset
	 * @param speed     Particle speed
	 * @param count     Particle count
	 * @param itemStack {@link ItemStack} containing the ID&amp;data of the item/block
	 * @throws ParticleException if this particle cannot have block or item data
	 */
	public void sendData(Collection<? extends Player> receivers, double x, double y, double z, double offsetX, double offsetY, double offsetZ, double speed, int count, ItemStack itemStack) {
		sendData(receivers, x, y, z, offsetX, offsetY, offsetZ, speed, count, itemStack.getType().getId(), itemStack.getData().getData());
	}
	
	public enum Feature {
		/**
		 * The particle can be colore
		 */
		COLOR {
			@Override
			Particle particle(ParticleEffects effect) {
				return new ColoredParticle(effect);
			}
		},
		/**
		 * The particle can have item or block data
		 */
		DATA {
			@Override
			Particle particle(ParticleEffects effect) {
				return new DataParticle(effect);
			}
		};
		
		Feature() {
		}
		
		public boolean applies(ParticleEffects particleEffects) {
			return particleEffects.hasFeature(this);
		}
		
		Particle particle(ParticleEffects effect) {
			return new Particle(effect);
		}
		
	}
	
	static class Particle {
		
		final Class<?> PacketParticle = Reflection.NMS_CLASS_RESOLVER.resolveSilent("PacketPlayOutWorldParticles");
		final Class<?> EnumParticle = Reflection.NMS_CLASS_RESOLVER.resolveSilent("EnumParticle");
		final ConstructorResolver PacketParticleConstructorResolver = new ConstructorResolver(PacketParticle);
		
		ParticleEffects effect;
		
		Particle(ParticleEffects particleEffects) {
			this.effect = particleEffects;
		}
		
		void send(Collection<? extends Player> receivers, double x, double y, double z, double offsetX, double offsetY, double offsetZ, double speed, int count, int... extra) {
			if(!this.effect.isCompatible()) {
				throw new ParticleException("Particle " + this.effect + " is not compatible with the server version (" + Minecraft.VERSION + " < " + this.effect.getMinVersion() + ")");
			}
			try {
				if(Minecraft.VERSION.newerThan(Version.v1_8_R1)) {
					send_1_8(receivers, x, y, z, offsetX, offsetY, offsetZ, speed, count, extra);
				}else {//1.7
					send_1_7(effect.name, receivers, x, y, z, offsetX, offsetY, offsetZ, speed, count);
				}
			}catch(Exception e) {
				throw new ParticleException("Failed to send particle " + this.effect + " to " + receivers.toString(), e);
			}
		}
		
		void send_1_8(Collection<? extends Player> receivers, double x, double y, double z, double offsetX, double offsetY, double offsetZ, double speed, int count, int... extra) throws Exception {
			Object packet = PacketParticleConstructorResolver.resolve(new Class[] {
			EnumParticle,
			boolean.class,
			float.class,
			float.class,
			float.class,
			float.class,
			float.class,
			float.class,
			float.class,
			int.class,
			int[].class}).newInstance(//
			getEnum(EnumParticle.getName() + "." + effect.name()),//particle enum
			true,//long-distance
			(float) x,//X
			(float) y,//Y
			(float) z,//Z
			(float) offsetX,//X-offset
			(float) offsetY,//Y-offset
			(float) offsetZ,//Z-offset
			(float) speed,//Speed
			count,//Count
			extra//Extra
			);
			
			for(Player receiver : receivers) {
				sendPacket(packet, receiver);
			}
		}
		
		void send_1_7(String name, Collection<? extends Player> receivers, double x, double y, double z, double offsetX, double offsetY, double offsetZ, double speed, int count) throws Exception {
			Object packet = PacketParticleConstructorResolver.resolve(new Class[] {
			String.class,
			float.class,
			float.class,
			float.class,
			float.class,
			float.class,
			float.class,
			float.class,
			int.class}).newInstance(//
			name,//Particle name
			(float) x,//X
			(float) y,//Y
			(float) z, //Z
			(float) offsetX,//X
			(float) offsetY,//Y
			(float) offsetZ,//Z
			(float) speed,//Speed
			count//Count
			);
			
			for(Player receiver : receivers) {
				sendPacket(packet, receiver);
			}
		}
		
	}
	
	static class ColoredParticle extends Particle {
		
		ColoredParticle(ParticleEffects particleEffects) {
			super(particleEffects);
		}
		
		void send(Collection<? extends Player> receivers, double x, double y, double z, Color color) {
			send(receivers, x, y, z, getColor(color.getRed()), getColor(color.getGreen()), getColor(color.getBlue()), 1, 0);
		}
		
		void send(Collection<? extends Player> receivers, double x, double y, double z, java.awt.Color color) {
			send(receivers, x, y, z, getColor(color.getRed()), getColor(color.getGreen()), getColor(color.getBlue()), 1, 0);
		}
		
		@Override
		void send(Collection<? extends Player> receivers, double x, double y, double z, double offsetX, double offsetY, double offsetZ, double speed, int count, int... extra) {
			super.send(receivers, x, y, z, offsetX, offsetY, offsetZ, speed, count, extra);
		}
		
		double getColor(double value) {
			if(value <= 0) {
				value = -1;
			}
			return value / 255;
		}
		
	}
	
	static class DataParticle extends Particle {
		
		DataParticle(ParticleEffects particleEffects) {
			super(particleEffects);
		}
		
		void send(Collection<? extends Player> receivers, double x, double y, double z, double offsetX, double offsetY, double offsetZ, double speed, int count, int id, byte data) {
			try {
				if(Minecraft.VERSION.newerThan(Version.v1_8_R1)) {
					send_1_8(receivers, x, y, z, offsetX, offsetY, offsetZ, speed, count, id,
					id | data << 12);
				}else {
					send_1_7(effect.name + id + (data >= 0 ? "_" + data : ""), receivers, x, y, z, offsetX, offsetY, offsetZ, speed, count);
				}
			}catch(Exception e) {
				throw new ParticleException("Failed to send particle " + this.effect + " to " + receivers.toString(), e);
			}
		}
		
		@Override
		void send(Collection<? extends Player> receivers, double x, double y, double z, double offsetX, double offsetY, double offsetZ, double speed, int count, int... extra) {
			super.send(receivers, x, y, z, offsetX, offsetY, offsetZ, speed, count, extra);
		}
		
	}
	
	static class Reflection {
		
		static final NMSClassResolver NMS_CLASS_RESOLVER = new NMSClassResolver();
		//Packets
		private static FieldResolver EntityPlayerFieldResolver;
		private static MethodResolver PlayerConnectionMethodResolver;
		
	}
	
}