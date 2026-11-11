package org.howard.edu.lsp.assignment6;
 
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
 
/**
 * Assignment 6: JUnit 5 tests for IntegerSet.
 *
 * Complete every TODO. Do not remove, rename, or disable test methods.
 * Each test must contain meaningful assertions and run independently.
 */
public class IntegerSetTest {
 
    // ===== clear() =====
 
    /**
     * Create a populated set; call clear(); assert that it is empty and its length is zero.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testClearClearsPopulatedSet() {
    	// Arrange
    	IntegerSet s = new IntegerSet();
    	s.add(1);
    	s.add(2);
    	s.add(3);
    	
    	//Act
    	s.clear();
    	//Assert
    	assertTrue(s.isEmpty());
    	assertEquals(0, s.length());
    	assertFalse(s.contains(2));
        
    }
 
    /**
     * Clear an already empty set; verify it remains empty.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testClearClearsEmptySet() {
        // Arrange - create the required IntegerSet objects.
    	IntegerSet s = new IntegerSet();
        //  Act - invoke the operation being tested.
    	s.clear();
    	
    	// Assert - verify the expected behavior with JUnit assertions.
    	assertTrue(s.isEmpty());
    	assertEquals(0, s.length());
    	
    }
 
    // ===== length() =====
 
    /**
     * Add repeated and unique integers; assert the number of distinct elements.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testLengthCountsUniqueElements() {
        //Arrange - create the required IntegerSet objects.
    	IntegerSet s = new IntegerSet();
    	s.add(1);
    	s.add(3);
    	s.add(3);
    	s.add(4);
        //Act - invoke the operation being tested. length() is being called
    	
        //Assert - verify the expected behavior with JUnit assertions.
    	assertEquals(3, s.length());
    }
 
    /**
     * Verify a new empty set has length zero.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testLengthEmptySetLength() {
        //Arrange - create the required IntegerSet objects.
    	IntegerSet s = new IntegerSet();
    	
        //Act - invoke the operation being tested. length() being called.
    	
        //Assert - verify the expected behavior with JUnit assertions.
    	assertTrue(s.isEmpty());
    	assertEquals(0, s.length());
    	
    }
 
    // ===== equals() =====
 
    /**
     * Construct sets with identical elements in different insertion orders; assert equality.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testEqualsIgnoresInsertionOrder() {
        //Arrange - create the required IntegerSet objects.
    	IntegerSet s1 = new IntegerSet();
    	IntegerSet s2 = new IntegerSet();
    	s1.add(1);
    	s1.add(2);
    	s1.add(3);
    	s2.add(3);
    	s2.add(2);
    	s2.add(1);
        //Act - invoke the operation being tested. equals() is called in the assert.
    	
    	
        //Assert - verify the expected behavior with JUnit assertions.
    	assertTrue(s1.equals(s2));
    }
 
    /**
     * Construct sets of equal length with different elements; assert inequality.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testEqualsDetectsDifferentContents() {
        //Arrange - create the required IntegerSet objects.
    	IntegerSet s1 = new IntegerSet();
    	IntegerSet s2 = new IntegerSet();
    	s1.add(1);
    	s1.add(2);
    	s1.add(3);
    	s2.add(4);
    	s2.add(5);
    	s2.add(6);
        //Act - equals() is called in the assert.
        //Assert - verify the expected behavior with JUnit assertions.
    	assertEquals(s1.length(), s2.length());
    	assertFalse(s1.equals(s2));
    }
 
    /**
     * Verify two separately constructed empty sets compare equal.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testEqualsEmptySetsEqual() {
        //Arrange - create the required IntegerSet objects.
    	IntegerSet s1 = new IntegerSet();
    	IntegerSet s2 = new IntegerSet();
        //Act - equals() called in assert.
        //Assert - verify the expected behavior with JUnit assertions.
    	assertTrue(s1.isEmpty());
    	assertTrue(s2.isEmpty());
    	assertTrue(s1.equals(s2));
    	assertTrue(s2.equals(s1));	
    }
 
    /**
     * Verify a set equals itself and differs from a set of another size.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testEqualsSameObjectAndDifferentSizes() {
        //Arrange - create the required IntegerSet objects.
    	IntegerSet s1 = new IntegerSet();
    	IntegerSet s2 = new IntegerSet();
    	s1.add(1);
    	s1.add(2);
    	s2.add(3);
    	s2.add(4);
    	s2.add(7);
        //Act - invoke the operation being tested.
        //Assert - verify the expected behavior with JUnit assertions.
    	assertTrue(s1.equals(s1));
    	assertNotEquals(s1.length(), s2.length());
    	assertFalse(s1.equals(s2));
    }
 
    // ===== contains() =====
 
    /**
     * Verify contains() is true for a present element and false for an absent one.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testContainsPresentAndAbsent() {
        //Arrange - create the required IntegerSet objects.
    	IntegerSet s1 = new IntegerSet();
    	s1.add(1);
    	s1.add(2);
        //Act - invoke the operation being tested.
        //Assert - verify the expected behavior with JUnit assertions.
    	assertTrue(s1.contains(2));
    	assertFalse(s1.contains(4));
    }
 
    /**
     * Verify an empty set does not contain an arbitrary integer.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testContainsEmptySetContainsNothing() {
        //Arrange - create the required IntegerSet objects.
    	IntegerSet s = new IntegerSet();
        // Act - invoke the operation being tested.
        //Assert - verify the expected behavior with JUnit assertions.
    	assertTrue(s.isEmpty());
    	assertFalse(s.contains(3));	
    }
    
 
    // ===== largest() =====
 
    /**
     * Verify largest() returns the maximum across negative, zero, and positive values.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testLargestNegativeAndPositiveValues() {
        //Arrange - create the required IntegerSet objects.
    	IntegerSet s = new IntegerSet();
    	s.add(2);
    	s.add(-7);
    	s.add(9);
    	s.add(0);
        //Act - invoke the operation being tested.
        //Assert - verify the expected behavior with JUnit assertions.
    	assertEquals(9, s.largest());
    }
 
    /**
     * Use assertThrows() to verify IllegalStateException for an empty set.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testLargestEmptySetThrowsException() {
        //Arrange - create the required IntegerSet objects.
    	IntegerSet s = new IntegerSet();

        //Act - invoke the operation being tested.
        //Assert - verify the expected behavior with JUnit assertions.
        assertThrows(IllegalStateException.class, () -> s.largest());
    }
 
    // ===== smallest() =====
 
    /**
     * Verify smallest() returns the minimum across negative, zero, and positive values.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testSmallestNegativeAndPositiveValues() {
        //Arrange - create the required IntegerSet objects.
    	IntegerSet s = new IntegerSet();
    	s.add(2);
    	s.add(-7);
    	s.add(9);
    	s.add(0);
        //Act - invoke the operation being tested.
        //Assert - verify the expected behavior with JUnit assertions.
    	assertEquals(-7, s.smallest());
    }
 
    /**
     * Use assertThrows() to verify IllegalStateException for an empty set.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testSmallestEmptySetThrowsException() {
        // TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet s = new IntegerSet();

        // TODO: Act - invoke the operation being tested.
        // TODO: Assert - verify the expected behavior with JUnit assertions.
        assertThrows(IllegalStateException.class, () -> s.smallest());

    }
 
    // ===== add() =====
 
    /**
     * Add the same integer repeatedly; verify only one copy exists.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testAddIgnoresDuplicates() {
        // TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet s = new IntegerSet();
    	s.add(2);
    	s.add(2);
    	s.add(2);
    	s.add(2);
    	
        // TODO: Act - invoke the operation being tested.
        // TODO: Assert - verify the expected behavior with JUnit assertions.
    	assertTrue(s.contains(2));
    	assertEquals(1, s.length());
    }
 
    /**
     * Add Integer.MIN_VALUE, Integer.MAX_VALUE, and zero; verify all are represented.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testAddHandlesIntegerBoundaries() {
        // TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet s = new IntegerSet();
    	
        // TODO: Act - invoke the operation being tested.
    	s.add(Integer.MIN_VALUE);
        s.add(Integer.MAX_VALUE);
        s.add(0);
        // TODO: Assert - verify the expected behavior with JUnit assertions.
        assertTrue(s.contains(Integer.MIN_VALUE));
        assertTrue(s.contains(Integer.MAX_VALUE));
        assertTrue(s.contains(0));
        assertEquals(3, s.length());
    }
 
    // ===== remove() =====
 
    /**
     * Remove a present element; verify it is absent and others remain.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testRemoveRemovesExistingElement() {
        // TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet s = new IntegerSet();
    	s.add(3);
    	s.add(4);
    	s.add(5);
        // TODO: Act - invoke the operation being tested.
    	s.remove(4);
        // TODO: Assert - verify the expected behavior with JUnit assertions.
    	assertFalse(s.contains(4));
    	assertTrue(s.contains(3));
    	assertTrue(s.contains(5));
    	assertEquals(2, s.length());
    }
 
    /**
     * Remove an absent element; verify the set is unchanged.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testRemoveIgnoresAbsentElement() {
        // TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet s = new IntegerSet();
    	s.add(3);
    	s.add(4);
    	s.add(5);
        // TODO: Act - invoke the operation being tested.
    	s.remove(6);
        // TODO: Assert - verify the expected behavior with JUnit assertions.
    	assertTrue(s.contains(4));
    	assertTrue(s.contains(3));
    	assertTrue(s.contains(5));
    	assertEquals(3, s.length());
    }
 
    // ===== union() =====
 
    /**
     * Verify union() contains all distinct elements from two overlapping sets.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testUnionOverlappingSets() {
        // TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet s1= new IntegerSet();
    	IntegerSet s2= new IntegerSet();

    	s1.add(3);
    	s1.add(4);
    	s1.add(5);
    	s2.add(3);
    	s2.add(9);
    	
        // TODO: Act - invoke the operation being tested.
    	IntegerSet result = s1.union(s2);
        // TODO: Assert - verify the expected behavior with JUnit assertions.
    	assertTrue(result.contains(3));
        assertTrue(result.contains(4));
        assertTrue(result.contains(5));
        assertTrue(result.contains(9));
        assertEquals(4, result.length());
    }
 
    /**
     * Verify union with an empty set and with the same object.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testUnionEmptyAndSameOperand() {
        // TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet s1= new IntegerSet();
    	IntegerSet s2= new IntegerSet();

    	s1.add(3);
    	s1.add(4);
    	s1.add(5);
        // TODO: Act - invoke the operation being tested.
    	IntegerSet r1 = s1.union(s2);
    	IntegerSet r2 = s1.union(s1);
        // TODO: Assert - verify the expected behavior with JUnit assertions.
    	// Part 1: union with an empty set gives back the same contents
        assertTrue(r1.equals(s1));
        assertEquals(3, r1.length());

        // Part 2: union with itself gives back the same contents
        assertTrue(r2.equals(s1));
        assertEquals(3, r2.length());

        // The original set was not changed
        assertEquals(3, s1.length());
    	
    }
 
    /**
     * Verify both inputs remain unchanged; modify the result and verify the inputs are still unchanged.
     * Assignment 5 equivalent: 3 point(s).
     */
    @Test
    public void testUnionDoesNotMutateInputs() {
        // TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet a = new IntegerSet();
    	
    	a.add(1);
    	a.add(2);
    	
    	IntegerSet b = new IntegerSet();
    	
    	b.add(2);
    	b.add(3);
        // TODO: Act - invoke the operation being tested.
    	IntegerSet result = a.union(b);
    	
        // TODO: Assert - verify the expected behavior with JUnit assertions.
    	assertEquals(2, a.length());
    	assertEquals(2, b.length());
    	assertFalse(a.contains(3));
    	// Result is a separate object, not a or b
        assertNotSame(result, a);
        assertNotSame(result, b);
        // Modify resuly
        result.add(99);
        
        // Assert: inputs still unchanged after modifying result
        assertFalse(a.contains(99));
        assertFalse(b.contains(99));
    }
 
