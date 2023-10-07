package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class Main {

    private static final int USERS_COUNT = 20;
    private static final int PAID_SLEEP = 1000;

    public static void main(String[] args) throws InterruptedException {

        RedisStorage redis = new RedisStorage();
        redis.init();

        while (true){
            redis.addUsers(USERS_COUNT);
            List<String> paidUsers = new ArrayList<>();

            for (String user : redis.getAllUsers()) {

                if (redis.isPaidUserCheck()){
                    String paidUser = String.valueOf(
                            ThreadLocalRandom.current().nextInt(1, USERS_COUNT + 1));
                    paidUsers.add(paidUser);
                    redis.printPaidInfo(paidUser);
                    Thread.sleep(PAID_SLEEP);
                }

                if (!paidUsers.contains(user)){
                    redis.printRegularInfo(user);
                    redis.getAllUsers().remove(user);
                }
            }
        }
    }
}