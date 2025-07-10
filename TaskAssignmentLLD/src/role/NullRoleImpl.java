package role;

import enums.PermissionType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NullRoleImpl implements Role{
    private String id;
    private String roleName;
    List<PermissionType> permissions;

    public NullRoleImpl(){
        this.roleName = "";
        this.permissions = null;
        this.id ="";
    }

    @Override
    public boolean addPermission(PermissionType permission){
        System.out.println("This is an empty role");
        return false;
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
