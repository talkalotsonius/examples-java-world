@ApiTest
@JIRA-12345
Feature: Just a random feature file containing some examples for API testing in Karate

	Background:
	# ==================== Values & Configuration ====================
	* def resourceObjectPath = 'value'
	* url baseUrl
	* path resource[resourceObjectPath].endpoint
	## Generate header
	* def headerGeneratorClass = Java.type(javaClassPathHeaderGenerator);
	* def headerGenerator = new headerGeneratorClass('requestHeaders.json');
	* def headerAsString = headerGenerator.getJsonObjectString('');
	* configure headers = convertToJSObject(headerAsString);
	##
	* json responseHeaders = read(`classpath:${responseHeaderPath}`)

	# ==================== Payloads ====================
	* json validRequestPayload = read(`classpath:${resource[resourceObjectPath].validpayload}`)


	Scenario: given an endpoint which is authorized via a token, when POST, then return HTTP 200
		Given header Authorization = `Bearer ${tokens.clientAccessToken}`
		And request validRequestPayload
		When method POST
		Then status 200


	Scenario: Validate
	* call read(httpStatusValidationFeature) { resourceObjectPath: "#(resourceObjectPath)",  headerAsString: "#(headerAsString)"}
	* call read(multipleFeature) { resourceObjectPath: "#(resourceObjectPath)",  headerAsString: "#(headerAsString)"}