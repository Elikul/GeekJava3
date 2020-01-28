/**
 * 2 Создать авто-проверялку для ДЗ
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.io.*;


public class CheckHW {
    public static void main(String[] args) throws Exception {
        File file = new File("D:/Owl/Programming Java");

        Class<?> ch = URLClassLoader.newInstance(new URL[]{
                new File("D:/Owl/Programming Java").toURL()}).loadClass("HomeWork");
        Constructor<?> constructor = ch.getConstructor();


        // Просто созданные переменные
        final int varOne = 1;
        final int varTwo = 1;
        final int varThree = 1;
        final int varFour = 1;

        // Переменные в "венгерской нотации", перед именем переменной
        // строчной буквой обозначен её тип
        final float fOne = 1;
        final float fTwo = 2;
        final float fThree = 3;
        final float fFour = 4.0f;


        Object test1 = constructor.newInstance();

        Method mCalc = ch.getDeclaredMethod("calculate");
        mCalc.invoke(test1,varOne,varTwo,varThree,varFour);
        mCalc.invoke(test1,fOne,fTwo,fThree,fFour);

        Method mCheckTwoNumbers = ch.getDeclaredMethod("checkTwoNumbers");
        mCheckTwoNumbers.invoke(test1,1,15);

        Method mPrintIsPositive = ch.getDeclaredMethod("printIsPositive");
        mPrintIsPositive.invoke(test1,-1);

        Method mIsNegative = ch.getDeclaredMethod("isNegative");
        mIsNegative.invoke(test1,-1);

        Method mPrintWelocome = ch.getDeclaredMethod("printWelocome");
        mPrintWelocome.invoke(test1,"Somename");

        Method mIsLeapYear = ch.getDeclaredMethod("isLeapYear");
        mIsLeapYear.invoke(test1,0);

    }

}

