import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import java.util.Random;

public class AutoChat extends JFrame implements KeyListener {
    private static final int INTERVAL = 22000; // 22 seconds
    private static final String[] PHRASES = {
        "ok",
        "no",
        "wild",
        "crazy",
        "imagine",
        "bruh",
        "what",
        "huh",
        "hm?",
        "hm",
        "hmmmmm?",
        "pardon?",
        "oh, ok",
        "hmmmmm, ok",
        "sure",
        "right",
        "right, ok",
        "right then",
        "bruhh",
        "nope",
        "nuhuh",
        "yup",
        "yuhuh",
        "insane",
        "mhm",
        "mmmmm",
        "ah",
        "oh noes",
        "oh",
        "ooooo",
        "that's wild",
        "thas cool",
        "really?",
        "nahhh",
        "ahh",
        "no wait what",
        "idk",
        "yay",
        "yippee",
        "wow",
        "oo, spooky",
        "seriously?",
        "that's so sad",
        "please",
        "pls",
        "don't do it",
        "badum pow",
        "that did not make sense",
        "heh",
        "very good",
        "very cool",
        "goop",
        "goober",
        "gooberro",
        "i dunno",
        "dunno",
        "uh huh",
        "yuuup",
        "indeed",
        "k",
        "funny",
        "bip bap skdoo bep",
        "my home...",
        "fr",
        "ok pal",
        "ok bud",
        "ok buddy",
        "sure pal",
        "sure bud",
        "sure buddy",
        "mate,",
        "ight mate",
        "alrighty mate",
        "very coolio",
        "aw",
        "unfortunate",
        "ehehehehhehe",
        "straight ballin'",
        "baller",
        "ballin'",
        "imagine not",
        "could not be me",
        "could be me",
        "agreed",
        "sorry",
        "mb",
        "whoops",
        "apologies",
        "my condolences",
        "yeah",
        "good",
        "bro",
        "brooooo",
        "oh?"
    };
    
    private static boolean running = true;
    
    public static void main(String[] args) {
        try {
            Robot robot = new Robot();
            Random random = new Random();
            
            // Initialize JFrame and add KeyListener
            AutoChat frame = new AutoChat();
            frame.setSize(400, 400);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            System.out.println("Starting in 5 seconds...");
            Thread.sleep(5000); // Wait 5 seconds before starting
            
            while (running) {
                String randomPhrase = getRandomPhrase(random);
                typeString(robot, randomPhrase);
                pressEnter(robot);
                Thread.sleep(INTERVAL);
            }
        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private static String getRandomPhrase(Random random) {
        return PHRASES[random.nextInt(PHRASES.length)];
    }
    
    private static void typeString(Robot robot, String text) {
        for (char c : text.toCharArray()) {
            if (c == ' ') {
                robot.keyPress(KeyEvent.VK_SPACE);
                robot.keyRelease(KeyEvent.VK_SPACE);
                continue;
            }
            
            boolean requiresShift = false;
            int keyCode = getKeyCode(c);
            
            if (keyCode == -1) {
                System.out.println("Skipping unsupported character: " + c);
                continue;
            }
            
            if (requiresShiftKey(c)) {
                robot.keyPress(KeyEvent.VK_SHIFT);
                requiresShift = true;
            }
            
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
            
            if (requiresShift) {
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }
            
            try {
                Thread.sleep(100); // Small delay between key presses
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static int getKeyCode(char c) {
        if (c >= 'A' && c <= 'Z') {
            return KeyEvent.VK_A + (c - 'A');
        } else if (c >= 'a' && c <= 'z') {
            return KeyEvent.VK_A + (c - 'a');
        }
        
        switch (c) {
            case '0': return KeyEvent.VK_0;
            case '1': return KeyEvent.VK_1;
            case '2': return KeyEvent.VK_2;
            case '3': return KeyEvent.VK_3;
            case '4': return KeyEvent.VK_4;
            case '5': return KeyEvent.VK_5;
            case '6': return KeyEvent.VK_6;
            case '7': return KeyEvent.VK_7;
            case '8': return KeyEvent.VK_8;
            case '9': return KeyEvent.VK_9;
            case ',': return KeyEvent.VK_COMMA;
            case '.': return KeyEvent.VK_PERIOD;
            case '/': return KeyEvent.VK_SLASH;
            case ';': return KeyEvent.VK_SEMICOLON;
            case ':': return KeyEvent.VK_COLON;
            case '-': return KeyEvent.VK_MINUS;
            case '=': return KeyEvent.VK_EQUALS;
            case '_': return KeyEvent.VK_MINUS;
            case '+': return KeyEvent.VK_EQUALS;
            case '!': return KeyEvent.VK_1;
            case '@': return KeyEvent.VK_2;
            case '#': return KeyEvent.VK_3;
            case '$': return KeyEvent.VK_4;
            case '%': return KeyEvent.VK_5;
            case '^': return KeyEvent.VK_6;
            case '&': return KeyEvent.VK_7;
            case '*': return KeyEvent.VK_8;
            case '(': return KeyEvent.VK_9;
            case ')': return KeyEvent.VK_0;
            case '?': return KeyEvent.VK_SLASH;
            default: return -1;
        }
    }
    
    private static boolean requiresShiftKey(char c) {
        return "!@#$%^&*()_+?:".indexOf(c) != -1;
    }
    
    private static void pressEnter(Robot robot) {
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P && e.isControlDown()) {
            System.out.println("Ctrl + P detected. Exiting...");
            running = false;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {}
    
    @Override
    public void keyTyped(KeyEvent e) {}
}
