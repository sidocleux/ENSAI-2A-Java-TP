package fr.ensai.library;

public class Item {
    protected String title;
    protected int year;
    protected int pageCount;

    public Item(String title, int year, int pageCount){
        this.title = title; 
        this.year = year; 
        this.pageCount = pageCount;
    }

}
