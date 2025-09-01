# ledger-service
A Spring Boot microservice that manages account balances and applies atomic debit/credit operations. 
It serves as the financial backbone for transfer orchestration.

# Tech Stack
Java 11

Spring Boot 2.7.18

Spring Data JPA

H2 Database
![img_4.png](img_4.png)
![img_5.png](img_5.png)

Maven

#Features

- Create accounts with initial balance
- Apply transfers between accounts
- Validates account existence and sufficient funds
- In-memory H2 database for local development

## Endpoints

### POST `/accounts`
Create a new account.

**Query Param:**
- `initialBalance`
![img.png](img.png)

### GET `/accounts/{id}`
Retrieve account details.
![img_1.png](img_1.png)

### POST `/ledger/transfer`
Apply a transfer.
![img_3.png](img_3.png)

**Payload:**
```json
{
  "transferId": "UUID",
  "fromAccountId": "UUID",
  "toAccountId": "UUID",
  "amount": 100.00
}
