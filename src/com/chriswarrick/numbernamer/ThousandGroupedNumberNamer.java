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

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Number namer for cultures with:
 * <ul>
 * <li>groups of thousands</li>
 * <li>hundreds tens ones or hundred ones tens word order</li>
 * </ul>
 * Example users: most European languages.
 */
public abstract class ThousandGroupedNumberNamer extends NumberNamer {

    /** The number by which groups are made (default 1000 — every 3 digits) */
    protected static long groupsEvery = 1000;
    /** The numbers 0 to 19 */
    protected static String[] zeroToNineteen;
    /** Names of tens (0 and 1 are null, 2 is “twenty” etc.) */
    protected static String[] tens;
    /** Group names (0 is null, 1 is “thousand” etc.) */
    protected static String[] groupNames;
    /** The word used before negative numbers with a trailing space */
    protected static String negativeWordSpace;
    /** Separator between tens and ones */
    protected static char separatorTO;
    /** Separator between hundreds and tens */
    protected static char separatorHT;
    /** Separator between groups */
    protected static char separatorG;
    /** Separator between group values and names */
    protected static char separatorGN;

    @Override
    public String name(long number) {
        if (number >= 0 && number < 20) {
            return zeroToNineteen[(int) number];
        } else if (number < 0 && number > -20) {
            return negativeWordSpace + zeroToNineteen[(int) (-number)];
        } else if (number == Long.MIN_VALUE) {
            throw new InvalidParameterException("Math.abs(input) must fit in a long");
        }

        StringBuilder sb = new StringBuilder();
        if (number < 0) {
            sb.append(negativeWordSpace);
            number = -number;
        }
        // Split into groups of three (or log(groupsEvery))

        List<Long> groups = new ArrayList<Long>();

        while (number != 0) {
            groups.add(number % groupsEvery);
            number /= groupsEvery;
        }

        int groupValue, gH, gT, gO, gTO;
        boolean namedTO;
        for (int i = groups.size() - 1; i >= 0; i--) {
            groupValue = groups.get(i).intValue();
            if (groupValue != 0) {
                // handle hundreds, tens, ones
                gH = groupValue / 100;
                gT = (groupValue / 10) % 10;
                gO = groupValue % 10;
                gTO = groupValue % 100;

                if (gH != 0) {
                    nameHundred(gH, sb);
                    if (gTO != 0 && separatorHT != '\0') {
                        sb.append(separatorHT);
                    }
                }

                namedTO = nameTO(gTO, gT, gO, i, sb);

                // last group does not need naming
                if (i != 0) {
                    if (separatorGN != '\0' && namedTO) {
                        sb.append(separatorGN);
                    }
                    sb.append(nameGroup(i, groupValue));
                }
                if (separatorG != '\0') {
                    sb.append(separatorG);
                }
            }
        }
        // delete trailing group separator
        if (sb.charAt(sb.length() - 1) == ' ') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

}
