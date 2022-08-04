package com.ironhack.domain;

import com.ironhack.domain.enums.Industry;
import com.ironhack.domain.enums.Product;
import com.ironhack.domain.enums.Status;
import org.junit.jupiter.api.*;

import java.util.List;

public class AccountTest {

    private List <Account> account;


        @BeforeEach
        @DisplayName("Starting the object for the test")
        void setUp() {

            var contacts = List.of(
                    new Contact(1, "Arthur Schopenhauer", "555-000-999", "arthurito@fantasymail.com" ),
                    new Contact(2, "Erwin Schrodinger", "555-999-999", "ilovecats@fantasymail.com" ),
                    new Contact(1, "Philo Farnsworth", "555-111-999", "iloveTV@fantasymail.com" )
            );

            var opportunities = List.of(
                    new Opportunity(1, contacts.get(0), Status.OPEN, Product.HYBRID, 5),
                    new Opportunity(2, contacts.get(1), Status.CLOSED_LOST, Product.FLATBED, 9),
                    new Opportunity(3, contacts.get(2), Status.CLOSED_WON, Product.BOX, 15)
            );

            account = List.of(
                    new Account(1, Industry.ECOMMERCE, 741, "Barcelona", "Spain", contacts, opportunities),
                    new Account(2, Industry.MANUFACTURING, 115, "Philadelphia", "USA", contacts, opportunities),
                    new Account(3, Industry.MEDICAL, 1089, "Berlin", "Germany", contacts, opportunities)
            );
        }

        @AfterEach
        void tearDown() {
        }

        @Test
        @DisplayName("Getting the correct id")
        void getId() {
            int correctID = 1;
            var accountTest = account.get(0);
            Assertions.assertNotNull(accountTest);
            Assertions.assertEquals(correctID, accountTest.getId());
        }

        @Test
        @DisplayName("Setting the id of account")
        void setId() {
            var accountTest = account.get(2);
            var newId = 7;
            accountTest.setId(7);
            Assertions.assertEquals(newId, accountTest.getId());
        }

        @Test
        @DisplayName("Getting the Industry value")
        void getIndustry() {
            var accountTest = account.get(1).getIndustry();
            var example = Industry.MANUFACTURING;
            Assertions.assertNotNull(accountTest);
            Assertions.assertTrue(example == accountTest);
        }

        @Test
        @DisplayName("Setting the ENUM")
        void setIndustry() {
            var originalEnum = account.get(0).getIndustry();
            var accountTest = account.get(0);
            accountTest.setIndustry(Industry.PRODUCE);
            Assertions.assertNotEquals(originalEnum, accountTest.getIndustry());
        }

        @Test
        @DisplayName("Check the correct employees count")
        void getEmployeesCount() {
            var realCount = 1089;
            var accountTest = account.get(2).getEmployeesCount();
            Assertions.assertTrue(realCount == accountTest);
        }

        @Test
        @DisplayName("Setting the count")
        void setEmployeesCount() {
            var newCount = 666;
            var counter = account.get(1);
            counter.setEmployeesCount(newCount);
            Assertions.assertEquals(newCount, counter.getEmployeesCount());
        }

        @Test
        @DisplayName("Getting the City attributte")
        void getCity() {
            var cityName = "Barcelona";
            var accountTest = account.get(0);
            Assertions.assertNotNull(accountTest);
            Assertions.assertTrue(cityName.equalsIgnoreCase(accountTest.getCity()));
        }

        @Test
        @DisplayName("Setting the City attributte")
        void setCity() {
            var accountTest = account.get(1);
            accountTest.setCity("Madrid");
            var city = "Madrid";
            Assertions.assertNotNull(accountTest);
            Assertions.assertTrue(city.equals(accountTest.getCity()));
        }

        @Test
        @DisplayName("Getting the country from the created object on the constructor")
        void getCountry() {
            var accounttest = account.get(2);
            Assertions.assertNotNull(accounttest);
            Assertions.assertEquals(accounttest.getCountry(), account.get(2).getCountry());
        }

        @Test
        void setCountry() {
            var accountest = account.get(1);
            accountest.setCountry("France");
            var newCountry = "france";
            Assertions.assertTrue(newCountry.equalsIgnoreCase(accountest.getCountry()));
        }

        @Test
        void getContactList() {
        }

        @Test
        void setContactList() {
        }

        @Test
        void getOpportunityList() {
        }

        @Test
        void setOpportunityList() {
        }

}
