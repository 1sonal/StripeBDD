@TestDemo
Feature: Validate delete customer API-Happy flow

  Scenario: TCID_005_ValidateDeleteCust-scenario to test delete customer functionality with valid secret key
    Given User is ready to execute TCID with valid secret key,baseUrl and endPoint
      | TCID                         |
      | TCID_005_ValidateDeleteCust |
    When User sends delete request with customer Id
    Then Verify status code should be "<statusCode>"
      | statusCode |
      | 200 |
   And Verify response contains deleted field as a key
   And Verify deleted field is not null
    And Verify the response header key and value
      | Key                                    | Value                                        |
      | Server                                 | nginx                                        |
      | Content-Type                           | application/json                             |
      | Connection                             | keep-alive                                   |
      | Access-Control-Allow-Methods           | GET, HEAD, PUT, PATCH, POST, DELETE          |
      | X-Stripe-Routing-Context-Priority-Tier | api-testmode                                 |
      | Strict-Transport-Security              | max-age=63072000; includeSubDomains; preload |
    And Verify json schema matches with 'DeleteCustomerJsonSchema'
    And Verify response time is less than '8000' ms
    And Verify response size is more than '0' kb
