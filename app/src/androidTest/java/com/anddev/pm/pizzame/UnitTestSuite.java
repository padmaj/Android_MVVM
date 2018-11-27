package com.anddev.pm.pizzame;

import com.anddev.pm.pizzame.activity.HomeActivityTest;
import com.anddev.pm.pizzame.activity.DetailActivityTest;
import com.anddev.pm.pizzame.activity.ResultTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// Runs all unit tests.
@RunWith(Suite.class)
@Suite.SuiteClasses({HomeActivityTest.class, DetailActivityTest.class, ResultTest.class})
public class UnitTestSuite {}
