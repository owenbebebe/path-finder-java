/*
 * File: BFSFinder.java
 * Description: Use BFS traversal to find a path from start to end node
 *              Output an array list path with paths string inside
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

public class BFSFinder {
    // ********************
    // Inner class
    static class BFSPathNode {
        PathNode pathNode;
        ArrayList<String> pathList;

        public BFSPathNode (PathNode pathNode) {
            this.pathNode = pathNode;
            this.pathList = new ArrayList<>();
        }
    }

    // ********************
    // Constructor
    public BFSFinder() {
    }

    // ********************
    // given a start and end node run BFS approach to find a path from startNode to endNode
    // return an arraylist of path result
    public ArrayList<String> runBFSFinder(PathNode startNode, PathNode endNode) {

        // input check
        if (null == startNode) {
            System.err.println(ErrorCode.ERR_STARTNODE_NULL);
            return null;
        }
        if (null == endNode) {
            System.err.println(ErrorCode.ERR_ENDNODE_NULL);
            return null;
        }

        // From starting Path Node Run BFS to target Node with a queue
        Set<PathNode> visitedNodeSet = new HashSet<>();;
        Queue<BFSPathNode> queue = new LinkedList<>();
        BFSPathNode startBFSNode = new BFSPathNode(startNode);
        queue.add(startBFSNode);
        while (!queue.isEmpty()) {
            BFSPathNode currentNode = queue.poll();

            // found a path
            if (currentNode.pathNode == endNode) {
                return currentNode.pathList;
            }

            visitedNodeSet.add(currentNode.pathNode);

            // Explore left neighbor and add its path string
            if (null != currentNode &&
                    null != currentNode.pathNode.getLeft() &&
                    !visitedNodeSet.contains(currentNode.pathNode.getLeft())) {
                BFSPathNode newNode = new BFSPathNode(currentNode.pathNode.getLeft());
                newNode.pathList.addAll(currentNode.pathList);
                newNode.pathList.add(GlobalConstants.G_PATH_LEFT);
                queue.add(newNode);
            }

            // Explore right neighbor add its path string
            if (null != currentNode &&
                    null != currentNode.pathNode.getRight() &&
                    !visitedNodeSet.contains(currentNode.pathNode.getRight())) {
                BFSPathNode newNode = new BFSPathNode(currentNode.pathNode.getRight());
                newNode.pathList.addAll(currentNode.pathList);
                newNode.pathList.add(GlobalConstants.G_PATH_RIGHT);
                queue.add(newNode);
            }

            // Explore parent neighbor add its path string
            if (null != currentNode && !visitedNodeSet.contains(currentNode.pathNode.getParent())) {
                BFSPathNode newNode = new BFSPathNode(currentNode.pathNode.getParent());
                newNode.pathList.addAll(currentNode.pathList);
                newNode.pathList.add(GlobalConstants.G_PATH_UP);
                queue.add(newNode);
            }
        }
        // path not found
        System.err.println("Search Failed");
        return null;
    }
}
