import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @dev :   devpar
 * @date :   23-May-2021
 */
public class MainFrame extends JFrame implements ComponentListener , KeyListener {
    private final String title="sudo</>terminal";
    TextAreaPanel textAreaPanel;
    public MainFrame(final int width,final int height){
        setSize(width,height);
        setTitle(title);
        textAreaPanel=new TextAreaPanel(width,height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        JScrollPane scrollPane=new JScrollPane(textAreaPanel);
        textAreaPanel.addScrollPane(scrollPane);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy((JScrollPane.VERTICAL_SCROLLBAR_ALWAYS));
        add(scrollPane,BorderLayout.CENTER);
        addComponentListener(this);
        addKeyListener(this);
        setVisible(true);
    }
    public static void main(String $[]){
        SwingUtilities.invokeLater(()->{
           MainFrame mainFrame=new MainFrame(500,500);
        });
    }

    @Override
    public void componentResized(ComponentEvent e) {
        textAreaPanel.resized(getWidth(),getHeight());
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        textAreaPanel.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
