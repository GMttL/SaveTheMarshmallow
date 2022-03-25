package websiteBlocker;

/**
 * This interface...
 *
 * <p>
 *     Requests users implement flush() for their particular system.
 * </p>
 *
 * @author Gabriel Mititelu
 */
public interface DNSFlushingBehaviour {

    boolean DNSflush();
}
