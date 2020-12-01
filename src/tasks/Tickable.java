package tasks;

/**
 * Egy interfész, ami minden olyan dolgot reprezentál, amely időben lépni tud.
 */
public interface Tickable {
    /**
     * Időzítő által hívható függvény
     */
    void tick();
}
