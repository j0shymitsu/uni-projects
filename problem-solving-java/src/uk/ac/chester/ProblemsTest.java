package uk.ac.chester;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import uk.ac.chester.testing.AccessModifier;
import uk.ac.chester.testing.MethodsTester;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static uk.ac.chester.Problems.*;

public class ProblemsTest
{

    private MethodsTester<Problems> tester;



    @BeforeEach
    public void setUp() throws Exception
    {
        tester = new MethodsTester<>(Problems.class, handler);
    }

    @AfterEach
    public void tearDown() throws Exception
    {
        tester = null;
    }


    @Test
    public void _0_triple(){
        assertEquals(6,triple(2));
        assertEquals(30,triple(10));
    }


    @Test
    public void _1a_heatingShouldBeOn() throws Exception {
        assertFalse(heatingShouldBeOn(25), "25 should not trigger heating");
        assertFalse(heatingShouldBeOn(19.5),"19.5 should not trigger heating");
        assertTrue(heatingShouldBeOn(17.5),"17.5 should trigger heating");
        assertTrue(heatingShouldBeOn(-28),"-28 should trigger heating");
    }

    @Test
    public void _1b_averageOfThreeNumbers_reflection() throws Exception {
        assertEquals(4, tester.executeStatic(int.class, "averageOfThreeNumbers", 3, 4, 5), "average of 3,4,5 should be 4");
        assertEquals(14, tester.executeStatic(int.class, "averageOfThreeNumbers",11, 13, 19), "average of 11,13,19 should be 14");
    }

    @Test
    public void _1c_concatenatedWords_reflection() throws Exception {
        String result = tester.executeStatic(String.class, "concatenatedWords","the", "cat", "sat", "mat");
        assertEquals( "the cat sat mat", result, "string should be \"the cat sat mat\"");
        String result2 = tester.executeStatic(String.class, "concatenatedWords","Once", "upon", "a","time");
        assertEquals( "Once upon a time", result2, "string should be \"Once upon a time\"");
    }

    @Test
    public void _1d_scrabbleValue_reflection() throws Exception {

        char[] letters = {'a','d','m','f','k','j','z'};
        int[] values = {1,2,3,4,5,8,10};

        for (int i = 0; i < values.length; i++) {
            int result = tester.executeStatic(Integer.class,"scrabbleTileValue",letters[i]);
            Assertions.assertEquals(values[i], result);
        }

    }

    @Test
    public void _1e_productOfNumbersInArray_reflection() throws Exception {
        double[] array = {5.0,4.0,6.0};
        assertEquals(120.0, tester.executeStatic(double.class, "productOfNumbersInArray", (Object) array),0.01,  "numbers in array multiplied");
        double[] array2 = {-3.0,4.5,2.0};
        assertEquals(-27.0, tester.executeStatic(double.class, "productOfNumbersInArray", (Object) array2), 0.01, "numbers in array multiplied with negative");
        double[] smallArray = {2.3};
        assertEquals(2.3, tester.executeStatic(double.class, "productOfNumbersInArray", (Object) smallArray), 0.01, "one item array");
        double[] largerArray = {1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0};
        assertEquals(40320.0, tester.executeStatic(double.class, "productOfNumbersInArray", (Object) largerArray), 0.01, "larger array");
    }

    @Test
    public void _1f_minValue_reflection(){
        {
            int[] numbers = {1, 4, 23, 76, 98, 24, 9};
            int result = tester.executeStatic(Integer.class, "minValue", numbers);
            Assertions.assertEquals(1, result);
        }
        {
            int[] negativeNumbers = {-34, -19472, -846, -21, -923746, -294};
            int result = tester.executeStatic(Integer.class, "minValue", negativeNumbers);
            Assertions.assertEquals(-923746, result);
        }
        {
            int[] mixedNumbers = {-34, -19472, 1, 4, 23, 76, 64854, 24, 9, -846, -21, -294,};
            int result = tester.executeStatic(Integer.class, "minValue", mixedNumbers);
            Assertions.assertEquals(-19472, result);
        }
        {
            int[] empty = {};
            int result = tester.executeStatic(Integer.class, "minValue", empty);
            Assertions.assertEquals(0, result);
        }
    }

    @Test
    public void _1g_sumOfNumbersFromOneToN_reflection() throws Exception {
        {
            int result = tester.executeStatic(Integer.class, "sumOfNumbersFromOneToN", 5);
            Assertions.assertEquals(15, result, "numbers from 1 to 5 should be 15");
        }
        {
            int result = tester.executeStatic(Integer.class, "sumOfNumbersFromOneToN", 100);
            Assertions.assertEquals(5050, result, "numbers from 1 to 100 should be 5050");
        }
    }


