package messageBox;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

public class LoginInfo extends Dialog {

	protected Object result;
	protected Shell shell;
	protected String windows_name;
	protected String label_show;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public LoginInfo(Shell parent, int style, String windows_name, String label_show) {
		super(parent, style);
		setText(windows_name);
		this.windows_name = windows_name;
		this.label_show = label_show;
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	void setMidden(int thisWidth, int thisHeight) {
		Shell parent = getParent();
		shell.setSize(thisWidth, thisHeight);
		shell.setLocation(parent.getLocation().x + (parent.getSize().x - thisWidth) / 2,
				parent.getLocation().y + (parent.getSize().y - thisHeight) / 2);
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.TITLE);

		getParent().setEnabled(false);

		shell.addShellListener(new ShellAdapter() {
			@Override
			public void shellClosed(ShellEvent e) {
				getParent().setEnabled(true);
			}
		});
		shell.setImage(SWTResourceManager.getImage(LoginInfo.class, "/javax/swing/plaf/metal/icons/ocean/warning.png"));

		setMidden(397, 197);

		shell.setText(this.windows_name);
		shell.setLayout(null);

		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		label.setBounds(79, 45, 226, 30);
		label.setAlignment(SWT.CENTER);
		label.setText(this.label_show);

		Button button = new Button(shell, SWT.NONE);
		button.setBounds(150, 107, 86, 30);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		button.setText("确定");

	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		return bindingContext;
	}
}
