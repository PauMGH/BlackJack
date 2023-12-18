import JuegoDeCartas.Carta;

public class Jugador {
    private final String nombre;
    private Carta[] mano;
    private int puntuacion;

    public Jugador(String nombre){
        this.nombre = nombre;
        this.mano = new Carta[12];
        this.puntuacion = 0;
    }

    public void anyadeMano(Carta carta){
        int i = 0;
        while (mano[i] != null && i < 6){
            i++;
        }
        mano[i] = carta;
    }

    public void reseteaMano(){
        mano = new Carta[12];
    }

    public void imprimeMano(){
        for (Carta carta: mano){
            if (carta != null) {
                System.out.print(carta + " ");
            }
        }
        System.out.println();
    }

    public String getNombre() {
        return nombre;
    }
    public Carta[] getMano() {
        return mano;
    }
    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}

//mano tamaño maximo 6