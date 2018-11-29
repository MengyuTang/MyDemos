// BookManager.aidl
package com.example.fpm0322.aidldemo;

// Declare any non-default types here with import statements
import com.example.fpm0322.aidldemo.Book;

interface BookManager {
       /**
        * Demonstrates some basic types that you can use as parameters
        * and return values in AIDL.
        */
       void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
               double aDouble, String aString);

       List<Book> getBooks();

       void addBook(in Book book);
}
