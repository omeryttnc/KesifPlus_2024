Feature: database

  @DB
  Scenario: database kullanimi
    Given database den promocode olustur
    Given read database
    When databaseden update et
    Then databaseden sil