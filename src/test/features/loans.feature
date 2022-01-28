Feature: Loans Application API

  Scenario Outline: Ability to successfully add new applicant to the loans application system
    When I add a new client with the following details <IdNumber> <Name> <Surname> <AccountNumber> <BankName>
    Then a new applicant is added to the application system
    Examples:
      | IdNumber      | Name    | Surname | AccountNumber | BankName     |
      | 9901025391084 | Pontsho | Molewa  | 1234567890    | ICONIC_BANK  |
      | 0401025391084 | Pontsho | Molewa  | 1234567890    | MINIONS_BANK |
      | 0301025391084 | Pontsho | Molewa  | 1234567890    | SCRUM_BANK   |

  Scenario Outline: Ability to throw a warning when molewa bank is captured as the client's bank
    When I add a new client with the following details <IdNumber> <Name> <Surname> <AccountNumber> <BankName>
    Then a <Warning> is returned
    Examples:
      | IdNumber      | Name    | Surname | AccountNumber | BankName     | Warning              |
      | 9901025391084 | Pontsho | Molewa  | 1234567890    | MOLEWA_BANK  | Refer to compliance  |


  Scenario Outline: Ability to validate missing or incorrect client details entered to the loans application system
    When I add a new client with the following details <IdNumber> <Name> <Surname> <AccountNumber> <BankName>
    Then an <Error> is returned
    Examples:
      | IdNumber       | Name     | Surname | AccountNumber  | BankName     | Error                                                         |
      | 9901025391084  | Pontsho  | Molewa  |                | ICONIC_BANK  | Bank account number must be 10 digits                         |
      | 9901025391084  | Pontsho  | Molewa  | 123456789      | MINIONS_BANK | Bank account number must be 10 digits                         |
      | 9901025391084  | Pontsho  | Molewa  | 12345678901011 | SCRUM_BANK   | Bank account number must be 10 digits                         |
      | 9901025391084  | Pontsho  | Molewa  | ABCDEFGHIJ     | ICONIC_BANK  | Bank account number must be 10 digits                         |
      | 9901025391084  | Pontsho  | Molewa  | 123456789%     | MINIONS_BANK | Bank account number must be 10 digits                         |
      | 9901025391084  | Pontsho  | Molewa  | 1111111e12     | MINIONS_BANK | Bank account number must be 10 digits                         |
      | 9901025391084  | Pon1tsho | Molewa  | 1234567890     | ICONIC_BANK  | Name must not have any special characters and digits          |
      | 9901025391084  | Pon&tsho | Molewa  | 1234567890     | MINIONS_BANK | Name must not have any special characters and digits          |
      | 9901025391084  |          | Molewa  | 1234567890     | MINIONS_BANK | Name is required                                              |
      | 9901025391084  | Pontsho  | Mol1ewa | 1234567890     | ICONIC_BANK  | Surname must not have any special characters and digits       |
      | 9901025391084  | Pontsho  | Mol@ewa | 1234567890     | SCRUM_BANK   | Surname must not have any special characters and digits       |
      | 9901025391084  | Pontsho  |         | 1234567890     | SCRUM_BANK   | Surname is required                                           |
      | 0501025391084  | Pontsho  | Molewa  | 1234567890     | ICONIC_BANK  | The client must be 18 years or older                          |
      | 050102539108   | Pontsho  | Molewa  | 1234567890     | ICONIC_BANK  | ID Number must be a valid South African ID number (13 digits) |
      | 05010253910822 | Pontsho  | Molewa  | 1234567890     | ICONIC_BANK  | ID Number must be a valid South African ID number (13 digits) |
      |                | Pontsho  | Molewa  | 1234567890     | ICONIC_BANK  | ID Number must be a valid South African ID number (13 digits) |
      | 9913325391084  | Pontsho  | Molewa  | 1234567890     | ICONIC_BANK  | ID Number must be a valid South African ID number (13 digits) |
