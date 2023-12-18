package JuegoDeCartas;

import com.diogonunes.jcolor.Attribute;

import static com.diogonunes.jcolor.Ansi.colorize;


public enum Palo {
    HEART('♥', Color.RED),
    SPADE('♠', Color.BLACK),
    CLUB('♣', Color.BLACK),
    DIAMOND('♦', Color.RED);

    private final Color color;

    private final char palo;

    Palo(char palo, Color color){
        this.palo = palo;
        this.color = color;
    }

    public char getPalo() {
        return palo;
    }

    @Override
    public String toString() {
        return colorize(palo + "", color.getColor());
    }

    public enum Color {
        RED(Attribute.RED_TEXT()),
        BLACK(Attribute.BLACK_TEXT());

        private final Attribute color;

        Color(Attribute color){
            this.color = color;
        }

        public Attribute getColor() {
            return color;
        }
    }
}

