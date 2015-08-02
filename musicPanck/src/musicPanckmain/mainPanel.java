package musicPanckmain;

import gamePanel.selectPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import BackResoce.StringSorce;
import BackResoce.Strings;
import BackResoce.downString;
import midtimeconv.MIDIPlayer;
import midtimeconv.midiFileSet;
import midtimeconv.midsequence;


public class mainPanel extends JPanel implements ActionListener,PanelWork{
	private ImageSever IS;
	private JButton start;
	private JButton stop;

	private FrameWork parent;
	private MusicGame MusicG;

	private downString DS;

	public mainPanel(mainFrame mainFrame) {
		parent = mainFrame;
		System.out.println(""+mainFrame.getHeight());

		setLayout(null);
		setBounds(0, 0,mainFrame.getWidth(), mainFrame.getHeight());
		IS = new ImageSever();
		IS.createImage("たいとる", "title.jpg");
		start = new JButton("Start");
		start.setBounds(mainFrame.getWidth()/2-50, mainFrame.getHeight()/2-10,100,20);
		start.addActionListener(this);
		stop = new JButton("stop");
		stop.setBounds(mainFrame.getWidth()/2-50, mainFrame.getHeight()/2+20,100,20);
		stop.addActionListener(this);
		this.add(start);
		this.add(stop);
		DS = new downString();
		DS.set_max_xy(mainFrame.getWidth(), mainFrame.getHeight());
		ArrayList<String> list = new ArrayList<String>();
		for(int cnt = 0;cnt< 10;cnt++){
			list.add(""+(char)(48+cnt));
		}
		for(int cnt = 0;cnt < 100;cnt++){
			StringSorce S = new StringSorce();
			S.setList(list, 5);
			DS.addS(S);
		}
		setBackground(Color.black);
		DS.setEnable(true);
	}

	public void run() {

		DS.refresh();
		repaint();
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);	
		DS.show(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(start)){
			System.out.println("すたーと");
			MessagePack AMP = new MessagePack();
			AMP.setMeta_A("Chiled_Change");
			AMP.setPresent(new selectPanel());
			this.parent.setmessage(AMP);
		}
		if(e.getSource().equals(stop)){
		}
	}



	public void setParent(FrameWork parent) {
		this.parent = parent;

	}
}
