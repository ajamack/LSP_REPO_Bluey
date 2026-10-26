package org.howard.edu.lsp.midterm.crccards;

/**
 * Represents a book in the library lending system. Book stores information( author, ISBN, availability, title)
 * @author Aja Wilkes
 */

public class Book {
	
	private final String isbn;
	private final String title;
	private final String author;
	private boolean available;
	
	/**
	 * Creates a new book. New books default as available.
	 * @param isbn the book's isbn
	 * @param author the book's author
	 * @param title the book's title
	 */
	
	public Book(String isbn, String title, String author) {
		
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.available = true;
	}
	
	/**
	 * Returns the ISBN of the book
	 * @return the ISBN
	 */
	
	public String getIsbn() {
		return isbn;
	}
	
	/**
	 * Return title of the book
	 * @return title
	 */
	
	public String getTitle() {
		return title;
	}
	
	/**
	 * Return author of the book
	 * @return author
	 */
	
	public String getAuthor() {
		return author;
	}
	
	/**
	 * Indicates whether book is available to borrow.
	 * @return True is book is available, false if otherwise.
	 */
	
	public boolean isAvailable() {
		return available;
	}
	 /**
	  * Makes an available book unavailable; otherwise it throws IllegalStateException
	  *
	  * @throws IllegalStateException
	  */
	
	public void borrowBook() {
		if (!available) {
			throw new IllegalStateException("Book " + isbn + " is already borrowed.");
		}
		available = false;
	}
	
	/**
	 * Marks book as returned and available again.
	 * @throws IllegalStateException
	 */
	
	public void returnBook() {
		if (available) {
			throw new IllegalStateException("Book " + isbn + " is not currently borrowed.");

		}
		available = true;
	}
	
	/**
	 * Returns a description of this book in the form
	 * "ISBN Title by Author [AVAILABLE] or [BORROWED]" when unavailable.
	 * @return the formatted book details
	 * 
	 */
	@Override
	public String toString() {
		if (available) {
			return isbn + " " + title + " " + author + " " + " [AVAILABLE]";
		}
		return isbn + " " + title + " " + author + " " + " [BORROWED]";
	}

}