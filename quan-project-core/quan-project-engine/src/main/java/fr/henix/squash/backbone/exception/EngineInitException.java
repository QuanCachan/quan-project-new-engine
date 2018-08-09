package fr.henix.squash.backbone.exception;

/**
 *
 * @author qtran
 */
public class EngineInitException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public EngineInitException() {
        super();
    }

    public EngineInitException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public EngineInitException(String arg0) {
        super(arg0);
    }

    public EngineInitException(Throwable cause) {
        super(cause);
    }

}
