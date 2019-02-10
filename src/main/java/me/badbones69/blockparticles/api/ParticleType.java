package me.badbones69.blockparticles.api;

public enum ParticleType {
	
	PARTICLE("Particle"), FOUNTAIN("Fountain");
	
	private String name;
	
	private ParticleType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static ParticleType[] getTypes() {
		return ParticleType.values();
	}
	
}