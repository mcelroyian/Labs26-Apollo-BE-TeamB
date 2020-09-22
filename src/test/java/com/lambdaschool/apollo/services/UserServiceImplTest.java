package com.lambdaschool.apollo.services;

import com.lambdaschool.apollo.ApolloApplication;
import com.lambdaschool.apollo.exceptions.ResourceNotFoundException;
import com.lambdaschool.apollo.models.Role;
import com.lambdaschool.apollo.models.User;
import com.lambdaschool.apollo.models.UserRoles;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApolloApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws
            Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws
            Exception {
    }

    @Test
    public void B_findUserById() {
        assertEquals("admin", userService.findUserById(4)
                .getUsername());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void BA_findUserByIdNotFound() {
        assertEquals("admin", userService.findUserById(10)
                .getUsername());
    }

    @Test
    public void C_findAll() {
        assertEquals(5, userService.findAll()
                .size());
    }

    @Test
    public void D_delete() {
        userService.delete(13);
        assertEquals(4, userService.findAll()
                .size());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void DA_notFoundDelete() {
        userService.delete(100);
        assertEquals(4, userService.findAll()
                .size());
    }

    @Test
    public void E_findByUsername() {
        assertEquals("admin", userService.findByName("admin")
                .getUsername());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void AA_findByUsernameNotfound() {
        assertEquals("admin", userService.findByName("turtle")
                .getUsername());
    }

    @Test
    public void AB_findByNameContaining() {
        assertEquals(4, userService.findByNameContaining("a")
                .size());
    }

    @Test
    public void F_save() {
        ArrayList<UserRoles> datas = new ArrayList<>();
        User u2 = new User("tiger", "tiger@school.lambda", datas);

        User saveU2 = userService.save(u2);

        System.out.println("*** DATA ***");
        System.out.println(saveU2);
        System.out.println("*** DATA ***");

    }

    @Transactional
    @WithUserDetails("cinnamon")
    @Test
    public void G_update() {
        ArrayList<UserRoles> datas = new ArrayList<>();
        User u2 = new User("cinnamon", "cinnamon@school.lambda", datas);

        User updatedu2 = userService.update(u2, 7);

        System.out.println("*** DATA ***");
        System.out.println(updatedu2);
        System.out.println("*** DATA ***");

    }

    @Transactional
    @WithUserDetails("cinnamon")
    @Test(expected = ResourceNotFoundException.class)
    public void GB_updateNotCurrentUserNorAdmin() {
        Role r2 = new Role("user");

        ArrayList<UserRoles> datas = new ArrayList<>();
        User u2 = new User("cinnamon", "cinnamon@school.lambda", datas);

        User updatedu2 = userService.update(u2, 8);

        System.out.println("*** DATA ***");
        System.out.println(updatedu2);
        System.out.println("*** DATA ***");

    }

    @Test(expected = ResourceNotFoundException.class)
    public void HA_deleteUserRoleRoleNotFound() {
        userService.deleteUserRole(7, 50);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void HB_deleteUserRoleUserNotFound() {
        userService.deleteUserRole(50, 2);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void IC_addUserRoleRoleNotFound() {
        userService.addUserRole(7, 50);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void ID_addUserRoleUserNotFound() {
        userService.addUserRole(50, 2);
    }
}