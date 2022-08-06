package com.ironhack.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ironhack.data.LeadRepository;
import com.ironhack.data.datasources.impl.InMemoryDatasource;
import com.ironhack.data.exceptions.DataNotFoundException;
import com.ironhack.domain.Lead;
import com.ironhack.domain.exceptions.Team5CrmException;

import static org.junit.jupiter.api.Assertions.*;

public class LeadRepositoryTest {

  LeadRepository leadRepository;

  Lead lead1;
  Lead lead2;
  Lead lead1Saved;
  Lead lead2Saved;

  @BeforeEach
  void setUp() {
    var datasource = InMemoryDatasource.getInstance();
    leadRepository = LeadRepository.getInstance(datasource);
  }

  @AfterEach
  void tearDown() {
    leadRepository.deleteAllLeads();
  }

  @Test
  void test_getInstance() {
    var datasource = InMemoryDatasource.getInstance();
    assertEquals(leadRepository, LeadRepository.getInstance(datasource));
  }

  @Test
  void test_maxLeadId() {
    var initId = leadRepository.maxLeadId();
    var leadCreated = leadRepository.saveLead(new Lead(initId, "test", "666666666", "email@gmail.com", "ironhack"));
    var afterId = leadRepository.maxLeadId();

    assertEquals(initId, leadCreated.getId());
    assertNotEquals(initId, afterId);
    assertEquals(initId + 1, afterId);

  }

  @Test
  void test_findById() {
    addLeadsToDatasource();
    Team5CrmException exception = null;
    try {
      var leadFound = leadRepository.findById(lead1.getId());
      assertEquals(lead1.getId(), leadFound.getId());
      assertEquals(lead1.getName(), leadFound.getName());
      assertEquals(lead1.getEmail(), leadFound.getEmail());
      assertEquals(lead1.getPhoneNumber(), leadFound.getPhoneNumber());
      assertEquals(lead1.getCompanyName(), leadFound.getCompanyName());
    } catch (DataNotFoundException e) {
      exception = e;
    }
    assertNull(exception);
  }

  @Test
  void test_findById_shouldThrowIfNotFound() {
    assertThrows(DataNotFoundException.class, () -> leadRepository.findById(6));
  }

  @Test
  void test_saveLead() {
    var lead = new Lead(leadRepository.maxLeadId(), "lead 1", "111111111", "lead1@gmail.com", "company 1");
    var leadSaved = leadRepository.saveLead(lead);
    assertEquals(lead.getId(), leadSaved.getId());
    assertEquals(lead.getName(), leadSaved.getName());
    assertEquals(lead.getEmail(), leadSaved.getEmail());
    assertEquals(lead.getPhoneNumber(), leadSaved.getPhoneNumber());
    assertEquals(lead.getCompanyName(), leadSaved.getCompanyName());

    Team5CrmException exception = null;
    try {
      var leadFound = leadRepository.findById(lead.getId());
      assertEquals(leadSaved.getId(), leadFound.getId());
      assertEquals(leadSaved.getName(), leadFound.getName());
      assertEquals(leadSaved.getEmail(), leadFound.getEmail());
      assertEquals(leadSaved.getPhoneNumber(), leadFound.getPhoneNumber());
      assertEquals(leadSaved.getCompanyName(), leadFound.getCompanyName());
    } catch (DataNotFoundException e) {
      exception = e;
    }
    assertNull(exception);
  }

  @Test
  void test_getAllLeads() {
    addLeadsToDatasource();
    var leads = leadRepository.getAllLeads();
    assertEquals(2, leads.size());
  }

  @Test
  void test_deleteAllLeads() {
    addLeadsToDatasource();
    leadRepository.deleteAllLeads();
    var leads = leadRepository.getAllLeads();
    assertEquals(0, leads.size());
  }

  @Test
  void test_deleteLead() {
    addLeadsToDatasource();
    Team5CrmException exception = null;
    try {
      var leads = leadRepository.getAllLeads();
      assertEquals(2, leads.size());
      leadRepository.deleteLead(lead1.getId());
      leads = leadRepository.getAllLeads();
      assertEquals(1, leads.size());
      assertEquals(lead2.getId(), leads.get(0).getId());
    } catch (DataNotFoundException e) {
      exception = e;
    }
    assertNull(exception);
  }

  @Test
  void test_deleteLead_shouldThrowIfNotFound() {
    assertThrows(DataNotFoundException.class, () -> leadRepository.deleteLead(6));
  }

  private void addLeadsToDatasource() {
    lead1 = new Lead(leadRepository.maxLeadId(), "lead 1", "111111111", "lead1@gmail.com", "company 1");
    lead1Saved = leadRepository.saveLead(lead1);
    lead2 = new Lead(leadRepository.maxLeadId() + 1, "lead 2", "222222222", "lead2@hotmail.com", "company inc 2");
    lead2Saved = leadRepository.saveLead(lead2);
  }

}
