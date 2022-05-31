package Graph;


import java.util.Objects;

public class Edge {
    private Node src;
    private Node dest;
    private int duration;
    private int flow;
    private int capacity;
    private boolean visited;

    public Edge(Node src, Node dest, int capacity, int duration) {
        this.src = src;
        this.dest = dest;
        this.duration = duration;
        this.capacity = capacity;
        this.flow = 0;
        this.visited = false;
    }

    public Node getSrc() {
        return src;
    }

    public Node getDest() {
        return dest;
    }

    public int getDuration() {
        return duration;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    public void setSrc(Node src) {
        this.src = src;
    }

    public void setDest(Node dest) {
        this.dest = dest;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void decreaseFlow(int flow) {
        this.flow -= flow;
    }

    public void increaseFlow(int flow) {
        this.flow += flow;
    }

    public void addFatherNodeToDestNode(Node fatherNode) {
        this.dest.setFatherNode(fatherNode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return src == edge.src && dest == edge.dest;
    }

    @Override
    public int hashCode() {
        return Objects.hash(src, dest);
    }
}
