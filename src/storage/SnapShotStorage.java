package storage;

import builders.CustomStringBuilder;

import java.util.Stack;

public class SnapShotStorage {
    private final Stack<CustomStringBuilder.SnapShot> storage = new Stack<>();

    public SnapShotStorage() {
    }


    public void save(CustomStringBuilder.SnapShot snapShot) {
        storage.push(snapShot);
    }

    public CustomStringBuilder.SnapShot get() {
        return storage.pop();
    }


}
