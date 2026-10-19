package org.howard.edu.lsp.assignment5;

import java.util.TreeSet;

/**
 * Represents a mathematical integer set with no duplicates
 */
public class IntegerSet {
	/**
	 * Stores elements of a set, avoiding duplicates while keeping them sorted.
	 */
	private final TreeSet<Integer> set = new TreeSet<>();
	
	/**
	 * Creates an empty IntegerSet
	 */
	public IntegerSet() {	
	}
	
	/**
	 * Adds item to the set, ignores if duplicate.
	 * @param item the value to add
	 */
	public void add(int item) {
		set.add(item);
	}
	
	/**
	 * Removes item from set, ignores if not present.
	 * @param item the value to remove
	 */
	public void remove(int item) {
		set.remove(item);
	}
	
	/**
	 * Check if value is in set
	 * @param value to look for
	 * @return true if value in set, false if otherwise
	 */
	public boolean contains(int value) {
		return set.contains(value);
	}
	
	/**
	 * Checks length of set
	 * @return Number of unique elements in the set
	 */
	public int length() {
		return set.size();
	}
	
	/**
	 * Checks if set is empty
	 * @return true if set is empty, false if otherwise
	 */
	public boolean isEmpty() {
		return set.isEmpty();
	}
	
	/**
	 * Removes all elements from a set
	 */
	public void clear() {
		set.clear();
	}
	
	/**
	 * Checks for the largest value in the set
	 * @return largest value in set
	 * @throws IllegalStateException if set is empty
	 */
	public int largest() {
		if (set.isEmpty()) {
			throw new IllegalStateException("Set is empty");
		}
		return set.last();
	}
	
	/**
	 * Checks for smallest value in set
	 * @return smallest value 
	 * @throws IllegalStateException if set is empty
	 */
	public int smallest() {
		if (set.isEmpty()) {
			throw new IllegalStateException("Set is empty");
		}
		return set.first();
	}
	
	/**
	 * Returns the elements in ascending order inside square brackets,
	 * separated by a comma and a space, for example [-3, 0, 8, 15].
	 * An empty set returns [].
	 * @return set as a formatted string
	 */
	@Override 
	public String toString() {
		return set.toString();
	}
	
	/**
	 * Checks if set a and set b are equal
	 * @param b the set to compare with
	 * @return true if both sets have the same elements, false if otherwise
	 */
	public boolean equals(IntegerSet b) {
		return set.equals(b.set);
	}
	
	/**
	 * Returns new set with all elements in set a or set b
	 * @param b the other set
	 * @return new set containing the union
	 */
	public IntegerSet union(IntegerSet b) {
		IntegerSet result = new IntegerSet();
		result.set.addAll(this.set);
		result.set.addAll(b.set);
		return result;	
	}
	
	/**
	 * Returns a new set with all elements in both set a and set b
	 * @param b the other set
	 * @return Intersection of set a and set b
	 */
	public IntegerSet intersect(IntegerSet b)  {
		IntegerSet result = new IntegerSet();
		result.set.addAll(this.set);
		result.set.retainAll(b.set);
		return result;	
	}
	
	/**
	 * Returns a new set with elements in set a but not set b
	 * @param b the other set
	 * @return diff between set a and set b
	 */
	public IntegerSet diff(IntegerSet b) {
		IntegerSet result = new IntegerSet();
		result.set.addAll(this.set);
		result.set.removeAll(b.set);
		return result;
	}
	
	/**
	 * Returns a new set with elements in set b but not set a
	 * @param b the other set
	 * @return complement between set a and set b
	 */
	public IntegerSet complement(IntegerSet b) {
		IntegerSet result = new IntegerSet();
		result.set.addAll(b.set);
		result.set.removeAll(this.set);
		return result;	
	}
	
	/**
	 * Checks whether every element in set a is also in set b
	 * @param b the other set
	 * @return true if set a is subset, false if otherwise
	 */
	public boolean isSubsetOf(IntegerSet b) {
		return b.set.containsAll(this.set);	
	}
	
	/**
	 * Checks whether set a and set b share no elements
	 * @param b the other set
	 * @return true if no shared elements, false if otherwise
	 */
	public boolean isDisjoint(IntegerSet b) {
		IntegerSet shared = this.intersect(b);
		return shared.isEmpty();	
	}
	
	/**
	 * Returns a new set containing elements that occur in exactly one of the two sets
	 * @param b the other set
	 * @return new set containing symmetric difference
	 */
	public IntegerSet symmetricDiff(IntegerSet b) {
		IntegerSet all = this.union(b);
		IntegerSet shared = this.intersect(b);
		return all.diff(shared); 	
	}	
}
