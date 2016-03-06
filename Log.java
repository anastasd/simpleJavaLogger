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
	public static void dump(Var tmp) {
		String strout = "";
		
		switch (tmp.type) {
			case "class java.lang.Double":
			case "class java.lang.Float":
			case "class java.lang.Integer":
			case "class java.lang.Short":
			case "class java.lang.Long":
			case "class java.lang.Number":
			case "class java.lang.String":
			case "class java.lang.Character":
			case "class java.lang.Byte":
			case "class java.lang.Boolean":
				strout += __dumpPrimitive(tmp.get().toString(), -1, 0);
				break;
			case "class [Ljava.lang.String;":
				strout += __dumpString1((String[]) tmp.get(), 0);
				break;
			case "class [[Ljava.lang.String;":
				strout += __dumpString2((String[][]) tmp.get(), 0);
				break;
			case "class [[[Ljava.lang.String;":
				strout += __dumpString3((String[][][]) tmp.get(), 0);
				break;
			case "class [Ljava.lang.Double;":
			case "class [Ljava.lang.Float;":
			case "class [Ljava.lang.Integer;":
			case "class [Ljava.lang.Short;":
			case "class [Ljava.lang.Long;":
			case "class [Ljava.lang.Number;":
				strout += __dumpNumber1((Number[]) tmp.get(), 0);
				break;
			case "class [[Ljava.lang.Double;":
			case "class [[Ljava.lang.Float;":
			case "class [[Ljava.lang.Integer;":
			case "class [[Ljava.lang.Short;":
			case "class [[Ljava.lang.Long;":
			case "class [[Ljava.lang.Number;":
				strout += __dumpNumber2((Number[][]) tmp.get(), 0);
				break;
			case "class [[[Ljava.lang.Double;":
			case "class [[[Ljava.lang.Float;":
			case "class [[[Ljava.lang.Integer;":
			case "class [[[Ljava.lang.Short;":
			case "class [[[Ljava.lang.Long;":
			case "class [[[Ljava.lang.Number;":
				strout += __dumpNumber3((Number[][][]) tmp.get(), 0);
				break;
			case "class [Ljava.lang.Byte;":
				strout += __dumpByte1((Byte[]) tmp.get(), 0);
				break;
			case "class [[Ljava.lang.Byte;":
				strout += __dumpByte2((Byte[][]) tmp.get(), 0);
				break;
			case "class [[[Ljava.lang.Byte;":
				strout += __dumpByte3((Byte[][][]) tmp.get(), 0);
				break;
			case "class [Ljava.lang.Character;":
				strout += __dumpChar1((Character[]) tmp.get(), 0);
				break;
			case "class [[Ljava.lang.Character;":
				strout += __dumpChar2((Character[][]) tmp.get(), 0);
				break;
			case "class [[[Ljava.lang.Character;":
				strout += __dumpChar3((Character[][][]) tmp.get(), 0);
				break;
			case "class java.util.ArrayList":
				strout += __dumpArray((ArrayList) tmp.get(), 0);
				break;
			case "null":
				strout += __dumpPrimitive("null", -1, 0);
				break;
			default:
				try {
					strout += __dumpObject(tmp.get(), -1, 0);
				} catch (Exception ex) {
					strout += __dumpPrimitive(tmp.get().toString(), -1, 0);
				}
				break;
		}

		__writeout(strout, tmp.type);
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
			switch (items.get(i).getClass().toString()) {
				case "class java.lang.Double":
				case "class java.lang.Float":
				case "class java.lang.Integer":
				case "class java.lang.Short":
				case "class java.lang.Long":
				case "class java.lang.Number":
				case "class java.lang.String":
				case "class java.lang.Character":
				case "class java.lang.Byte":
				case "class java.lang.Boolean":
					strout += __dumpPrimitive(items.get(i).toString(), i, level + 1);
					break;
				case "class [Ljava.lang.String;":
					strout += __dumpString1((String[]) items.get(i), level + 1);
					break;
				case "class [[Ljava.lang.String;":
					strout += __dumpString2((String[][]) items.get(i), level + 1);
					break;
				case "class [[[Ljava.lang.String;":
					strout += __dumpString3((String[][][]) items.get(i), level + 1);
					break;
				case "class [Ljava.lang.Double;":
				case "class [Ljava.lang.Float;":
				case "class [Ljava.lang.Integer;":
				case "class [Ljava.lang.Short;":
				case "class [Ljava.lang.Long;":
				case "class [Ljava.lang.Number;":
					strout += __dumpNumber1((Number[]) items.get(i), level + 1);
					break;
				case "class [[Ljava.lang.Double;":
				case "class [[Ljava.lang.Float;":
				case "class [[Ljava.lang.Integer;":
				case "class [[Ljava.lang.Short;":
				case "class [[Ljava.lang.Long;":
				case "class [[Ljava.lang.Number;":
					strout += __dumpNumber2((Number[][]) items.get(i), level + 1);
					break;
				case "class [[[Ljava.lang.Double;":
				case "class [[[Ljava.lang.Float;":
				case "class [[[Ljava.lang.Integer;":
				case "class [[[Ljava.lang.Short;":
				case "class [[[Ljava.lang.Long;":
				case "class [[[Ljava.lang.Number;":
					strout += __dumpNumber3((Number[][][]) items.get(i), level + 1);
					break;
				case "class [Ljava.lang.Byte;":
					strout += __dumpByte1((Byte[]) items.get(i), level + 1);
					break;
				case "class [[Ljava.lang.Byte;":
					strout += __dumpByte2((Byte[][]) items.get(i), level + 1);
					break;
				case "class [[[Ljava.lang.Byte;":
					strout += __dumpByte3((Byte[][][]) items.get(i), level + 1);
					break;
				case "class [Ljava.lang.Character;":
					strout += __dumpChar1((Character[]) items.get(i), level + 1);
					break;
				case "class [[Ljava.lang.Character;":
					strout += __dumpChar2((Character[][]) items.get(i), level + 1);
					break;
				case "class [[[Ljava.lang.Character;":
					strout += __dumpChar3((Character[][][]) items.get(i), level + 1);
					break;
				case "class java.util.ArrayList":
					strout += __dumpArray((ArrayList) items.get(i), level + 1);
					break;
				case "null":
					strout += __dumpPrimitive("null", i, level + 1);
					break;
				default:
					try {
						strout += __dumpObject(items.get(i), i, level + 1);
					} catch (Exception ex) {
						strout += __dumpPrimitive(items.get(i).toString(), i, level + 1);
					}
					break;
			}
		}
		strout += "\n" + tabs + "}";
		
		return strout;
	}
	
	private static String __dumpObject(Object obj, Integer index, int level) {
		Integer i;
		String strout = "";
		Object value;
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
				switch (fields[i].get(obj).getClass().toString()) {
					case "class java.lang.Double":
					case "class java.lang.Float":
					case "class java.lang.Integer":
					case "class java.lang.Short":
					case "class java.lang.Long":
					case "class java.lang.Number":
					case "class java.lang.String":
					case "class java.lang.Character":
					case "class java.lang.Byte":
					case "class java.lang.Boolean":
						value = __dumpPrimitive(fields[i].get(obj).toString(), -1, level + 2);
						break;
					case "class [Ljava.lang.String;":
						value = __dumpString1((String[]) fields[i].get(obj), level + 2);
						break;
					case "class [[Ljava.lang.String;":
						value = __dumpString2((String[][]) fields[i].get(obj), level + 2);
						break;
					case "class [[[Ljava.lang.String;":
						value = __dumpString3((String[][][]) fields[i].get(obj), level + 2);
						break;
					case "class [Ljava.lang.Double;":
					case "class [Ljava.lang.Float;":
					case "class [Ljava.lang.Integer;":
					case "class [Ljava.lang.Short;":
					case "class [Ljava.lang.Long;":
					case "class [Ljava.lang.Number;":
						value = __dumpNumber1((Number[]) fields[i].get(obj), level + 2);
						break;
					case "class [[Ljava.lang.Double;":
					case "class [[Ljava.lang.Float;":
					case "class [[Ljava.lang.Integer;":
					case "class [[Ljava.lang.Short;":
					case "class [[Ljava.lang.Long;":
					case "class [[Ljava.lang.Number;":
						value = __dumpNumber2((Number[][]) fields[i].get(obj), level + 2);
						break;
					case "class [[[Ljava.lang.Double;":
					case "class [[[Ljava.lang.Float;":
					case "class [[[Ljava.lang.Integer;":
					case "class [[[Ljava.lang.Short;":
					case "class [[[Ljava.lang.Long;":
					case "class [[[Ljava.lang.Number;":
						value = __dumpNumber3((Number[][][]) fields[i].get(obj), level + 2);
						break;
					case "class [Ljava.lang.Byte;":
						value = __dumpByte1((Byte[]) fields[i].get(obj), level + 2);
						break;
					case "class [[Ljava.lang.Byte;":
						value = __dumpByte2((Byte[][]) fields[i].get(obj), level + 2);
						break;
					case "class [[[Ljava.lang.Byte;":
						value = __dumpByte3((Byte[][][]) fields[i].get(obj), level + 2);
						break;
					case "class [Ljava.lang.Character;":
						value = __dumpChar1((Character[]) fields[i].get(obj), level + 2);
						break;
					case "class [[Ljava.lang.Character;":
						value = __dumpChar2((Character[][]) fields[i].get(obj), level + 2);
						break;
					case "class [[[Ljava.lang.Character;":
						value = __dumpChar3((Character[][][]) fields[i].get(obj), level + 2);
						break;
					case "class java.util.ArrayList":
						value = __dumpArray((ArrayList) fields[i].get(obj), level + 2);
						break;
					case "null":
						value = __dumpPrimitive("null", -1, level + 2);
						break;
					default:
						try {
							value = __dumpObject(fields[i].get(obj), -1, level + 2);
						} catch (Exception ex) {
							value = __dumpPrimitive(fields[i].get(obj).toString(), -1, level + 2);
						}
						break;
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