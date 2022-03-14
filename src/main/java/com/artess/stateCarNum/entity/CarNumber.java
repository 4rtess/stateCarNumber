package com.artess.stateCarNum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class CarNumber {
    private static final List<Character> alphabet = Arrays.asList('А', 'Е', 'Т', 'О', 'Р', 'Н', 'У', 'К', 'Х', 'С', 'В', 'М');
    private static final String region = "95 RUS";
    private static List<CarNumber> createdCarNumbers  = new ArrayList<>();

    private char firstLetter;
    private char secondLetter;
    private char thirdLetter;
    private int num;


    @Override
    public String toString() {
        return String.format("%c%03d%c%c %s", firstLetter, num, secondLetter, thirdLetter, region);
    }

    public static CarNumber createRandomCarNumber() {
        int randNum = (int) (Math.random() * 1000);
        while(randNum <990)
            randNum = (int) (Math.random() * 1000);
        int randFirstLetter = (int) (Math.random()*alphabet.size());
        int randSecondLetter = (int) (Math.random()*alphabet.size());
        int randThirdLetter = (int) (Math.random()*alphabet.size());
        CarNumber cn = new CarNumber(alphabet.get(randFirstLetter), alphabet.get(randSecondLetter), alphabet.get(randThirdLetter), randNum);
        //CarNumber cn = new CarNumber('В','М','М', randNum);
        String carNum = cn.toString();
        if(createdCarNumbers.contains(carNum)) {
            return createRandomCarNumber();
        } else {
            createdCarNumbers.add(cn);
            return cn;
        }
    }

    public static CarNumber nextCarNumber() {

        if(createdCarNumbers.size()==0) {CarNumber c = new CarNumber('A','A','A',0);createdCarNumbers.add(c);return c;}

        CarNumber cn = createdCarNumbers.get(createdCarNumbers.size()-1);
        int num = cn.getNum()+1;
        if(num>=1000) {
            num=0;
            List<Character> chars = nextLetterNum(cn.getFirstLetter(), cn.getSecondLetter(), cn.getThirdLetter());
            CarNumber carNumber = new CarNumber(chars.get(0),chars.get(1),chars.get(2), num);
            createdCarNumbers.add(carNumber);
            return carNumber;
        } else {
            CarNumber carNumber = new CarNumber(cn.getFirstLetter(), cn.getSecondLetter(), cn.getThirdLetter(), num);
            createdCarNumbers.add(carNumber);
            return carNumber;
        }
    }

    // #TODO FIX MBM position
    private static List<Character> nextLetterNum(char firstLetter, char secondLetter, char thirdLetter) {
        //Convert to decimal and get next num
        int toDec = (alphabet.indexOf(thirdLetter)+1) + 12*(alphabet.indexOf(secondLetter)+1) + 144*(alphabet.indexOf(firstLetter)+1);
        toDec+=1;

        //Convert back to twelve numeral system
        List<Character> chars = new ArrayList<>();
         do {
             int n = toDec % 12;
             if (n==0)
                 n=12;
             System.out.println(toDec + " | " + n);
             chars.add(alphabet.get(n-1));
             toDec /= 12;
         } while(toDec>=12);
         chars.add(alphabet.get(toDec%12 -1));
         System.out.println(chars);
         if(chars.size()>3)
             chars.remove(chars.size()-1);
         Collections.reverse(chars);
         return chars;

      /*  System.out.println(toDec);
        while(toDec>=12) {
            int remainder = toDec%12;
            if (remainder==0)
                remainder=12;
            System.out.println(toDec + " | " + remainder);
            chars.add(alphabet.get(remainder-1));
            toDec/=12;
        }
        System.out.println("-->"+toDec);
        chars.add(alphabet.get(toDec%12-1));
        System.out.println(chars);
        if (chars.size()>3) {
            //if (chars.get(1)=='A' && chars.get(2) == 'A') {
            //    chars.remove(2);
            //    chars.remove(1);
            //    chars.add(1,'М');
            //}
            if (chars.get(2) == 'А' && chars.get((3)) == 'А') {
                chars.remove(3);
                chars.remove(2);
                chars.add('М');

            }
        }
        System.out.println(chars);
        Collections.reverse(chars);

        return chars;
*/
    }
}
