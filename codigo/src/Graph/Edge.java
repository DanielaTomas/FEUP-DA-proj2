package Graph;


class Edge {
    private int src;
    private int dest;
    private int duration;
    private int flow = 0;
    private int capacity;

    public Edge(int src, int dest, int duration, int capacity) {
        this.src = src;
        this.dest = dest;
        this.duration = duration;
        this.capacity = capacity;
    }

    public int getSrc() {
        return src;
    }

    public int getDest() {
        return dest;
    }

    public int getDuration() {
        return duration;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }
}
