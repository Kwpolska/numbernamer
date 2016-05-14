package com.chriswarrick.numbernamer;

/** The number namer for English. */
public class EnglishNumberNamer extends ThousandGroupedNumberNamer {
    /* Word for hundred */
    private static String hundredWord = "hundred";
    private static String[] groupNames = new String[] {
            null, "thousand", "million", "billion", "trillion", "quardillion", "quintillion",
            "sextillion"
    };
    /** Separator between hundreds value and the word for hundred */
    private static char separatorHW = ' ';
    static {
        zeroToNineteen = new String[] {
                "zero", "one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine",
                "ten", "eleven", "twelve", "thirteen", "fourteen",
                "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
        };
        tens = new String[] {
                null, null, "twenty", "thirty", "forty",
                "fifty", "sixty", "seventy", "eighty", "ninety"
        };

        negativeWordSpace = "minus ";
        separatorTO = '-';
        separatorHT = ' ';
        separatorG = ' ';
        separatorGN = ' ';
    }

    protected String nameGroup(int groupIndex, int groupValue) {
        return groupNames[groupIndex];
    }

    protected void nameHundred(int hundred, StringBuilder sb) {
        if (hundred != 1) {
            // special casing: 100 → hundred
            sb.append(zeroToNineteen[hundred]);
            sb.append(separatorHW);
        }
        sb.append(hundredWord);
    }

    protected void nameTO(int tenOne, int ten, int one, int groupIndex, StringBuilder sb) {
        if (tenOne >= 20) {
            sb.append(tens[ten]);
            if (one != 0) {
                sb.append(separatorTO);
                sb.append(zeroToNineteen[one]);
            }
        } else if (tenOne != 0) {
            sb.append(zeroToNineteen[tenOne]);
        }
    }
}
