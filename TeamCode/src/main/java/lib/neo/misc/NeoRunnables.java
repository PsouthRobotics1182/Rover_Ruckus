/**
 * NeoRunnables | This honestly shouldn't be a module at all, but because this is Java, I'm afraid
 * that's how it has to be. Why is there not an "empty runnable" in the standard library? How did
 * everyone at Oracle think that would never be a common thing? The world may never know.
 * @author David Garland
 */

package lib.neo.misc;

public class NeoRunnables {
    public static class emptyRunnable implements Runnable {
        public void run() {}
    }
}