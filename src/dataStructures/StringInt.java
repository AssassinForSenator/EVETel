package dataStructures;

@Deprecated
public class StringInt implements Comparable<StringInt> {
	private int integer;
	private String string;

	public int getInteger() {
		return integer;
	}

	public void setInteger(int integer) {
		this.integer = integer;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	@Override
	public int compareTo(StringInt other) {
		return other.getInteger() - this.getInteger();
	}
}
