package com.programming.techie;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContactManagerTest {

    ContactManager contactManager;


    @BeforeAll
    public void setUpAlll() {
        System.out.println("Should Print Before All Tests");
    }

    @BeforeEach
    public void setup() {
        contactManager = new ContactManager();
    }


    @Test
    public void shouldCreateContact() {
        contactManager.addContact("Antonio", "Duque", "0693958019");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());

        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .anyMatch(contact -> contact.getFirstName().equals("Antonio") &&
                        contact.getLastName().equals("Duque") &&
                        contact.getPhoneNumber().equals("0693958019")));

    }

    @Test
    @DisplayName("Should Not Create Contact When First Name is Null")
    public void shouldThrowRunTimeExceptionWhenFirstNameIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact(null, "Duque", "0693958019");
        });
    }

    @Test
    @DisplayName("Should Not Create Contact When Last Name is Null")
    public void shouldThrowRunTimeExceptionWhenLastNameIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Antonio", null, "0693958019");
        });
    }

    @Test
    @DisplayName("Should Not Create Contact When Phone Number is Null")
    public void shouldThrowRunTimeExceptionWhenPhoneNumberIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Antonio", "Duque", null);
        });
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Should Execute After Each Test");
    }

    @AfterAll
    public void tearDownAll() {
        System.out.println("Should be execute at the end of the Test");
    }


    @Test
    @DisplayName("Should Create Contact on MAC OS")
    @DisabledOnOs(value = OS.MAC, disabledReason = "Disabled on MAC OS")
    public void shouldNotCreateContactOnlyOnMac() {
        contactManager.addContact("Antonio", "Duque", "0693958019");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());

        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .anyMatch(contact -> contact.getFirstName().equals("Antonio") &&
                        contact.getLastName().equals("Duque") &&
                        contact.getPhoneNumber().equals("0693958019")));

    }

    @Test
    @DisplayName("Should Not Create Contact on Windows OS")
    @DisabledOnOs(value = OS.WINDOWS, disabledReason = "Disabled on Windows OS")
    public void shouldNotCreateContactOnlyOnWindows() {
        contactManager.addContact("Antonio", "Duque", "0693958019");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());

        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .anyMatch(contact -> contact.getFirstName().equals("Antonio") &&
                        contact.getLastName().equals("Duque") &&
                        contact.getPhoneNumber().equals("0693958019")));

    }

    @Test
    @DisplayName("Test Contact Creation on Developer Machine")
    public void shouldTestContactCreationOnDev() {
        //System.getProperties().put("DEV","ENV");
        Assumptions.assumeTrue("DEV".equals(System.getenv("ENV")));
        contactManager.addContact("Antonio", "Duque", "0693958019");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());


    }


    @Nested
    class RepeatedNestedTest {
        @DisplayName("Repeat Contact Creation test 5 Times")
        @RepeatedTest(value = 5, name = "Repeating Contact Creation Test {currentRepetition} of {totalRepetitions}")
        public void shouldTestContactCreationRepeatedly() {

            contactManager.addContact("Antonio", "uque", "0693958019");
            assertFalse(contactManager.getAllContacts().isEmpty());
            assertEquals(1, contactManager.getAllContacts().size());


        }

    }

    @Nested
    class ParameterizedNestedTest {

        @DisplayName("Value Source Case - Phone Number Should match the required Format")
        @ParameterizedTest
        @ValueSource(strings = {"0693958019", "0123456770", "0123123499"})

        public void shouldTestContactCreationUsingValueSource(String phoneNumber) {
            contactManager.addContact("Antonio", "Duque", phoneNumber);
            assertFalse(contactManager.getAllContacts().isEmpty());
            assertEquals(1, contactManager.getAllContacts().size());
        }

        @DisplayName("Method Source Case - Phone Number Should match the required Format")
        @ParameterizedTest
        @MethodSource("phoneNumberList")
        public void shouldTestContactCreationUsingMethodSource(String phoneNumber) {
            contactManager.addContact("Antonio", "Duque", phoneNumber);
            assertFalse(contactManager.getAllContacts().isEmpty());
            assertEquals(1, contactManager.getAllContacts().size());
        }

        private static List<String> phoneNumberList() {
            return List.of("0693958019", "0123456770", "0123123439");
        }

        @DisplayName("CVS Source Case - Phone Number Should match the required Format")
        @ParameterizedTest
        @CsvSource({"0693958019", "0123456770", "0420420420"})
        public void shouldTestContactCreationUsingCVSSource(String phoneNumber) {
            contactManager.addContact("Antonio", "Duque", phoneNumber);
            assertFalse(contactManager.getAllContacts().isEmpty());
            assertEquals(1, contactManager.getAllContacts().size());
        }


        @DisplayName("CVS FileSource Case - Phone Number Should match the required Format")
        @ParameterizedTest
        @CsvFileSource(resources = "/data.csv")
        @Disabled
        public void shouldTestContactCreationUsingCVSFileSource(String phoneNumber) {
            contactManager.addContact("Antonio", "Duque", phoneNumber);
            assertFalse(contactManager.getAllContacts().isEmpty());
            assertEquals(1, contactManager.getAllContacts().size());
        }

    }


}


