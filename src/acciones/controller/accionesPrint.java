package acciones.controller;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class accionesPrint extends javax.swing.JFrame implements Printable{

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if (pageIndex == 0) {
			Graphics2D impresion = (Graphics2D) graphics;
			impresion.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
			printAll(impresion);
			return PAGE_EXISTS;
		} else return NO_SUCH_PAGE;
	}

}
