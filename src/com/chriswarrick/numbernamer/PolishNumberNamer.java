package com.chriswarrick.numbernamer;

/** The number namer for Polish. */
public class PolishNumberNamer extends ThousandGroupedNumberNamer {
    private static String[] groupNames = new String[] { null, "tysiąc", "milion", "miliard",
            "bilion", "biliard", "trylion", "tryliard" };
    /** Group names in first plural form */
    private static String[] groupNamesPlural1 = new String[] { null, "tysiące", "miliony",
            "miliardy", "biliony", "biliardy", "tryliony", "tryliardy" };
    /** Group names in second plural form */
    private static String[] groupNamesPlural2 = new String[] { null, "tysięcy", "milionów",
            "miliardów", "bilionów", "biliardów", "trylionów", "tryliardów" };
    /** Names of hundreds (0 is null, 1 is "hundred" etc.) */
    private static String[] hundreds = new String[] {
            null, "sto", "dwieście", "trzysta", "czterysta", "pięćset", "sześćset", "siedemset",
            "osiemset", "dziewięćset"
    };
    private static String[] zeroToNineteen = new String[] {
            "zero", "jeden", "dwa", "trzy", "cztery",
            "pięć", "sześć", "siedem", "osiem", "dziewięć",
            "dziesięć", "jedenaście", "dwanaście", "trzynaście", "czternaście",
            "piętnaście", "szesnaście", "siedemnaście", "osiemnaście", "dziewiętnaście"
    };
    private static String[] tens = new String[] {
            null, null, "dwadzieścia", "trzydzieści", "czterdzieści",
            "pięćdziesiąt", "sześćdziesiąt", "siedemdziesiąt", "osiemdziesiąt",
            "dziewięćdziesiąt"
    };

    public PolishNumberNamer() {
        negativeWordSpace = "minus ";
        separatorTO = ' ';
        separatorHT = ' ';
        separatorG = ' ';
        separatorGN = ' ';
    }

    protected String nameGroup(int groupIndex, int groupValue) {
        // Parts copied from gettext.
        if (groupValue == 1) {
            return groupNames[groupIndex];
        } else if (groupValue % 10 >= 2 && groupValue % 10 <= 4
                && (groupValue % 100 < 10 || groupValue % 100 >= 20)) {
            return groupNamesPlural1[groupIndex];
        } else {
            return groupNamesPlural2[groupIndex];
        }
    }

    protected void nameHundred(int hundred, StringBuilder sb) {
        sb.append(hundreds[hundred]);
    }

    protected boolean nameTO(int tenOne, int ten, int one, int groupIndex, StringBuilder sb) {
        // special casing for group tens/ones
        if (groupIndex != 0 && tenOne == 1)
            return false;
        if (tenOne >= 20) {
            sb.append(tens[ten]);
            if (one != 0) {
                sb.append(separatorTO);
                sb.append(zeroToNineteen[one]);
            }
        } else if (tenOne != 0) {
            sb.append(zeroToNineteen[tenOne]);
        }
        return true;
    }
}
