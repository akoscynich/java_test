package data;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ContactsInGroups extends ForwardingSet<ContactsInGroupsData> {

    private Set<ContactsInGroupsData> delegate;

    public ContactsInGroups(ContactsInGroups contactInGroups) {
        this.delegate = new HashSet<>(contactInGroups.delegate);
    }

    public ContactsInGroups() {
        this.delegate = new HashSet<ContactsInGroupsData>();
    }

    public ContactsInGroups(Collection<ContactsInGroupsData> contactsInGroups) {
        this.delegate = new HashSet<>(contactsInGroups);
    }

    @Override
    protected Set<ContactsInGroupsData> delegate() {
        return delegate;
    }

   /* public ContactsInGroups withAdded(ContactsInGroupsData contactInGroups){
        ContactsInGroups contactsInGroups = new ContactsInGroups(this);
        contactsInGroups.add(contactInGroups);
        return contactsInGroups;
    }

        public ContactsInGroups without(ContactsInGroupsData contactInGroups){
        ContactsInGroups contactsInGroups = new ContactsInGroups(this);
        contactsInGroups.remove(contactInGroups);
        return contactsInGroups;
    }*/
}
