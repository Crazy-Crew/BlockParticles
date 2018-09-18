package org.inventivetalent.particle;

public class ParticleException extends RuntimeException {

    public ParticleException() {
    }

    ParticleException(String message) {
        super(message);
    }

    ParticleException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParticleException(Throwable cause) {
        super(cause);
    }

    public ParticleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}