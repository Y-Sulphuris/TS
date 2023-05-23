package test;

import org.ts.*;

public class Main {
	public static void main(String[] args) {
		Space space = new Space();
		/*TSField[] fields = new TSField[] {
				new TSField(space.getType("int"),"value1"),
				new TSField(TSTypePointer.forParent(space.getType("int")),"pointer")
		};
		TSType<TS_Structure> mytype = space.defineType("mytype",128,fields);
		TS_Structure var = space.defineVar("mystruct", mytype,4);
		var.setValue("value1",3);
		//System.out.println(var.getValue());
		var.setValue(fields[1],new TS_Pointer(var,fields[0]));*/
		space.vts("type position {int x; int y;};");
		space.vts("type [476] /*comment* *test*/ fixedTypeword {position value1;};");
		space.vts("int a = 4;");
		System.out.println(space);
	}
}



