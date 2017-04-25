package com.nest.sagepos.webservice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Locale;

import javax.swing.JPanel;
/**
 * Took this file from idempiere..
 */
public class Splash extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7671371032359729541L;

	/**
	 * Get Splash Screen
	 * 
	 * @return Splash Screen
	 */
	public static Splash getSplash() {
		String msg;
		if (Locale.getDefault().getLanguage().equals("es")) // espańol -
															// globalqss
			msg = new String("");
		else
			// Default english
			msg = new String("");
		return getSplash(msg);
	} // getSplash

	/**
	 * Get Splash Screen
	 * 
	 * @param text
	 *            splash text
	 * @return Splash Screen
	 */
	public static Splash getSplash(String text) {
		if (s_splash == null)
			s_splash = new Splash(text);
		else
			s_splash.setText(text);
		return s_splash;
	} // getSplash

	private static Splash s_splash = null;

	/**************************************************************************
	 * Standard constructor
	 * 
	 * @param text
	 *            clear text
	 */
	public Splash(String text) {
		super("Sage X3");
		System.out.println(text);
		message.setText(text);
		try {
			jbInit();
		} catch (Exception e) {
			System.out.println("Splash");
			e.printStackTrace();
		}
		display();
	} // Splash

	/** Tracker */
	private MediaTracker tracker = new MediaTracker(this);
	//
	private CImage cImage = new CImage();
	private AImage aImage = new AImage();
	//
	private Label productLabel = new Label();
	private JPanel contentPanel = new JPanel();
	private GridBagLayout contentLayout = new GridBagLayout();
	private Label message = new Label();

	/**
	 * Static Init
	 * 
	 * @throws Exception
	 */
	private void jbInit() throws Exception {
		this.setBackground(Color.white);
		// this.setBackground(new Color(0,0,0,0));
		setUndecorated(true);
		// setBackground(new Color(1.0f,1.0f,1.0f,0.5f));
		// setOpacity(0.8f);

		// this.setContentPane(new TranslucentPane());
		this.setName("splash");
		// this.setUndecorated(true);
		//
		productLabel.setAlignment(Label.CENTER);
		message.setFont(new java.awt.Font("Serif", 1, 20)); // italic bold 20 pt
		message.setForeground(SystemColor.activeCaption);
		message.setAlignment(Label.CENTER);
		contentPanel.setLayout(contentLayout);
		contentPanel.setName("splashContent");
		contentPanel.setOpaque(true);
		contentPanel.setBackground(Color.white);
		//
		productLabel.setFont(new java.awt.Font("Serif", 1, 20));
		productLabel.setForeground(Color.BLACK);
		productLabel.setText("Creating Sales Order From POS...");
		// productLabel.setToolTipText(Adempiere.getURL());
		//
		contentPanel.add(productLabel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(15, 5, 0, 10), 0, 0));
		contentPanel.add(cImage, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 5, 0, 10), 0, 0));
		contentPanel.add(message, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(5, 5, 10, 10), 0, 0));
		/*
		 * contentPanel.add(message, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
		 * ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5,
		 * 10, 10), 0, 0));
		 */
		// Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Image cImage
		// =toolkit.getImage(CommonLoadingPage.class.getResource("sage_logo.jpg"));

		this.add(aImage, BorderLayout.WEST);
		this.add(contentPanel, BorderLayout.EAST);
		// this.add(cImage, BorderLayout.WEST);

	} // jbInit

	/**
	 * Set Text (20 pt)
	 * 
	 * @param text
	 *            translated text to display
	 */
	public void setText(String text) {
		message.setText(text);
		display();
	} // setText

	/**
	 * Show Window
	 * 
	 * @param visible
	 *            true if visible
	 */
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible)
			toFront();
	} // setVisible

	/**
	 * Calculate size and display
	 */
	private void display() {
		pack();
		Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle bounds = getBounds();
		setBounds((ss.width - bounds.width) / 2, (ss.height - bounds.height) / 2, bounds.width, bounds.height);
		setVisible(true);
	} // display

	/**
	 * Dispose Splash
	 */
	public void dispose() {
		super.dispose();
		s_splash = null;
	} // dispose

	/**************************************************************************
	 * Adempiere Image
	 */
	private class CImage extends Component {

		/**
		 * 
		 */
		private static final long serialVersionUID = -4980770762959724071L;

		/**
		 * Adempiere Image
		 */
		public CImage() {
			Toolkit toolkit = Toolkit.getDefaultToolkit();

			m_image = toolkit.getImage(Splash.class.getResource("sage_logo.jpg"));

			tracker.addImage(m_image, 0);
		}

		/** The Image */
		private Image m_image = null;
		/* The Dimension */
		private Dimension m_dim = null;

		/**
		 * Calculate Size
		 * 
		 * @return size
		 */
		public Dimension getPreferredSize() {
			try {
				tracker.waitForID(0);
			} catch (Exception e) {
				System.err.println("Splash.CImage");
				e.printStackTrace();
			}
			m_dim = new Dimension(m_image.getWidth(this), m_image.getHeight(this));
			return m_dim;
		} // getPreferredSize

		/**
		 * Paint
		 * 
		 * @param g
		 *            Graphics
		 */
		public void paint(Graphics g) {
			if (tracker.checkID(0))
				g.drawImage(m_image, 0, 0, this);
		} // paint

	} // CImage

	/**
	 * Animation Image
	 * 
	 * @author jjanke
	 * @version $Id: Splash.java,v 1.3 2006/07/30 00:54:36 jjanke Exp $
	 */
	private class AImage extends Component {
		/**
		 * 
		 */
		private static final long serialVersionUID = -3003967119777877704L;

		/**
		 * Animation Image
		 */
		public AImage() {
			super();
			URL url = Splash.class.getResource("infinity.gif");
			if (url == null)
				url = Splash.class.getResource("infinity.gif");
			if (url != null) {
				m_image = Toolkit.getDefaultToolkit().getImage(url);
				tracker.addImage(m_image, 1);
			}
		} // AImage

		/** The image */
		private Image m_image = null;
		/** The dimension */
		private Dimension m_dim = null;

		/**
		 * Calculate Size
		 * 
		 * @return size
		 */
		public Dimension getPreferredSize() {
			try {
				tracker.waitForID(1);
			} catch (Exception e) {
				System.err.println("Splash.AImage");
				e.printStackTrace();
			}
			m_dim = new Dimension(m_image.getWidth(this) + 15, m_image.getHeight(this) + 15);
			return m_dim;
		} // getPreferredSize

		/**
		 * Paint
		 * 
		 * @param g
		 *            Graphics
		 */
		public void paint(Graphics g) {
			if (tracker.checkID(1))
				g.drawImage(m_image, 10, 10, this);
		} // paint

		/**
		 * Update
		 * 
		 * @param g
		 *            Graphics
		 */
		public void update(Graphics g) {
			paint(g);
		} // update

	} // AImage

} // Splash
