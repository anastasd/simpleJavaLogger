package dv.utils;

import java.io.*;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.*;

public class Log {
    private static String __DIR__;
    private static File __fleLog;
	private static String __logName = "debug.txt";

	private enum classList {
		DOUBLE("class java.lang.Double"),
		FLOAT("class java.lang.Float"),
		INTEGER("class java.lang.Integer"),
		SHORT("class java.lang.Short"),
		LONG("class java.lang.Long"),
		NUMBER("class java.lang.Number"),
		STRING("class java.lang.String"),
		CHARACTER("class java.lang.Character"),
		BYTE("class java.lang.Byte"),
		BOOLEAN("class java.lang.Boolean"),
		STRING1("class [Ljava.lang.String;"),
		STRING2("class [[Ljava.lang.String;"),
		STRING3("class [[[Ljava.lang.String;"),
		DOUBLE1("class [Ljava.lang.Double;"),
		FLOAT1("class [Ljava.lang.Float;"),
		INTEGER1("class [Ljava.lang.Integer;"),
		SHORT1("class [Ljava.lang.Short;"),
		LONG1("class [Ljava.lang.Long;"),
		NUMBER1("class [Ljava.lang.Number;"),
		DOUBLE2("class [[Ljava.lang.Double;"),
		FLOAT2("class [[Ljava.lang.Float;"),
		INTEGER2("class [[Ljava.lang.Integer;"),
		SHORT2("class [[Ljava.lang.Short;"),
		LONG2("class [[Ljava.lang.Long;"),
		NUMBER2("class [[Ljava.lang.Number;"),
		DOUBLE3("class [[[Ljava.lang.Double;"),
		FLOAT3("class [[[Ljava.lang.Float;"),
		INTEGER3("class [[[Ljava.lang.Integer;"),
		SHORT3("class [[[Ljava.lang.Short;"),
		LONG3("class [[[Ljava.lang.Long;"),
		NUMBER3("class [[[Ljava.lang.Number;"),
		BYTE1("class [Ljava.lang.Byte;"),
		BYTE2("class [[Ljava.lang.Byte;"),
		BYTE3("class [[[Ljava.lang.Byte;"),
		CHARACTER1("class [Ljava.lang.Character;"),
		CHARACTER2("class [[Ljava.lang.Character;"),
		CHARACTER3("class [[[Ljava.lang.Character;"),
		ARRAYLIST("class java.util.ArrayList"),
		NIL("null");
		
		private final String name;
	
		// Reverse-lookup map for getting a day from an abbreviation
		private static final Map<String, classList> lookup = new HashMap<String, classList>();
	
		static {
			for (classList d : classList.values()) {
				lookup.put(d.getName(), d);
			}
		}
	
		private classList(String name) {
			this.name = name;
		}
	
		public String getName() {
			return name;
		}
	
		public static classList get(String name) {
			return lookup.get(name);
		}
	}
	/*
	 * Prints a value
	 * Var<Number> tmp = new Var<Number>(123.456); Log.dump(tmp);
	 * ......
	 * Var<String[][]> tmp = new Var<String[][]>(new String[][] {{"a", "b"}, {"c", "d"}}); Log.dump(tmp);
	 * ......
	 * 	List<String[]> list = new ArrayList<String[]>();
		list.add(new String[] {"qwer", "asdf"});
		list.add(new String[] {"zxvc"});
		Var<List<String[]>> t4 = new Var<List<String[]>>(list);
		Log.dump(t4);
	 */
	public static void dump(Var obj) {
		String strout = "";
		classList ctype = classList.get(obj.type);
		
		if (ctype == null) {
			try {
				strout += __dumpObject(obj.get(), -1, 0);
			} catch (Exception ex) {
				strout += __dumpPrimitive(obj.get().toString(), -1, 0);
			}
		} else {
			switch (ctype) {
				case DOUBLE:
				case FLOAT:
				case INTEGER:
				case SHORT:
				case LONG:
				case NUMBER:
				case STRING:
				case CHARACTER:
				case BYTE:
				case BOOLEAN:
					strout += __dumpPrimitive(obj.get().toString(), -1, 0);
					break;
				case STRING1:
					strout += __dumpString1((String[]) obj.get(), 0);
					break;
				case STRING2:
					strout += __dumpString2((String[][]) obj.get(), 0);
					break;
				case STRING3:
					strout += __dumpString3((String[][][]) obj.get(), 0);
					break;
				case DOUBLE1:
				case FLOAT1:
				case INTEGER1:
				case SHORT1:
				case LONG1:
				case NUMBER1:
					strout += __dumpNumber1((Number[]) obj.get(), 0);
					break;
				case DOUBLE2:
				case FLOAT2:
				case INTEGER2:
				case SHORT2:
				case LONG2:
				case NUMBER2:
					strout += __dumpNumber2((Number[][]) obj.get(), 0);
					break;
				case DOUBLE3:
				case FLOAT3:
				case INTEGER3:
				case SHORT3:
				case LONG3:
				case NUMBER3:
					strout += __dumpNumber3((Number[][][]) obj.get(), 0);
					break;
				case BYTE1:
					strout += __dumpByte1((Byte[]) obj.get(), 0);
					break;
				case BYTE2:
					strout += __dumpByte2((Byte[][]) obj.get(), 0);
					break;
				case BYTE3:
					strout += __dumpByte3((Byte[][][]) obj.get(), 0);
					break;
				case CHARACTER1:
					strout += __dumpChar1((Character[]) obj.get(), 0);
					break;
				case CHARACTER2:
					strout += __dumpChar2((Character[][]) obj.get(), 0);
					break;
				case CHARACTER3:
					strout += __dumpChar3((Character[][][]) obj.get(), 0);
					break;
				case ARRAYLIST:
					strout += __dumpArray((ArrayList) obj.get(), 0);
					break;
				case NIL:
					strout += __dumpPrimitive("null", -1, 0);
					break;
			}
		}
		
		__writeout(strout, obj.type);
	}
	
