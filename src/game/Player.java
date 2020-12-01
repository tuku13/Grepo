package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Játékost reprezentáló osztály
 */
public class Player implements Serializable {
    String name;
    String password;

    /**
     * Konstruktor
     * @param name játékos neve
     * @param password játékos jelszava
     */
    public Player(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
