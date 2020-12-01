package game;

import exceptions.NotEnoughResource;

import java.io.Serializable;

/**
 * Nyersanyagokat összefogó osztály
 */
public class ResourceStack implements Serializable {
    private double wood,stone,silver,favour;

    /**
     * Konstruktor
     * @param wood fa mennyisége
     * @param stone kő mennyisége
     * @param silver ezüst mennyisége
     * @param favour áldás mennyisége
     */
    public ResourceStack(double wood, double stone, double silver, double favour) {
        this.wood = wood;
        this.stone = stone;
        this.silver = silver;
        this.favour = favour;
    }

    /**
     * Konstruktor
     * @param wood fa mennyisége
     * @param stone kő mennyisége
     * @param silver ezüst mennyisége
     */
    public ResourceStack(double wood, double stone, double silver){
        this(wood,stone,silver,0);
    }

    /**
     * Összead 2 nyersanyag kupacot
     * @param wood fa mennyisége
     * @param stone kő mennyisége
     * @param silver ezüst mennyisége
     */
    public void add(double wood,double stone,double silver){
        this.wood += wood;
        this.stone += stone;
        this.silver += silver;
    }

    /**
     * Összead 2 nyersanyag kupacot
     * @param wood fa mennyisége
     * @param stone kő mennyisége
     * @param silver ezüst mennyisége
     * @param favour áldás mennyisége
     */
    public void add(double wood,double stone,double silver, double favour){
        this.wood += wood;
        this.stone += stone;
        this.silver += silver;
        this.favour += favour;
    }

    /**
     * Összead 2 nyersanyag kupacot
     * @param r másik nyersanyaghalom
     */
    public void add(ResourceStack r){
        this.wood += r.wood;
        this.stone += r.stone;
        this.silver += r.silver;
        this.favour += r.favour;
    }

    /**
     * Ellenőrzi, hogy van elég minden nyersanyagból
     * @param r szükséges nyersanyag
     * @return
     */
    public boolean hasEnough(ResourceStack r){
        if(this.wood - r.wood < 0 || this.stone - r.stone < 0 || this.silver - r.silver< 0 || this.favour - r.favour < 0){
            return false;
        }
        return true;
    }

    /**
     * Minden nyersanyagot megszoroz a paraméterben megadott számmal
     * @param d
     */
    public void multiply(double d){
        this.wood *= d;
        this.stone *= d;
        this.silver *= d;
        this.favour *= d;
    }

    /**
     * Levon paraméterben megadott nyersanyag mennyiséget
     * @param wood szükséges fa mennyisége
     * @param stone szükséges kő mennyisége
     * @param silver szükséges ezüst mennyisége
     * @throws NotEnoughResource kivétel dobódik, ha nincs elég nyersanyag
     */
    public void subtract(double wood,double stone,double silver)throws NotEnoughResource{
        if(!hasEnough(new ResourceStack(wood,stone,silver,0))){
            throw new NotEnoughResource();
        }
        this.wood -= wood;
        this.stone -= stone;
        this.silver -= silver;
    }

    /**
     *Levon paraméterben megadott nyersanyag mennyiséget
     * @param wood szükséges fa mennyisége
     * @param stone szükséges kő mennyisége
     * @param silver szükséges ezüst mennyisége
     * @param favour szükséges áldás mennyisége
     * @throws NotEnoughResource kivétel dobódik, ha nincs elég nyersanyag
     */
    public void subtract(double wood,double stone,double silver, double favour)throws NotEnoughResource{
        if(!hasEnough(new ResourceStack(wood,stone,silver,favour))){
            throw new NotEnoughResource();
        }
        this.wood -= wood;
        this.stone -= stone;
        this.silver -= silver;
        this.favour -= favour;
    }

    /**
     * Levon paraméterben megadott nyersanyag mennyiséget
     * @param r szükséges nyersanyag
     * @throws NotEnoughResource kivétel dobódik, ha nincs elég nyersanyag
     */
    public void subtract(ResourceStack r) throws NotEnoughResource{
        if(!hasEnough(r)){
            throw new NotEnoughResource();
        }
        this.wood -= r.wood;
        this.stone -= r.stone;
        this.silver -= r.silver;
        this.favour -= r.favour;
    }

    public double getWood() {
        return wood;
    }

    public void setWood(double wood) {
        this.wood = wood;
    }

    public double getStone() {
        return stone;
    }

    public void setStone(double stone) {
        this.stone = stone;
    }

    public double getSilver() {
        return silver;
    }

    public void setSilver(double silver) {
        this.silver = silver;
    }

    public double getFavour() {
        return favour;
    }

    public void setFavour(double favour) {
        this.favour = favour;
    }
}
