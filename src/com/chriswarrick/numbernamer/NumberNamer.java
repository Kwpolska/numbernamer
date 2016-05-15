package com.chriswarrick.numbernamer;

/* Number Namer v0.2.0
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

/** The base for all number namers. */
public abstract class NumberNamer {
    /** Minimum number */
    public final long MIN = Long.MIN_VALUE + 1;
    /** Maximum number */
    public final long MAX = Long.MAX_VALUE;

    /**
     * Name a number.
     *
     * @param number
     *            Number to name
     * @return Number as text
     */
    public abstract String name(long number);

    /**
     * Name a number.
     *
     * @param number
     *            Number to name
     * @return Number as text
     */
    public String name(int number) {
        return name((long) number);
    }

    /**
     * Name a group.
     *
     * @param groupIndex
     *            Index of the group
     * @param groupValue
     *            Number that is in the group
     * @return Name of the group
     */
    protected abstract String nameGroup(int groupIndex, int groupValue);

    /**
     * Name a hundred.
     *
     * @param hundred
     *            Hundreds in the number
     * @param sb
     *            StringBuilder instance (output)
     */
    protected abstract void nameHundred(int hundred, StringBuilder sb);

    /**
     * Name a ten-one group.
     *
     * @param tenOne
     *            The value of the ten-one group, equal to ten * 10 + one
     * @param ten
     *            Tens in the number
     * @param one
     *            Ones in the number
     * @param groupIndex
     *            Index of the group (used for special casing)
     * @param sb
     *            StringBuilder instance (output)
     * @return true if buffer was changed, false otherwise
     */
    protected abstract boolean nameTO(int tenOne, int ten, int one, int groupIndex, StringBuilder sb);
}
