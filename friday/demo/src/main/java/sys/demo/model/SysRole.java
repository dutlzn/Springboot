package sys.demo.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
//@Builder
@EqualsAndHashCode(callSuper = true)
public class SysRole extends BaseEntity<Integer> {

	private static final long serialVersionUID = -6525908145032868837L;

	private String name;
	private String description;

	@Override
	public String toString() {
		return "SysRole{" +
				"name='" + name + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
