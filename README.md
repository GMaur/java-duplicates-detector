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

### Known limitations

This class only works in ``static`` classes, otherwise you get an error (below). To solve it, make the class temporarily static. Stack:

```
java.lang.IllegalArgumentException: Can not set final com.gmaur.tool.java.duplicatedetector.RepeatedFinderShould field com.gmaur.tool.java.duplicatedetector.RepeatedFinderShould$RepeatedConstants.this$0 to java.lang.Object
	at sun.reflect.UnsafeFieldAccessorImpl.throwSetIllegalArgumentException(UnsafeFieldAccessorImpl.java:167)
	at sun.reflect.UnsafeFieldAccessorImpl.throwSetIllegalArgumentException(UnsafeFieldAccessorImpl.java:171)
	at sun.reflect.UnsafeFieldAccessorImpl.ensureObj(UnsafeFieldAccessorImpl.java:58)
	at sun.reflect.UnsafeQualifiedObjectFieldAccessorImpl.get(UnsafeQualifiedObjectFieldAccessorImpl.java:38)
```
