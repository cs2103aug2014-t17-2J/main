

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;


public class UserInter extends Composite implements SelectionListener {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Label lblWelcome;
	private Text txtTypeCommandHere;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public UserInter(Composite parent, int style) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		setLayout(new FormLayout());
		
		lblWelcome = new Label(this, SWT.NONE);
		lblWelcome.setAlignment(SWT.CENTER);
		FormData fd_lblWelcome = new FormData();
		fd_lblWelcome.top = new FormAttachment(0);
		fd_lblWelcome.left = new FormAttachment(0);
		lblWelcome.setLayoutData(fd_lblWelcome);
		toolkit.adapt(lblWelcome, true, true);
		lblWelcome.setText("Welcome to WeDo!");
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(4, false));
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(lblWelcome, 275, SWT.BOTTOM);
		fd_composite.right = new FormAttachment(100, -61);
		fd_composite.top = new FormAttachment(lblWelcome, 15);
		fd_composite.left = new FormAttachment(lblWelcome, 0, SWT.LEFT);
		composite.setLayoutData(fd_composite);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		Button btnHotKey1 = new Button(composite, SWT.NONE);
		btnHotKey1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		toolkit.adapt(btnHotKey1, true, true);
		btnHotKey1.setText("F1");
		
		Button btnHotKey2 = new Button(composite, SWT.NONE);
		btnHotKey2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		toolkit.adapt(btnHotKey2, true, true);
		btnHotKey2.setText("F2");
		
		Button btnHotKey3 = new Button(composite, SWT.NONE);
		btnHotKey3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		toolkit.adapt(btnHotKey3, true, true);
		btnHotKey3.setText("F3");
		
		Button btnHotKey4 = new Button(composite, SWT.NONE);
		btnHotKey4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		toolkit.adapt(btnHotKey4, true, true);
		btnHotKey4.setText("F4");

		
		txtTypeCommandHere = new Text(composite, SWT.BORDER);
		txtTypeCommandHere.setText("type command here");
		txtTypeCommandHere.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1));
		toolkit.adapt(txtTypeCommandHere, true, true);
		
		Label lblDisplay = new Label(composite, SWT.NONE);
		toolkit.adapt(lblDisplay, true, true);
		lblDisplay.setText("New Label");
		
		Button btnEnter = new Button(composite, SWT.NONE);
		btnEnter.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		toolkit.adapt(btnEnter, true, true);
		btnEnter.setText("Enter");
	}
	
		
	
	public static void main(String[] args){
	    Display display = new Display();
	    Shell shell = new Shell(display);
	    UserInter userInter = new UserInter(shell, SWT.NONE);
	    userInter.pack();
	    shell.pack();
	    shell.open();
	    while(!shell.isDisposed()){
	        if(!display.readAndDispatch()) display.sleep();
	    }
	}
	

	@Override
	public void widgetSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		
	}


}
