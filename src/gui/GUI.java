package gui;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import GAME.*;

public class GUI implements ActionListener, MouseListener, MouseMotionListener, KeyListener {


    public static Image getImage(String path) {
        Toolkit.getDefaultToolkit();
        BufferedImage IMG = null;
        try {
            IMG = ImageIO.read(new File(GUI.class.getResource(path).getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return IMG;
    }
    public static Image getImage(File file) {
        Toolkit.getDefaultToolkit();
        BufferedImage IMG = null;
        try {
            IMG = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return IMG;
    }

    private final BufferedImage offscreenImage; // ������� ����������� �����������
    private final BufferedImage onscreenImage;  //������� ����������� �����������
    private final Graphics2D offscreen;
    private final Graphics2D onscreen;
    private JFrame frame;                       // ��������� �������� ������
    private JPanel center = new JPanel();       // ����������� ������


    // ������� ����������� ��������� � ����, ��������� ������, � ���� ������� ������� �� ������ �� ������
    public GUI(int width, int height) {
        offscreenImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        onscreenImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        offscreen = (Graphics2D) offscreenImage.getGraphics();
        onscreen = (Graphics2D) onscreenImage.getGraphics();
        //TODO: ����������� ���� ���
        DBHandler.getInstance().loadQuizes();
        // ������ ������
        ImageIcon icon = new ImageIcon(onscreenImage);
        JLabel draw = new JLabel(icon);
        draw.addMouseListener(this);
        draw.addMouseMotionListener(this);


        //����� �� ����� �������� ����� ����������
        center.add(draw);
        center.addKeyListener(this);

        // west ������ ������

        //frame = new JFrame();
        frame = new JFrame("���? ���? �����?");
        frame.setIconImage(getImage("Data/icon.png"));
        frame.setSize(new Dimension(1280, 720));
        MyPanel west = new MyPanel("Data/Fon1231.jpg");
        JButton button1 = new JButton("����");
        button1.setFont(new Font("TimesNewRoman", Font.BOLD, 36));
        button1.setBounds(550, 240, 320, 48);
        button1.setForeground(Color.getHSBColor(400, 155, 150));

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                TeamScreen ff = new TeamScreen(frame);
                frame.setVisible(true);


                //frame.setIconImage();

            }
        });

        JButton button2 = new JButton("��������");
        button2.setFont(new Font("TimesNewRoman", Font.BOLD, 36));
        button2.setBounds(550, 310, 320, 48);
        button2.setForeground(Color.getHSBColor(400, 155, 150));
        JButton button3 = new JButton("����������");
        button3.setFont(new Font("TimesNewRoman", Font.BOLD, 36));
        button3.setBounds(550, 380, 320, 48);
        button3.setForeground(Color.getHSBColor(400, 155, 150));
        JButton button4 = new JButton("���������");
        button4.setFont(new Font("TimesNewRoman", Font.BOLD, 36));
        button4.setBounds(550, 450, 320, 48);
        button4.setForeground(Color.getHSBColor(400, 155, 150));
        JButton button5 = new JButton("�����");
        button5.setFont(new Font("TimesNewRoman", Font.BOLD, 36));
        button5.setBounds(550, 520, 320, 48);
        button5.setForeground(Color.getHSBColor(400, 155, 150));
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                SettingsScreen ss = new SettingsScreen(frame);
                frame.setVisible(true);
            }
        });
        button5.addActionListener(this);
        button1.setToolTipText("������ ����");
        west.add(button1);
        west.add(button2);
        west.add(button3);
        west.add(button4);
        west.add(button5);
        button5.addActionListener(new
                                          ActionListener() {
                                              public void actionPerformed(ActionEvent event) {
                                                  System.exit(0);
                                              }
                                          });
        frame.add(west);

        // ����
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("�������");
        menuBar.add(menu);
        JMenuItem menuReg = new JMenuItem(" ������� ");
        JMenuItem menuDoc = new JMenuItem(" ������������ ");
        //menuReg.addActionListener(this);
        menuReg.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        menu.add(menuReg);
        menuReg.addActionListener(new
                                          ActionListener() {
                                              public void actionPerformed(ActionEvent event) {
                                                  File f = new File("reg.txt");
                                                  FileReader in = null;
                                                  try {
                                                      in = new FileReader(f);
                                                  } catch (FileNotFoundException e) {
                                                      e.printStackTrace();
                                                  }
                                                  char[] arr = new char[(int) f.length()];
                                                  int read = 0;
                                                  try {
                                                      read = in.read(arr);
                                                  } catch (IOException e) {
                                                      e.printStackTrace();
                                                  }
                                                  try {
                                                      in.close();
                                                  } catch (IOException e) {
                                                      e.printStackTrace();
                                                  }
                                                  JFrame jf = new JFrame("���? ���? �����?");
                                                  jf.setIconImage(getImage("Data/icon.png"));
                                                  JTextArea jta = new JTextArea();
                                                  jta.append(String.valueOf(arr, 0, read));
                                                  jf.add(jta);
                                                  jf.setSize(1280, 720);
                                                  jf.setVisible(true);
                                                  jf.setResizable(false);
                                              }
                                          });


        menuDoc.addActionListener(this);
        menuDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        menu.add(menuDoc);
        menuDoc.addActionListener(new
                                          ActionListener() {
                                              public void actionPerformed(ActionEvent event) {
                                                  File f = new File("doc.txt");
                                                  FileReader in = null;
                                                  try {
                                                      in = new FileReader(f);
                                                  } catch (FileNotFoundException e) {
                                                      e.printStackTrace();
                                                  }
                                                  char[] arr = new char[(int) f.length()];
                                                  int read = 0;
                                                  try {
                                                      read = in.read(arr);
                                                  } catch (IOException e) {
                                                      e.printStackTrace();
                                                  }
                                                  try {
                                                      in.close();
                                                  } catch (IOException e) {
                                                      e.printStackTrace();
                                                  }
                                                  JFrame jf = new JFrame("���? ���? �����?");
                                                  jf.setIconImage(getImage("Data/icon.png"));
                                                  JTextArea jta = new JTextArea();
                                                  jta.append(String.valueOf(arr, 0, read));
                                                  jf.add(jta);
                                                  jf.setSize(1280, 720);
                                                  jf.setVisible(true);
                                                  jf.setResizable(false);
                                              }
                                          });

        // ��������� ����� � ��������� ����������
        frame.setIconImage(getImage("Data/icon.png"));
        frame.setJMenuBar(menuBar);
        frame.add(west, BorderLayout.CENTER);
        frame.add(center, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();

        // ���� ����� �� ����������� ������
        center.requestFocusInWindow();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    // ���������� ������� �� ������
    public void showz() {
        frame.setSize(new Dimension(1280, 760));
        OwlScreen owlScreen = new OwlScreen(frame, "Data/Fon111.jpg", "���? ���? �����?");


    	    	/*west.setResizable(false);
                onscreen.drawImage(offscreenImage, 0, 0, null);
                west.repaint();*/
    }

    /*************************************************************************
     * �������� ������ �������
     *************************************************************************/

    // ��� ������ � ����
    public void actionPerformed(ActionEvent e) {
        // �� ���� ����� ����������
        center.requestFocusInWindow();
    }

    // ��� �����
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println("Mouse pressed at " + x + ", " + y);
        offscreen.setColor(Color.GREEN);
        offscreen.fillOval(x - 3, y - 3, 6, 6);
        showz();
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }


    // ��� ����������
    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
        System.out.println("Key = '" + e.getKeyChar() + "'");
    }

    // ���� �������
    public static void main(String[] args) {
        GUI gui = new GUI(1280, 720);
        //    gui.picture(0, 0, "Untitled-1.png");
        gui.showz();
    }

}