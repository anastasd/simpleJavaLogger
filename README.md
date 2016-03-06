# simpleJavaLogger
A very simple class to dump java variables

Prettyprints types in a file together with timestamps:
- Double
- Float
- Short
- Integer
- Long
- Number
- Number and Number array nested up to three levels
- String and String array nested up to three levels
- Character and Character array nested up to three levels
- Byte and Byte array nested up to three levels
- Boolean
- null
- ArrayList - nested up to any levels
- custom Object

Example usage:

```Java
// Double
Var<Number> tdb = new Var<Number>(123.456d);
Log.dump(tdb);

// String[][]
Var<String[][]> tsa2 = new Var<String[][]>(new String[][] {{"a1", "b1"}, {"c2", "d2"}});
Log.dump(tsa2);

// List<String>
List<String> list1 = new ArrayList<String>();
list1.add("string 1");
list1.add("string 2");
Var<List<String>> tas1 = new Var<List<String>>(list1);
Log.dump(tas1);
		
// Object Array
ArrayList<Tmp> objlist = new ArrayList<Tmp>();
Tmp y = new Tmp("hello", "world");
Tmp z = new Tmp("this is my", "first java class");
objlist.add(y);
objlist.add(z);
Var<List<Tmp>> toa1 = new Var<List<Tmp>>(objlist);
Log.dump(toa1);
```

See more in "src/Test.java"