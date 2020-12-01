package units;

/**
 * Interface, melyet vízi egységek valósítanak meg
 */
public interface Naval {
    /**
     * Megtámadja a paraméterben megadott egységet, ha túléli vissza támad
     * @param other megtámadandó vízi egység
     */
    void attack(NavalUnit other);

    /**
     * Megsebzi a paraméterben megadott egységet, fegyverétől függő támadással
     * @param other megsebzendő vízi egység
     */
    void damage(NavalUnit other);
}
