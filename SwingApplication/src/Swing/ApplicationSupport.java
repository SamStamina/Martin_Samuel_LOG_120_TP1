package Swing;
/*
 * Ce code provient d'un exemple sur Internet, de David Geary, � la page suivante:
 * http://www.javaworld.com/javaworld/jw-05-2003/jw-0530-designpatterns-p2.html
 * 
 *  A Swing application fa�ade
 *  Swing is one of my all-time favorite GUI frameworks, but even after writing 
 * a 1,600-page book on the subject, I have to refer to the book and Javadocs 
 * almost every time I write a Swing application. Just getting a Swing application 
 * off the ground can require heavy lifting, especially if you follow best 
 * practices by internationalizing your application. This section shows you how to 
 * implement a fa�ade that encapsulates much of the grunt work involved in setting 
 * up a Swing application.
 */
import java.awt.*;
import java.text.MessageFormat;
import java.util.*;
import javax.swing.*;

public final class ApplicationSupport {

	// ////////////////////////////////////////////////
	// Constructeur(s)
	// ////////////////////////////////////////////////
	
   // disallow direct instantiation
   private ApplicationSupport() {}
   
   public static void launch(final JFrame jFrame, String title,
                       final int xBounds, final int yBounds, 
                       final int width, int height) {
      jFrame.setTitle(title);
      jFrame.setBounds(xBounds,yBounds,width,height);
      jFrame.setVisible(true);
      jFrame.setResizable(true);

      STATUS.setHorizontalAlignment(JLabel.LEFT);

      STATUS_AREA.setBorder(BorderFactory.createEtchedBorder());
      STATUS_AREA.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
      STATUS_AREA.add(STATUS);

      jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
   }
   public static Locale getLocale() {
	   return locale;
   }
   
   public static int getPortNumber() throws java.util.MissingResourceException {
	   return Integer.parseInt(preferences.getString(PREFERED_PORT_NUMBER));
   }
   
   public static String getHostName() throws java.util.MissingResourceException {
	  return preferences.getString(PREFERED_HOST_NAME);
   }
   
   public static JMenu addMenu(final JFrame jFrame, String titleKey, String[] itemKeys) {
      JMenuBar menuBar = jFrame.getJMenuBar();
      if(menuBar == null) {
         menuBar = new JMenuBar();
         jFrame.setJMenuBar(menuBar);
      }

      JMenu menu = new JMenu(ApplicationSupport.getResource(titleKey));

      for(int i=0; i < itemKeys.length; ++i) {
         menu.add(new JMenuItem(ApplicationSupport.getResource(itemKeys[i])));
      }
      menuBar.add(menu);
      return menu;
   }
   
   public static JMenu addRadioButtonMenu(final JFrame jFrame, String titleKey, String[] itemKeys) {
	      JMenuBar menuBar = jFrame.getJMenuBar();
	      if(menuBar == null) {
	         menuBar = new JMenuBar();
	         jFrame.setJMenuBar(menuBar);
	      }

	      JMenu menu = new JMenu(ApplicationSupport.getResource(titleKey));
	      ButtonGroup group = new ButtonGroup();
	      
	      for(int i=0; i < itemKeys.length; ++i) {
	    	 JRadioButtonMenuItem radioButton = new JRadioButtonMenuItem(ApplicationSupport.getResource(itemKeys[i]));
	    	 group.add(radioButton);
	         menu.add(radioButton);
	      }
	      menuBar.add(menu);
	      return menu;
	   }
   
   public static JPanel getStatusArea() {
      return STATUS_AREA;
   }
   
   public static void showStatus(String statusString) {
      STATUS.setText(statusString);
   }
   
   public static String getResource(String key) {
	  try {
		  return (resources == null) ? null : resources.getString(key);
	  } catch (MissingResourceException e) {
		  return "**" + key + "**";
	  }
   }
   
   public static String formatMessage(String patternKey, String[] params) {
      String pattern = ApplicationSupport.getResource(patternKey);
      MessageFormat fmt = new MessageFormat(pattern);
      return fmt.format(params);
   }
   
   	// ////////////////////////////////////////////////
	// Attribut(s)
	// ////////////////////////////////////////////////
   
   static private final String PREFS_BUNDLE_BASENAME = "prefs";
   static private final String BUNDLE_BASENAME = "app";
   static private final String PREFERRED_LOCALE_KEY = "locale";
   static private final String PREFERED_HOST_NAME = "host_name";
   static private final String PREFERED_PORT_NUMBER = "port_number";

   static private final JPanel STATUS_AREA = new JPanel();
   static private final JLabel STATUS = new JLabel();
   static private ResourceBundle preferences, resources;
   static private Locale locale;
   
   static {
	      try {
	         preferences = ResourceBundle.getBundle(PREFS_BUNDLE_BASENAME);
	         locale = new Locale(preferences.getString(PREFERRED_LOCALE_KEY));
	      }
	      catch(java.util.MissingResourceException ex) {
	         System.err.println("ERROR: cannot find preferences properties file " + 
	                            BUNDLE_BASENAME);
	      }

	      try {
	         resources = ResourceBundle.getBundle(BUNDLE_BASENAME, locale);
	      }
	      catch(java.util.MissingResourceException ex) {
	         System.err.println("ERROR: cannot find properties file for " + BUNDLE_BASENAME);
	      }

	   };
}
