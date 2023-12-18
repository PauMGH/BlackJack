package JuegoDeCartas;

public class Carta {
    private final Palo palo;
    private final int simbolo;

    public Carta(Palo palo, int simbolo){
        this.palo = palo;
        this.simbolo = simbolo;
    }

    public int getSimbolo() {
        return simbolo;
    }
    public Palo getPalo() {
        return palo;
    }

    @Override
    public String toString() {
        if (simbolo == 1){
            return "" + 'A' + palo;
        } else if (simbolo == 11) {
            return "" + 'J' + palo;
        } else if (simbolo == 12) {
            return "" + 'Q' + palo;
        } else if (simbolo == 13) {
            return "" + 'K' + palo;
        }else {
            return "" + simbolo + palo;
        }
    }
}
