//package com.journalapp.journalApp.service;
//
//import com.journalapp.journalApp.repository.UserRepo;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest // Loads full Spring Boot context
//class UserServiceTests {
//
//    @Autowired // Injects bean from Spring container
//    private UserRepo userRepo;
//
//    // ------------------------------------------------
//    // @BeforeAll
//    // Runs ONCE before all test methods
//    // Must be static
//    // Used for DB connection/setup
//    // ------------------------------------------------
//    @BeforeAll
//    static void beforeAll() {
//        System.out.println("Runs once before all tests");
//    }
//
//    // ------------------------------------------------
//    // @BeforeEach
//    // Runs before EVERY test method
//    // Used for initialization
//    // ------------------------------------------------
//    @BeforeEach
//    void beforeEach() {
//        System.out.println("Runs before every test");
//    }
//
//    // ------------------------------------------------
//    // @Test
//    // Marks normal test method
//    // ------------------------------------------------
//    @Test
//    void addTest() {
//        int result = 2 + 3;
//
//        // assertEquals(expected, actual)
//        assertEquals(5, result);
//    }
//
//    // ------------------------------------------------
//    // @Disabled
//    // Skips this test
//    // Useful when test is incomplete
//    // ------------------------------------------------
//    @Disabled
//    @Test
//    void disabledTest() {
//        assertEquals(4, 2 + 1);
//    }
//
//    // ------------------------------------------------
//    // assertNotNull()
//    // Checks object should not be null
//    // ------------------------------------------------
//    @Test
//    void testFindByUserName() {
//        assertNotNull(userRepo.findByUserName("ArjunPandey"));
//    }
//
//    // ------------------------------------------------
//    // assertTrue()
//    // Condition must be true
//    // ------------------------------------------------
//    @Test
//    void trueTest() {
//        assertTrue(10 > 5);
//    }
//
//    // ------------------------------------------------
//    // assertFalse()
//    // Condition must be false
//    // ------------------------------------------------
//    @Test
//    void falseTest() {
//        assertFalse(5 > 10);
//    }
//
//    // ------------------------------------------------
//    // assertNull()
//    // Value must be null
//    // ------------------------------------------------
//    @Test
//    void nullTest() {
//        String data = null;
//        assertNull(data);
//    }
//
//    // ------------------------------------------------
//    // assertThrows()
//    // Checks exception should occur
//    // ------------------------------------------------
//    @Test
//    void exceptionTest() {
//
//        Exception exception = assertThrows(
//                ArithmeticException.class,
//                () -> {
//                    int result = 10 / 0;
//                }
//        );
//
//        assertEquals("/ by zero", exception.getMessage());
//    }
//
//    // ------------------------------------------------
//    // @ParameterizedTest
//    // Runs same test with multiple values
//    // ------------------------------------------------
//    @ParameterizedTest
//    @CsvSource({
//            "1,1,2",
//            "2,4,6",
//            "5,5,10"
//    })
//    void additionTest(int a, int b, int expected) {
//
//        assertEquals(
//                expected,
//                a + b,
//                "Failed for expected = " + expected
//        );
//    }
//
//    // ------------------------------------------------
//    // @ValueSource
//    // Pass single values
//    // ------------------------------------------------
//    @ParameterizedTest
//    @ValueSource(strings = {
//            "madam",
//            "level",
//            "radar"
//    })
//    void palindromeTest(String word) {
//
//        String reverse =
//                new StringBuilder(word)
//                        .reverse()
//                        .toString();
//
//        assertEquals(word, reverse);
//    }
//
//    // ------------------------------------------------
//    // @RepeatedTest
//    // Runs same test multiple times
//    // ------------------------------------------------
//    @RepeatedTest(3)
//    void repeatedTest() {
//        System.out.println("Runs 3 times");
//    }
//
//    // ------------------------------------------------
//    // @AfterEach
//    // Runs after every test
//    // Used for cleanup
//    // ------------------------------------------------
//    @AfterEach
//    void afterEach() {
//        System.out.println("Runs after every test");
//    }
//
//    // ------------------------------------------------
//    // @AfterAll
//    // Runs ONCE after all tests
//    // Must be static
//    // ------------------------------------------------
//    @AfterAll
//    static void afterAll() {
//        System.out.println("Runs once after all tests");
//    }
//}