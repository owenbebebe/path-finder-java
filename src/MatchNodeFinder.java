/*
 * File: MatchNodeFinder.java
 * Description: Use two array to find two paths from start and end node to root
 *              match both array to get a path result
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

import java.nio.file.Path;
import java.util.*;

public class MatchNodeFinder
{
    // ********************
    // Properties
    PathNode startNode;
    PathNode endNode;
    ArrayList<Map<PathNode, String>> startNodeList;
    ArrayList<Map<PathNode, String>> endNodeList;

    // ********************
    // Constructor
    public MatchNodeFinder()
    {
        startNode = null;
        endNode = null;
        startNodeList = new ArrayList<>();
        endNodeList = new ArrayList<>();
    }

    // ********************
    // create arraylist paths for start and end nodes
    public ArrayList<String> runMatchNodeFinder(PathNode startNode, PathNode endNode) {

        // store path nodes and path strings from start and end pathNodes to root
        findNodePathToRoot(startNode, null, startNodeList);
        findNodePathToRoot(endNode, null, endNodeList);

        // find matched node from two arrays
        return findMatchNodes();
    }

    // ********************
    // find a path from PathNode back to root (recursively)
    private void findNodePathToRoot(PathNode currentPathNode, PathNode previousPathNode,
                                     ArrayList<Map<PathNode, String>> pathNodeList) {
        // base case
        if (null == currentPathNode) {
            return;
        }

        // add path strings to array
        Map<PathNode, String> tempMap = new HashMap<>();
        tempMap.put(currentPathNode, null);
        if (null != previousPathNode && currentPathNode.getLeft().equals(previousPathNode)) {
            tempMap.put(currentPathNode, GlobalConstants.G_PATH_LEFT);
        }
        if (null != previousPathNode && currentPathNode.getRight().equals(previousPathNode)) {
            tempMap.put(currentPathNode, GlobalConstants.G_PATH_RIGHT);
        }

        // traverse to parent PathNode
        pathNodeList.add(tempMap);
        findNodePathToRoot(currentPathNode.getParent(), currentPathNode, pathNodeList);
    }

    // ********************
    // from tail of both nodePathLists find last mached node
    private ArrayList<String> findMatchNodes() {
        int startTail = startNodeList.size()-1;
        int endTail = endNodeList.size()-1;
        PathNode startNodeKey = null;
        PathNode endNodeKey = null;

        //get tail Node from array list
        Map<PathNode, String> startNodeMap = startNodeList.get(startTail);
        if (startNodeMap != null) {
            startNodeKey = startNodeMap.keySet().iterator().next();
        }
        Map<PathNode, String> endNodeMap = endNodeList.get(endTail);
        if (endNodeMap != null) {
            endNodeKey = endNodeMap.keySet().iterator().next();
        }

        //From tail of both starting and targeting node ArrayLists find last matched node
        while (null != startNodeKey &&
                null != endNodeKey &&
                startNodeKey.equals(endNodeKey)) {

            // both tails move to previous one
            startTail--;
            endTail--;
            startNodeMap = startNodeList.get(startTail);
            endNodeMap = endNodeList.get(endTail);
            startNodeKey = startNodeMap.keySet().iterator().next();
            endNodeKey = endNodeMap.keySet().iterator().next();
        }

        //For every element from head to matched add a “Up” String into our result arrayList
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < startTail+1; i++) {
            result.add(GlobalConstants.G_PATH_UP);
        }

        //For every elements from matched node to head in target ArrayList add each its path operation
        for (int i = 0; i <= endTail+1; i++) {
            Map<PathNode, String> currentMap = endNodeList.get(i);
            for (Map.Entry<PathNode, String> entry : currentMap.entrySet()) {
                String value = entry.getValue();
                if (null != value) {
                    result.add(value);
                }
            }
        }
        return result;
    }
}
