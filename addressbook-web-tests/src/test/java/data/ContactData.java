package data;

import java.util.Objects;

public class ContactData {

    private String firstname;
    private String middlename;
    private String lastname;
    private String address;
    private String email;
    private String email2;
    private String email3;
    private String home;
    private String mobile;
    private String work;
    private String phones;
    private String emails;
    private int id = Integer.MAX_VALUE;

    public String getEmails() {
        return emails;
    }

    public ContactData withEmails(String emails) {
        this.emails = emails;
        return this;
    }

    public String getPhones() {
        return phones;
    }

    public ContactData withPhones(String phones) {
        this.phones = phones;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withHomePhone(String home) {
        this.home = home;
        return this;
    }

    public ContactData withMobilePhone(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData withWorkPhone(String work) {
        this.work = work;
        return this;
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
    public String getAddress() {
        return address;
    }
    public String getEmail() {
        return email;
    }
    public String getEmail2() {
        return email2;
    }
    public String getEmail3() {
        return email3;
    }
    public String getHomePhone() {
        return home;
    }
    public String getMobilePhone() {
        return mobile;
    }
    public String getWorkPhone() {
        return work;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, id);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                '}';
    }
}
