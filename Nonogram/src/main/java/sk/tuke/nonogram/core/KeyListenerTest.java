package sk.tuke.nonogram.core;

import javax.swing.*;
import java.awt.event.*;

public class KeyListenerTest extends JFrame implements KeyListener {

    public KeyListenerTest() {
        super("Key Listener Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        JTextArea textArea = new JTextArea();
        textArea.addKeyListener(this);

        add(textArea);

        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // This method is called when a key is typed (pressed and released).
        char keyChar = e.getKeyChar();
        System.out.println("Key Typed: " + keyChar);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // This method is called when a key is pressed.
        int keyCode = e.getKeyCode();
        System.out.println("Key Pressed: " + KeyEvent.getKeyText(keyCode));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // This method is called when a key is released.
        int keyCode = e.getKeyCode();
        System.out.println("Key Released: " + KeyEvent.getKeyText(keyCode));
    }
}