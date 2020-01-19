package mate.academy.internetshop.model;

public class Role {
    private static Long idGenerator = 0L;
    private Long id;
    private RoleName roleName;

    public Role() {
        this.id = ++idGenerator;
    }

    public Role(RoleName roleName) {
        this();
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public static Role of(String roleName) {
        return new Role(RoleName.valueOf(roleName));
    }

    public enum RoleName {
        USER, ADMIN;
    }
}
