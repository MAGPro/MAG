package br.com.mag.business;

import java.io.Serializable;

import javassist.SerialVersionUID;

public abstract class AbstractEntity implements Serializable {
	
	private static final long SerialVersionUID = 1L;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass())
			return false;
		
		AbstractEntity other = (AbstractEntity) obj;

		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	public abstract Integer  getId();

}
