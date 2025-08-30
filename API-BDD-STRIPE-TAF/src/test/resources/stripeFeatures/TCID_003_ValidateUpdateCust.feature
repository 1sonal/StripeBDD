@TestDemo
Feature: Validate update customer API

  Scenario Outline: TCID_003_ValidateUpdateCust-scenario to test update customer functionality with valid secret key
    Given User is ready to execute TCID "<TCID name>" with valid secret key,baseUrl and endPoint
    When User sends post request with "<formParameter1Key>" "<formParameter1Value>" and "<formParameter2Key>" "<formParameter2Value>" and customer Id
    Then Verify status code should be "<statusCode>"
      | statusCode |
      | 200 |
    And Verify email field in response is not null
    And Verify City field in response is updated
      | City |
      | Pune |
    And Verify the response header key and value
      | Key                                    | Value                                        |
      | Server                                 | nginx                                        |
      | Content-Type                           | application/json                             |
      | Connection                             | keep-alive                                   |
      | Access-Control-Allow-Methods           | GET, HEAD, PUT, PATCH, POST, DELETE          |
      | X-Stripe-Routing-Context-Priority-Tier | api-testmode                                 |
      | Strict-Transport-Security              | max-age=63072000; includeSubDomains; preload |
    And Verify json schema matches with 'UpdateCustJsonSchema'
    And Verify response time is less than '8000' ms
    And Verify response size is more than '0' kb

    Examples: 
      | TCID name                   | formParameter1Key | formParameter1Value | formParameter2Key | formParameter2Value |
      | TCID_003_ValidateUpdateCust | email             | test@gmail.com      | address[city]     | Pune                |