	private static String __dumpString1(String[] items, int level) {
		Integer i;
		String strout = "";
		String tabs = new String(new char[level]).replace("\0", "\t");
		
		strout += "\n" + tabs + "String[] {";
		for (i = 0;i < items.length;i++) {
			strout += __dumpPrimitive(items[i], i, level + 1);
		}
		strout += "\n" + tabs + "}";
		
		return strout;
	}
	
	private static String __dumpString2(String[][] items, int level) {
		Integer i;
		String strout = "";
		String tabs = new String(new char[level]).replace("\0", "\t");
		
		strout += "\n" + tabs + "String[][] {";
		for (i = 0;i < items.length;i++) {
			strout += __dumpString1((String[]) items[i], level + 1);
		}
		strout += "\n" + tabs + "}";
		
		return strout;
	}
	
	private static String __dumpString3(String[][][] items, int level) {
		Integer i;
		String strout = "";
		String tabs = new String(new char[level]).replace("\0", "\t");
		
		strout += "\n" + tabs + "String[][][] {";
		for (i = 0;i < items.length;i++) {
			strout += __dumpString2((String[][]) items[i], level + 1);
		}
		strout += "\n" + tabs + "}";
		
		return strout;
	}
	
	private static String __dumpChar1(Character[] items, int level) {
		Integer i;
		String strout = "";
		String tabs = new String(new char[level]).replace("\0", "\t");
		
		strout += "\n" + tabs + "Character[] {";
		for (i = 0;i < items.length;i++) {
			strout += __dumpPrimitive(items[i].toString(), i, level + 1);
		}
		strout += "\n" + tabs + "}";
		
		return strout;
	}
	
	private static String __dumpChar2(Character[][] items, int level) {
		Integer i;
		String strout = "";
		String tabs = new String(new char[level]).replace("\0", "\t");
		
		strout += "\n" + tabs +"Character[][] {";
		for (i = 0;i < items.length;i++) {
			strout += __dumpChar1((Character[]) items[i], level + 1);
		}
		strout += "\n" + tabs + "}";
		
		return strout;
	}
	
	private static String __dumpChar3(Character[][][] items, int level) {
		Integer i;
		String strout = "";
		String tabs = new String(new char[level]).replace("\0", "\t");
		
		strout += "\n" + tabs +"Character[][][] {";
		for (i = 0;i < items.length;i++) {
			strout += __dumpChar2((Character[][]) items[i], level + 1);
		}
		strout += "\n" + tabs + "}";
		
		return strout;
	}
	
	private static String __dumpNumber1(Number[] items, int level) {
		Integer i;
		String strout = "";
		String tabs = new String(new char[level]).replace("\0", "\t");
		
		strout += "\n" + tabs +"Number[] {";
		for (i = 0;i < items.length;i++) {
			strout += __dumpPrimitive(items[i].toString(), i, level + 1);
		}
		strout += "\n" + tabs + "}";
		
		return strout;
	}
	
	private static String __dumpNumber2(Number[][] items, int level) {
		Integer i;
		String strout = "";
		String tabs = new String(new char[level]).replace("\0", "\t");
		
		strout += "\n" + tabs +"Number[][] {";
		for (i = 0;i < items.length;i++) {
			strout += __dumpNumber1((Number[]) items[i], level + 1);
		}
		strout += "\n" + tabs + "}";
		
		return strout;
	}
	
