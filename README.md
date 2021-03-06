# TestingLibrary
[![Build Status](https://travis-ci.org/AkiValiaho/TestingLibrary.svg?branch=master)](https://travis-ci.org/AkiValiaho/TestingLibrary)

Humorously named educational unit test and mocking library

Implemented as a Maven MOJO-plugin which is injected into the test phase of
your build.



# How to use

TestingLibrary is used in a very familiar way if you've done work with testing libraries
like JUnit.

You need the plugin in your pom:
    
    <dependencies>
        <dependency>
            <groupId>com.akivaliaho</groupId>
            <artifactId>AkiTest-maven-plugin</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>com.akivaliaho</groupId>
                <artifactId>AkiTest-maven-plugin</artifactId>
                <version>0.0.1-SNAPSHOT</version>
                <executions>
                    <execution>
                        <phase>test</phase>
                        <configuration>
                            <defaultPackage>com.akivaliaho.AkiTestingClientCode</defaultPackage>
                            <parallel>true</parallel>
                        </configuration>
                        <goals>
                            <goal>test</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
    
    
 Tests are written as follows:
 
    public class TestClass {
        //Typical mocking annotation for a service
        @AkiMock
        BeerService beerService;
        private org.slf4j.Logger LOG = LoggerFactory.getLogger(TestClass.class);
    
        @Before
        public void init() {
            LOG.debug("Test initialization begin");
        }
    
        @Test(expected = AssertionError.class)
        public void initializationTest() {
            BeerService mockInstance = new AkiMockInstance<BeerService>() {
                //@AkiMockUp can be used to influence method returns and call verifications for the mock
                @AkiMockUp(hit = 1)
                public String getNoArgsString() {
                    return "Hello world";
                }
            }.getMockInstance();
            String noArgsString = mockInstance.getNoArgsString();
            Assertion.assertTrue(noArgsString.equals("Hello world"));
            LOG.info("Debug string: {}", noArgsString);
            LOG.info("Found the test method!");
            Assertion.assertTrue(beerService != null);
            Assertion.assertTrue(false);
        }
        
        #Example with jsonPath matcher
           @Test
            public void jsonPathParserTest() {
                BeerService mockInstance = new AkiMockInstance<BeerService>() {
                    @AkiMockUp(hit = 1)
                    public String getNoArgsString() {
                        return "{\"aki\": \"hello\", \"testing\" : \"testbed\"}";
                    }
                }.getMockInstance();
                String noArgsString = mockInstance.getNoArgsString();
                String testing = jsonPath(noArgsString, "testing");
                LOG.info("JsonPath found string: {}", testing);
                assertEquals(testing, "testbed");
            }
    }
    
    
    
    
    
# Worklist
    
    jsonPath navigation for jsons with arrays inside them
