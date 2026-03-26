package org.examples.objets;

public class Duree {
    private int duree;
    public Duree(int duree) {
        if(duree>0){
            this.duree = duree;
        }
    }

    public int getDuree() {
        return duree;
    }
}