    @Test
    public void _2a_removeDoubleSpaces_reflection(){
        {
            String source = "The cat.  Sat on.  The Mat.  ";
            String actual = tester.executeStatic(String.class, "removeDoubleSpaces",source);
            Assertions.assertEquals("The cat. Sat on. The Mat. ", actual);
        }
        {
            String source = "The cat. Sat on. The Mat. ";
            String actual = tester.executeStatic(String.class, "removeDoubleSpaces",source);
            Assertions.assertEquals("The cat. Sat on. The Mat. ", actual, "sentences with no double spaces should be unchanged");
        }
        {
            String source = "The  cat. Sat   on.  The    Mat. ";
            String actual = tester.executeStatic(String.class, "removeDoubleSpaces",source);
            Assertions.assertEquals("The  cat. Sat   on. The    Mat. ", actual, "spaces that do not follow a full stop should not be removed");
        }
    }

    @Test
    public void _2b_apaFormatCitation_reflection() throws Exception {
        {
            String expected = "Palmer, 2008";
            String[] author = {"Palmer"};
            String result = tester.executeStatic(String.class, "apaFormatCitation", author, 2008);
            Assertions.assertEquals(expected, result);
        }
        {
            String expected = "Palmer & Roy, 2008";
            String[] authors = {"Palmer", "Roy"};
            String result =  tester.executeStatic(String.class, "apaFormatCitation", authors, 2008);
            Assertions.assertEquals(expected, result);
        }
        {
            String expected = "Sharp, Aarons, Wittenberg, & Gittens, 2007";
            String[] authors = {"Sharp", "Aarons", "Wittenberg", "Gittens"};
            String result =  tester.executeStatic(String.class, "apaFormatCitation", authors, 2007);
            Assertions.assertEquals(expected, result);
        }
        {
            String expected = "Mendelsohn et al., 2010";
            String[] authors = {"Mendelsohn", "Sharp", "Aarons", "Wittenberg", "Gittens", "Palmer"};
            String result = tester.executeStatic(String.class, "apaFormatCitation", authors, 2010);
            Assertions.assertEquals(expected, result);
        }


    }


    @Test
    public void _2c_validMoveBishopMove_reflection() throws Exception {
        Assertions.assertFalse(tester.executeStatic(boolean.class,"isValidBishopMove",'c',3,'c',3), "Can't stay still (not a move)");
        Assertions.assertTrue(tester.executeStatic(boolean.class,"isValidBishopMove",'h',8,'g',7), "On h8 can go g7");
        Assertions.assertTrue(tester.executeStatic(boolean.class,"isValidBishopMove",'d',4,'e',3), "On d4 can go e3");
        Assertions.assertTrue(tester.executeStatic(boolean.class,"isValidBishopMove",'b',7,'a',8), "On b7 can go a8");
        Assertions.assertFalse(tester.executeStatic(boolean.class,"isValidBishopMove",'h',8,'h',9), "On h8 can't go h9 (doesn't exist)");
        Assertions.assertTrue(tester.executeStatic(boolean.class,"isValidBishopMove",'h',8,'a',1), "On h8 can go a1");
        Assertions.assertFalse(tester.executeStatic(boolean.class,"isValidBishopMove",'c',3,'e',4), "On c3 can't go e4 (not diagonal)");
        Assertions.assertFalse(tester.executeStatic(boolean.class,"isValidBishopMove",'e',3,'b',0), "On e3 can't go b0 (doesn't exist)");
        Assertions.assertFalse(tester.executeStatic(boolean.class,"isValidBishopMove",'h',3,'k',0), "On h3 can't go k6 (doesn't exist)");
    }

    @Test
    public void _2d_priceAsString_reflection(){
        Assertions.assertEquals("£3.16", tester.executeStatic(String.class,"priceAsString",316,'£',false));
        Assertions.assertEquals("$3.06", tester.executeStatic(String.class,"priceAsString",306,'$',false));
        Assertions.assertEquals("$4.60", tester.executeStatic(String.class,"priceAsString",460,'$',false));
        Assertions.assertEquals("$5.00", tester.executeStatic(String.class,"priceAsString",500,'$',false));
        Assertions.assertEquals("24.70€", tester.executeStatic(String.class,"priceAsString",2470,'€',true));
        Assertions.assertEquals("£0.78", tester.executeStatic(String.class,"priceAsString",78,'£',false));
        Assertions.assertEquals("¥24.78", tester.executeStatic(String.class,"priceAsString",2478,'¥',false));
    }

    @Test
    public void _2e_lastIndexOfItem_reflection() throws Exception {
        String[] array = {"ABC", "BCD", "DEFGH", "GHI", "HIJK"};

        String[] searchPatterns = {"GH", "ab", "XY"};
        int[] indices = {3,0,-1};

        for (int i = 0; i < searchPatterns.length; i++) {
            int result = tester.executeStatic(Integer.class,"lastPartialMatch",searchPatterns[i],array);
            Assertions.assertEquals(indices[i], result);
        }
    }

    @Test
    public void _2f_wordsFromVariableName_reflection(){
        assertEquals("My String", tester.executeStatic(String.class,"wordsFromVariableName","myString"));
        assertEquals("A Longer Variable Name", tester.executeStatic(String.class,"wordsFromVariableName","aLongerVariableName"));
    }


