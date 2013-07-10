/*
 * node class contains router information used in constructing a network. Contains a router 'id', neighbors of the router and the distance to these neighbors.
 */
public class node{
  
	public int id;
	//neighbor index correlates to distance index
	public int[] neighbors;
	public int[] distance;
	
	public node(int id,int[] neighbors,int[] distance){
		this.id = id;
		this.neighbors = neighbors;
		this.distance = distance;
	}
	
	public node(node theNode){
		this.id = theNode.id;
		this.neighbors = theNode.neighbors;
		this.distance = theNode.distance;
	}
	
	public boolean isEqual(node theNode){
		
		if(this.id==theNode.id && this.neighbors==theNode.neighbors && this.distance==theNode.distance)
			return true;
		
		return false;
	}
	
	public boolean isNeighbor(node theNode){

		for(int i=0;i<neighbors.length;i++){
			if(theNode.id==this.neighbors[i])
				return true;
		}
		
		return false;
	}
	
	//only call if isNeighbor() returns true
	public int findDistance(node theNode){
		
		for(int i=0;i<neighbors.length;i++){
			if(theNode.id==this.neighbors[i])
				return i;
		}
		return (Integer) null; //should not get here
	}
	
}
