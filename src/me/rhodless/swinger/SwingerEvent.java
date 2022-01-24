package me.rhodless.swinger;

public class SwingerEvent {
    public static final int BUTTON_CLICKED_EVENT = 0;

    private Object source;

    private int type;

    public SwingerEvent(Object source, int type) {
        this.source = source;
    }

    public Object getSource() {
        return this.source;
    }

    public int getType() {
        return this.type;
    }
}
