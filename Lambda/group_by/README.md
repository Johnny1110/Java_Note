# Lambda with Group By

<br>

---

<br>

## Case:

there's List data:

```
parentID, id, name
0, 1, parent_1
0, 2, parent_3
0, 3, parent_3
1, 4, son_1
1, 5, son_2
1, 6, son_3
2, 7, son_4
2, 8, son_5
2, 9, son_6
3, 10, son_7
3, 11, son_8
3, 12, son_9 
```

<br>

I want to collect em to a `Map<parentID, List<Data>>` collection, like:

<br>

```
parentID: 1
    sonID: 4, 5, 6 

parentID: 2
    sonID: 7, 8, 9 

parentID: 3
    sonID: 10, 11, 12 
```

<br>

solution:

```java
// collect sons
Map<Integer, List<Data>> grouped = list.stream()
    .filter(x -> x.getParentID() != 0)
    .collect(Collectors.groupingBy(Data::getParentID)); // # groupingBy()

// collect parents
List<Data> parents = list.stream()
    .filter(x -> x.getParentID() == 0)
    .collect(Collectors.toList());
```