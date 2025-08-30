@TestDemo
Feature: Validate retrieve customer API

  Scenario: TCID_002_ValidateRetriveCust-scenario to test retrieve customer functionality with valid secret key
    Given User is ready to execute TCID with valid secret key,baseUrl and endPoint
      | TCID                         |
      | TCID_002_ValidateRetriveCust |
    When User sends get request with customer Id
    Then Verify status code should be "<statusCode>"
      | statusCode |
      | 200 |
    And Verify response contains Id
    And Verify the response header key and value
      | Key                                    | Value                                        |
      | Server                                 | nginx                                        |
      | Content-Type                           | application/json                             |
      | Connection                             | keep-alive                                   |
      | Access-Control-Allow-Methods           | GET, HEAD, PUT, PATCH, POST, DELETE          |
      | X-Stripe-Routing-Context-Priority-Tier | api-testmode                                 |
      | Strict-Transport-Security              | max-age=63072000; includeSubDomains; preload |
    And Verify json schema matches with 'RetrieveCustomerJsonSchema'
    And Verify response time is less than '8000' ms
    And Verify response size is more than '0' kb