	private static String __dumpNumber3(Number[][][] items, int level) {
		Integer i;
		String strout = "";
		String tabs = new String(new char[level]).replace("\0", "\t");
		
		strout += "\n" + tabs +"Number[][][] {";
		for (i = 0;i < items.length;i++) {
			strout += __dumpNumber2((Number[][]) items[i], level + 1);
		}
		strout += "\n" + tabs + "}";
		
		return strout;
	}
	
	private static String __dumpByte1(Byte[] items, int level) {
		Integer i;
		String strout = "";
		String tabs = new String(new char[level]).replace("\0", "\t");
		
		strout += "\n" + tabs +"Byte[] {";
		for (i = 0;i < items.length;i++) {
			strout += __dumpPrimitive(items[i].toString(), i, level + 1);
		}
		strout += "\n" + tabs + "}";
		
		return strout;
	}
	
	private static String __dumpByte2(Byte[][] items, int level) {
		Integer i;
		String strout = "";
		String tabs = new String(new char[level]).replace("\0", "\t");
		
		strout += "\n" + tabs +"Byte[][] {";
		for (i = 0;i < items.length;i++) {
			strout += __dumpByte1((Byte[]) items[i], level + 1);
		}
		strout += "\n" + tabs + "}";
		
		return strout;
	}
	
	private static String __dumpByte3(Byte[][][] items, int level) {
		Integer i;
		String strout = "";
		String tabs = new String(new char[level]).replace("\0", "\t");
		
		strout += "\n" + tabs +"Byte[][][] {";
		for (i = 0;i < items.length;i++) {
			strout += __dumpByte2((Byte[][]) items[i], level + 1);
		}
		strout += "\n" + tabs + "}";
		
		return strout;
	}
	
	private static String __dumpArray(ArrayList items, int level) {
		Integer i;
		String strout = "";
		String tabs = new String(new char[level]).replace("\0", "\t");
		
		strout += "\n" + tabs +"Array[] {";
		for (i = 0;i < items.size();i++) {
			classList ctype = classList.get(items.get(i).getClass().toString());
			
			if (ctype == null) {
				try {
					strout += __dumpObject(items.get(i), i, level + 1);
				} catch (Exception ex) {
					strout += __dumpPrimitive(items.get(i).toString(), i, level + 1);
				}
			} else {
				switch (ctype) {
					case DOUBLE:
					case FLOAT:
					case INTEGER:
					case SHORT:
					case LONG:
					case NUMBER:
					case STRING:
					case CHARACTER:
					case BYTE:
					case BOOLEAN:
						strout += __dumpPrimitive(items.get(i).toString(), i, level + 1);
						break;
					case STRING1:
						strout += __dumpString1((String[]) items.get(i), level + 1);
						break;
					case STRING2:
						strout += __dumpString2((String[][]) items.get(i), level + 1);
						break;
					case STRING3:
						strout += __dumpString3((String[][][]) items.get(i), level + 1);
						break;
					case DOUBLE1:
					case FLOAT1:
					case INTEGER1:
					case SHORT1:
					case LONG1:
					case NUMBER1:
						strout += __dumpNumber1((Number[]) items.get(i), level + 1);
						break;
					case DOUBLE2:
					case FLOAT2:
					case INTEGER2:
					case SHORT2:
					case LONG2:
					case NUMBER2:
						strout += __dumpNumber2((Number[][]) items.get(i), level + 1);
						break;
					case DOUBLE3:
					case FLOAT3:
					case INTEGER3:
					case SHORT3:
					case LONG3:
					case NUMBER3:
						strout += __dumpNumber3((Number[][][]) items.get(i), level + 1);
						break;
					case BYTE1:
						strout += __dumpByte1((Byte[]) items.get(i), level + 1);
						break;
					case BYTE2:
						strout += __dumpByte2((Byte[][]) items.get(i), level + 1);
						break;
					case BYTE3:
						strout += __dumpByte3((Byte[][][]) items.get(i), level + 1);
						break;
					case CHARACTER1:
						strout += __dumpChar1((Character[]) items.get(i), level + 1);
						break;
					case CHARACTER2:
						strout += __dumpChar2((Character[][]) items.get(i), level + 1);
						break;
					case CHARACTER3:
						strout += __dumpChar3((Character[][][]) items.get(i), level + 1);
						break;
					case ARRAYLIST:
						strout += __dumpArray((ArrayList) items.get(i), level + 1);
						break;
					case NIL:
						strout += __dumpPrimitive("null", i, level + 1);
						break;
				}
			}
		}
		strout += "\n" + tabs + "}";
		
		return strout;
	}
	
