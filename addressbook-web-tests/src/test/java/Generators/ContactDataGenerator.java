package Generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
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

    @Parameter(names = "-d", description = "Data format")
    public String format;

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
        if (format.equals("csv"))
            saveAsCsv(contacts, new File(file));
        else if (format.equals("xml"))
            saveAsXml(contacts, new File(file));
        else if (format.equals("json"))
            saveAsJson(contacts, new File(file));
        else System.out.println("Unknown format " + format);
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

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
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

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        //xstream.processAnnotations(ContactData.class);
        //xstream.alias("contact", ContactData.class);
        String xml = xstream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

}
