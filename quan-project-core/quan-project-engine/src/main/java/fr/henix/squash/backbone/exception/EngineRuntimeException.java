package fr.henix.squash.backbone.exception;

/**
 *
 * @author qtran
 */
public class EngineRuntimeException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 6250058301881542183L;

    public EngineRuntimeException() {
        super();
    }

    public EngineRuntimeException(String message, Throwable cause) {
        super(message + "\nDetails: " + cause.getMessage(), cause);
    }

    public EngineRuntimeException(String message) {
        super(message);
    }

    public EngineRuntimeException(Throwable cause) {
        super(cause);
    }
}
