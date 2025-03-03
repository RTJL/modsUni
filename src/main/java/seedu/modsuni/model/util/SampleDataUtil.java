package seedu.modsuni.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.modsuni.model.AddressBook;
import seedu.modsuni.model.ModuleList;
import seedu.modsuni.model.ReadOnlyAddressBook;
import seedu.modsuni.model.ReadOnlyModuleList;
import seedu.modsuni.model.module.Code;
import seedu.modsuni.model.module.Module;
import seedu.modsuni.model.module.Prereq;
import seedu.modsuni.model.person.Address;
import seedu.modsuni.model.person.Email;
import seedu.modsuni.model.person.Person;
import seedu.modsuni.model.person.Phone;
import seedu.modsuni.model.tag.Tag;
import seedu.modsuni.model.user.Name;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static Module[] getSampleModules() {
        return new Module[]{
            new Module(new Code("ACC1002"), "Accounting", "Financial Accounting",
                "The course provides an introduction to financial accounting. It examines "
                    + "accounting from an external user's perspective: an external user being an "
                    + "investor or a creditor. Such users would need to understand financial "
                    + "accounting in order to make investing or lending decisions. However, to "
                    + "attain a good understanding, it is also necessary to be familiar with how "
                    + "the information is derived. Therefore, students would learn how to prepare "
                    + "the reports or statements resulting from financial accounting and how to use "
                    + "them for decision-making.",
                4, true, true, false, false, new ArrayList<Code>(), new Prereq()),
            new Module(new Code("CS1010"), "Computer Science", "Programming Methodology",
                "This module introduces the fundamental concepts of problem solving by "
                    + "computing and programming using an imperative programming language. It is the "
                    + "first and foremost introductory course to computing. It is also the first part"
                    + " of a three-part series on introductory programming and problem solving by "
                    + "computing, which also includes CS1020 and CS2010. Topics covered include "
                    + "problem solving by computing, writing pseudo-codes, basic problem formulation "
                    + "and problem solving, program development, coding, testing and debugging, "
                    + "fundamental programming constructs (variables, types, expressions, "
                    + "assignments, functions, control structures, etc.), fundamental data "
                    + "structures: arrays, strings and structures, simple file processing, and basic "
                    + "recursion. This module is appropriate for SoC students.",
                4, true, true, false, false, new ArrayList<Code>(), new Prereq()),
            new Module(new Code("ACC1002X"), "Accounting", "Financial Accounting",
                "The course provides an introduction to financial accounting. It examines "
                        + "accounting from an external user's perspective: an external user being an investor or "
                        + "a creditor. Such users would need to understand financial accounting in order to "
                        + "make investing or lending decisions. However, to attain a good understanding, it is"
                        + " also necessary to be familiar with how the information are derived. Therefore, "
                        + "students would learn how to prepare the reports or statements resulting from "
                        + "financial accounting and how to use them for decision-making.",
                4, true, true, false, false,
                new ArrayList<Code>(), new Prereq()),
            new Module(new Code("CS2103T"), "Computer Science", "Software Engineering",
                "This module introduces the necessary conceptual and analytical tools for systematic "
                        + "and rigorous development of software systems. It covers four main areas of software "
                        + "development, namely object-oriented system analysis, object-oriented system modelling and "
                        + "design, implementation, and testing, with emphasis on system modelling and design and "
                        + "implementation of software modules that work cooperatively to fulfill the requirements of "
                        + "the system. Tools and techniques for software development, such as Unified Modelling "
                        + "Language (UML), program specification, and testing methods, will be taught. Major software "
                        + "engineering issues such as modularisation criteria, program correctness, "
                        + "and software quality will also be covered.",
                4, true, true, false, false,
                new ArrayList<Code>(), new Prereq())
        };
    }

    public static ReadOnlyModuleList getSampleModuleList() {
        ModuleList sampleModuleList = new ModuleList();
        for (Module sampleModule : getSampleModules()) {
            sampleModuleList.addModule(sampleModule);
        }
        return sampleModuleList;
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
            .map(Tag::new)
            .collect(Collectors.toSet());
    }

}
