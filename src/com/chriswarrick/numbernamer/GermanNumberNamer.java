package com.chriswarrick.numbernamer;

/** The number namer for English. */
/** The number namer for German. */
public class GermanNumberNamer extends ThousandGroupedNumberNamer {
    private static String hundredWord = "hundert";
    private static String[] groupNames = new String[] {
            null, "tausend", " Milion", " Miliarde", " Billion", " Billarde", " Trillion",
            " Trilliarde"
    };
    private static String[] groupNamesPlural = new String[] {
            null, "tausend", " Milionen", " Miliarden", " Billionen", " Billarden", " Trillionen",
            " Trilliarden"
    };
    private static String[] zeroToNineteen = new String[] {
            "null", "eins", "zwei", "drei", "vier",
            "fünf", "sechs", "sieben", "acht", "neun",
            "zehn", "elf", "zwölf", "dreizehn", "vierzehn",
            "fünfzehn", "sechzehn", "siebzehn", "achtzehn", "neunzehn"
    };
    private static String[] tens = new String[] {
            null, null, "zwanzig", "dreißig", "vierzig",
            "fünfzig", "sechzig", "siebzig", "achtzig", "neunzig"
    };

    public GermanNumberNamer() {
        negativeWordSpace = "minus ";
        separatorHT = '\0';
        separatorG = '\0';
        separatorGN = '\0';
    }

    protected String nameGroup(int groupIndex, int groupValue) {
        if (groupIndex > 1 && groupValue == 1) {
            return groupNames[groupIndex] + " ";
        } else if (groupIndex > 1) {
            return groupNamesPlural[groupIndex] + " ";
        }
        return groupNames[groupIndex];
    }

    protected void nameHundred(int hundred, StringBuilder sb) {
        if (hundred != 1) {
            // special casing: 100 → hundred
            sb.append(zeroToNineteen[hundred]);
        }
        sb.append(hundredWord);
    }

    protected boolean nameTO(int tenOne, int ten, int one, int groupIndex, StringBuilder sb) {
        if (tenOne == 1 && groupIndex > 1) {
            sb.append("ein");
            return true;
        } else if (tenOne == 1 && groupIndex == 1) {
            return false;
        } else if (tenOne >= 20) {
            if (one == 1) {
                sb.append("einund");
            } else if (one != 0) {
                sb.append(zeroToNineteen[one]);
                sb.append("und");
            }
            sb.append(tens[ten]);
        } else if (tenOne != 0) {
            sb.append(zeroToNineteen[tenOne]);
        }
        return true;
    }
}
