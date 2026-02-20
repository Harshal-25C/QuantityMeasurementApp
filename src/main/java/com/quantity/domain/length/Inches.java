package com.quantity.domain.length;

public class Inches {

	private final double value;

	public Inches(double value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Inches other = (Inches) obj;
		return Double.compare(this.value, other.value) == 0;
	}
}