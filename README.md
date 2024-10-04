<!--StartFragment-->

**Class: FinderFactory**

Fix Date: 2024/04/23 change Class name from PathFiner to FinderFactory

Fix Date: 2024/04/23 redefine binary tree format 

Fix Date: 2024/04/23 add color green for inline comment

Fix Date: 2024/04/23 change PathNode class property from character to String 

Fix Date: 2024/04/23 change method MatchNodeFinder to PathsComparisonFinder

Author: Owen Li

**Catalog:**

- **Problem** 

- **Input and Data Structure**  

- **Usage**

- **Four FinderFactory Algorithms**

**Problem:**

**Given a binary tree, find a path from any of the starting nodes to ending nodes.**

**Figure 1.**

![](https://lh7-rt.googleusercontent.com/docsz/AD_4nXcQQozJF3kXa_lxpPNbJZCtwuO9bzdoedRvGi_7cb9rn05aEo_vnxnUgFSGNY0ntVBbQEtOrDTJZ9hnkqOwtyOhomBdJLasNBPwXfR5dohWZWA9XxEsYzX2N7E3_SphuLHMmdHCH1z3Poo0AxXtkFMH4GyP?key=R9jxwiICbeMSIxUm7sWvZQ)

For example: Figure 1. Node D as starting node and Node M as ending node

Output : Up(D->B),Right (B-> E),Right (E->M)

**Input and Data Structure:**

********

**Define Binary Tree Format:**

FinderFactory constructs a binary tree using a String. Each node is represented by a String, separated by a comma. If node String == “null”, FinderFactory tree constructor would only put a null to its location, and a parent node can not be null. The number of nodes inside this binary tree must be equal to a complete binary tree, otherwise FinderFactory would not run.

Figure 1 input String would be the following: 

“A,B,C,D,E,F,G,null,null,H,M,null,null,J,K”. 

**function:** getTree()

**purpose:** get a String from the user keyboard. 

private void FinderFactory.getTree() 

Importance: ****only reads a max memory of 1MB String, only takes the first character of any given node string as.



**Construct a binary tree**

To be able to find a path from Node D to Node B see Figure 1, each node must have a parent node to perform the “Up” path operation. Each node is a class called PathNode that saves both left and right children with an extra parent node (see Figure 2.).

**Figure 2 .**

\- ex. node with character 'K' has left, right, and parent nodes

A node is represented in java class like below

A binary tree only has two children nodes (left and right). FinderFactory assigns each node with an index and saves it into an ArrayList starting from root = 1.


**Figure 3.**

****

****![](https://lh7-rt.googleusercontent.com/docsz/AD_4nXeNmgvYwIRSKWWfTc7wwmXO5H51pwhlo2ma_FuqxhbTdUALvBmYjIqylAuplrj6oGWnEyNIrcVHnNCoY_1UEaVL43qk34SKiUw5Hxiru_mdCIQMmPkKouRn10zrERAsI1WpvYUuz8fV78_y_zDpHqtki8iL?key=R9jxwiICbeMSIxUm7sWvZQ)****

**function:** buildTree(String inputString)

**purpose:** takes a string and construct a PathNode Tree and put each PathNode into its corresponding index in FinderFactory.treeArrayList

private void FinderFactory.buildTree(String inputString)

Importance: ****only takes a complete tree.


**Get startPathNode and endPathNode**

By providing indexes in FinderFactory.treeArrayList we can get its corresponding PathNode or NULL.

**function:** ArrayList\<PathNode> getStartAndEndNode()

**purpose:** Gets int startNodeIndex and int endNodeIndex for user and return an //ArrayList with its corresponded PathNodes.

private ArrayList\<PathNode> FinderFactory.getStartAndEndNode() 

Importance: does not take indexes not in tree index range or index correspond is null

 

**Usage:**

**Initialize FinderFactory:** 

FinderFactory finderFactory = new FinderFactory(); 

// when calling below four algorithms functions, it would prompt from the console input tree, according to the format from above, startPathNode index, and an endPathNode index. 

**To run DFS methods call:**

//default always calls getTree() -> getStartAndEndNode();

FinderFactory.runDFSFinder();

//This returns an ArrayList\<String> with paths String in it.




**To run BFS method call:**

//default always calls getTree() -> getStartAndEndNode();

FinderFactory.runBFSFinder(); 

//This returns an ArrayList\<String> with paths String in it.

**To run Paths Comparison method call:**

//default always calls getTree() -> getStartAndEndNode();

FinderFactory.runPathsComparisonFinder();

//This returns an ArrayList\<String> with paths String in it.

**To run OneBinaryTree method call:**

//default always calls getTree() -> getStartAndEndNode();

FinderFactory.runOneBinaryTreeFinder();

//This returns an ArrayList\<String> with paths String in it.

********

********

**Method 1: DFS traversal** 

Assumption input: Given a start PathNode and end PathNode

Assumption output: Output a path from start PathNode to end PathNode. 

<!--StartFragment-->

![](https://lh7-rt.googleusercontent.com/docsz/AD_4nXd2F1xZog-Bb68afZE5cVF82IxtfiNhr5EeXsYylTylrEClxUdfATJEFCJwdyJZLn_s9yWCerq0fIpKdIs4DdvLxXdaqzBm3-T2UnU3T1SfhJOhwRWMAH0pH0mFEiMM4UmQEQZqtTg-5TVMe8Nxd9qWp33E?key=R9jxwiICbeMSIxUm7sWvZQ)


**Method 2: BFS Traversal**

Assumption input: Given a start PathNode and end PathNode

Assumption output: Output a path from start PathNode to end PathNode. 

![](https://lh7-rt.googleusercontent.com/docsz/AD_4nXdBqqyUyEhnG9BSG0TrgJz0-XpiJP4Uof2SDeil5ohJSfFSYt5VlP74zxEXKlJhqO3C0x7UzP6YfShksGgP_9-BvtwZVrwda0lL4PIo4UivDFc26MlmAfa4wvHwO442tMry8_MzwPXUglBkT72KlwbkjCQ?key=R9jxwiICbeMSIxUm7sWvZQ)


**Method 3: Match Node to Root Path** 

Assumption input: Given a start PathNode and end PathNode

Assumption output: Output path from starting PathNode to ending PathNode

![](https://lh7-rt.googleusercontent.com/docsz/AD_4nXcr5YpzmOp7c3iLFhsMOvYElnHyDP0ho67OKBuTtT2aIQ-5RV7_zpHv5b-q1Cvl-aHY4SV9qs__heOFfNeu-MvWyzfetqpf5oYu-aEGVBom9i3ui9-5EQpz_wqrop7Wdlo2hqutQzvBJmBoGHfvzdD4757o?key=R9jxwiICbeMSIxUm7sWvZQ)


**Method 4: One Binary Tree**

Assumption input: Given a starting index and ending index

Assumption output: Output a path operator from starting PathNode to ending PathNode. 

ex. Up, Left, Left

![](https://lh7-rt.googleusercontent.com/docsz/AD_4nXcGwmpkJGxKBLYoNl7_qhL8xldA2I3Gy1CJGi6kgo-GWUSk7Iw1SdIKRsKaW_KmUXphP_p6YEGXKeoLnn_cP2EDwS77IpGZggujP9iIZ08QMDinpS_3AMFXrvEtxkaa2jE8Hgbss--JsykigS3P1KV9aUw?key=R9jxwiICbeMSIxUm7sWvZQ)



<!--EndFragment-->



<!--EndFragment-->
