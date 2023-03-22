package test;

import org.ts.Space;
import org.ts.TSField;

public class Main {
	public static void main(String[] args) {
		Space space = new Space();
		TSField[] fields = new TSField[] {
				space.newField("",space.getType("int"),"value1")
		};
		space.defineType("mytype",128,fields);
	}
}



