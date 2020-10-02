import java.util.HashSet;
import java.util.Random;

/**
 * CS5004 Assignment 10: Problem 1 - BirthdayParadox.
 * This program is used to approximate the probability that 2 or more people in the same room
 * have same birthday, for 2 to 50 people.
 *
 * @author Yi Deng
 * @since 2020-08-01
 */
public class BirthdayParadox {
    public static void main(String[] args) {
        Random rand = new Random();
        //declare variable for birthday number.
        int birthday;
        //initialize variable for number of people.
        int numOfPpl = 2;
        //initialize variable for recording number of trials.
        int trial = 0;
        //while number of ppl is in the range.
        while (numOfPpl <= 50){
            //every range of increasing number of ppl, make the count to be 0.
            int count = 0;
            //while loop for trials, every number of ppl range should try for 5000 times.
            while (trial < 5000){ //trial is 0, so the range is 0-4999.
                //initialize the hashset for every trial.
                HashSet<Integer> birthdaySet = new HashSet<>();
                //for loop for every time when generating a birthday.
                // the upper bound is the number of ppl for each turn.
                for(int j = 0; j < numOfPpl; j++) {
                    //generating birthdays for ppl, because the range is 1 - 365,
                    // for the rand.nextInt(365) would generating a number between 0(inclusive) to 364(inclusive),
                    // so make it plus one.
                    birthday = rand.nextInt(365) + 1; //1-365
                    //check if the hashset contains this birthday.
                    if (birthdaySet.contains(birthday)) {
                        //if it contains, count plus 1, and move to another trial.
                        count++;
                        break;
                    } else { // if not contains, add it to the hashset.
                        birthdaySet.add(birthday);
                    }
                } //for loop break out：1， go over all the number of ppl；
                // 2， once there are two ppl have same birthday.
                trial++; //in the end of every trial, trial += 1.
            }

//            System.out.println(count);
//            System.out.println(trial);

            //in the end of all trial for certain numbers of ppl, calculate the probability, and print.
            double probability = ((double) count / trial);
            System.out.printf("For %d people, the probability of two birthdays is about %.4f.\n", numOfPpl, probability);
            //then number of ppl += 1, which means we are going to next certain number of ppl.
            numOfPpl++;
            //at the same time, make trail to be 0 again.
            trial = 0;
        }
    }
}
