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

**Method 1: DFS traversal** 

Assumption input: Given a start PathNode and end PathNode

Assumption output: Output a path from start PathNode to end PathNode. 

1\. From start PathNode run DFS recursively to find M

\- create a visited set to store nodes

\- create a path arraylist 

func DFS(current Node)

Base case

**a.** current PathNode == null

return false

**b.** current PathNode is in visited set

return false

**c.** current PathNode == end PathNode 

Return true

**Else**

********add current PathNode to visited set

add current PathNode to path

********traverse order: 

// explore left child

If DFS(left node, push left node to Stack) == true: return true

// explore right child 

if DFS(right PathNode, push left PathNode  to Stack) == true; return true

// explore parent node

If DFS(parent PathNode, push parent PathNode to Stack) == true

return true

// if we found every possible current paths

pop last element from stack

return false

**Method 2: BFS Traversal**

Assumption input: Given a start PathNode and end PathNode

Assumption output: Output a path from start PathNode to end PathNode. 

1. Create a BFSPathNode that has extra path properties to store “Up”, “Right”, and “Left”.

\
\


2.  From starting BFSPathNode Run BFS to target Node with a queue

\- create a visited set to store node

every current BFSPathNode we pop from queue

If (current BFSPathNode == target node) 

we find a match

else

add current BFSPathNode to visited set

check current BFSPathNode neighboring nodes

if neighbor BFSPathNode is not in visited set

Create a new BFSPathNode and neighbor PathNode Properties 

add neighbor BFSPathNodes to queue with a path operation 

(ex. current BFSPathNode add its left node to queue

       : left BFSPathNode.path add 'Left')

3\. Return found BFSPathNode.path property

**Method 3: Match Node to Root Path** 

Assumption input: Given a start PathNode and end PathNode

Assumption output: Output path from starting PathNode to ending PathNode

1\. Save a traversing path in an ArrayList from starting PathNode to root PathNode. 

2\. Save a traversing path in an ArrayList from ending PathNode to root PathNode. 

3\. From tail of both starting and targeting node ArrayLists find last matched node

while (PathNode in tail pointer in ending PathNode ArrayList == 

          PathNode in tail pointer in starting PathNode ArrayList )

both tail pointer move to previous one

4\. Get path from two arrayLists

For every elements from head to matched not in starting ArrayList from add a “Up” String into our result arrayList

For every elements from matched node to head in target ArrayList add each its path operation into our result list

5\. Return a result arraylist

**Method 4: One Binary Tree**

Assumption input: Given a starting index and ending index

Assumption output: Output a path operator from starting PathNode to ending PathNode. 

ex. Up, Left, Left

1.  Find common parent of both nodes

Start with an starting path arrayList and ending path arrayList

while (starting index != ending index)

if (starting index > ending index)

Starting index = starting index / 2

Add an ‘UP’ string to starting path arrayList

else (ending index > starting index)

Check if ending index is even number

Add an ‘Left’ string to ending path arrayList

else  

 Add an ‘Right’ string to ending path arrayList

target index = target index / 2 

2\. The elements from starting path arrayList + The elements from ending path arrayList from back to end, is output path result.

\


<!--EndFragment-->