    // ===== intersect() =====
 
    /**
     * Verify intersection contains exactly the shared elements.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testIntersectOverlappingSets() {
        // TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet a = new IntegerSet();
       	a.add(1);
    	a.add(2);
    	a.add(3);
    	a.add(4);
    	IntegerSet b = new IntegerSet();
    	b.add(3);
    	b.add(4);
    	b.add(5);
        // TODO: Act - invoke the operation being tested.
    	IntegerSet result = a.intersect(b);
        // TODO: Assert - verify the expected behavior with JUnit assertions.
    	assertTrue(result.contains(3));
    	assertTrue(result.contains(4));
    	assertFalse(result.contains(1));
    	assertFalse(result.contains(5));
    	assertEquals(2, result.length());
    	
    }
 
    /**
     * Verify disjoint intersection is empty and self-intersection is unchanged.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testIntersectDisjointAndSameOperand() {
        // TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet a = new IntegerSet();
        a.add(1);
        a.add(2);

        IntegerSet b = new IntegerSet();
        b.add(8);
        b.add(9);
        // TODO: Act - invoke the operation being tested.
        IntegerSet r1 = a.intersect(b);
        IntegerSet r2 = a.intersect(a);

        // TODO: Assert - verify the expected behavior with JUnit assertions.
        assertTrue(r1.isEmpty());
        assertEquals(0, r1.length());

        assertTrue(r2.equals(a));
        assertEquals(2, r2.length());

        assertEquals(2, a.length());
    }
 
    /**
     * Modify the intersection result and verify both input sets remain unchanged.
     * Assignment 5 equivalent: 3 point(s).
     */
    @Test
    public void testIntersectIndependentResult() {
        // TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet a = new IntegerSet();
        a.add(1);
        a.add(2);
        a.add(3);

        IntegerSet b = new IntegerSet();
        b.add(2);
        b.add(3);
        b.add(4);
        // TODO: Act - invoke the operation being tested.
        IntegerSet result = a.intersect(b);
        // TODO: Assert - verify the expected behavior with JUnit assertions.
        assertNotSame(result, a);
        assertNotSame(result, b);
        // Modify
        result.add(99);
        result.remove(2);

        assertEquals(3, a.length());
        assertEquals(3, b.length());
        assertTrue(a.contains(2));
        assertTrue(b.contains(2));
        assertFalse(a.contains(99));
        assertFalse(b.contains(99));

    }
 
