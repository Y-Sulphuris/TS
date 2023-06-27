package test;

import org.ts.*;
import org.ts.vars.TS_Array;
import org.ts.vars.TS_Structure;

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
		space.vts("position pos1 = (3,6);");

		//TS_Array<TS_Structure> array1 = new TS_Array<>(OverriddenTypes.ts_byte,16);
		//array1.set(3,OverriddenTypes.ts_byte.valueOf(5));
		//space.defineVar("array1",array1);

		space.vts("byte[] array1 = {3,6,9,7,3};");
		space.vts("int[][] array2 = {{3},{6,7},{9,3,1},{7},{3}};");

		//space.defineVar("pos1",space.getType("position"),3,6);
		System.out.println(space);
	}
}



