package com.lambdaschool.apollo;

import com.lambdaschool.apollo.models.Role;
import com.lambdaschool.apollo.models.User;
import com.lambdaschool.apollo.models.UserRoles;
import com.lambdaschool.apollo.services.RoleService;
import com.lambdaschool.apollo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * SeedData puts both known and random data into the database. It implements CommandLineRunner.
 * <p>
 * CoomandLineRunner: Spring Boot automatically runs the run method once and only once
 * after the application context has been loaded.
 */
@Transactional
@Component
public class SeedData
        implements CommandLineRunner {
    /**
     * Connects the Role Service to this process
     */
    @Autowired
    RoleService roleService;

    /**
     * Connects the user service to this process
     */
    @Autowired
    UserService userService;

    /**
     * Generates test, seed data for our application
     * First a set of known data is seeded into our database.
     * Second a random set of data using Java Faker is seeded into our database.
     * Note this process does not remove data from the database. So if data exists in the database
     * prior to running this process, that data remains in the database.
     *
     * @param args The parameter is required by the parent interface but is not used in this process.
     */
    @Transactional
    @Override
    public void run(String[] args)
            throws
            Exception {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);
        r3 = roleService.save(r3);

        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(),
                r1));
        admins.add(new UserRoles(new User(),
                r2));
        admins.add(new UserRoles(new User(),
                r3));
        User u1 = new User("FinnTheHuman",
                "Jeremy@Shada.Ooo",
                admins);

        userService.save(u1);

        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(),
                r3));
        datas.add(new UserRoles(new User(),
                r2));
        User u2 = new User("JakeTheDog",
                "John@DiMaggio.Ooo",
                datas);
        userService.save(u2);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(),
                r2));
        User u3 = new User("BMO",
                "Niki@Yang.Ooo",
                users);
        userService.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(),
                r2));
        User u4 = new User("IceKing",
                "Tom@Kenny.Ooo",
                users);
        userService.save(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(),
                r2));
        User u5 = new User("Lemongrab",
                "Justin@Roiland.Ooo",
                users);
        userService.save(u5);
    }
}