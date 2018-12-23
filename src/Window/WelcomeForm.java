package Window;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import swing2swt.layout.BorderLayout;
import usrManager.LoginWindow;

public class WelcomeForm {
	Shell shell;
	ProgressBar progressBar;
	private Composite composite;
	private Label label_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			new WelcomeForm().open();
			new LoginWindow().open();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WelcomeForm() throws InterruptedException {
		shell = new Shell(SWT.NONE);
		shell.setAlpha(238);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_LINK_FOREGROUND));
		shell.setSize(546, 337);
		setScreenMid();
		createContents();
	}

	public void open() {
		shell.open();

		for (int i = 0; i < 3; ++i) {
			progressBar.setSelection(i * 30);
			try {
				Thread.sleep(600);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		progressBar.setSelection(100);
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		shell.close();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		shell.setLayout(new BorderLayout(0, 0));

		progressBar = new ProgressBar(shell, SWT.NONE);
		progressBar.setLayoutData(BorderLayout.SOUTH);

		composite = new Composite(shell, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		composite.setLayoutData(BorderLayout.CENTER);
		composite.setLayout(null);
		Label lblMyNotepad = new Label(composite, SWT.NONE);
		lblMyNotepad.setBounds(0, 130, 544, 76);
		lblMyNotepad.setText("My Notepad");
		lblMyNotepad.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblMyNotepad.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 35, SWT.NORMAL));
		lblMyNotepad.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblMyNotepad.setAlignment(SWT.CENTER);

		label_1 = new Label(composite, SWT.NONE);
		label_1.setBounds(416, 33, 0, 20);

	}

	public void setScreenMid() {
		shell.setLocation(Display.getCurrent().getClientArea().width / 2 - shell.getShell().getSize().x / 2,
				Display.getCurrent().getClientArea().height / 2 - shell.getSize().y / 2);
	}
}