    // ===== diff() =====
 
    /**
     * Verify A.diff(B) returns elements in A but not B, and distinguish direction.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testDiffDirectionMatters() {
        // TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet a = new IntegerSet();
        a.add(1);
        a.add(2);
        a.add(3);

        IntegerSet b = new IntegerSet();
        b.add(2);
        b.add(3);
        b.add(4);
        // TODO: Act - invoke the operation being tested.
        IntegerSet aMinusB = a.diff(b);
        IntegerSet bMinusA = b.diff(a);
        // TODO: Assert - verify the expected behavior with JUnit assertions.
        assertTrue(aMinusB.contains(1));
        assertFalse(aMinusB.contains(4));
        assertEquals(1, aMinusB.length());

        assertTrue(bMinusA.contains(4));
        assertFalse(bMinusA.contains(1));
        assertEquals(1, bMinusA.length());

        assertFalse(aMinusB.equals(bMinusA));
    }
 
    /**
     * Verify difference with an empty set and difference with itself.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testDiffEmptyAndSameOperand() {
        // TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet a = new IntegerSet();
        a.add(1);
        a.add(2);
        a.add(3);

        IntegerSet empty = new IntegerSet();

        
        // TODO: Act - invoke the operation being tested.
        IntegerSet r1 = a.diff(empty);
        IntegerSet r2 = empty.diff(a);
        IntegerSet r3 = a.diff(a);
        // TODO: Assert - verify the expected behavior with JUnit assertions.
        assertTrue(r1.equals(a));
        assertEquals(3, r1.length());

        assertTrue(r2.isEmpty());

        assertTrue(r3.isEmpty());
        assertEquals(0, r3.length());

        assertEquals(3, a.length());
    }
 
    /**
     * Modify the difference result and verify neither input changes.
     * Assignment 5 equivalent: 3 point(s).
     */
    @Test
    public void testDiffIndependentResult() {
        // TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet a = new IntegerSet();
        a.add(1);
        a.add(2);
        a.add(3);

        IntegerSet b = new IntegerSet();
        b.add(2);
        b.add(3);
        b.add(4);
        // TODO: Act - invoke the operation being tested.
        IntegerSet result = a.diff(b);

        // TODO: Assert - verify the expected behavior with JUnit assertions.
        assertNotSame(result, a);
        assertNotSame(result, b);
        // Modify
        result.add(99);
        result.remove(1);

        assertEquals(3, a.length());
        assertEquals(3, b.length());
        assertTrue(a.contains(1));
        assertFalse(a.contains(99));
        assertFalse(b.contains(99));
    }
 
