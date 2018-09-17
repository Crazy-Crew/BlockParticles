package me.badbones69.blockparticles.api;

public enum ParticleType {
	
	PARTICLE("Particle"), FOUNTAIN("Fountain");
	
	String Name;
	
	private ParticleType(String name) {
		this.Name = name;
	}
	
	public static ParticleType[] getTypes() {
		ParticleType[] p = ParticleType.values();
		return p;
	}
	
}