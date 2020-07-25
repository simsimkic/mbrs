package myplugin.generator.fmmodel;

public class TextValidation extends Validation {
	
	private Integer minLength;
	private Integer maxLength;

	public TextValidation(Boolean unique, Boolean notNull, Integer minLength, Integer maxLength) {
		super(unique, notNull);
		this.minLength = minLength;
		this.maxLength = maxLength;
	}

	public Integer getMinLength() {
		return minLength;
	}

	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

}
