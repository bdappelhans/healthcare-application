package com.cogent.student_service.request;

public class CreateStudentRequest {

    private String firstName;
    private String lastName;
    private String email;
    private int bookId;

    public CreateStudentRequest() {
    }

    public CreateStudentRequest(String firstName, String lastName, String email, int bookId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.bookId = bookId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
