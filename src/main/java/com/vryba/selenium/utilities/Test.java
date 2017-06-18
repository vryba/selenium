package com.vryba.selenium.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class Test {

    static final Logger logger = LogManager.getLogger(Test.class);

    public static void main(String[] args){
        logger.info("Hy I am in the man method");
        LoginTest();
    }
    public static void LoginTest(){

        logger.error("I've got failed!");
    }
}
