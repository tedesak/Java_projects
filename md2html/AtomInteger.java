package md2html;

public class AtomInteger {
    private int val;

    public AtomInteger(int val) {
        this.val=val;
    }

    public int getVal() {
        return val;
    }

    public void inrease(int change) {
        this.val += change;
    }
}
