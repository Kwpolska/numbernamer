package com.chriswarrick.numbernamer;

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

                nameTO(gTO, gT, gO, i, sb);

                // last group does not need naming
                if (i != 0) {
                    if (separatorGN != '\0') {
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
