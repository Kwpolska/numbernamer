package com.chriswarrick.numbernamer;

/* Number Namer v0.2.1
 * Copyright © 2016, Chris Warrick.
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
    private static String[] tens = new String[] {
            null, null, "zwanzig", "dreißig", "vierzig",
            "fünfzig", "sechzig", "siebzig", "achtzig", "neunzig"
    };

    public GermanNumberNamer() {
        negativeWordSpace = "minus ";
        separatorHT = '\0';
        separatorG = '\0';
        separatorGN = '\0';
        zeroToNineteen = new String[] {
                "null", "eins", "zwei", "drei", "vier",
                "fünf", "sechs", "sieben", "acht", "neun",
                "zehn", "elf", "zwölf", "dreizehn", "vierzehn",
                "fünfzehn", "sechzehn", "siebzehn", "achtzehn", "neunzehn"
        };
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
