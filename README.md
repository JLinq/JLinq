# JLinq [![Build Status](https://travis-ci.org/JLinq/JLinq.svg?branch=master)](https://travis-ci.org/JLinq/JLinq)
A basic LINQ-like toolset for java 

## Getting started
### Dependency
**Include as maven dependency**
```
<dependency>
    <groupId>com.github.jlinq</groupId>
    <artifactId>jlinq</artifactId>
    <version>0.0.3a</version>
</dependency>
```
**Include as gradle dependency**
```
compile 'com.github.jlinq:jlinq:0.0.3a'
```
### Use queries on collection-classes
1. Create a ``Queryable<...>`` from a plain old java iterable:
```
Queryable<String> q1 = JLinq.create(new String[]{"Hello", "World", "Foo", "Bar", "42"});
Queryable<Integer> q2 = JLinq.create(someListOfInteger);
```
**Note** that changes on the underlying ``Iterable`` will be applied to the generated ``Queryable<...>``.
