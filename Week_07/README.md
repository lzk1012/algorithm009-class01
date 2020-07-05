# Week07作业

## 1. 分析《单词搜索 2 》用 Tire 树方式实现的时间复杂度
前缀树的插入、查询时间复杂度为O(n)，n代表字符串长度。而本题使用前缀树实现的时间复杂度应该是:
> O(M * N * 4^L)：M、N代表二维网格宽高，L代表单词的最大长度

## 2. 双向BFS代码模板

```
// 1.使用两个集合作为扩散元素的容器，因为要快速判断集合中是否有某一元素，所以使用Set
Set<String> beginSet = new HashSet<String>(){{
    // 添加初始值
}};
Set<String> endSet = new HashSet<String>(){{
    // 添加初始值
}};
// 2.标准写法，使用一个新的Set表示存储遍历的元素（不标准的写法就是对原始字典Set进行remove）
Set<String> visited = new HashSet<String>(){{
    // 添加beginSet和endSet的初始值
}};
int step = 1;
// 3.二者为空，表示下一层次没有可遍历的元素，退出while，返回0
while (!beginSet.isEmpty() && !endSet.isEmpty()) {
    // 选取一个小的集合开始本次遍历扩散
    if(endSet.size() < beginSet.size()){
        swap(endSet,beginSet);
    }
    // 上面已经保证beginSet较小，依次遍历。将扩散的元素添加至nextLevelSet，此Set用于下次遍历
    Set<String> nextLevelSet = new HashSet<>();
    for(String str:beginSet){
        char[] charArray = str.toCharArray();
        for(int i = 0;i<str.length();i++){
            char originChar = charArray[i];
            for(char replaceCh = 'a';replaceCh <= 'z';replaceCh++){
                if(originChar == replaceCh){
                    continue;
                }
                charArray[i] = replaceCh;
                // 取出str中的每一个字符并替换成新的字符，以此构建新字符串
                String newStr = String.valueOf(charArray);
                // 如果原始字典集合wordSet中有这个新字符串，代表此元素可以扩散
                if(wordSet.contains(newStr)){
                    // endSet中有相同元素，代表下一次扩散就可以完成目标
                    if(endSet.contains(newStr)){
                        return step + 1;
                    }
                    // 或者添加至visited集合中，防止重复判断
                    if(!visited.contains(newStr)){
                        visited.add(newStr);
                        nextLevelSet.add(newStr);
                    }
                }
            }
            // 还原字符串，下一个字符的扩散要使用原字符串
            charArray[i] = originChar;
        }
    }
    // 每次while循环代表一次扩散
    step++;
    beginSet = nextLevelSet;
}
return 0;
```
