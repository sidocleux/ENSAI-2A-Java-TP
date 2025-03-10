package fr.ensai.library;

public class Magazine extends Item {

    protected String issn;
    protected String issueNumber;

    public Magazine(String issn, String title, String issueNumber, int year, int pageCount) {
        super(title, year, pageCount);
        this.issn = issn;
        this.issueNumber = issueNumber;
    }

    public String toString() {
        return "Magazine " + title + " number " + issueNumber.toString();
    }
}