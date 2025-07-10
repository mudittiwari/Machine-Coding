package role;

import enums.PermissionType;

import java.security.Permission;
import java.util.List;
import java.util.UUID;

public class RoleImpl implements Role{
    private String id;
    private String roleName;
    List<PermissionType> permissions;

    public RoleImpl(String roleName, List<PermissionType> permissions){
        this.roleName = roleName;
        this.permissions = permissions;
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public boolean addPermission(PermissionType permission){
        this.permissions.add(permission);
        return true;
    }

    @Override
    public String getRoleName() {
        return roleName;
    }

    @Override
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public List<PermissionType> getPermissions() {
        return permissions;
    }

    @Override
    public void setPermissions(List<PermissionType> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String getId() {
        return id;
    }
}
