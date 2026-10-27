package org.howard.edu.lsp.midterm.crccards;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Maintains a collection of books for borrowing and returning.
 * Books are stored in a HashMap keyed by ISBN for fast lookup and easy duplicate detection. The library enforces the three-book
 * borrowing limit and delegates each state change to the Book itself.
 * @author Aja Wilkes
 */


public class Library {
	private static final int MAX_BORROWED = 3;
	private final Map<String, Book> books = new HashMap<>();
	
	/**
	 * Adds book to the library.
	 * @param book the book to add
	 * @throws IllegalArgumentException if book with same ISBN already exists
	 */
	public void addBook(Book book) {
		if (books.containsKey(book.getIsbn())) {
			throw new IllegalArgumentException("Duplicate ISBN: " + book.getIsbn());
		}
		books.put(book.getIsbn(), book);
	}
	
	/**
	 * Finds a book by ISBN.
	 * @param isbn the ISBN to look up
	 * @return the matching book or null if not found
	 */
	public Book findBook(String isbn) {
		return books.get(isbn);
	}
	
	/**
	 * Borrows the book with given ISBN
	 * @param isbn ISBN of the book to borrow
	 * @throws IllegalArgumentException if no book has that ISBN
	 * @throws IllegalStateException if book is already borrowed or 3-book limit reached
	 */
	public void borrowBook(String isbn) {
		Book book = requireBook(isbn);
		if (!book.isAvailable()) {
			throw new IllegalStateException("Book " + isbn + " is already borrowed.");
		}
		if (getBorrowedBookCount() >= MAX_BORROWED) {
			throw new IllegalStateException("Borrowing limit of " + MAX_BORROWED + " reached.");
		}
		book.borrowBook();	
	}
	/**
	 * Returns the book with the given ISBN.
	 *
	 * @param isbn the ISBN of the book to return
	 * @throws IllegalArgumentException if no book has that ISBN
	 * @throws IllegalStateException if the book is not currently borrowed
	 */
	public void returnBook(String isbn) {
		Book book = requireBook(isbn);
		book.returnBook();
	}

	/**
	 * Returns all books that are currently available.
	 *
	 * @return a list of available books, empty if none
	 */
	public List<Book> getAvailableBooks() {
		List<Book> result = new ArrayList<>();
		for (Book book : books.values()) {
			if (book.isAvailable()) {
				result.add(book);
			}
		}
		return result;
	}

	/**
	 * Returns all books by the given author (exact, case-sensitive match),
	 * including borrowed books.
	 *
	 * @param author the author name to match
	 * @return a list of matching books, empty if none
	 */
	public List<Book> getBooksByAuthor(String author) {
		List<Book> result = new ArrayList<>();
		for (Book book : books.values()) {
			if (book.getAuthor().equals(author)) {
				result.add(book);
			}
		}
		return result;
	}

	/**
	 * Returns all books sorted by ISBN in ascending order, regardless of
	 * availability. Does not modify the library.
	 *
	 * @return a sorted list of all books, empty if the library is empty
	 */
	public List<Book> getBooksSortedByIsbn() {
		List<Book> result = new ArrayList<>(books.values());
		result.sort(Comparator.comparing(Book::getIsbn));
		return result;
	}

	/**
	 * Returns the number of books currently borrowed.
	 *
	 * @return the borrowed book count
	 */
	public int getBorrowedBookCount() {
		int count = 0;
		for (Book book : books.values()) {
			if (!book.isAvailable()) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Looks up a book by ISBN and fails if it is missing.
	 */
	private Book requireBook(String isbn) {
		Book book = books.get(isbn);
		if (book == null) {
			throw new IllegalArgumentException("No book with ISBN " + isbn);
		}
		return book;
	}

}
