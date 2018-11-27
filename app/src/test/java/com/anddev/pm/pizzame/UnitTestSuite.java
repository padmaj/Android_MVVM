package com.anddev.pm.pizzame;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// Runs all unit tests.
@RunWith(Suite.class)
@Suite.SuiteClasses({PizzaApiTest.class, ResultsViewModelTest.class, UtilsTest.class})
public class UnitTestSuite {}
