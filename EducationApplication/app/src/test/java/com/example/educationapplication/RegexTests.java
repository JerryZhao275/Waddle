package com.example.educationapplication;

import com.example.educationapplication.util.CommonRegexUtil;

import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Pattern;

public class RegexTests {

    @Test
    public void test_correct_email_matches() {
        String[] validEmails = {
                "u7499989@anu.edu.au",
                "test@something.com"
        };
        for (String email : validEmails) {
            Assert.assertTrue(email + " failed", Pattern.matches(CommonRegexUtil.EMAIL, email));
        }
    }

    @Test
    public void test_email_regex() {
        String[] invalidEmails = {
                "noatsymbol.com",
                "@invalid.com",
                "two@at@symbols.com"
        };
        for (String email : invalidEmails) {
            Assert.assertFalse(email + " passed", Pattern.matches(CommonRegexUtil.EMAIL, email));
        }
    }

}