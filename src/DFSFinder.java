/*
 * File: DFSFinder.java
 * Description: Use DFS traversal to find a path from start to end node
 *              Traversal order currrent -> left -> right -> parent
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

public class DFSFinder
{
    // ********************
    // Properties
    private Set<PathNode> visitedNodeSet;
    private PathNode startNode;
    private PathNode endNode;
    private ArrayList<String> result;

    // ********************
    // Constructor
    public DFSFinder() {
        this.startNode = null;
        this.endNode = null;
        this.visitedNodeSet = new HashSet<>();
        this.result = new ArrayList<>();
    }

    // ********************
    // given a start and end node run the entire path find machine
    public ArrayList<String> runDFSFinder (PathNode startNode, PathNode endNode)
            throws IllegalArgumentException {

        // input check
        if (null == startNode) {
            throw new IllegalArgumentException(ErrorCode.ERR_STARTNODE_NULL.getDescription());
        }
        if (null == endNode) {
            System.out.println(ErrorCode.ERR_ENDNODE_NULL);
            return null;
        }
        this.startNode = startNode;
        this.endNode = endNode;
        if (!searchDFSFinder(startNode)) {
            System.out.println("Search Failed");
            return null;
        };
        return result;
    }

    // ********************
    // From starting Node run DFS recursively to find M
    private boolean searchDFSFinder(PathNode currentNode) {
        // base cases
        if (null == currentNode) {
            return false;
        }
        if (visitedNodeSet.contains(currentNode)) {
            return false;
        }
        // found path !
        if (endNode == currentNode) {
            return true;
        }
        else {
            // add current node to visited set
            visitedNodeSet.add(currentNode);
            // add current node to path
            // traversing order left -> right -> parent
            // explore left
            result.add(GlobalConstants.G_PATH_LEFT);
            if (searchDFSFinder(currentNode.getLeft())) {
                return true;
            }
            else {
                result.removeLast();
            }
            // explore right
            result.add(GlobalConstants.G_PATH_RIGHT);
            if (searchDFSFinder(currentNode.getRight())) {
                return true;
            }
            else {
                result.removeLast();
            }
            result.add(GlobalConstants.G_PATH_UP);
            // explore parent
            if (searchDFSFinder(currentNode.getParent())) {
                return true;
            }
            else {
                result.removeLast();
            }
            // found every possible current Node paths
            return false;
        }
    }
}