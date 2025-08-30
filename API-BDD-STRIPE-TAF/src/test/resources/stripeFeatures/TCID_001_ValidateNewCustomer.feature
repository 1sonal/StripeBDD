@TestDemo
Feature: Validate creation of new customer

  Scenario Outline: TCID_001_ValidateNewCustomer-scenario to test create customer functionality with valid secret key
    Given User is ready to execute TCID "<TCID name>" with valid secret key,baseUrl and endPoint
    When User sends post request with "<formParameter1Key>" "<formParameter1Value>" and "<formParameter2Key>" "<formParameter2Value>"
    Then new customer "<createType>" be created
    And Verify status code should be <statusCode>
    And Verify response contains Id
    And Verify 'id' field is a key in response
    And Verify 'id' field is not null
    And Verify 'id' field is a String
    And Verify response contains email field  with "<formParameter1Key>" "<formParameter1Value>" 
    And Verify response contains name field with "<formParameter2Key>" "<formParameter2Value>"
    And Verify the response header key and value
      | Key                                    | Value                                        |
      | Server                                 | nginx                                        |
      | Content-Type                           | application/json                             |
      | Connection                             | keep-alive                                   |
      | Access-Control-Allow-Methods           | GET, HEAD, PUT, PATCH, POST, DELETE          |
      | X-Stripe-Routing-Context-Priority-Tier | api-testmode                                 |
      | Strict-Transport-Security              | max-age=63072000; includeSubDomains; preload |
    And Verify json schema matches with 'CreateNewCustomerJsonSchema'
    And Verify response time is less than '8000' ms
    And Verify response size is more than '0' kb

    Examples: 
      | TCID name                    | formParameter1Key | formParameter1Value | formParameter2Key | formParameter2Value | createType | statusCode |
      | TCID_001_ValidateNewCustomer | email             | rest@gmail.com      | name              | Test User           | should     |        200 |
