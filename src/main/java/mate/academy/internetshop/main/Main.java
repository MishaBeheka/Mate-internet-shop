package mate.academy.internetshop.main;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

public class Main {
    @Inject
    private static UserService userService;

    static {
        try {
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        User user1 = new User("111", "111", "111", "111", "111", "111");
        User user2 = new User("222", "222", "222", "222", "222", "222");

        userService.create(user1);
        userService.create(user2);
        System.out.println("Get all");
        System.out.println(userService.getAll());
        System.out.println("User with ID = 2");
        System.out.println(userService.get(2L));
        user2.setFirstName("99999");
        System.out.println("Update user2");
        userService.update(user2);
        System.out.println(userService.getAll());
        System.out.println("Delete by ID 2");
        userService.deleteById(2L);
        System.out.println(userService.getAll());
        System.out.println("Delete by Entity user1");
        userService.deleteByEntity(user1);
        System.out.println(userService.getAll());

    }
}
