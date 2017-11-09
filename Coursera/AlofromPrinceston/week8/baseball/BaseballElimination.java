import java.util.*;
import edu.princeton.cs.algs4.*;

public class BaseballElimination{
	private int[] wins;
	private int[] losses;
	private int[] remaining;
	private int[][] games;
	private Map<String,Integer> teamToId;
	private int maxWin = Integer.MIN_VALUE;
	private String leaderTeam;

	public BaseballElimination(String filename){
		In in = new In(filename);
		int teams = in.readInt();
		wins = new int[teams];
		losses = new int[teams];
		remaining = new int[teams];
		games = new int[teams][teams];
		teamToId = new HashMap<String,Integer>();
		for (int id = 0; id < teams; id ++ ){
			String team = in.readString();
			teamToId.put(team,id);
			wins[id] = in.readInt();
			losses[id] = in.readInt();
			remaining[id] = in.readInt();
			for(int against = 0; against < teams; against++){
				games[id][against] = in.readInt();
			}
			if (wins[id] > maxWin){
				maxWin = wins[id];
				leaderTeam = team;
			}
		}
	}
	public int numberOfTeams(){
		return teamToId.size();
	}
	public Iterable<String> teams(){
		return teamToId.keySet();
	}
	public int wins(String team){
		if (!teamToId.containsKey(team))
			throw new IllegalArgumentException("no such team");
		return wins[teamToId.get(team)];
	}
	public int losses(String team){
		if (!teamToId.containsKey(team))
			throw new IllegalArgumentException("no such team");
		return losses[teamToId.get(team)];
	}
	public int remaining(String team){
		if (!teamToId.containsKey(team))
			throw new IllegalArgumentException("no such team");
		return remaining[teamToId.get(team)];
	}
	public int against(String team1, String team2){
		if (!teamToId.containsKey(team1) || !teamToId.containsKey(team2) )
			throw new IllegalArgumentException("no such team1 or team2");
		return games[teamToId.get(team1)][teamToId.get(team2)];
	}
	public boolean isEliminated(String team){
		if (!teamToId.containsKey(team))
			throw new IllegalArgumentException("no such team");	
		int id = teamToId.get(team);
		if (triEliminated(id)){
			return true;
		}
		Graph graph = buildNetWork(id);
		for (FlowEdge e : graph.network.adj(graph.source)){
			// after min computing, if the outflow < capacity, the id vertice can be eliminated out
			if (e.flow() < e.capacity()) 
				return true;
		}
		return false;
	}
	private boolean triEliminated(int id){
		if (wins[id]+remaining[id] < maxWin)
			return true;
		return false;
	}
	public Iterable<String> certificateOfElimination(String team){
		if (!teamToId.containsKey(team))
			throw new IllegalArgumentException("no such team");	
		int id = teamToId.get(team);
		Set<String> eliminatedSet = new HashSet<>();
		if (triEliminated(id)){
			eliminatedSet.add(leaderTeam);
			return eliminatedSet;
		}
		Graph graph = buildNetWork(id);
		for (FlowEdge e : graph.network.adj(graph.source)){
			if (e.flow() < e.capacity()){
				for (String s : teams()){
					int i = teamToId.get(s);
					if (graph.ff.inCut(i))
						eliminatedSet.add(s);
				}
			}
		}
		graph = null;
		if (eliminatedSet.isEmpty())
			return null;
		return eliminatedSet;
	}
	public static void main(String[] args) {
		BaseballElimination division = new BaseballElimination(args[0]);
		for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team))
                    StdOut.print(t + " ");
                StdOut.println("}");
            } else {
                StdOut.println(team + " is not eliminated");
            }
        }
		
	}
	private Graph buildNetWork(int id){
		int n = numberOfTeams();
		int source = n;
		int sink = n+1;
		int gamesNode = n+2;
		int idMaxWins = wins[id]+remaining[id];
		Set<FlowEdge> edges = new HashSet<>();
		for (int i = 0; i < n; i++){
			if (i == id || wins[i] + remaining[i] < maxWin) // to jump through i == id and to spare time 
				continue;
			for (int j = 0; j < i; j++){
				if (j == id || games[i][j] == 0 || wins[j] + remaining[j] < maxWin)
					continue;
				edges.add(new FlowEdge(source,gamesNode,games[i][j]));
				edges.add(new FlowEdge(gamesNode,i,Double.POSITIVE_INFINITY));
				edges.add(new FlowEdge(gamesNode,j,Double.POSITIVE_INFINITY));
				gamesNode++; // gamesNode from n+2 to ...... and used gamesNode as the node counter
			}
			edges.add(new FlowEdge(i,sink,idMaxWins-wins[i]));
		}
		FlowNetwork network = new FlowNetwork(gamesNode); // use gamesNode as node total counter
		for (FlowEdge edge : edges)
			network.addEdge(edge);
		FordFulkerson ff = new FordFulkerson(network,source,sink);
		return new Graph(ff,network,source,sink);  
	}
	private class Graph{			// Graph can be used to calculate mincut and capacity and flow  
		FordFulkerson ff; 					// without modifier, default modifier = package private, 
		int source, sink;					//be seem only in the same package
		FlowNetwork network;
		public Graph(FordFulkerson ff, FlowNetwork network, int source, int sink){
			super();
			this.ff = ff;
			this.source = source;
			this.sink = sink;
			this.network = network;
			
		}
	}
}