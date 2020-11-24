package game;

import exceptions.NotEnoughResource;

import java.io.Serializable;

public class ResourceStack implements Serializable {
    private double wood,stone,silver,favour;

    public ResourceStack(double wood, double stone, double silver, double favour) {
        this.wood = wood;
        this.stone = stone;
        this.silver = silver;
        this.favour = favour;
    }

    public ResourceStack(double wood, double stone, double silver){
        this(wood,stone,silver,0);
    }

    public void add(double wood,double stone,double silver){
        this.wood += wood;
        this.stone += stone;
        this.silver += silver;
    }

    public void add(double wood,double stone,double silver, double favour){
        this.wood += wood;
        this.stone += stone;
        this.silver += silver;
        this.favour += favour;
    }

    public void add(ResourceStack r){
        this.wood += r.wood;
        this.stone += r.stone;
        this.silver += r.silver;
        this.favour += r.favour;
    }

    public boolean hasEnough(ResourceStack r){
        if(this.wood - r.wood < 0 || this.stone - r.stone < 0 || this.silver - r.silver< 0 || this.favour - r.favour < 0){
            return false;
        }
        return true;
    }

    public void subtract(double wood,double stone,double silver)throws NotEnoughResource{
        if(!hasEnough(new ResourceStack(wood,stone,silver,0))){
            throw new NotEnoughResource();
        }
        this.wood -= wood;
        this.stone -= stone;
        this.silver -= silver;
    }

    public void subtract(double wood,double stone,double silver, double favour)throws NotEnoughResource{
        if(!hasEnough(new ResourceStack(wood,stone,silver,favour))){
            throw new NotEnoughResource();
        }
        this.wood -= wood;
        this.stone -= stone;
        this.silver -= silver;
        this.favour -= favour;
    }

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
