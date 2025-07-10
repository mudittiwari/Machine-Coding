package user;

import role.Role;

public interface User {
    public String getName();
    public void setName(String name);
    public String getEmail();
    public void setEmail(String email);
    public Role getRole();
    public void setRole(Role role);
    public String getId();
}
