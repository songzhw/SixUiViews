package ca.six.views.rv.stickyheader;

import java.util.List;

public class StickyColumnTableAdapter<T> {
    private List<T> leftData;
    private List<T> rightData;

    public StickyColumnTableAdapter(List<T> leftData, List<T> rightData) {
        this.leftData = leftData;
        this.rightData = rightData;
    }

    public List<T> getLeftData() {
        return leftData;
    }

    public List<T> getRightData() {
        return rightData;
    }
}
