package com.chriswarrick.numbernamer;

/* Number Namer v0.2.2
 * Copyright © 2017, Chris Warrick.
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
        zeroToNineteen = new String[] {
                "zero", "jeden", "dwa", "trzy", "cztery",
                "pięć", "sześć", "siedem", "osiem", "dziewięć",
                "dziesięć", "jedenaście", "dwanaście", "trzynaście", "czternaście",
                "piętnaście", "szesnaście", "siedemnaście", "osiemnaście", "dziewiętnaście"
        };
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
