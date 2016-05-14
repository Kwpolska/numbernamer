package com.chriswarrick.numbernamer;

/** A console application for naming numbers. */
public class NamerConsole {
    public static void main(String[] args) {
        java.util.Scanner s = new java.util.Scanner(System.in);
        NumberNamer namer = null;
        System.out.print("Language: ");
        String language = s.nextLine();

        switch (language) {
        case "en":
            namer = new EnglishNumberNamer();
            break;
        case "pl":
            namer = new PolishNumberNamer();
            break;
        case "de":
            namer = new GermanNumberNamer();
            break;
        default:
            System.out.println("Unknown language");
            System.exit(2);
        }

        try {
            while (s.hasNextLong()) {
                System.out.println(namer.name(s.nextLong()));
            }
        } finally {
            s.close();
        }
    }

}
