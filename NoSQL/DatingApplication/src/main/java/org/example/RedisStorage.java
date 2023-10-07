package org.example;

import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.Date;

public class RedisStorage {

    private RedissonClient redisson;
    private RScoredSortedSet<String> users;
    private final static String KEY = "NEW_USERS";

    private double getTs() {
        return new Date().getTime();
    }

    public void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            System.out.println("Не удалось подключиться к Redis");
            System.out.println(Exc.getMessage());
        }
        RKeys rKeys = redisson.getKeys();
        users = redisson.getScoredSortedSet(KEY);
        rKeys.delete(KEY);
    }

    public void addUsers(int usersCount) {
        for (int i = 1; i <= usersCount; i++) {
            users.add(getTs(), String.valueOf(i));
        }
    }

    public RScoredSortedSet<String> getAllUsers() {
        return users;
    }

    public boolean isPaidUserCheck() {
        return Math.random() < 0.1;
    }

    public void printRegularInfo(String user){
        System.out.println("На главной странице показываем пользователя " + user);
    }

    public void printPaidInfo(String user){
        System.out.println("Пользователь " + user + " оплатил платную услугу\nНа главной странице 1 сек платно показываем пользователя " + user);
    }
}
