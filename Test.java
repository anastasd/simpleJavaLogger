package dv.utils;

import java.util.*;
import java.util.Arrays;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		
		// Double
		Var<Number> tdb = new Var<Number>(123.456d);
		Log.dump(tdb);
		
		// Float
		Var<Number> tfl = new Var<Number>(123.456f);
		Log.dump(tfl);
		
		// Short
		Var<Number> tsh = new Var<Number>((short) 123);
		Log.dump(tsh);
		
		// String
		Var<String> ts = new Var<String>("single string");
		Log.dump(ts);
		
		// String[]
		Var<String[]> tsa1 = new Var<String[]>(new String[] {"a", "b"});
		Log.dump(tsa1);
		
		// String[][]
		Var<String[][]> tsa2 = new Var<String[][]>(new String[][] {{"a1", "b1"}, {"c2", "d2"}});
		Log.dump(tsa2);
		
		// String[][][]
		Var<String[][][]> tsa3 = new Var<String[][][]>(new String[][][] {{{"a1", "b1"}, {"c2", "d2"}}, {{"bebebbe"}}});
		Log.dump(tsa3);
		
		// Number
		Var<Number> tn = new Var<Number>(123.456);
		Log.dump(tn);
		
		// Number[]
		Var<Number[]> tna1 = new Var<Number[]>(new Number[] {123.456, 987});
		Log.dump(tna1);
		
		// Number[][]
		Var<Number[][]> tna2 = new Var<Number[][]>(new Number[][] {{123.456, 987}, {23, 45}});
		Log.dump(tna2);
		
		// Number[][][]
		Var<Number[][][]> tna3 = new Var<Number[][][]>(new Number[][][] {{{123.456, 987}, {23, 45}}, {{9876543}}});
		Log.dump(tna3);
		
		// Char
		Var<Character> tch = new Var<Character>(new Character('\u0123'));
		Log.dump(tch);
		
		// Char[]
		Var<Character[]> tcha1 = new Var<Character[]>(new Character[]{'\u0123', '\u0456', '\u0789'});
		Log.dump(tcha1);
		
		// Char[][]
		Var<Character[][]> tcha2 = new Var<Character[][]>(new Character[][]{{'\u0123', '\u0456'}, {'\u0789'}});
		Log.dump(tcha2);
		
		// Char[][][]
		Var<Character[][][]> tcha3 = new Var<Character[][][]>(new Character[][][]{{{'\u0123', '\u0456'}, {'\u0789'}}, {{'\u0159'}}});
		Log.dump(tcha3);
		
		// Byte
		Var<Byte> tby = new Var<Byte>((byte) 123);
		Log.dump(tby);
		
		// Byte[]
		Var<Byte[]> tbya1 = new Var<Byte[]>(new Byte[]{(byte) 12, (byte) 34, (byte) 56});
		Log.dump(tbya1);
		
		// Byte[][]
		Var<Byte[][]> tbya2 = new Var<Byte[][]>(new Byte[][]{{(byte) 12, (byte) 34}, {(byte) 56}});
		Log.dump(tbya2);
		
		// Byte[][][]
		Var<Byte[][][]> tbya3 = new Var<Byte[][][]>(new Byte[][][]{{{(byte) 12, (byte) 34}, {(byte) 56}}, {{(byte) 200}}});
		Log.dump(tbya3);
		
		// Boolean
		Var<Boolean> tb = new Var<Boolean>(true);
		Log.dump(tb);
		
		// null
		String val = null;
		Var<String> tnull = new Var<String>(val);
		Log.dump(tnull);
		
		// List<String>
		List<String> list1 = new ArrayList<String>();
		list1.add("string 1");
		list1.add("string 2");
		Var<List<String>> tas1 = new Var<List<String>>(list1);
		Log.dump(tas1);
		
		// List<String[]>
		List<String[]> list2 = new ArrayList<String[]>();
		list2.add(new String[] {"qwer", "asdf"});
		list2.add(new String[] {"zxvc"});
		Var<List<String[]>> tas2 = new Var<List<String[]>>(list2);
		Log.dump(tas2);
		
		// Object 
		Tmp x = new Tmp("hello", "world");
		Var<Tmp> to = new Var<Tmp>(x);
		Log.dump(to);
		
		// Object Array
		ArrayList<Tmp> objlist = new ArrayList<Tmp>();
		Tmp y = new Tmp("hello", "world");
		Tmp z = new Tmp("this is my", "first java class");
		objlist.add(y);
		objlist.add(z);
		Var<List<Tmp>> toa1 = new Var<List<Tmp>>(objlist);
		Log.dump(toa1);
	}
}

class Tmp {
	public String[] p1 = new String[] {"hello", "world"};
	public String p2 = "heheheheh";
	private String __str1;
	private String __str2;
	
	public Tmp(String str1, String str2) {
		__str1 = str1;
		__str2 = str2;
	}
}