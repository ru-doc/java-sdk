/*
 * @(#)MetalScrollPaneUI.java	1.23 10/03/23
 *
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.swing.plaf.metal;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;

import java.awt.*;
import java.beans.*;
import java.awt.event.*;


/**
 * A Metal L&F implementation of ScrollPaneUI.
 * <p>
 * <strong>Warning:</strong>
 * Serialized objects of this class will not be compatible with
 * future Swing releases. The current serialization support is
 * appropriate for short term storage or RMI between applications running
 * the same version of Swing.  As of 1.4, support for long term storage
 * of all JavaBeans<sup><font size="-2">TM</font></sup>
 * has been added to the <code>java.beans</code> package.
 * Please see {@link java.beans.XMLEncoder}.
 *
 * @version 1.23 03/23/10
 * @author Steve Wilson
 */
    public class MetalScrollPaneUI extends BasicScrollPaneUI
{

    private PropertyChangeListener scrollBarSwapListener;

    public static ComponentUI createUI(JComponent x) {
	return new MetalScrollPaneUI();
    }

    public void installUI(JComponent c) {

        super.installUI(c);

	JScrollPane sp = (JScrollPane)c;
        updateScrollbarsFreeStanding();
    }

    public void uninstallUI(JComponent c) {
        super.uninstallUI(c);

	JScrollPane sp = (JScrollPane)c;
	JScrollBar hsb = sp.getHorizontalScrollBar();
	JScrollBar vsb = sp.getVerticalScrollBar();
        if (hsb != null) {
            hsb.putClientProperty( MetalScrollBarUI.FREE_STANDING_PROP, null);
        }
        if (vsb != null) {
            vsb.putClientProperty( MetalScrollBarUI.FREE_STANDING_PROP, null);	
        }
        c.removePropertyChangeListener(scrollBarSwapListener);
    }


    public void installListeners(JScrollPane scrollPane) {
        super.installListeners(scrollPane);
	scrollBarSwapListener = createScrollBarSwapListener();
	scrollPane.addPropertyChangeListener(scrollBarSwapListener);
    }

    // This method doesn't override 
    // BasicScrollPane.uninstallListeners(JComponent c)
    // that's why it never gets called. 
    // We should consider changing its signature in JDK 7
    public void uninstallListeners(JScrollPane scrollPane) {
    }

    /**
     * If the border of the scrollpane is an instance of
     * <code>MetalBorders.ScrollPaneBorder</code>, the client property
     * <code>FREE_STANDING_PROP</code> of the scrollbars 
     * is set to false, otherwise it is set to true.
     */
    private void updateScrollbarsFreeStanding() {
        if (scrollpane == null) {
            return;
        }
        Border border = scrollpane.getBorder();
        Object value;

        if (border instanceof MetalBorders.ScrollPaneBorder) {
            value = Boolean.FALSE;
        }
        else {
            value = Boolean.TRUE;
        }
        JScrollBar sb = scrollpane.getHorizontalScrollBar();
        if (sb != null) {
            sb.putClientProperty
                   (MetalScrollBarUI.FREE_STANDING_PROP, value);
        }
        sb = scrollpane.getVerticalScrollBar();
        if (sb != null) {
            sb.putClientProperty
                   (MetalScrollBarUI.FREE_STANDING_PROP, value);
        }
    }

    protected PropertyChangeListener createScrollBarSwapListener() {
        return new PropertyChangeListener() {
	    public void propertyChange(PropertyChangeEvent e) {
		  String propertyName = e.getPropertyName();
		  if (propertyName.equals("verticalScrollBar") ||
		      propertyName.equals("horizontalScrollBar")) {
                      JScrollBar oldSB = (JScrollBar)e.getOldValue();
                      if (oldSB != null) {
                          oldSB.putClientProperty(
                              MetalScrollBarUI.FREE_STANDING_PROP, null);
                      }
                      JScrollBar newSB = (JScrollBar)e.getNewValue();
                      if (newSB != null) {
                          newSB.putClientProperty(
                              MetalScrollBarUI.FREE_STANDING_PROP,
                              Boolean.FALSE);
                      }
		  }	  
                  else if ("border".equals(propertyName)) {
                      updateScrollbarsFreeStanding();
                  }
	}};
    }

}
