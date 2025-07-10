package role;

import enums.PermissionType;

import java.util.List;

public interface Role {
    public boolean addPermission(PermissionType permission);

    public String getRoleName();

    public void setRoleName(String roleName);

    public List<PermissionType> getPermissions();

    public void setPermissions(List<PermissionType> permissions);

    public String getId();
}
