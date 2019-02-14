package Generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import data.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        save(contacts, new File(file));
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++)
            contacts.add(new ContactData()
                    .withFirstname(String.format("First name %s", i))
                    .withMiddlename(String.format("Middle name %s", i))
                    .withLastname(String.format("Last name %s", i))
                    .withAddress(String.format("address %s", i))
                    .withEmail(String.format("email %s", i))
                    .withEmail2(String.format("email2 %s", i))
                    .withEmail3(String.format("email3 %s", i))
                    .withHomePhone(String.format("111 %s", i))
                    .withMobilePhone(String.format("222 %s", i))
                    .withWorkPhone(String.format("333 %s", i)));
        return contacts;
    }

    private void save(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts)
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n"
                    , contact.getFirstname()
                    , contact.getMiddlename()
                    , contact.getLastname()
                    , contact.getAddress()
                    , contact.getEmail()
                    , contact.getEmail2()
                    , contact.getEmail3()
                    , contact.getHomePhone()
                    , contact.getMobilePhone()
                    , contact.getWorkPhone()));
        writer.close();
    }

}
