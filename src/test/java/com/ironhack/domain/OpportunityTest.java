package com.ironhack.domain;

import com.ironhack.domain.enums.Product;
import com.ironhack.domain.enums.Status;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OpportunityTest {

    private List <Opportunity> opportunities;
    private Opportunity testing;
    private List <Contact> contacts;


    @DisplayName("Setting the start values for instance")
    @BeforeEach
    void setUp() {
        contacts = List.of(
                new Contact(1, "Arthur Schopenhauer", "555-000-999", "arthurito@fantasymail.com" ),
                new Contact(2, "Erwin Schrodinger", "555-999-999", "ilovecats@fantasymail.com" ),
                new Contact(3, "Philo Farnsworth", "555-111-999", "iloveTV@fantasymail.com" )
        );
        opportunities = List.of(
                new Opportunity(1, contacts.get(0), Status.OPEN, Product.HYBRID, 5),
                new Opportunity(2, contacts.get(1), Status.CLOSED_LOST, Product.FLATBED, 9),
                new Opportunity(3, contacts.get(2), Status.CLOSED_WON, Product.BOX, 15)
        );

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Check new object was added")
    void getId() {
        testing = opportunities.get(1);
        Assertions.assertNotNull(testing);
        var auxId = 2;
        Assertions.assertTrue(auxId == testing.getId());
    }

    @Test
    @DisplayName("Setting values ok")
    void setId() {
        testing = opportunities.get(0);
        Assertions.assertNotNull(testing);
        testing.setId(666);
        var aux = 666;
        Assertions.assertEquals(aux, testing.getId());
    }

    @Test
    @DisplayName("Getting the contact object ok")
    void getDecisionMaker() {
        testing = opportunities.get(2);
        Assertions.assertNotNull(testing);
        Contact cont = testing.getDecisionMaker();
        Assertions.assertTrue(cont.equals(testing));
    }

    @Test
    @DisplayName("Setting the contact object ok")
    void setDecisionMaker() {
        testing = opportunities.get(2);
        Assertions.assertNotNull(testing);
        Contact cont = testing.getDecisionMaker();
        cont.setId(44);
        Assertions.assertFalse(cont.equals(testing));
    }

    @Test
    @DisplayName("Getting values from ENUM")
    void getStatus() {
        testing = opportunities.get(0);
        var example = Status.OPEN;
        Assertions.assertNotNull(testing);
        Assertions.assertEquals(example, testing.getStatus());
    }

    @Test
    @DisplayName("Setting values from ENUM")
    void setStatus() {
        testing = opportunities.get(0);
        var example = Status.CLOSED_WON;
        testing.setStatus(Status.CLOSED_WON);
        Assertions.assertNotNull(testing);
        Assertions.assertEquals(example, testing.getStatus());
    }

    @Test
    @DisplayName("Getting values from ENUM")
    void getProduct() {
        testing = opportunities.get(0);
        var example = Product.HYBRID;
        Assertions.assertNotNull(testing);
        Assertions.assertEquals(example, testing.getStatus());
    }

    @Test
    @DisplayName("Setting values from ENUM")
    void setProduct() {
        testing = opportunities.get(0);
        var example = Product.HYBRID;
        testing.setProduct(Product.BOX);
        Assertions.assertNotNull(testing);
        Assertions.assertFalse(example.equals(testing.getStatus()));
    }

    @Test
    @DisplayName("Getting values from Quantity")
    void getQuantity() {
        testing = opportunities.get(1);
        var quanty = 9;
        Assertions.assertNotNull(testing);
        Assertions.assertTrue(quanty == testing.getQuantity());
    }

    @Test
    @DisplayName("Setting values from Quantity")
    void setQuantity() {
        testing = opportunities.get(1);
        var quanty = 99;
        testing.setQuantity(99);
        Assertions.assertNotNull(testing);
        Assertions.assertTrue(quanty == testing.getQuantity());
    }
}