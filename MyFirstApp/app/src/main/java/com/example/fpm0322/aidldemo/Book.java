package com.example.fpm0322.aidldemo;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {

    private String bookName ;
    private double bookPrice ;

    public Book(){

    }

    protected Book(Parcel in) {
        bookName = in.readString();
        bookPrice = in.readDouble();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(bookName);
        parcel.writeDouble(bookPrice);
    }

    /**
     * 参数是一个Parcel,用它来存储与传输数据
     * @param parcel
     */
    public void readFromParcel(Parcel parcel){
        //注意，此处的读值顺序应当是和writeToParcel()方法中一致的
        bookName = parcel.readString();
        bookPrice = parcel.readDouble();
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", bookPrice=" + bookPrice +
                '}';
    }
}
