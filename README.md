# java-duplicates-detector

This is a tool to help you find duplicates in constants in java files.

### Usage

  1. Download the library
  1. Copy-paste this snippet:
  
```java
RepeatedFinder sut = new RepeatedFinder(YourClass.class);
MultiMap<Object, String> duplicates = sut.findDuplicates();

for(Object current : duplicates.all()){
	System.out.println("found value = '" + current+"', with variables = " + duplicates.get(current));
}
```

### Tests

See [RepeatedFinderShould](https://github.com/GMaur/java-duplicates-detector/blob/master/src/test/java/com/gmaur/tool/java/duplicatedetector/RepeatedFinderShould.java) for some tests

### Behavior

This class only finds repeated values in ``static`` variables.

Example:

```java
public static Integer _3 = 3;
public static Integer THREE = 3; // repeated with _3

public Integer _2 = 2;
public Integer TWO = 2; // no repetition
```
