package sax;

public enum Types {

	    BYTE,
	    BOOLEAN,
	    SHORT,
	    CHAR,
	    INT,
	    FLOAT,
	    LONG,
	    DOUBLE,
	    STRING;
	
	public static Types getTypes(Class<?> clazz){
		return Types.valueOf(clazz.getSimpleName().toUpperCase());
	}
}
