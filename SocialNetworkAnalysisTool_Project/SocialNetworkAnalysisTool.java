package SocialNetworkAnalysisTool_Project;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// Graph class
class Graph {
    private Map<User, List<User>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(User user) {
        adjacencyList.put(user, new ArrayList<>());
    }

    public void addEdge(User user1, User user2) {
        if (adjacencyList.containsKey(user1) && adjacencyList.containsKey(user2)) {
            List<User> connections1 = adjacencyList.get(user1);
            List<User> connections2 = adjacencyList.get(user2);

            if (!connections1.contains(user2)) {
                connections1.add(user2);
            }

            if (!connections2.contains(user1)) {
                connections2.add(user1);
            }
        }
    }

    public List<User> shortestPath(User user1, User user2) {
        if (!adjacencyList.containsKey(user1) || !adjacencyList.containsKey(user2)) {
            return null;
        }

        Map<User, User> parentMap = new HashMap<>();
        Queue<User> queue = new LinkedList<>();
        Set<User> visited = new HashSet<>();

        queue.offer(user1);
        visited.add(user1);

        while (!queue.isEmpty()) {
            User current = queue.poll();

            if (current == user2) {
                break;
            }

            for (User neighbor : adjacencyList.get(current)) {
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }

        List<User> path = new ArrayList<>();
        User current = user2;

        while (current != null) {
            path.add(0, current);
            current = parentMap.get(current);
        }

        return path;
    }

    public List<User> commonConnections(User user1, User user2) {
        if (!adjacencyList.containsKey(user1) || !adjacencyList.containsKey(user2)) {
            return null;
        }

        List<User> connections1 = adjacencyList.get(user1);
        List<User> connections2 = adjacencyList.get(user2);

        List<User> commonConnections = new ArrayList<>(connections1);
        commonConnections.retainAll(connections2);

        return commonConnections;
    }

    public List<User> influentialUsers() {
        List<User> users = new ArrayList<>(adjacencyList.keySet());
        users.sort(Comparator.comparingInt(user -> adjacencyList.get(user).size()));
        Collections.reverse(users);

        return users;
    }
}

// User class
class User {
    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and setters for id and name
    public String getId() {
    	return id;
    }
    public void setId(String id) {
    	this.id=id;
    }
    public String getName() {
    	return name;
    }
    public void setName(String name) {
    	this.name=name;
    }
    @Override
    public String toString(){
        return name;
    }
}

// Main program
public class SocialNetworkAnalysisTool {
    public static void main(String[] args) {
        Graph socialNetwork = new Graph();

        User user1 = new User("1", "John");
        User user2 = new User("2", "Alice");
        User user3 = new User("3", "Bob");
        User user4 = new User("4", "Sarah");

        socialNetwork.addVertex(user1);
        socialNetwork.addVertex(user2);
        socialNetwork.addVertex(user3);
        socialNetwork.addVertex(user4);

        socialNetwork.addEdge(user1, user2);
        socialNetwork.addEdge(user2, user3);
        socialNetwork.addEdge(user3, user4);
        socialNetwork.addEdge(user4, user1);

        List<User> shortestPath = socialNetwork.shortestPath(user1, user3);
        System.out.println("Shortest path from John to Bob: " + shortestPath);

        List<User> commonConnections = socialNetwork.commonConnections(user1, user4);
        System.out.println("Common connections between John and Sarah: " + commonConnections);

        List<User> influentialUsers = socialNetwork.influentialUsers();
        System.out.println("Most influential users: " + influentialUsers);
    }
}