    // ===== complement() =====
 
    /**
     * Verify A.complement(B) returns elements in B but not A.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testComplementReverseDifference() {
        // TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet a = new IntegerSet();
        a.add(1);
        a.add(2);
        a.add(3);

        IntegerSet b = new IntegerSet();
        b.add(2);
        b.add(3);
        b.add(4);
        b.add(5);
        // TODO: Act - invoke the operation being tested.
        IntegerSet result = a.complement(b);

        // TODO: Assert - verify the expected behavior with JUnit assertions.
        assertTrue(result.contains(4));
        assertTrue(result.contains(5));
        assertFalse(result.contains(1));
        assertFalse(result.contains(2));
        assertEquals(2, result.length());
        assertTrue(result.equals(b.diff(a)));
    }
 
    /**
     * Verify complement with an empty receiver and with an empty argument.
     * Assignment 5 equivalent: 1 point(s).
     */
    @Test
    public void testComplementEmptyOperands() {
        // TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet a = new IntegerSet();
        a.add(1);
        a.add(2);

        IntegerSet empty = new IntegerSet();
        // TODO: Act - invoke the operation being tested.
        IntegerSet r1 = empty.complement(a);
        IntegerSet r2 = a.complement(empty);
        // TODO: Assert - verify the expected behavior with JUnit assertions.
        assertTrue(r1.equals(a));
        assertEquals(2, r1.length());
        assertTrue(r2.isEmpty());
    }
 
