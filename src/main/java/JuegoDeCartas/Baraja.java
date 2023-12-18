package JuegoDeCartas;

import java.util.Arrays;
import java.util.Collections;

public class Baraja {
    private Carta[] cartas;

    public Baraja(){
        cartas = new Carta[52];

        int conta = 1;
        Palo palo = Palo.HEART;
        for (int i = 0; i < cartas.length; i++){
            if (i == 13) {
                palo = Palo.SPADE;
                conta = 1;
            } else if (i == 26) {
                palo = Palo.CLUB;
                conta = 1;
            } else if (i == 39) {
                palo = Palo.DIAMOND;
                conta = 1;
            }
            cartas[i] = new Carta(palo, conta);
            conta++;
        }
    }

    public void barajar(){
        if (cartas[0] != null && cartas[cartas.length-1] != null){
            Collections.shuffle(Arrays.asList(cartas));
        }
    }

    public Carta obtenerCarta(){
        if (quedanCartas()) {
            Carta aux;
            if (cartas[0] == null) {
                int i = 1;
                while (cartas[i] == null) {
                    i++;
                }
                aux = cartas[i];
                cartas[i] = null;
            } else {
                aux = cartas[0];
                cartas[0] = null;
            }
            return aux;
        }else {
            return null;
        }
    }

    public boolean quedanCartas(){
        boolean quedan = false;
        int i = 0;
        while (i < cartas.length && !quedan){
            if (cartas[i] != null){
                quedan = true;
            }else{
                i++;
            }
        }
        return quedan;
    }

    @Override
    public String toString() {
        String aux = "";
        for (Carta carta : cartas) {
            aux += carta + "\n";
        }

        return aux;
    }
}
