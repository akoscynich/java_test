package data;

import java.util.Objects;

public class ContactData {

    private final String firstname;
    private final String middlename;
    private final String lastname;
    private int id;

    public ContactData(String firstname, String middlename, String lastname, int id) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.id = id;
    }

    public ContactData(String firstname, String middlename, String lastname) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.id = Integer.MAX_VALUE;

    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                '}';
    }
}
