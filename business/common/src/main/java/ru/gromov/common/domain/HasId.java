package ru.gromov.common.domain;
/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface HasId {
	Long getId();

	void setId(Long id);

	default boolean isNew() {
		return getId() == null;
	}
}
