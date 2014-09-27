import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class Caluculator extends Composite implements SelectionListener{

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Label lblNewLabel;

	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Caluculator(Composite parent, int style) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		setLayout(new FormLayout());
		
		lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setAlignment(SWT.RIGHT);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(0, 10);
		fd_lblNewLabel.left = new FormAttachment(0);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		toolkit.adapt(lblNewLabel, true, true);
		lblNewLabel.setText("0");
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(4, false));
		FormData fd_composite = new FormData();
		fd_composite.left = new FormAttachment(lblNewLabel, 4);
		fd_composite.bottom = new FormAttachment(100, -10);
		fd_composite.top = new FormAttachment(100, -404);
		fd_composite.right = new FormAttachment(0, 426);
		composite.setLayoutData(fd_composite);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		toolkit.adapt(btnNewButton, true, true);
		btnNewButton.setText("New Button");
		new Label(composite, SWT.NONE);
		
		btnNewButton.addSelectionListener(this);
		
		Button btnNewButton_3 = new Button(composite, SWT.NONE);
	//	btnNewButton_3.addSelectionListener(new SelectionAdapter() {
	//		@Override
	//		public void widgetSelected(SelectionEvent e) {
	//			Button b = (Button)e.getSource();
	//			int num = Integer.parseInt(lblNewLabel.getText());
		//		num += Integer.parseInt(b.getText());
	//			lblNewLabel.setText(num+"");
	//		}
	//	}
	//	);
		
		toolkit.adapt(btnNewButton_3, true, true);
		btnNewButton_3.setText("1");
		
		Button btnNewButton_4 = new Button(composite, SWT.NONE);
		toolkit.adapt(btnNewButton_4, true, true);
		btnNewButton_4.setText("2");
		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		toolkit.adapt(btnNewButton_1, true, true);
		btnNewButton_1.setText("3");
		
		Button btnNewButton_2 = new Button(composite, SWT.NONE);
		toolkit.adapt(btnNewButton_2, true, true);
		btnNewButton_2.setText("4");
		
		btnNewButton_1.addSelectionListener(this);
		btnNewButton_2.addSelectionListener(this);
		btnNewButton_3.addSelectionListener(this);
		btnNewButton_4.addSelectionListener(this);
		

	}
	
	public static void main(String[] args){
	    Display display = new Display();
	    Shell shell = new Shell(display);
	    Caluculator calc = new Caluculator(shell, SWT.NONE);
	    calc.pack();
	    shell.pack();
	    shell.open();
	    while(!shell.isDisposed()){
	        if(!display.readAndDispatch()) display.sleep();
	    }
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		Button b = (Button)e.getSource();
		int num = Integer.parseInt(lblNewLabel.getText());
		num += Integer.parseInt(b.getText());
		lblNewLabel.setText(num+"");
		
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
