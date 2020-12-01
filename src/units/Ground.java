package units;

/**
 * Interface, melyet szárazföldi egységek valósítanak meg
 */
public interface Ground {
    /**
     * Megtámadja a paraméterben megadott egységet, ha túléli vissza támad
     * @param other megtámadandó szárazföldi egység
     */
    void attack(GroundUnit other);

    /**
     * Megsebzi a paraméterben megadott egységet, fegyverétől függő támadással
     * @param other megsebzendő szárazföldi egység
     */
    void damage(GroundUnit other);
}
