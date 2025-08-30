@TestDemo
Feature: Validate delete customer API-Unhappy flow

Scenario: TCID_006_UnHappyFlowDelCust-scenario to test delete customer functionality for non existing customer
    Given User is ready to execute TCID with valid secret key,baseUrl and endPoint
      | TCID                         |
      | TCID_006_UnHappyFlowDelCust |
    When User sends delete request with customer Id
    Then Verify status code should be "<statusCode>"
      | statusCode |
      | 404 |
   And Verify response contains error as a key
   And Verify the response header key and value
      | Key                                    | Value                                        |
      | Server                                 | nginx                                        |
      | Content-Type                           | application/json                             |
      | Connection                             | keep-alive                                   |
      | Access-Control-Allow-Methods           | GET, HEAD, PUT, PATCH, POST, DELETE          |
      | X-Stripe-Routing-Context-Priority-Tier | api-testmode                                 |
      | Strict-Transport-Security              | max-age=63072000; includeSubDomains; preload |
    And Verify json schema matches with 'DeleteNonexistingCustomerJsonSchema'
    And Verify response time is less than '8000' ms
    And Verify response size is more than '0' kb