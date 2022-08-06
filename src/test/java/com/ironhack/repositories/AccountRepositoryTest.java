package com.ironhack.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ironhack.data.AccountRepository;
import com.ironhack.data.datasources.Datasource;
import com.ironhack.data.datasources.impl.InMemoryDatasource;
import com.ironhack.domain.Account;

import static org.junit.jupiter.api.Assertions.*;

public class AccountRepositoryTest {

  Datasource datasource;
  AccountRepository accountRepository;

  @BeforeEach
  void setUp() {
    datasource = InMemoryDatasource.getInstance();
    accountRepository = AccountRepository.getInstance(datasource);
  }

  @AfterEach
  void tearDown() {
    accountRepository.deleteAllAccounts();
  }

  @Test
  void test_getInstance() {
    var datasource = InMemoryDatasource.getInstance();
    assertEquals(accountRepository, AccountRepository.getInstance(datasource));
  }

  @Test
  void test_getMaxAccountId() {
    var initId = accountRepository.getMaxAccountId();
    var accCreated = accountRepository
        .saveAccount(new Account(accountRepository.getMaxAccountId(), null, 0, null, null, null, null));
    var afterId = accountRepository.getMaxAccountId();

    assertEquals(initId, accCreated.getId());
    assertNotEquals(initId, afterId);
    assertEquals(initId + 1, afterId);
  }

  @Test
  void test_saveAccount() {
    var acc = new Account(accountRepository.getMaxAccountId(), null, 0, null, null, null, null);
    var accCreated = accountRepository
        .saveAccount(acc);
    assertEquals(acc.getId(), accCreated.getId());
  }

  @Test
  void test_deleteAllAccounts() {
    var acc = new Account(accountRepository.getMaxAccountId(), null, 0, null, null, null, null);
    accountRepository
        .saveAccount(acc);
    var accounts = datasource.getAllAccounts();
    assertEquals(1, accounts.size());
    accountRepository.deleteAllAccounts();
    accounts = datasource.getAllAccounts();
    assertEquals(0, accounts.size());
  }

}
