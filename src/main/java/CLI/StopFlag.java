package CLI;

//stopFlag method for managed stop state
class StopFlag {
    private volatile boolean stopped = false;

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }
}