    /**
     * Modify the complement result and verify neither input changes.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testComplementIndependentResult() {
        // TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet a = new IntegerSet();
        a.add(1);
        a.add(2);

        IntegerSet b = new IntegerSet();
        b.add(2);
        b.add(3);
        b.add(4);
        // TODO: Act - invoke the operation being tested.
        IntegerSet result = a.complement(b);

        // TODO: Assert - verify the expected behavior with JUnit assertions.
        assertNotSame(result, a);
        assertNotSame(result, b);
        // Modify
        result.add(99);
        result.remove(3);

        assertEquals(2, a.length());
        assertEquals(3, b.length());
        assertTrue(b.contains(3));
        assertFalse(a.contains(99));
        assertFalse(b.contains(99));
    }
 
    // ===== isSubsetOf() =====
 
    /**
     * Verify a proper subset is recognized.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testIsSubsetOfProperSubset() {
        // TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet small = new IntegerSet();
        small.add(1);
        small.add(2);
        // TODO: Act - invoke the operation being tested.
        IntegerSet big = new IntegerSet();
        big.add(1);
        big.add(2);
        big.add(3);

        // TODO: Assert - verify the expected behavior with JUnit assertions.
        assertTrue(small.isSubsetOf(big));
        assertFalse(big.isSubsetOf(small));
    }
 
    /**
     * Verify a set with an element missing from the other set is not a subset.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testIsSubsetOfNotSubset() {
        // TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet a = new IntegerSet();
        a.add(1);
        a.add(5);

        IntegerSet b = new IntegerSet();
        b.add(1);
        b.add(2);
        b.add(3);

        // TODO: Act - invoke the operation being tested.
        // TODO: Assert - verify the expected behavior with JUnit assertions.
        assertFalse(a.isSubsetOf(b));

    }
 
    /**
     * Verify equal sets are subsets of each other.
     * Assignment 5 equivalent: 1 point(s).
     */
    @Test
    public void testIsSubsetOfEqualSets() {
        // TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet a = new IntegerSet();
        a.add(1);
        a.add(2);

        IntegerSet b = new IntegerSet();
        b.add(2);
        b.add(1);
        // TODO: Act - invoke the operation being tested.
        // TODO: Assert - verify the expected behavior with JUnit assertions.
        assertTrue(a.isSubsetOf(b));
        assertTrue(b.isSubsetOf(a));
    }
 
