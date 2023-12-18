import java.util.Scanner;

public class MetodosApoyo {
    public static String getString(String message){
        Scanner sc = new Scanner(System.in);
        System.out.println(message);
        return sc.nextLine();
    }

    public static int getInteger(String message){
        Scanner sc = new Scanner(System.in);
        System.out.println(message);
        while (!sc.hasNextInt()) {
            System.out.println("Debes darme un numero.");
            sc.next();
        }
        return sc.nextInt();
    }

    public static String pideCarta(){
        String sigue;
        System.out.println();
        do {
            sigue = getString("Quieres pedir otra carta? (Si/No)");
            if (sigue.isEmpty() || ( !(sigue.equalsIgnoreCase("SI")) && (!(sigue.equalsIgnoreCase("NO"))) ) ){
                System.out.println("No me vale esa respuesta caballero.");
                System.out.println();
            }
        }while (!(sigue.equalsIgnoreCase("SI"))  && !(sigue.equalsIgnoreCase("NO")));
        System.out.println();

        return sigue;
    }

    public static String continua(){
        String sigue;
        System.out.println();
        do {
            sigue = getString("Bien jugado, quieres juagar otra partida?");
            if (sigue.isEmpty() || ( !(sigue.equalsIgnoreCase("SI")) && (!(sigue.equalsIgnoreCase("NO"))) ) ){
                System.out.println("No me vale esa respuesta caballero.");
                System.out.println();
            }
        }while (!(sigue.equalsIgnoreCase("SI"))  && !(sigue.equalsIgnoreCase("NO")));
        System.out.println();

        return sigue;
    }
}
