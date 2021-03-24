package acciones.controller;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class imprimir implements Printable{

	final Component comp;
	
	public imprimir(Component comp) {
		this.comp = comp;
	}
	
	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if (pageIndex == 0) {
			Graphics2D impresion = (Graphics2D) graphics;
			impresion.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
			comp.paint(impresion);
			impresion.drawString("hola", 100, 100);
			return PAGE_EXISTS;
		} else return NO_SUCH_PAGE;	}

}
