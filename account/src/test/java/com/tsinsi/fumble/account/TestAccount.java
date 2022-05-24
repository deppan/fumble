package com.tsinsi.fumble.account;

import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class TestAccount {

    @Test
    public void test() {
        String date = "2022-12-21";
        LocalDate localDateTime = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-M-dd"));
        System.out.println(localDateTime);

        LocalDate now = LocalDate.now();
        LocalDate localDate = LocalDate.of(2022, 12, 31);
        Period period = Period.between(now, localDate);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
        System.out.println(localDate.toEpochDay() - now.toEpochDay());
    }
}