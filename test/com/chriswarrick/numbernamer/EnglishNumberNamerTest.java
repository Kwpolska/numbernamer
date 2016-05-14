package com.chriswarrick.numbernamer;

//import com.chriswarrick.numbernamer.*;
import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.junit.Before;
import org.junit.Test;

public class EnglishNumberNamerTest {
    NumberNamer namer;

    @Before
    public void setUp() throws Exception {
        namer = new EnglishNumberNamer();
    }

    @Test
    public void testBasic() {
        assertEquals("Basic integers (0) failed", "zero", namer.name(0L));
        assertEquals("Basic integers (1) failed", "one", namer.name(1L));
        assertEquals("Basic integers (7) failed", "seven", namer.name(7L));
        assertEquals("Basic integers (10) failed", "ten", namer.name(10L));
        assertEquals("Basic integers (19) failed", "nineteen", namer.name(19L));
    }

    @Test
    public void testShort() {
            assertEquals("20 failed", "twenty", namer.name(20L));
            assertEquals("64 failed", "sixty-four", namer.name(64L));
            assertEquals("100 failed", "hundred", namer.name(100L));
            assertEquals("512 failed", "five hundred twelve", namer.name(512L));
            assertEquals("384 failed", "three hundred eighty-four", namer.name(384L));
    }

    @Test
    public void testNegative() {
        assertEquals("-15 failed", "minus fifteen", namer.name(-15L));
        assertEquals("-20 failed", "minus twenty", namer.name(-20L));
        assertEquals("-256 failed", "minus two hundred fifty-six", namer.name(-256L));
    }

    @Test
    public void testOneTwoGroups() {
        assertEquals("1000 failed", "one thousand", namer.name(1000L));
        assertEquals("8192 failed", "eight thousand hundred ninety-two", namer.name(8192L));
        assertEquals("1000000 failed", "one million", namer.name(1000000L));
        assertEquals("2000000 failed", "two million", namer.name(2000000L));
        assertEquals("2300405 failed", "two million three hundred thousand four hundred five", namer.name(2300405L));
    }

    @Test
    public void testLarge() {
        assertEquals("2147483647 fail",
                "two billion hundred forty-seven million four hundred eighty-three thousand six hundred forty-seven",
                namer.name(2147483647L));
        assertEquals("-2147483648 fail",
                "minus two billion hundred forty-seven million four hundred eighty-three thousand six hundred forty-eight",
                namer.name(-2147483648L));
        assertEquals("Long.MAX_VALUE fail",
                "nine quintillion two hundred twenty-three quardillion three hundred seventy-two trillion thirty-six billion eight hundred fifty-four million seven hundred seventy-five thousand eight hundred seven",
                namer.name(9223372036854775807L));
        assertEquals("Long.MIN VALUE - 1 fail",
                "minus nine quintillion two hundred twenty-three quardillion three hundred seventy-two trillion thirty-six billion eight hundred fifty-four million seven hundred seventy-five thousand eight hundred seven",
                namer.name(-9223372036854775807L));
    }

    @Test
    public void testTooLarge() {
        try {
            namer.name(Long.MIN_VALUE);
        } catch (InvalidParameterException ex) {
            return;
        }
        fail("Long.MIN_VALUE did not throw InvalidParameterException");
    }

}