	private static String __dumpObject(Object obj, Integer index, int level) {
		Integer i;
		String strout = "";
		Object value = "";
		String tabs = new String(new char[level]).replace("\0", "\t");
		
		if (index == -1) {
			strout += "\n" + tabs + "Object {";
		} else {
			strout += "\n" + tabs + "[" + index.toString() + "] => Object {";
		}
		
		Class<? extends Object> cls = obj.getClass();
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = cls.getDeclaredFields();
		
		for (i = 0; i < fields.length; i++) {
			String name = fields[i].getName();
			String pub = Modifier.isPublic(fields[i].getModifiers()) ? "[:public]" : "[:private]";
			fields[i].setAccessible(true);
			
			try {
				classList ctype = classList.get(fields[i].get(obj).getClass().toString());
				
				if (ctype == null) {
					try {
						value = __dumpObject(fields[i].get(obj), -1, level + 2);
					} catch (Exception ex) {
						value = __dumpPrimitive(fields[i].get(obj).toString(), -1, level + 2);
					}
				} else {
					switch (ctype) {
						case DOUBLE:
						case FLOAT:
						case INTEGER:
						case SHORT:
						case LONG:
						case NUMBER:
						case STRING:
						case CHARACTER:
						case BYTE:
						case BOOLEAN:
							value = __dumpPrimitive(fields[i].get(obj).toString(), -1, level + 2);
							break;
						case STRING1:
							value = __dumpString1((String[]) fields[i].get(obj), level + 2);
							break;
						case STRING2:
							value = __dumpString2((String[][]) fields[i].get(obj), level + 2);
							break;
						case STRING3:
							value = __dumpString3((String[][][]) fields[i].get(obj), level + 2);
							break;
						case DOUBLE1:
						case FLOAT1:
						case INTEGER1:
						case SHORT1:
						case LONG1:
						case NUMBER1:
							value = __dumpNumber1((Number[]) fields[i].get(obj), level + 2);
							break;
						case DOUBLE2:
						case FLOAT2:
						case INTEGER2:
						case SHORT2:
						case LONG2:
						case NUMBER2:
							value = __dumpNumber2((Number[][]) fields[i].get(obj), level + 2);
							break;
						case DOUBLE3:
						case FLOAT3:
						case INTEGER3:
						case SHORT3:
						case LONG3:
						case NUMBER3:
							value = __dumpNumber3((Number[][][]) fields[i].get(obj), level + 2);
							break;
						case BYTE1:
							value = __dumpByte1((Byte[]) fields[i].get(obj), level + 2);
							break;
						case BYTE2:
							value = __dumpByte2((Byte[][]) fields[i].get(obj), level + 2);
							break;
						case BYTE3:
							value = __dumpByte3((Byte[][][]) fields[i].get(obj), level + 2);
							break;
						case CHARACTER1:
							value = __dumpChar1((Character[]) fields[i].get(obj), level + 2);
							break;
						case CHARACTER2:
							value = __dumpChar2((Character[][]) fields[i].get(obj), level + 2);
							break;
						case CHARACTER3:
							value = __dumpChar3((Character[][][]) fields[i].get(obj), level + 2);
							break;
						case ARRAYLIST:
							value = __dumpArray((ArrayList) fields[i].get(obj), level + 2);
							break;
						case NIL:
							value = __dumpPrimitive("null", -1, level + 2);
							break;
					}
				}
			} catch (Exception ex) {
				value = "\n" + tabs;	
			}

			strout += "\n\t" + tabs + pub + " " + name.toString() + " => " + value.toString().substring(level + 3);
		}
		
		strout += "\n" + tabs + "}";
		
		return strout;
	}
	
	private static String __dumpPrimitive(String val, Integer index, int level) {
		String tabs = new String(new char[level]).replace("\0", "\t");
		
		if (index == -1) {
			return "\n" + tabs + val;
		} else {
			return "\n" + tabs + "[" + index.toString() + "] => " + val;
		}
	}
	
	private static void __writeout(String strout, String itemType) {
        String __DIR__ = System.getProperty("user.dir");
        File __fleLog = new File(__DIR__ + File.separator + __logName);
		strout = "\n\n[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + "] " + itemType + strout;
		
        if (!__fleLog.exists()) {
            try {
                __fleLog.createNewFile();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
		
        try {
            FileOutputStream outStr = new FileOutputStream(__fleLog, true);
            outStr.write(strout.getBytes());
            outStr.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
}