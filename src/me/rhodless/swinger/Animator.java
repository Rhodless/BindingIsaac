package me.rhodless.swinger;

import com.sun.awt.AWTUtilities;
import me.rhodless.GameFrame;

import java.awt.*;

public class Animator {

    public static final int SLOW = 20;

    public static final int NORMAL = 10;

    public static final int FAST = 5;

    public static void query(final long to, final QueryLoopAction loopAction) {
        Thread t = new Thread(() -> {
            for (long query = 0L; query < to + 1L; query++)
                loopAction.onLoop(query);
        });
        t.start();
    }

    public static void query(final long to, final long toWait, final QueryLoopAction loopAction) {
        Thread t = new Thread(() -> {
            for (long query = 0L; query < to + 1L; query++) {
                loopAction.onLoop(query);
                try {
                    Thread.sleep(toWait);
                } catch (InterruptedException ignored) {
                }
            }
        });
        t.start();
    }

    public static void query(final long from, final long to, final long toWait, final QueryLoopAction loopAction) {
        Thread t = new Thread() {
          public void run() {
              for (long query = from; query < to + 1L; query++) {
                  loopAction.onLoop(query);
                  try {
                      sleep(toWait);
                  } catch (InterruptedException e) {
                  }
              }
          }
        };
        t.start();
    }

    public static void query(final long from, final long to, final long speed, final long toWait, final QueryLoopAction loopAction) {
        Thread t = new Thread() {
          public void run() {
              for (long query = from; query < to + 1L; query += speed) {
                  loopAction.onLoop(query);
                  try {
                      sleep(toWait);
                  } catch (InterruptedException e) {
                  }
              }
          }
        };
        t.start();
    }

    public static void fadeInFrame(Window toFade) {
        fade(toFade, 10, false, null);
    }

    public static void fadeInFrame(Window toFade, Runnable callback) {
        fade(toFade, 10, false, callback);
    }

    public static void fadeInFrame(Window toFade, int speed) {
        fade(toFade, speed, false, null);
    }

    public static void fadeInFrame(Window toFade, int speed, Runnable callback) {
        fade(toFade, speed, false, callback);
    }

    public static void fadeOutFrame(Window toFade) {
        fade(toFade, 10, true, null);
    }

    public static void fadeOutFrame(Window toFade, Runnable callback) {
        fade(toFade, 10, true, callback);
    }

    public static void fadeOutFrame(Window toFade, int speed) {
        fade(toFade, speed, true, null);
    }

    public static void fadeOutFrame(Window toFade, int speed, Runnable callback) {
        fade(toFade, speed, true, callback);
    }

    private static void fade(final Window toFade, int speed, final boolean inverted, final Runnable callback) {
        query(100L, speed, query -> {
            AWTUtilities.setWindowOpacity(toFade, inverted ? ((float) (100L - query) / 100.0F) : ((float) query / 100.0F));
            if (query == 100L && callback != null) {
                callback.run();

                System.out.println(false);
            }
        });
    }
}