    /**
     * Verify the empty set is a subset of a nonempty set, but not vice versa.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testIsSubsetOfEmptyCases() {
        // TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet empty = new IntegerSet();
    	IntegerSet a = new IntegerSet();
        a.add(1);
        a.add(2);
        // TODO: Act - invoke the operation being tested.
        // TODO: Assert - verify the expected behavior with JUnit assertions.
        assertTrue(empty.isSubsetOf(a));
        assertFalse(a.isSubsetOf(empty));
    }
 
    // ===== isDisjoint() =====
 
    /**
     * Verify disjoint sets return true.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testIsDisjointNoOverlap() {
    	// TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet a = new IntegerSet();
        a.add(1);
        a.add(2);

        IntegerSet b = new IntegerSet();
        b.add(3);
        b.add(4);
        // TODO: Act - invoke the operation being tested.
        // TODO: Assert - verify the expected behavior with JUnit assertions.
        assertTrue(a.isDisjoint(b));
        assertTrue(b.isDisjoint(a));
    }
 
    /**
     * Verify overlapping sets return false.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testIsDisjointOverlap() {
    	// TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet a = new IntegerSet();
        a.add(1);
        a.add(2);
        a.add(3);

        IntegerSet b = new IntegerSet();
        b.add(3);
        b.add(4);
        // TODO: Act - invoke the operation being tested.
        // TODO: Assert - verify the expected behavior with JUnit assertions.
        assertFalse(a.isDisjoint(b));
        assertFalse(b.isDisjoint(a));
    }
 
    /**
     * Verify disjointness with an empty set and with the same nonempty object.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testIsDisjointEmptyAndSameOperand() {
    	// TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet a = new IntegerSet();
        a.add(1);
        a.add(2);

        IntegerSet empty = new IntegerSet();
        // TODO: Act - invoke the operation being tested.
        // TODO: Assert - verify the expected behavior with JUnit assertions.
        assertTrue(a.isDisjoint(empty));
        assertTrue(empty.isDisjoint(a));
        assertFalse(a.isDisjoint(a));
    }
 
    // ===== symmetricDiff() =====
 
    /**
     * Verify symmetric difference contains elements appearing in exactly one set.
     * Assignment 5 equivalent: 3 point(s).
     */
    @Test
    public void testSymmetricDiffPartiallyOverlappingSets() {
    	// TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet a = new IntegerSet();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);

        IntegerSet b = new IntegerSet();
        b.add(3);
        b.add(4);
        b.add(5);
        b.add(6);
        // TODO: Act - invoke the operation being tested.
        IntegerSet result = a.symmetricDiff(b);
        // TODO: Assert - verify the expected behavior with JUnit assertions.
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(5));
        assertTrue(result.contains(6));
        assertFalse(result.contains(3));
        assertFalse(result.contains(4));
        assertEquals(4, result.length());
    }
 
    /**
     * Verify symmetric difference of identical sets is empty.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testSymmetricDiffIdenticalSets() {
    	// TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet a = new IntegerSet();
        a.add(1);
        a.add(2);
        a.add(3);

        IntegerSet b = new IntegerSet();
        b.add(3);
        b.add(2);
        b.add(1);
        // TODO: Act - invoke the operation being tested.
        IntegerSet r1 = a.symmetricDiff(b);
        IntegerSet r2 = a.symmetricDiff(a);
        // TODO: Assert - verify the expected behavior with JUnit assertions.
        assertTrue(r1.isEmpty());
        assertEquals(0, r1.length());
        assertTrue(r2.isEmpty());
        assertEquals(3, a.length());
    }
 
    /**
     * Verify behavior with an empty set and with disjoint sets.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testSymmetricDiffEmptyAndDisjointSets() {
    	// TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet a = new IntegerSet();
        a.add(1);
        a.add(2);

        IntegerSet c = new IntegerSet();
        c.add(5);
        c.add(6);

        IntegerSet empty = new IntegerSet();
        // TODO: Act - invoke the operation being tested.
        IntegerSet r1 = a.symmetricDiff(empty);
        IntegerSet r2 = a.symmetricDiff(c);
        // TODO: Assert - verify the expected behavior with JUnit assertions.
        assertTrue(r1.equals(a));
        assertEquals(2, r1.length());

        assertTrue(r2.contains(1));
        assertTrue(r2.contains(2));
        assertTrue(r2.contains(5));
        assertTrue(r2.contains(6));
        assertEquals(4, r2.length());
    }
 
    /**
     * Modify the result and verify both input sets remain unchanged.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testSymmetricDiffIndependentResult() {
    	// TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet a = new IntegerSet();
        a.add(1);
        a.add(2);
        a.add(3);

        IntegerSet b = new IntegerSet();
        b.add(3);
        b.add(4);
        // TODO: Act - invoke the operation being tested.
        IntegerSet result = a.symmetricDiff(b);
        // TODO: Assert - verify the expected behavior with JUnit assertions.
        assertNotSame(result, a);
        assertNotSame(result, b);
        // Modify
        result.add(99);
        result.remove(1);
        result.remove(4);

        assertEquals(3, a.length());
        assertEquals(2, b.length());
        assertTrue(a.contains(1));
        assertTrue(b.contains(4));
        assertFalse(a.contains(99));
        assertFalse(b.contains(99));
    }
 
    // ===== isEmpty() =====
 
    /**
     * Verify a newly constructed set is empty.
     * Assignment 5 equivalent: 1 point(s).
     */
    @Test
    public void testIsEmptyNewSet() {
    	// TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet s = new IntegerSet();
        // TODO: Act - invoke the operation being tested.
        // TODO: Assert - verify the expected behavior with JUnit assertions.
        assertTrue(s.isEmpty());
        assertEquals(0, s.length());
    }
 
    /**
     * Verify a set with an element is not empty.
     * Assignment 5 equivalent: 1 point(s).
     */
    @Test
    public void testIsEmptyPopulatedSet() {
    	// TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet s = new IntegerSet();
        s.add(5);
        // TODO: Act - invoke the operation being tested.
        // TODO: Assert - verify the expected behavior with JUnit assertions.
        assertFalse(s.isEmpty());
        assertEquals(1, s.length());
    }
 
    /**
     * Clear a populated set and verify it is empty.
     * Assignment 5 equivalent: 1 point(s).
     */
    @Test
    public void testIsEmptyAfterClear() {
    	// TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet s = new IntegerSet();
        s.add(1);
        s.add(2);
        assertFalse(s.isEmpty());
        // TODO: Act - invoke the operation being tested.
        s.clear();
        // TODO: Assert - verify the expected behavior with JUnit assertions.
        assertTrue(s.isEmpty());
    }
 
    // ===== toString() =====
 
    /**
     * Verify elements appear in ascending numeric order regardless of insertion order.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testToStringAscendingOrder() {
    	// TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet s = new IntegerSet();
        s.add(5);
        s.add(-3);
        s.add(10);
        s.add(0);
        // TODO: Act - invoke the operation being tested.
        String result = s.toString();
        // TODO: Assert - verify the expected behavior with JUnit assertions.
        assertEquals("[-3, 0, 5, 10]", result);
    }
 
    /**
     * Verify an empty set formats as [].
     * Assignment 5 equivalent: 1 point(s).
     */
    @Test
    public void testToStringEmptyFormatting() {
    	// TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet s = new IntegerSet();
        // TODO: Act - invoke the operation being tested.
        String result = s.toString();
        // TODO: Assert - verify the expected behavior with JUnit assertions.
        assertEquals("[]", result);
    }
 
    /**
     * Verify comma-space formatting, brackets, and duplicate suppression.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testToStringExactSpacingAndDuplicates() {
    	// TODO: Arrange - create the required IntegerSet objects.
    	IntegerSet s = new IntegerSet();
        s.add(3);
        s.add(1);
        s.add(3);
        s.add(2);
        s.add(1);
        // TODO: Act - invoke the operation being tested.
        String result = s.toString();
        // TODO: Assert - verify the expected behavior with JUnit assertions.
        assertEquals("[1, 2, 3]", result);
    }
 
}

