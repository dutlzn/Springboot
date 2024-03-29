package com.lzn.friday.model;

import lombok.Data;

@Data
public class SysPermission extends BaseEntity<Integer> {

	private static final long serialVersionUID = -6525908145032868837L;
	private Integer parentId;
	private String name;
	private String css;
	private String href;
	private Integer type;
	private String permission;
	private Integer sort;

	@Override
	public String toString() {
		return "SysPermission{" +
				"parentId=" + parentId +
				", name='" + name + '\'' +
				", css='" + css + '\'' +
				", href='" + href + '\'' +
				", type=" + type +
				", permission='" + permission + '\'' +
				", sort=" + sort +
				'}';
	}
}
