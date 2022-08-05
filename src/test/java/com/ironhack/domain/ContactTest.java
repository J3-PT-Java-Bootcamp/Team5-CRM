package com.ironhack.domain;

import org.junit.jupiter.api.*;

import java.util.List;

class ContactTest {

    private List<Contact> contacts;
    private Contact testContact;

    @DisplayName("Adding instances of Contacts ")
    @BeforeEach
    void setUp() {
        contacts = List.of(
                new Contact(1, "Arthur Schopenhauer", "555-000-999", "arthurito@fantasymail.com"),
                new Contact(2, "Erwin Schrodinger", "555-999-999", "ilovecats@fantasymail.com"),
                new Contact(3, "Philo Farnsworth", "555-111-999", "iloveTV@fantasymail.com"));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Getting the id from a Contact")
    void getId() {
        testContact = contacts.get(0);
        Assertions.assertNotNull(testContact);
        var idTest = 1;
        Assertions.assertEquals(idTest, testContact.getId());
    }

    @Test
    @DisplayName("Setting the id of instance")
    void setId() {
        testContact = contacts.get(0);
        testContact.setId(99);
        var aux = 99;
        Assertions.assertNotNull(testContact);
        Assertions.assertEquals(aux, testContact.getId());
    }

    @Test
    @DisplayName("Getting name from the instance contact")
    void getName() {
        testContact = contacts.get(2);
        var aux = "Philo Farnsworth";
        Assertions.assertNotNull(testContact);
        Assertions.assertEquals(aux, testContact.getName());
    }

    @Test
    @DisplayName("Setting name from contact ok")
    void setName() {
        testContact = contacts.get(1);
        testContact.setName("Ludwig Van Beethoven");
        var aux = "Ludwig Van Beethoven";
        Assertions.assertNotNull(testContact);
        Assertions.assertTrue(aux.equalsIgnoreCase(testContact.getName()));
    }

    @Test
    @DisplayName("Getting phone from the instance contact")
    void getPhone() {
        testContact = contacts.get(2);
        var aux = "555-111-999";
        Assertions.assertNotNull(testContact);
        Assertions.assertEquals(aux, testContact.getPhone());
    }

    @Test
    @DisplayName("Setting Phone from contact ok")
    void setPhone() {
        testContact = contacts.get(1);
        testContact.setPhone("1-777-99-0");
        var aux = "1-777-99-0";
        Assertions.assertNotNull(testContact);
        Assertions.assertEquals(aux, testContact.getPhone());
    }

    @Test
    @DisplayName("Getting Email from the instance contact")
    void getEmail() {
        testContact = contacts.get(0);
        var aux = "arthurito@fantasymail.com";
        Assertions.assertNotNull(testContact);
        Assertions.assertEquals(aux, testContact.getEmail());
    }

    @Test
    @DisplayName("Setting Email from contact ok")
    void setEmail() {
        testContact = contacts.get(1);
        var aux = "imaginarymail@fantasymail.com";
        testContact.setEmail("imaginarymail@fantasymail.com");
        Assertions.assertNotNull(testContact);
        Assertions.assertEquals(aux, testContact.getEmail());
    }
}
