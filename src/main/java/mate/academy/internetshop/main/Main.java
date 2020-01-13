package mate.academy.internetshop.main;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.UserService;

public class Main {
    @Inject
    private static UserService userService;

    @Inject
    private static ItemService itemService;
    @Inject
    private static BucketService bucketService;

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

        //////////Item/////////////////
        Item item1 = new Item("Car", 11.1);
        Item item2 = new Item("Plain", 22.2);
        System.out.println("Create Item");
        System.out.println(itemService.create(item1));
        System.out.println(itemService.create(item2));
        System.out.println(itemService.getAllItems());

        System.out.println(bucketService.getByUserId(1L));
    }
}
