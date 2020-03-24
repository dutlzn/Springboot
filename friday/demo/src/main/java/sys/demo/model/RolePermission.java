package sys.demo.model;

import lombok.Builder;
import lombok.Data;

@Data
//@Builder
public class RolePermission {
    private Integer roleId;
    private Integer permissionId;

}
