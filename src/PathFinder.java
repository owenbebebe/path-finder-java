/*
 * File: PathFinder.java
 * Description: An entrance portal that get its input and call four algorithms to find a
 *              path between two nodes
 * BUGS:
 * NOTE:
 * First Created on Date: 2024/04/19 by Author: Owen Li
 * Last Modified on Date: 2024/04/19 by Author: Owen Li
 * Fix on Date:
 * Code Review Record on Date:
 * Copy right (c) DMS, Delta Electronics, INC.
 * All rights reserved
 * */

import java.util.*;

public class PathFinder {

    // ********************
    // constants
    private final String SYM_SEPERATOR = ",";
    private final String SYM_BLANK = "null";
    private final String INPUT_MESSAGE_TREE = "Please enter a BFS tree: ";
    private final String INPUT_MESSAGE_START = "Enter the start PathNode integer index: ";
    private final String INPUT_MESSAGE_END = "Enter the end PathNode integer index: ";
    // 1 MB
    private final int MAX_STRING_LEN = 1024*1024;

    // ********************
    // properties
    PathNode root;
    ArrayList<PathNode> treeArrayList;
    int startNodeIndex;
    int endNodeIndex;

    // ********************
    // Constructor
    public PathFinder() {
        root = new PathNode();
        treeArrayList = new ArrayList<>();
        startNodeIndex = 0;
        endNodeIndex = 0;
    }

    // ********************
    // run PathFinder class to get input tree with four algorithms
    // to find path from start node to end node
    public void runPathFinder() {

        // build tree
        getTree();

        try {

            // read starting and ending nodes index PathNodes
            ArrayList<PathNode> startAndEndNodeArr = getStartAndEndNode();
            DFSFinder dfsFinder = new DFSFinder();

            // ask about this
            // run DFS algorithm and print its result
            ArrayList<String> result;
            result = dfsFinder.runDFSFinder(startAndEndNodeArr.get(0), startAndEndNodeArr.get(1));
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i));
            }

            // run BFS algorithm and print its result
            BFSFinder bfsFinder= new BFSFinder();
            result = bfsFinder.runBFSFinder(startAndEndNodeArr.get(0), startAndEndNodeArr.get(1));
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i));
            }

            // run Match Node and print
            MatchNodeFinder matchNodeFinder = new MatchNodeFinder();
            result = matchNodeFinder.runMatchNodeFinder(startAndEndNodeArr.get(0), startAndEndNodeArr.get(1));
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i));
            }

            // run One Binary Tree
            OneBinaryTreeFinder oneBinaryTreeFinder = new OneBinaryTreeFinder();
            result = oneBinaryTreeFinder.runOneBinaryTree(startNodeIndex, endNodeIndex);
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i));
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // ********************
    // ask for input string to build a PathNode Tree
    // TEST CASE: A,B,C,D,E,F,G,null,null,H,M,null,null,J,K
    private void getTree() {
        // ask user for an input string
        Scanner scanner = new Scanner(System.in);
        System.out.println(INPUT_MESSAGE_TREE);
        String userInput = scanner.nextLine();
        try {
            // passing user input to build a PathNode Tree
            buildTree(userInput);
        } catch (IllegalArgumentException e) {
            scanner.close();
            System.out.println(e.getMessage());
        }
    }

    // ********************
    // read a string in BFS traversal and build a PathNode tree
    private void buildTree(String inputString) throws IllegalArgumentException {

        // input check
        if (null == inputString) {
            throw new IllegalArgumentException(ErrorCode.ERR_STRING_NULL.getDescription());
        }
        if (inputString.isEmpty()) {
            throw new IllegalArgumentException(ErrorCode.ERR_STRING_EMPTY.getDescription());
        }
        if (MAX_STRING_LEN < inputString.length()) {
            throw new IllegalArgumentException(ErrorCode.ERR_STRING_TOOLARGE.getDescription());
        }

        // split string using a comma and save each split into a string array
        String[] inputStringArr = inputString.split(SYM_SEPERATOR);

        // check if root node is null
        if (inputStringArr[0].equals(SYM_BLANK)) {
            throw new IllegalArgumentException(ErrorCode.ERR_ROOT_NULL.getDescription());
        }

        //check if number of nodes is a complete tree
        int numberOfDepthNodes = 1;
        int numberOfNodes = inputStringArr.length;
        while (numberOfNodes > 0) {
            numberOfNodes = numberOfNodes-numberOfDepthNodes;
            numberOfDepthNodes = numberOfDepthNodes*2;
        }
        if (0 != numberOfNodes) {
            throw new IllegalArgumentException(ErrorCode.ERR_TREE_INCOMPLETE.getDescription());
        }

        // construct each node using queue
        root.setCharacter(inputStringArr[0].charAt(0));
        Queue<PathNode> queue = new LinkedList<>();
        queue.add(root);

        // let root node to start at index 1
        treeArrayList.add(null);
        treeArrayList.add(root);

        for (int i = 1; i < inputStringArr.length; i += 2) {

            // check if parent node exist
            PathNode parentPathNode = queue.poll();
            if (null == parentPathNode) {
                throw new IllegalArgumentException(ErrorCode.ERR_TREE_INCOMPLETE.getDescription());
            }

            // create new nodes for left child if not null
            if (!inputStringArr[i].equals(SYM_BLANK)) {
                parentPathNode.setLeft(new PathNode());
                parentPathNode.getLeft().setCharacter(inputStringArr[i].charAt(0));

                // link left child to parent
                parentPathNode.getLeft().setParent(parentPathNode);
                queue.add(parentPathNode.getLeft());
                treeArrayList.add(parentPathNode.getLeft());
            }
            else {
                treeArrayList.add(null);
            }

            // create new nodes for right child if not null
            if (i + 1 < inputStringArr.length
                    && !inputStringArr[i+1].equals(SYM_BLANK)) {
                parentPathNode.setRight(new PathNode());
                parentPathNode.getRight().setCharacter(inputStringArr[i+1].charAt(0));

                // link right child to parent
                parentPathNode.getRight().setParent(parentPathNode);
                queue.add(parentPathNode.getRight());
                treeArrayList.add(parentPathNode.getRight());
            }
            else {
                treeArrayList.add(null);
            }
        }
    }

    public ArrayList<PathNode> getStartAndEndNode() throws IllegalArgumentException {

        // get two integers from users
        Scanner scanner = new Scanner(System.in);
        System.out.println(INPUT_MESSAGE_START);
        int startInput = scanner.nextInt();
        System.out.println(INPUT_MESSAGE_END);
        int endInput = scanner.nextInt();

        // check if two indexes contains PathNode
        if (0 > startInput
                || treeArrayList.size() <= startInput
                || null == treeArrayList.get(startInput)) {
            scanner.close();
            throw new IllegalArgumentException(ErrorCode.ERR_STARTNODE_INDEX_NOT_FOUND.getDescription());
        }
        if (0 > endInput
                || treeArrayList.size() <= endInput
                || null == treeArrayList.get(endInput)) {
            scanner.close();
            throw new IllegalArgumentException(ErrorCode.ERR_ENDNODE_INDEX_NOT_FOUND.getDescription());
        }

        // get start and end nodes
        scanner.close();
        ArrayList<PathNode> startAndEndNodesArray = new ArrayList<>();
        startNodeIndex = startInput;
        endNodeIndex = endInput;
        startAndEndNodesArray.add(treeArrayList.get(startInput));
        startAndEndNodesArray.add(treeArrayList.get(endInput));
        return startAndEndNodesArray;
    }
}
