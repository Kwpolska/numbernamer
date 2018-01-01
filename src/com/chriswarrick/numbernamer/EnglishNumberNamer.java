package com.chriswarrick.numbernamer;

/* Number Namer v0.2.2
 * Copyright © 2017-2018, Chris Warrick.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions, and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions, and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the author of this software nor the names of
 *    contributors to this software may be used to endorse or promote
 *    products derived from this software without specific prior written
 *    consent.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

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
    private static String[] tens = new String[] {
            null, null, "twenty", "thirty", "forty",
            "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    public EnglishNumberNamer() {
        negativeWordSpace = "minus ";
        separatorTO = '-';
        separatorHT = ' ';
        separatorG = ' ';
        separatorGN = ' ';
        zeroToNineteen = new String[] {
                "zero", "one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine",
                "ten", "eleven", "twelve", "thirteen", "fourteen",
                "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
        };
    }

    protected String nameGroup(int groupIndex, int groupValue) {
        return groupNames[groupIndex];
    }

    protected void nameHundred(int hundred, StringBuilder sb) {
        sb.append(zeroToNineteen[hundred]);
        sb.append(separatorHW);
        sb.append(hundredWord);
    }

    protected boolean nameTO(int tenOne, int ten, int one, int groupIndex, StringBuilder sb) {
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
