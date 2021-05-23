import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @dev :   devpar
 * @date :   23-May-2021
 */
public class TextAreaPanel extends JPanel{
    private final String terminal="$>";
    private BufferedImage image;
    private Graphics2D graphics2D;
    private ArrayList<String>text;
    private int cursorX,cursorY;
    private JScrollPane scrollPane;
    public TextAreaPanel(final int width,final int height){
//        setSize(width,height);
        setPreferredSize(new Dimension(width,height));
        text=new ArrayList<>();
        initializeImage(width,height);
//        setPreferredSize(new Dimension(width,height));
//        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        addText(terminal);
    }
    public void addScrollPane(JScrollPane pane){
        this.scrollPane=pane;
    }
    public void initializeImage(final int width,final int height){
        image=new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR);
        graphics2D=(Graphics2D)image.getGraphics();
        if(image==null)
            System.out.println("image is null");
        if(graphics2D==null)
            System.out.println("graphics 2d is null");
    }
    private void addText(final String str){
        if(text.size()==0)
            text.add("");
        text.set(cursorY,text.get(cursorY)+str);
        cursorX+=str.length();
        int max=0;
        for(String string:text)
            max=Math.max(max,graphics2D.getFontMetrics().stringWidth(string));
        {
            if(scrollPane!=null){
                JScrollBar sb=scrollPane.getHorizontalScrollBar();
                int minimum=sb.getMinimum();
                int value=sb.getValue();
                int maximum=sb.getMaximum();
                System.out.println("min : "+minimum);
                System.out.println("value : "+value);
                System.out.println("max : "+maximum);
            }
        }
        if(max>getPreferredSize().width){
//            setSize(max,getHeight());
            resized(max,getHeight());
//            setPreferredSize(new Dimension(max,getHeight()));
            JScrollBar sb=scrollPane.getHorizontalScrollBar();
            int minimum=sb.getMinimum();
            int value=sb.getValue();
            int maximum=sb.getMaximum();
            System.out.println("min : "+minimum);
            System.out.println("value : "+value);
            System.out.println("max : "+maximum);
            sb.setValue(sb.getMaximum());
            this.revalidate();
            System.out.println("resetting size since text size is more");
        }
        repaint();
    }
    private void addTextNewColumn(final String str){
        cursorY++;
        cursorX=0;
        text.add(terminal+str);
        cursorX=str.length();
        int max=0;
        for(String string:text)
            max=Math.max(max,graphics2D.getFontMetrics().stringWidth(string));
        int currHeight=(cursorY+1)*graphics2D.getFontMetrics().getHeight();
        if(currHeight>getHeight()){
            resized(getWidth(),currHeight);
            this.revalidate();
            System.out.println("resetting height since text size is more");
        }
        if(max>getPreferredSize().width){
//            setSize(max,getHeight());
            resized(max,getHeight());
            this.revalidate();
            System.out.println("resetting width since text size is more");
        }
        repaint();
    }
    public void drawStringOnImage(){
        if(graphics2D==null){
            System.out.println("returning graphics 2d is null");
            return;
        }
        graphics2D.clearRect(0,0,getWidth(),getHeight());
        int row=0,col=0;
        FontMetrics fontMetrics=graphics2D.getFontMetrics();
        int height=0,width=0;
        for(int i=0;i<text.size();i++){
            width=0;
            height+=fontMetrics.getHeight();
            final String str=text.get(i);
//            width+=fontMetrics.stringWidth(str);
            graphics2D.drawString(str,width,height);
        }
    }
    @Override
    public void paint(Graphics g){
        final int width=getWidth();
        final int height=getHeight();
        drawStringOnImage();
        g.drawImage(image,0,0,this);
    }
    public void keyTyped(KeyEvent e) {
        char ch=e.getKeyChar();
        System.out.println(ch);
        if(ch=='\n')
            addTextNewColumn(ch+"");
        else
            addText(ch+"");
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }
    public void resized(final int width,final int height){
        System.out.println("resized");
        System.out.println("width "+width);
        System.out.println("height "+height);
        initializeImage(Math.max(width,getWidth()),Math.max(height,getHeight()));
        setPreferredSize(new Dimension(Math.max(width,getWidth()),Math.max(height,getHeight())));
    }
    private String getDirectory(){
        return System.getProperty("user.dir");
    }
}
