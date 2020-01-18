package mate.academy.internetshop.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User {

    private static Long idGenerator = 0L;

    private Long userId;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String login;
    private String password;
    private String token;
    private Set<Role> roles = new HashSet<>();

    public User(String firstName, String lastName, String address,
                String phone, String login, String password) {

        this.userId = ++idGenerator;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.login = login;
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public Long getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(userId, user.userId)
                && Objects.equals(firstName, user.firstName)
                && Objects.equals(lastName, user.lastName)
                && Objects.equals(address, user.address)
                && Objects.equals(phone, user.phone)
                && Objects.equals(login, user.login)
                && Objects.equals(password, user.password)
                && Objects.equals(token, user.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, address, phone, login, password);
    }

    @Override
    public String toString() {
        return "User{"
                + "userId=" + userId
                + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + ", address='" + address + '\''
                + ", phone='" + phone + '\''
                + ", login='" + login + '\''
                + ", password='" + password + '\''
                + '}';
    }
}
