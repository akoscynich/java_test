package data;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "address_in_groups")
public class ContactsInGroupsData {
    @Id
    @Column(name = "id")
    @Type(type ="int")
    private int contactId;
    @Column(name = "group_id")
    @Type(type ="int")
    private int groupId;

    public ContactsInGroupsData withGroupId(int groupId) {
        this.groupId = groupId;
        return this;
    }

    public ContactsInGroupsData withContactId(int contactId) {
        this.contactId = contactId;
        return this;
    }

    public int getGroupId() {
        return groupId;
    }

    public int getContactId() {
        return contactId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactsInGroupsData that = (ContactsInGroupsData) o;
        return groupId == that.groupId &&
                contactId == that.contactId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, contactId);
    }

    @Override
    public String toString() {
        return "ContactsInGroupsData{" +
                "groupId=" + groupId +
                ", contactId=" + contactId +
                '}';
    }

}
