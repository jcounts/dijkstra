/*
 * (WIP - Needs further debugging, currently does not update after initialization and first iteration of Loop section)
 * This program creates a network of routers and then run's Dijkstra's Algorithm to determine the least-cost path from a source node.
 */

import java.util.*;

public class LinkState{
  
	public static Random randGen = new Random();
	public static int numOfRouters = 6;
	public static node[] network = new node[numOfRouters];
	
	//Set up the network environment in which the algorithm will run.
	public static void networkSetup(){
		
		int[] routers = new int[numOfRouters];
		//assign random router numbers (Right now just using random number, may be nice to have it be a properly formated MAC address.)
		for(int i=0;i<numOfRouters;i++)
			routers[i] = randGen.nextInt();
		
		network[0] = new node(routers[0],new int[]{routers[1],routers[2],routers[3]},new int[]{2,5,1});
		network[1] = new node(routers[1],new int[]{routers[0],routers[2],routers[3]},new int[]{2,3,2});
		network[2] = new node(routers[2],new int[]{routers[0],routers[1],routers[3],routers[4],routers[5]},new int[]{5,3,3,1,5});
		network[3] = new node(routers[3],new int[]{routers[0],routers[1],routers[2],routers[4]},new int[]{1,2,3,1});
		network[4] = new node(routers[4],new int[]{routers[2],routers[3],routers[5]},new int[]{1,1,2});
		network[5] = new node(routers[5],new int[]{routers[2],routers[4]},new int[]{5,2});
		
	}//end networkSetup
	
	//Check whether or not a particular node has been added
	public static boolean isInN_prime(Vector<node> theNode,int i){
		
		ListIterator<node> it = theNode.listIterator();
		while(it.hasNext()){
			if(network[i].isEqual(it.next()))
				return true;
		}

		return false;
	}
	
	public static void main(String argv[]){
		
		networkSetup();
		
		int[] D = new int[numOfRouters];	//cost of the least-cost path from the source node to destination
		node[] p = new node[numOfRouters];	//previous node along the current least-cost path (may not use)
		Vector<node> N_prime = new Vector<node>();	//subset of nodes
		
		//Initialization
		N_prime.add(new node(network[0]));
		for(int i=1;i<numOfRouters;i++){
			if(N_prime.get(0).isNeighbor(network[i]))
				D[i] = N_prime.get(0).distance[N_prime.get(0).findDistance(network[i])];	//distance of source node to a neighbor
			else
				D[i] = 999999999;	//infinity
		}
		//Loop
		int least_cost = D[1];	//start with first value
		while(N_prime.size()<numOfRouters){	//loop until N_prime contains all nodes from network
			//find node with least-cost
			int positionWithMinPath = 0;
			
			for(int i=1;i<numOfRouters;i++){
				if(D[i]<least_cost){	//is current D smaller than the last?
					least_cost = D[i];
					positionWithMinPath = i;
				}
			}
//			System.out.println(positionWithMinPath);
			N_prime.add(network[positionWithMinPath]);
			for(int i=1;i<numOfRouters;i++){
				if(N_prime.lastElement().isNeighbor(network[i]) && !isInN_prime(N_prime,i)) //if it is a neighbor and not already in N_prime
					//min(current D, least_cost + distance from this path)
					D[i] = Math.min(D[i], least_cost + (N_prime.lastElement().distance[N_prime.lastElement().findDistance(network[i])]));
			}
		}
		
		//System.out.println();
		for(int i=1;i<numOfRouters;i++)
			System.out.println(D[i]);
		
	}//end main
	
}//end LinkState
