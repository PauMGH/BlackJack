import JuegoDeCartas.Baraja;
import JuegoDeCartas.Carta;

public class Game {

    public static void partida(){
        System.out.println("Hola! Bienvenido al blackjack.");
        int nJug =  MetodosApoyo.getInteger("Dime cuantos jugadores van a jugar?");
        if (nJug < 1){
            System.out.println("Si no juega ningún jugador gano yo! Hasta luego!");
        }else{
            Jugador[] jugadores =  new Jugador[nJug+1];
            String nombre;
            String ronda = "si";

            for (int i = 0; i < nJug + 1; i++) {
                if (i == nJug) {
                    jugadores[i] = new Jugador("Banca");
                } else {
                    nombre = MetodosApoyo.getString("Como te llamas jugador " + (i + 1) + "?");
                    jugadores[i] = new Jugador(nombre);
                }
            }
            while (ronda.equals("si")) {
                System.out.println();
                System.out.println("Bien, es la hora del duelo! Voy a repartir cartas!");
                Baraja baraja = new Baraja();
                baraja.barajar();
                repartirCartas(baraja, nJug, jugadores);

                System.out.println("Que empiece la partida!");
                for (int i = 0; i < jugadores.length; i++) {
                    if (i != jugadores.length-1 ){
                        System.out.println("Bien jugador " + (i + 1) + " : " + jugadores[i].getNombre());
                        System.out.println("Veamos que te deparan las cartas...");
                        turnoJP(jugadores[i], baraja);
                        System.out.println();
                    } else {
                        turnoBanca(jugadores, baraja);
                    }
                }
                System.out.println("La mano y puntuación de la banca es:");
                System.out.println("Puntuación: " + calculaPuntuacion(jugadores[jugadores.length-1]));
                System.out.print("Mano: ");
                jugadores[jugadores.length-1].imprimeMano();
                System.out.println();

                System.out.println("Y el ganador ha sido: ");
                System.out.println(calculaGanador(jugadores));
                resetea(jugadores, baraja);
                ronda = MetodosApoyo.continua();
            }
        }
    }

    public static void repartirCartas(Baraja baraja, int nJug, Jugador[] jugadores){
        for (int i = 0; i < nJug + 1; i++){
            jugadores[i].anyadeMano(baraja.obtenerCarta());
            jugadores[i].anyadeMano(baraja.obtenerCarta());
        }
    }

    public static int calculaPuntuacion(Jugador jugador) {
        int suma = 0;
        int pendiente = 0;
        Carta[] mano = jugador.getMano();
        {
            int i = 0;
            while (jugador.getMano()[i] != null && i < jugador.getMano().length) {
                if (mano[i].getSimbolo() == 1){
                    pendiente++;
                }else{
                    if (jugador.getMano()[i].getSimbolo() == 11 || jugador.getMano()[i].getSimbolo() == 12 || jugador.getMano()[i].getSimbolo() == 13){
                        suma+= 10;
                    }else{
                        suma+= jugador.getMano()[i].getSimbolo();
                    }
                }
                i++;
            }
        }
        for (int i = 0; i < pendiente; i++){
            if (suma + 11 > 21){
                suma+= 1;
            }else{
                suma+= 11;
            }
        }

        if (suma > 21){
            suma = -1;
        }

        return suma;
    }

    public static void turnoJP(Jugador jugador, Baraja baraja){
        int puntuacion = calculaPuntuacion(jugador);
        boolean sigue;
        String pide;

        do {
            if (puntuacion == -1) {
                jugador.imprimeMano();
                System.out.println("Lamentablemente, con esta mano te pasas de 21, estás fuera.");
                jugador.setPuntuacion(puntuacion);
                sigue = false;
            } else if (puntuacion == 21) {
                jugador.imprimeMano();
                System.out.println("Madre mia! Eso suma 21, el resto lo tenemos complicado!.");
                jugador.setPuntuacion(puntuacion);
                sigue = false;
            } else {
                System.out.println("Esta es tu puntuación actual: " + puntuacion);
                System.out.print("Y esta es tu mano actual: ");
                jugador.imprimeMano();
                pide = MetodosApoyo.pideCarta();

                if (pide.equalsIgnoreCase("Si")) {
                    jugador.anyadeMano(baraja.obtenerCarta());
                    puntuacion = calculaPuntuacion(jugador);
                    sigue = true;
                } else {
                    sigue = false;
                    System.out.println("Sea pues.");
                    jugador.setPuntuacion(puntuacion);
                }
            }
        }while (sigue);
    }

    public static int calculaMax(Jugador[] jugadores){
        int max = 0;
        for (Jugador jugador : jugadores){
            if (jugador.getPuntuacion() > max){
                max = jugador.getPuntuacion();

            }
        }
        return max;
    }

    public static void turnoBanca(Jugador[] jugadores, Baraja baraja){
       int max = calculaMax(jugadores), puntuacion = calculaPuntuacion(jugadores[jugadores.length-1]);
        while (puntuacion < max && calculaPuntuacion(jugadores[jugadores.length-1]) != -1){
            jugadores[jugadores.length-1].anyadeMano(baraja.obtenerCarta());
            puntuacion = calculaPuntuacion(jugadores[jugadores.length-1]);
        }

    }

    public static String calculaGanador(Jugador[] jugadores){

        String[] ganadores = new String[jugadores.length];
        int i = 0;

        int max = calculaMax(jugadores);

        if (jugadores[jugadores.length-1].getPuntuacion() == max) {
            return "La banca siempre gana!";
        }else{

            for (Jugador jugador : jugadores) {
                if (jugador.getPuntuacion() == max) {
                    ganadores[i] = jugador.getNombre();
                    i++;
                }
            }

            if (i > 1) {
                String aux = "Vaya, tenemos varios ganadores:  \n";
                for (String jugador : ganadores) {
                    if (jugador != null) {
                        if (!jugador.isEmpty()) {
                            aux += jugador + " ";
                        }
                    }
                }
                return aux;
            } else {
                return ganadores[0];
            }
        }
    }

    public static void resetea(Jugador[] jugadores, Baraja baraja){
        baraja = new Baraja();
        for (int i = 0; i < jugadores.length; i++){
            jugadores[i].reseteaMano();
        }
    }
}