    @Test
    public void _3a_formattedNameFromArray_reflection() throws Exception {
        String[][] namesArray = {
                {"Dr", "Gregory", "House"},
                {"Thirteen"},
                {"Ms", "Stacey", "Warner"},
                {"Alison", "Barbera", "Cameron"},
                {"Andrew", "Barry", "Chris", "David", "Edward", "Gary", "Henry", "Smythe"},
                {"Robert", "Chase"}
        };

        String[] expectedNames = {"Dr. G. House",
                "Thirteen",
                "Ms. S. Warner",
                "Alison B. Cameron",
                "Andrew B. C. D. E. G. H. Smythe",
                "Robert Chase"};

        for (int i = 0; i < 6; i++) {
            String result = tester.executeStatic(String.class,"formattedNameFromArray",(Object)namesArray[i]);
            Assertions.assertEquals(expectedNames[i], result);
        }
    }




    @Test
    public void _3b_textChunks_reflection() throws Exception {

        String sentence = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        ArrayList<String> chunks = new ArrayList<String>();
        chunks.add("Lorem ipsum");
        chunks.add("dolor sit");
        chunks.add("amet,");
        chunks.add("consectetur");
        chunks.add("adipiscing");
        chunks.add("elit, sed do");
        chunks.add("eiusmod");
        chunks.add("tempor");
        chunks.add("incididunt");
        chunks.add("ut labore et");
        chunks.add("dolore magna");
        chunks.add("aliqua.");
        {
            ArrayList<String> result = tester.executeStatic(ArrayList.class, "textChunks", sentence);
            Assertions.assertEquals(chunks, result);
        }
        {
            String shortText = "No split";
            ArrayList<String> notSplit = new ArrayList<String>();
            notSplit.add(shortText);

            ArrayList<String> result = tester.executeStatic(ArrayList.class, "textChunks", shortText);
            Assertions.assertEquals(notSplit, result);
        }
        {
            String shortWords = "a a a a a a a a a a a a a a a a a a a a a a a";
            ArrayList<String> expected = new ArrayList<>();
            expected.add("a a a a a a");
            expected.add("a a a a a a");
            expected.add("a a a a a a");
            expected.add("a a a a a");
            ArrayList<String> result = tester.executeStatic(ArrayList.class, "textChunks", shortWords);
            Assertions.assertEquals(expected, result);
        }
    }

    private MethodsTester.EventHandler handler = new MethodsTester.EventHandler()
    {


        @Override
        public void notFound(String methodName, Class searchClass) {
            fail("No valid method matching " + methodName + " found");
        }

        @Override
        public void wrongCaseName(String methodName)
        {
            fail("No valid method matching " + methodName + " found");
        }

        @Override
        public void incorrectReturnType(String methodName, Class requiredReturnType)
        {
            fail("No valid method matching " + methodName + " found");
        }

        @Override
        public void incorrectNumberOfParameters(String methodName, int expectedParamCount)
        {
            fail("No valid method matching " + methodName + " found");
        }

        @Override
        public void incorrectParameters(String methodName, Class[] requiredParamTypes)
        {
            fail("No valid method matching " + methodName + " found");
        }

        @Override
        public void incorrectParamOrder(String methodName, Class[] requiredParams)
        {
            fail("No valid method matching " + methodName + " found");
        }

        @Override
        public void paramNameUnconventional(String methodName, String paramName)
        {

        }

        @Override
        public void accessModifierIncorrect(String methodName, AccessModifier requiredModifier)
        {

        }

        @Override
        public void staticDeclarationIncorrect(String methodName, boolean requiredStatic) {
            if (requiredStatic) {
                fail("No valid static method matching " + methodName + " found");
            }
        }
    };



    private static class Move
    {
        private final int currentRow;
        private final char currentCol;
        private final int destinationRow;
        private final char destinationCol;

        Move(char currentCol, int currentRow, char destinationCol, int destinationRow)
        {
            this.currentCol = currentCol;
            this.currentRow = currentRow;
            this.destinationCol = destinationCol;
            this.destinationRow = destinationRow;
        }

        public static Move make(String from, String to)
        {
            char currentCol = from.charAt(0);
            int currentRow = Integer.parseInt(Character.toString(from.charAt(1)));
            char destinationCol = to.charAt(0);
            int destinationRow = Integer.parseInt(Character.toString(to.charAt(1)));

            return new Move(currentCol,currentRow,destinationCol,destinationRow);
        }

        public int getCurrentRow()
        {
            return currentRow;
        }

        public char getCurrentCol()
        {
            return currentCol;
        }

        public int getDestinationRow()
        {
            return destinationRow;
        }

        public char getDestinationCol()
        {
            return destinationCol;
        }

        @Override
        public String toString()
        {
            return "" + currentRow + currentCol + " to " + destinationRow + destinationCol;
        }
    }
}